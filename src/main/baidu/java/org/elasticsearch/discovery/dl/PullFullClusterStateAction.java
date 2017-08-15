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

import java.io.IOException;

import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.compress.Compressor;
import org.elasticsearch.common.compress.CompressorFactory;
import org.elasticsearch.common.io.stream.BytesStreamOutput;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.BytesTransportResponse;
import org.elasticsearch.transport.TransportChannel;
import org.elasticsearch.transport.TransportException;
import org.elasticsearch.transport.TransportRequest;
import org.elasticsearch.transport.TransportRequestHandler;
import org.elasticsearch.transport.TransportRequestOptions;
import org.elasticsearch.transport.TransportResponseHandler;
import org.elasticsearch.transport.TransportService;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;

public class PullFullClusterStateAction extends AbstractComponent {

    public static final String PULL_FULL_STATE_ACTION_NAME = "internal:discovery/dl/pull_full_state";
    
    private TransportService transportService;
    private ClusterService clusterService;
    private ClusterStateOpLog clusterStateOpLog;
    private DiscoveryNode localNode;
    private boolean isPullingState;
    
    public PullFullClusterStateAction(Settings settings, ClusterService clusterService, TransportService transportService, ClusterStateOpLog clusterStateOpLog) {
        super(settings);
        this.clusterService = clusterService;
        this.transportService = transportService;
        this.clusterStateOpLog = clusterStateOpLog;
        this.localNode = clusterService.localNode();
        this.isPullingState = false;
        
        this.transportService.registerRequestHandler(PULL_FULL_STATE_ACTION_NAME, PullFullClusterStateRequest.class, ThreadPool.Names.GENERIC, new PullFullClusterStateFromNodeHandler());
    }
    
    public void pullFullClusterStateFromNode(final DiscoveryNode node, final TimeValue publishTimeout) {
        if (isPullingState) {
            return;
        }
        isPullingState = true;
        TransportRequestOptions options = TransportRequestOptions.builder().withType(TransportRequestOptions.Type.STATE).withCompress(false).withTimeout(publishTimeout).build();
        transportService.sendRequest(node, PULL_FULL_STATE_ACTION_NAME, 
                new PullFullClusterStateRequest(clusterService.state().getClusterName(), localNode), options,
                new PullFullClusterStateFromNodeResponseHandler());
    }
    
    private class PullFullClusterStateFromNodeHandler extends TransportRequestHandler<PullFullClusterStateRequest> {

        @Override
        public void messageReceived(PullFullClusterStateRequest request, final TransportChannel channel) throws Exception {
            ClusterStateWithDLSN clusterStateWithDLSN = clusterStateOpLog.getLatestClusterState();
            if (!clusterStateWithDLSN.state().getClusterName().equals(request.clusterName)) {
               throw new java.lang.Exception("master cluster name is [" + clusterStateWithDLSN.state().getClusterName() + "], request cluster name is [" + request.clusterName + "]");
            }
            if (!clusterStateWithDLSN.state().nodes().localNodeMaster()) {
                throw new java.lang.Exception("current node is no longer master node");
            }
            BytesStreamOutput bStream = new BytesStreamOutput();
            try (StreamOutput stream = CompressorFactory.defaultCompressor().streamOutput(bStream)) {
                clusterStateWithDLSN.writeTo(stream);
            }
            BytesReference fullStateBytes = bStream.bytes();
            channel.sendResponse(new org.elasticsearch.transport.BytesTransportResponse(fullStateBytes));
        }
    }
    
    private class PullFullClusterStateFromNodeResponseHandler implements TransportResponseHandler<BytesTransportResponse> {

        @Override
        public BytesTransportResponse newInstance() {
            return new BytesTransportResponse();
        }

        @Override
        public void handleResponse(BytesTransportResponse response) {
            try {
                Compressor compressor = CompressorFactory.compressor(response.bytes());
                StreamInput in;
                if (compressor != null) {
                    in = compressor.streamInput(response.bytes().streamInput());
                } else {
                    in = response.bytes().streamInput();
                }
                final ClusterStateWithDLSN lastSeenClusterStateWithDLSN = ClusterStateWithDLSN.PROTO.readFrom(in, clusterService.localNode());
                logger.debug("received full cluster state version {} with size {}", lastSeenClusterStateWithDLSN.state().version(), response.bytes().length());
                clusterService.submitStateUpdateTask("receive full cluster state", new ClusterStateUpdateTask() {
                    
                    @Override
                    public boolean runOnlyOnMaster() {
                        return false;
                    }
                    
                    @Override
                    public void onFailure(String source, Throwable t) {
                        logger.debug("errors while pull full cluster state from master and apply to local", t);
                        isPullingState = false;
                    }
                    
                    @Override
                    public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
                        isPullingState = false;
                    }
                    
                    @Override
                    public ClusterState execute(ClusterState currentState) throws Exception {
                        // check cluster name and others ? original zen discovery do this, but we use pull here, not need do it
                        if (currentState.version() < lastSeenClusterStateWithDLSN.state().version()) {
                            ClusterState newClusterState = lastSeenClusterStateWithDLSN.state();
                            if (!newClusterState.nodes().nodeExists(localNode.getId())) {
                                DiscoveryNodes.Builder nodesBuilder = DiscoveryNodes.builder(newClusterState.getNodes()); 
                                // if local node restarts, then the id has changed, so node in the state with the same ip and port should be removed
                                // and add the new node to the state
                                for (DiscoveryNode activeNode : newClusterState.nodes()) {
                                    if (activeNode.address().equals(localNode.address())) {
                                        nodesBuilder.remove(activeNode.getId());
                                    }
                                }
                                for (ObjectObjectCursor<String, DiscoveryNode> nodeIdValuePair: newClusterState.nodes().deadNodes()) {
                                    if (nodeIdValuePair.value.address().equals(clusterService.localNode().address())) {
                                        nodesBuilder.removeDeadNodeByIpPort(nodeIdValuePair.value);
                                    }
                                }
                                nodesBuilder.put(clusterService.localNode());
                                nodesBuilder.localNodeId(clusterService.localNode().getId());
                                newClusterState = ClusterState.builder(newClusterState).nodes(nodesBuilder).build();
                            }
                            clusterStateOpLog.restartPullTask(newClusterState, lastSeenClusterStateWithDLSN.dlsn());
                            logger.debug("apply full cluster state successfully, with version [{}], previous local version is [{}]", 
                                    lastSeenClusterStateWithDLSN.state().version(), currentState.version());
                            return newClusterState;
                        }
                        logger.debug("full cluster state from master's version is [{}], not larger than local [{}]", 
                                lastSeenClusterStateWithDLSN.state().version(), currentState.version());
                        return currentState;
                    }
                });
            } catch (IOException e) {
                logger.error("errors while handle full state from master", e);
                isPullingState = false;
            }
        }

        @Override
        public void handleException(TransportException exp) {
            logger.debug("errors while pull full cluster state from master", exp);
            isPullingState = false;
        }

        @Override
        public String executor() {
            return ThreadPool.Names.SAME;
        }
    }

    public static class PullFullClusterStateRequest extends TransportRequest {
        
        private ClusterName clusterName;
        private DiscoveryNode requestNode;
        
        public PullFullClusterStateRequest() {
            
        }
        
        public PullFullClusterStateRequest(ClusterName clusterName, DiscoveryNode requestNode) {
            this.clusterName = clusterName;
            this.requestNode = requestNode;
        }
        
        @Override
        public void readFrom(StreamInput in) throws IOException {
            super.readFrom(in);
            this.clusterName = ClusterName.readClusterName(in);
            this.requestNode = DiscoveryNode.readNode(in);
        }

        @Override
        public void writeTo(StreamOutput out) throws IOException {
            super.writeTo(out);
            this.clusterName.writeTo(out);
            this.requestNode.writeTo(out);
        }
    }
    
    
}
