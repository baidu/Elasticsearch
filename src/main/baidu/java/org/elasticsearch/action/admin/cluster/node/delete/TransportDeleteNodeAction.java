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

package org.elasticsearch.action.admin.cluster.node.delete;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.master.TransportMasterNodeAction;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.metadata.MetaData;
import org.elasticsearch.cluster.metadata.MetaDataDeleteIndexService;
import org.elasticsearch.cluster.metadata.TenantMetadata;
import org.elasticsearch.cluster.metadata.TenantProperty;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.settings.NodeSettingsService;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;

/**
 * Delete index action.
 */
public class TransportDeleteNodeAction extends TransportMasterNodeAction<DeleteNodeRequest, DeleteNodeResponse> {
    @Inject
    public TransportDeleteNodeAction(Settings settings, TransportService transportService, ClusterService clusterService,
                                      ThreadPool threadPool, MetaDataDeleteIndexService deleteIndexService,
                                      NodeSettingsService nodeSettingsService, ActionFilters actionFilters,
                                      IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, DeleteNodeAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, DeleteNodeRequest.class);
    }

    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected DeleteNodeResponse newResponse() {
        return new DeleteNodeResponse();
    }

    @Override
    protected void masterOperation(final DeleteNodeRequest request, final ClusterState state, final ActionListener<DeleteNodeResponse> listener) {
        
        clusterService.submitStateUpdateTask("delete-node " + request.getIpPortAddress(), new ClusterStateUpdateTask(Priority.URGENT) {
            @Override
            public boolean runOnlyOnMaster() {
                return true;
            }
            
            @Override
            public ClusterState execute(ClusterState currentState)
                    throws Exception {
                for (DiscoveryNode activeNode : currentState.nodes()) {
                    if (activeNode.getIpPortAddress().equals(request.getIpPortAddress())) {
                        if (activeNode.getId().equals(currentState.nodes().localNodeId())) {
                            throw new Exception("the node to remove is the elected master node, could not remove!!!");
                        }
                        DiscoveryNodes.Builder nodesBuilder = new DiscoveryNodes.Builder(currentState.nodes()).remove(activeNode.getId());
                        TenantProperty tenantProperty = currentState.metaData().tenantMetadata().getNodeTenant(activeNode);
                        ClusterState.Builder builder = ClusterState.builder(currentState).nodes(nodesBuilder);
                        if (tenantProperty != null) {
                            TenantProperty.Builder tenantPropertyBuilder = new TenantProperty.Builder(tenantProperty);
                            tenantPropertyBuilder.removeNode(activeNode);
                            TenantMetadata.Builder tenantMetadataBuilder = new TenantMetadata.Builder(currentState.metaData().tenantMetadata());
                            tenantMetadataBuilder.addOrChangeTenantProperty(tenantPropertyBuilder);
                            MetaData.Builder metaDataBuilder = new MetaData.Builder(currentState.metaData());
                            metaDataBuilder.tenantMetadata(tenantMetadataBuilder);
                            builder.metaData(metaDataBuilder);
                        }
                        return builder.build();
                    }
                }
                
                for (ObjectObjectCursor<String, DiscoveryNode> nodeIdValuePair: currentState.nodes().deadNodes()) {
                    if (nodeIdValuePair.value.getIpPortAddress().equals(request.getIpPortAddress())) {
                        TenantProperty tenantProperty = currentState.metaData().tenantMetadata().getNodeTenant(nodeIdValuePair.value);
                        DiscoveryNodes.Builder nodesBuilder = new DiscoveryNodes.Builder(currentState.nodes()).removeDeadNodeByIpPort(nodeIdValuePair.value);
                        ClusterState.Builder builder = ClusterState.builder(currentState).nodes(nodesBuilder);
                        if (tenantProperty != null) {
                            TenantProperty.Builder tenantPropertyBuilder = new TenantProperty.Builder(tenantProperty);
                            tenantPropertyBuilder.removeNode(nodeIdValuePair.value);
                            TenantMetadata.Builder tenantMetadataBuilder = new TenantMetadata.Builder(currentState.metaData().tenantMetadata());
                            tenantMetadataBuilder.addOrChangeTenantProperty(tenantPropertyBuilder);
                            MetaData.Builder metaDataBuilder = new MetaData.Builder(currentState.metaData());
                            metaDataBuilder.tenantMetadata(tenantMetadataBuilder);
                            builder.metaData(metaDataBuilder);
                        }
                        return builder.build();
                    }
                }
                
                throw new Exception("could not find node in active and dead node list " + request.getIpPortAddress());
            }

            @Override
            public void onFailure(String source, Throwable t) {
                listener.onFailure(t);
            }
            
            @Override
            public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
                listener.onResponse(new DeleteNodeResponse(true));
            }
        });
        return;
    }

    @Override
    protected ClusterBlockException checkBlock(DeleteNodeRequest request,
            ClusterState state) {
        return null;
    }
}
