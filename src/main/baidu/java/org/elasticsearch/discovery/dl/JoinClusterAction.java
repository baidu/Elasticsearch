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

import java.io.IOException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.ExceptionsHelper;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.NotMasterException;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.cluster.routing.RoutingService;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.EmptyTransportResponseHandler;
import org.elasticsearch.transport.TransportChannel;
import org.elasticsearch.transport.TransportRequest;
import org.elasticsearch.transport.TransportRequestHandler;
import org.elasticsearch.transport.TransportResponse;
import org.elasticsearch.transport.TransportService;

public class JoinClusterAction extends AbstractComponent {
    
    private static final String JOIN_ACTION_NAME = "internal:discovery/dl/join";
    
    public final static String SETTING_JOIN_TIMEOUT = "discovery.dl.join_timeout";
    public final static String SETTING_PING_TIMEOUT = "discovery.dl.ping_timeout";
    public final static String SETTING_JOIN_RETRY_ATTEMPTS = "discovery.dl.join_retry_attempts";
    public final static String SETTING_JOIN_RETRY_DELAY = "discovery.dl.join_retry_delay";
    
    private TransportService transportService;
    private ClusterService clusterService;
    private ThreadPool threadPool;
    private RoutingService routingService;
    
    private ScheduledFuture<?> joinMasterTask;
    private final TimeValue joinTimeout;
    private final TimeValue pingTimeout;
    private final int joinRetryAttempts;
    private final TimeValue joinRetryDelay;
    
    public JoinClusterAction(Settings settings, TransportService transportService, ClusterService clusterService, 
            ThreadPool threadPool, RoutingService routingService) {
        super(settings);
        this.pingTimeout = this.settings.getAsTime(SETTING_PING_TIMEOUT, timeValueSeconds(3));
        this.joinTimeout = settings.getAsTime(SETTING_JOIN_TIMEOUT, TimeValue.timeValueMillis(this.pingTimeout.millis() * 20));
        this.joinRetryAttempts = settings.getAsInt(SETTING_JOIN_RETRY_ATTEMPTS, 3);
        this.joinRetryDelay = settings.getAsTime(SETTING_JOIN_RETRY_DELAY, TimeValue.timeValueMillis(100));
        
        this.transportService = transportService;
        this.clusterService = clusterService;
        this.threadPool = threadPool;
        this.routingService = routingService;
        
        transportService.registerRequestHandler(JOIN_ACTION_NAME, JoinRequest.class, ThreadPool.Names.GENERIC, new JoinRequestRequestHandler());
    }
    
    public void setRoutingService(RoutingService routingService) {
        this.routingService = routingService;
    }

    public void sendJoinRequestBlocking(DiscoveryNode masterNode, DiscoveryNode node, TimeValue timeout) {
        transportService.submitRequest(masterNode, JOIN_ACTION_NAME, new JoinRequest(node), EmptyTransportResponseHandler.INSTANCE_SAME)
                .txGet(timeout.millis(), TimeUnit.MILLISECONDS);
    }

    public static class JoinRequest extends TransportRequest {

        DiscoveryNode node;

        public JoinRequest() {
        }

        private JoinRequest(DiscoveryNode node) {
            this.node = node;
        }

        @Override
        public void readFrom(StreamInput in) throws IOException {
            super.readFrom(in);
            node = DiscoveryNode.readNode(in);
        }

        @Override
        public void writeTo(StreamOutput out) throws IOException {
            super.writeTo(out);
            node.writeTo(out);
        }
    }

    public static interface JoinCallback {
        void onSuccess();

        void onFailure(Throwable t);
    }
    
    private class JoinRequestRequestHandler extends TransportRequestHandler<JoinRequest> {

        @Override
        public void messageReceived(final JoinRequest request, final TransportChannel channel) throws Exception {
            handleJoinRequest(request.node, clusterService.state(), new JoinCallback() {
                @Override
                public void onSuccess() {
                    try {
                        channel.sendResponse(TransportResponse.Empty.INSTANCE);
                    } catch (Throwable t) {
                        onFailure(t);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    try {
                        channel.sendResponse(t);
                    } catch (Throwable e) {
                        logger.warn("failed to send back failure on join request", e);
                    }
                }
            });
        }
    }
    
    void handleJoinRequest(final DiscoveryNode node, final ClusterState state, final JoinCallback callback) {
        if (!transportService.addressSupported(node.address().getClass())) {
            logger.warn("received a wrong address type from [{}], ignoring...", node);
        } else {
            transportService.connectToNode(node);
            try {
                // if the node already in active node list, then ignore this request
                if (clusterService.state().nodes().nodeExists(node.getId())) {
                    callback.onSuccess();
                    return;
                }
                // If this node is not in dead node list, then ignore this request
                ImmutableOpenMap<String, DiscoveryNode> deadNodes = clusterService.state().nodes().deadNodes();
                if (deadNodes.get(node.getIpPortAddress()) == null) {
                    logger.warn("failed to find node [{}] in node list, ignore the join request", node);
                    callback.onFailure(new IllegalStateException("could not find this node " + node + " from node list"));
                    return;
                }
            } catch (Throwable e) {
                logger.warn("failed to validate incoming join request from node [{}]", node);
                callback.onFailure(new IllegalStateException("failure when sending a validation request to node", e));
                return;
            }
            // join task has to be immediate because if node is left then all shards is failed, this will generate a lot of tasks
            clusterService.submitStateUpdateTask("dl-disco-join(join from node[" + node + "])", new ProcessJoinsTask(Priority.IMMEDIATE, node, callback));
        }
    }
    
    public boolean joinElectedMaster(DiscoveryNode masterNode) {
        if (joinMasterTask == null || joinMasterTask.isCancelled() || joinMasterTask.isDone()) {
            joinMasterTask = threadPool.schedule(TimeValue.timeValueSeconds(0), ThreadPool.Names.GENERIC, new JoinElectedMasterTask(masterNode));
        } else {
            logger.debug("there is already a running join task, not start a new one");
        }
        return true;
    }
    /**
     * Join a newly elected master.
     *
     * @return true if successful
     */
    class JoinElectedMasterTask implements Runnable {
        public DiscoveryNode masterNode;
        
        public JoinElectedMasterTask(DiscoveryNode masterNode) {
            this.masterNode = masterNode;
        }
        
        public void run() {
            logger.info("begin to join the elected master [{}]", masterNode);
            try {
                // first, make sure we can connect to the master
                transportService.connectToNode(masterNode);
            } catch (Exception e) {
                logger.warn("failed to connect to master [{}], retrying...", e, masterNode);
                return;
            }
            int joinAttempt = 0; // we retry on illegal state if the master is not yet ready
            while (true) {
                try {
                    logger.debug("joining master {}", masterNode);
                    sendJoinRequestBlocking(masterNode, clusterService.localNode(), joinTimeout);
                    return;
                } catch (Throwable t) {
                    Throwable unwrap = ExceptionsHelper.unwrapCause(t);
                    if (unwrap instanceof NotMasterException) {
                        if (++joinAttempt == joinRetryAttempts) {
                            logger.info("failed to send join request to master [{}], reason [{}], tried [{}] times", masterNode, ExceptionsHelper.detailedMessage(t), joinAttempt);
                            return;
                        } else {
                            logger.trace("master {} failed with [{}]. retrying... (attempts done: [{}])", masterNode, ExceptionsHelper.detailedMessage(t), joinAttempt);
                        }
                    } else {
                        if (logger.isTraceEnabled()) {
                            logger.trace("failed to send join request to master [{}]", t, masterNode);
                        } else {
                            logger.info("failed to send join request to master [{}], reason [{}]", masterNode, ExceptionsHelper.detailedMessage(t));
                        }
                        return;
                    }
                }
    
                try {
                    Thread.sleep(joinRetryDelay.millis());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    
    class ProcessJoinsTask extends ClusterStateUpdateTask {

        private DiscoveryNode node;
        private final JoinCallback callback;
        
        public ProcessJoinsTask(Priority priority, DiscoveryNode node, JoinCallback callback) {
            super(priority);
            this.node = node;
            this.callback = callback;
        }

        @Override
        public ClusterState execute(ClusterState currentState) {
            DiscoveryNodes.Builder nodesBuilder;
            nodesBuilder = DiscoveryNodes.builder(currentState.nodes());
            if (currentState.nodes().nodeExists(node.id())) {
                logger.debug("received a join request for an existing node [{}]", node);
                return currentState;
            } 
            // If this node is not in dead node list, then ignore this request
            ImmutableOpenMap<String, DiscoveryNode> deadNodes = clusterService.state().nodes().deadNodes();
            if (deadNodes.get(node.getIpPortAddress()) == null) {
                logger.warn("failed to find node [{}] in node list, ignore the join request", node);
                throw new IllegalStateException("could not find this node " + node + " from active node list and dead node list");
            }
            nodesBuilder.put(node);
            nodesBuilder.removeDeadNodeByIpPort(node);
            final ClusterState.Builder newStateBuilder = ClusterState.builder(currentState);
            newStateBuilder.nodes(nodesBuilder);
            ClusterState newState = newStateBuilder.build();
            return newState;
        }

        @Override
        public void onFailure(String source, Throwable t) {
            logger.error("unexpected failure during [{}]", t, source);
            callback.onFailure(t);
        }

        @Override
        public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
            logger.info("add node [{}] to cluster successfully", node);
            if (oldState.version() != newState.version()) {
                routingService.reroute("[" + node + "] added");
            }
            callback.onSuccess();
        }
    }
}
