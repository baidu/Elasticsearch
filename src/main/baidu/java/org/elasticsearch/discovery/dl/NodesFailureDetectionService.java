/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elasticsearch.discovery.dl;

import static org.elasticsearch.common.unit.TimeValue.timeValueSeconds;
import static org.elasticsearch.common.util.concurrent.ConcurrentCollections.newConcurrentMap;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.cluster.routing.RoutingService;
import org.elasticsearch.cluster.routing.allocation.RoutingAllocation;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.BaseTransportResponseHandler;
import org.elasticsearch.transport.ConnectTransportException;
import org.elasticsearch.transport.TransportChannel;
import org.elasticsearch.transport.TransportException;
import org.elasticsearch.transport.TransportRequest;
import org.elasticsearch.transport.TransportRequestHandler;
import org.elasticsearch.transport.TransportRequestOptions;
import org.elasticsearch.transport.TransportResponse;
import org.elasticsearch.transport.TransportService;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import org.apache.distributedlog.DLSN;

public class NodesFailureDetectionService extends AbstractLifecycleComponent<NodesFailureDetectionService> implements Runnable {

    public static final String PING_ACTION_NAME = "internal:discovery/dl/fd/ping";
    public static final String SETTING_PING_INTERVAL = "discovery.dl.fd.ping_interval";
    public static final String SETTING_PING_TIMEOUT = "discovery.dl.fd.ping_timeout";
    public static final String SETTING_PING_RETRIES = "discovery.dl.fd.ping_retries";
    
    protected final TimeValue pingInterval;
    protected final TimeValue pingTimeout;
    protected final int pingRetryCount;
    private ThreadPool threadPool;
    private TransportService transportService;
    private ClusterName clusterName;
    private ClusterService clusterService;
    private RoutingService routingService;
    private JoinClusterAction joinClusterAction;
    private ClusterStateOpLog clusterStateOpLog;

    private final ConcurrentMap<DiscoveryNode, NodeFD> nodesFD = newConcurrentMap();
    private volatile long clusterStateVersion = ClusterState.UNKNOWN_VERSION;
    private volatile DiscoveryNode localNode;

    public NodesFailureDetectionService(Settings settings, ThreadPool threadPool, TransportService transportService, ClusterName clusterName, ClusterService clusterService, 
            RoutingService routingService, JoinClusterAction joinClusterAction, ClusterStateOpLog clusterStateOpLog) {
        super(settings);
        this.pingInterval = settings.getAsTime(SETTING_PING_INTERVAL, timeValueSeconds(1));
        this.pingTimeout = settings.getAsTime(SETTING_PING_TIMEOUT, timeValueSeconds(5));
        this.pingRetryCount = settings.getAsInt(SETTING_PING_RETRIES, 3);
        this.threadPool = threadPool;
        this.transportService = transportService;
        this.clusterName = clusterName;
        this.clusterService = clusterService;
        this.routingService = routingService;
        this.joinClusterAction = joinClusterAction;
        this.clusterStateOpLog = clusterStateOpLog;
        this.localNode = clusterService.localNode();
        logger.debug("[node  ] uses ping_interval [{}], ping_timeout [{}], ping_retries [{}]", pingInterval, pingTimeout, pingRetryCount);
        transportService.registerRequestHandler(PING_ACTION_NAME, PingRequest.class, ThreadPool.Names.SAME, new PingRequestHandler());
    }
    
    public void setRoutingService(RoutingService routingService) {
        this.routingService = routingService;
    }

    @Override
    public void run() {
        try {
            updateNodesAndPing(clusterService.state());
        } finally {
            threadPool.schedule(TimeValue.timeValueMillis(1000), ThreadPool.Names.GENERIC, this);
        }
    }
    /**
     * make sure that nodes in clusterState are pinged. Any pinging to nodes which are not
     * part of the cluster will be stopped
     */
    private void updateNodesAndPing(ClusterState clusterState) {
        if (clusterStateOpLog.localNodeMaster()) {
            // remove any nodes we don't need, this will cause their FD to stop
            for (DiscoveryNode monitoredNode : nodesFD.keySet()) {
                if (!clusterState.nodes().nodes().containsKey(monitoredNode.getId()) 
                        && !clusterState.nodes().deadNodes().containsKey(monitoredNode.getIpPortAddress())) {
                    nodesFD.remove(monitoredNode);
                    // here could close the connection, because the node not belong to the cluster
                    transportService.disconnectFromNode(monitoredNode);
                    logger.info("node [{}] not in active and dead not list, remove it from failure detection list", monitoredNode);
                }
            }
            
            // monitor active nodes
            for (DiscoveryNode node : clusterState.nodes()) {
                if (node.equals(localNode)) {
                    // no need to monitor the local node
                    continue;
                }
                if (!nodesFD.containsKey(node)) {
                    NodeFD fd = new NodeFD(node, false);
                    // it's OK to overwrite an existing nodeFD - it will just stop and the new one will pick things up.
                    nodesFD.put(node, fd);
                    // we use schedule with a 0 time value to run the pinger on the pool as it will run on later
                    threadPool.schedule(TimeValue.timeValueMillis(0), ThreadPool.Names.SAME, fd);
                } else {
                    nodesFD.get(node).isDeadNode = false;
                }
            }
            
            // monitor dead nodes
            for (ObjectObjectCursor<String, DiscoveryNode> nodeIdValuePair: clusterState.nodes().deadNodes()) {
                DiscoveryNode node = nodeIdValuePair.value;
                if (node.equals(localNode)) {
                    // no need to monitor the local node
                    continue;
                }
                if (!nodesFD.containsKey(node)) {
                    NodeFD fd = new NodeFD(node, true);
                    // it's OK to overwrite an existing nodeFD - it will just stop and the new one will pick things up.
                    nodesFD.put(node, fd);
                    // we use schedule with a 0 time value to run the pinger on the pool as it will run on later
                    threadPool.schedule(TimeValue.timeValueMillis(0), ThreadPool.Names.SAME, fd);
                } else {
                    nodesFD.get(node).isDeadNode = true;
                }
            }
            DLSN minimalDlsn = clusterStateOpLog.getDumpedDlsn();
            int liveNodeNum = 0;
            for (NodeFD fd : nodesFD.values()) {
                if (!fd.isDeadNode) {
                    ++ liveNodeNum;
                    DLSN fdDlsn = fd.latestDlsn;
                    if (fdDlsn.compareTo(minimalDlsn) < 0) {
                        minimalDlsn = fdDlsn;
                    }
                }
            }
            int totalNodeNum = clusterState.nodes().nodes().size() + clusterState.nodes().deadNodes().size();
            if (liveNodeNum > Math.ceil(totalNodeNum / 2)) {
                clusterStateOpLog.truncateLogBeforeDLSN(minimalDlsn);
            }
        } else {
            nodesFD.clear();
            // should not close connection with node here, because other service maybe using the connection
        }
    }
    
    private void handleNodeFailure(final NodeFD nodeFd, String reason) {
        if (clusterService.state().nodes().get(nodeFd.node.id()) == null) {
            logger.trace("node [{}] already removed from active node list. ignoring.", nodeFd.node);
            return;
        }
        clusterService.submitStateUpdateTask("dl-disco-node_failed(" + nodeFd.node + "), reason " + reason, new ClusterStateUpdateTask(Priority.IMMEDIATE) {
            @Override
            public ClusterState execute(ClusterState currentState) {
                if (currentState.nodes().get(nodeFd.node.id()) == null) {
                    logger.debug("node [{}] already removed from active node list. ignoring.", nodeFd.node);
                    return currentState;
                }
                if (clusterStateOpLog.localNodeMaster()) {
                    DiscoveryNodes.Builder builder = DiscoveryNodes.builder(currentState.nodes())
                            .remove(nodeFd.node.id())
                            .putDeadNodeByIpPort(nodeFd.node);
                    currentState = ClusterState.builder(currentState).nodes(builder).build();
                    // eagerly run reroute to remove dead nodes from routing table
                    RoutingAllocation.Result routingResult = routingService.getAllocationService().reroute(
                            ClusterState.builder(currentState).build(),
                            "[" + nodeFd.node + "] failed");
                    return ClusterState.builder(currentState).routingResult(routingResult).build();
                } else {
                    return currentState;
                }
            }

            @Override
            public void onNoLongerMaster(String source) {
            }

            @Override
            public void onFailure(String source, Throwable t) {
                logger.error("unexpected failure during [{}]", t, source);
            }

            @Override
            public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
            }
        });
    }

    private class NodeFD implements Runnable {
        volatile int retryCount;
        private final DiscoveryNode node;
        private boolean isDeadNode;
        private DLSN latestDlsn = DLSN.InitialDLSN;
        private NodeFD(DiscoveryNode node, boolean isDeadNode) {
            this.node = node;
            this.isDeadNode = isDeadNode;
        }

        private boolean running() {
            return NodeFD.this.equals(nodesFD.get(node));
        }

        @Override
        public void run() {
            if (!running()) {
                return;
            }
            final PingRequest pingRequest = new PingRequest(node, clusterName, localNode, clusterStateVersion, isDeadNode);
            final TransportRequestOptions options = TransportRequestOptions.builder().withType(TransportRequestOptions.Type.PING).withTimeout(pingTimeout).build();
            transportService.sendRequest(node, PING_ACTION_NAME, pingRequest, options, new BaseTransportResponseHandler<PingResponse>() {
                @Override
                public PingResponse newInstance() {
                    return new PingResponse();
                }

                @Override
                public void handleResponse(PingResponse response) {
                    if (!running()) {
                        return;
                    }
                    latestDlsn = response.dlsnProcessed;
                    retryCount = 0;
                    threadPool.schedule(pingInterval, ThreadPool.Names.SAME, NodeFD.this);
                }

                @Override
                public void handleException(TransportException exp) {
                    retryCount++;
                    try {
                        if (!running()) {
                            return;
                        }

                        if (!isDeadNode && retryCount >= pingRetryCount) {
                            logger.debug("[node  ] failed to ping [{}], tried [{}] times, each with  maximum [{}] timeout", exp, node, pingRetryCount, pingTimeout);
                            handleNodeFailure(NodeFD.this, "failed to ping, tried [" + pingRetryCount + "] times, each with maximum [" + pingTimeout + "] timeout");
                            retryCount = 0;
                        }
                        
                        if (exp instanceof NodeIdNotMatchException || exp.getCause() instanceof NodeIdNotMatchException) {
                            handleNodeFailure(NodeFD.this, "node id not match, move the node and wait for the node to rejoin");
                        }
                        
                        if (exp instanceof ConnectTransportException || exp.getCause() instanceof ConnectTransportException) {
                            transportService.connectToNode(node);
                        } else {
                            logger.debug("[node  ] failed to ping [{}], num retry [{}], nodefd detail [{}]", exp, node, retryCount, NodeFD.this);
                        }
                    } catch (Throwable t) {
                        logger.error("errors while handle exceptions when ping node [{}]", NodeFD.this);
                    }
                    threadPool.schedule(TimeValue.timeValueMillis(Math.min(1000 * retryCount, 5000)), ThreadPool.Names.SAME, NodeFD.this);
                }

                @Override
                public String executor() {
                    return ThreadPool.Names.SAME;
                }
            }
            );
        }

        @Override
        public String toString() {
            return "NodeFD [retryCount=" + retryCount + ", node=" + node
                    + ", isDeadNode=" + isDeadNode + "]";
        }
    }

    class PingRequestHandler extends TransportRequestHandler<PingRequest> {
        @Override
        public void messageReceived(PingRequest request, TransportChannel channel) throws Exception {
            
            // PingRequest will have clusterName set to null if it came from a node of version <1.4.0
            if (request.clusterName != null && !request.clusterName.equals(clusterName)) {
                // Don't introduce new exception for bwc reasons
                throw new IllegalStateException("Got pinged with cluster name [" + request.clusterName + "], but I'm part of cluster [" + clusterName + "]");
            }
            
            // if we are not the node we are supposed to be pinged, send an exception
            // this can happen when a kill -9 is sent, and another node is started using the same port
            if (!localNode.equals(request.pingNode)) {
                logger.warn("Got pinged as node [{}], but I am node [{}], cluster name is equal, it means I am restarted, so rejoin the cluster now", request.pingNode, localNode);
                joinClusterAction.joinElectedMaster(request.masterNode);
                throw new NodeIdNotMatchException(localNode, request.pingNode);
            }
            
            if (request.isDeadNode) {
                logger.warn("master ping me as a dead node, so that I should rejoin the cluster");
                joinClusterAction.joinElectedMaster(request.masterNode);
            }
            channel.sendResponse(new PingResponse(clusterStateOpLog.getDumpedDlsn()));
        }
    }
    
    class NodeIdNotMatchException extends TransportException {
        
        private static final long serialVersionUID = 1625962366780859888L;

        public NodeIdNotMatchException(DiscoveryNode localNode, DiscoveryNode pingNode) {
            super("Got pinged with node Info [" + pingNode + "], but my node is [" + localNode + "], not equal so that rejoin the cluster");
        }
    }


    public static class PingRequest extends TransportRequest {

        // the nodeid assumed ping
        private DiscoveryNode pingNode;
        private boolean isDeadNode;
        private ClusterName clusterName;
        // master node told to data node to join
        private DiscoveryNode masterNode;
        private long clusterStateVersion = ClusterState.UNKNOWN_VERSION;
        
        public PingRequest() {
            isDeadNode = true;
        }

        PingRequest(DiscoveryNode pingNode, ClusterName clusterName, DiscoveryNode masterNode, long clusterStateVersion, boolean isDeadNode) {
            this.pingNode = pingNode;
            this.clusterName = clusterName;
            this.masterNode = masterNode;
            this.clusterStateVersion = clusterStateVersion;
            this.isDeadNode = isDeadNode;
        }

        @Override
        public void readFrom(StreamInput in) throws IOException {
            super.readFrom(in);
            pingNode = DiscoveryNode.readNode(in);
            isDeadNode = in.readBoolean();
            clusterName = ClusterName.readClusterName(in);
            masterNode = DiscoveryNode.readNode(in);
            clusterStateVersion = in.readLong();
        }

        @Override
        public void writeTo(StreamOutput out) throws IOException {
            super.writeTo(out);
            pingNode.writeTo(out);
            out.writeBoolean(isDeadNode);
            clusterName.writeTo(out);
            masterNode.writeTo(out);
            out.writeLong(clusterStateVersion);
        }
    }

    private class PingResponse extends TransportResponse {

        private DLSN dlsnProcessed;
        private PingResponse() {
            dlsnProcessed = DLSN.InitialDLSN;
        }
        private PingResponse(DLSN dlsn) {
            this.dlsnProcessed = dlsn;
        }

        @Override
        public void readFrom(StreamInput in) throws IOException {
            super.readFrom(in);
            dlsnProcessed = DLSN.deserializeBytes(in.readByteArray());
        }

        @Override
        public void writeTo(StreamOutput out) throws IOException {
            super.writeTo(out);
            out.writeByteArray(dlsnProcessed.serializeBytes());
        }
    }

    @Override
    protected void doStart() {
        threadPool.schedule(TimeValue.timeValueMillis(0), ThreadPool.Names.GENERIC, this);
    }

    @Override
    protected void doStop() {
        
    }

    @Override
    protected void doClose() {
        
    }
}
