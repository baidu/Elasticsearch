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

package org.elasticsearch.action.admin.cluster.node.add;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.Version;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.master.TransportMasterNodeAction;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.metadata.MetaDataDeleteIndexService;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.node.settings.NodeSettingsService;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;

/**
 * Delete index action.
 */
public class TransportAddNodeAction extends TransportMasterNodeAction<AddNodeRequest, AddNodeResponse> {
    
    private static final String TMP_ADD_PREFIX = "temp_add_prefix_";
    
    @Inject
    public TransportAddNodeAction(Settings settings, TransportService transportService, ClusterService clusterService,
                                      ThreadPool threadPool, MetaDataDeleteIndexService deleteIndexService,
                                      NodeSettingsService nodeSettingsService, ActionFilters actionFilters,
                                      IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, AddNodeAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, AddNodeRequest.class);
    }

    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected AddNodeResponse newResponse() {
        return new AddNodeResponse();
    }

    @Override
    protected void masterOperation(final AddNodeRequest request, final ClusterState state, final ActionListener<AddNodeResponse> listener) {

        clusterService.submitStateUpdateTask("admin-add-node " + request.getIpPortAddress(), new ClusterStateUpdateTask(Priority.NORMAL) {

            @Override
            public boolean runOnlyOnMaster() {
                return true;
            }
            
            @Override
            public ClusterState execute(ClusterState currentState)
                    throws Exception {
                for (DiscoveryNode activeNode : currentState.nodes()) {
                    if (activeNode.getIpPortAddress().equals(request.getIpPortAddress())) {
                        // if the node already exist, then return and send error message to client
                        listener.onFailure(new Exception("node " + request.getIpPortAddress() + " is already an active node in list"));
                        return currentState;
                    }
                }
                
                for (ObjectObjectCursor<String, DiscoveryNode> nodeIdValuePair: currentState.nodes().deadNodes()) {
                    if (nodeIdValuePair.value.getIpPortAddress().equals(request.getIpPortAddress())) {
                        // if the node already exist, then return and send error message to client
                        listener.onFailure(new Exception("node " + request.getIpPortAddress() + " is already an dead node in list"));
                        return currentState;
                    }
                }
                
                String nodeId = TMP_ADD_PREFIX + Strings.randomBase64UUID();
                TransportAddress transportAddress = new InetSocketTransportAddress(request.getAddress(), request.getNodePort());
                Map<String, String> attributes = new HashMap<String, String>();
                attributes.put(DiscoveryNode.MASTER_ATTR, "false");
                DiscoveryNode node = new DiscoveryNode(nodeId, nodeId, transportAddress, attributes, Version.CURRENT);
            
                DiscoveryNodes.Builder nodesBuilder = new DiscoveryNodes.Builder(currentState.nodes()).putDeadNodeByIpPort(node);
                return ClusterState.builder(currentState).nodes(nodesBuilder.build()).build();
            }

            @Override
            public void onFailure(String source, Throwable t) {
                listener.onFailure(t);
            }
            
            @Override
            public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
                listener.onResponse(new AddNodeResponse(true));
            }
        });
        return;
    }

    @Override
    protected ClusterBlockException checkBlock(AddNodeRequest request,
            ClusterState state) {
        return null;
    }
}
