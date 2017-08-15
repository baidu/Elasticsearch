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

package org.elasticsearch.action.admin.cluster.tenant.modify;

import io.crate.sql.tree.TenantModificationOperation;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.master.TransportMasterNodeAction;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.metadata.AllocatedNodeStatus;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.metadata.MetaData;
import org.elasticsearch.cluster.metadata.AllocatedNodeInfo;
import org.elasticsearch.cluster.metadata.TenantMetadata;
import org.elasticsearch.cluster.metadata.TenantProperty;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

public class TransportModifyTenantNodesAction extends TransportMasterNodeAction<ModifyTenantNodesRequest, ModifyTenantNodesResponse> {
    
    @Inject
    public TransportModifyTenantNodesAction(Settings settings, TransportService transportService, ClusterService clusterService,
                                      ThreadPool threadPool, ActionFilters actionFilters,
                                      IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, ModifyTenantNodesAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, ModifyTenantNodesRequest.class);
    }
    
    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected ModifyTenantNodesResponse newResponse() {
        return new ModifyTenantNodesResponse();
    }

    @Override
    protected void masterOperation(final ModifyTenantNodesRequest request,
            final ClusterState state, final ActionListener<ModifyTenantNodesResponse> listener)
            throws Exception {

        clusterService.submitStateUpdateTask("add or remove nodes to tenant " + request.tenantName(), new ClusterStateUpdateTask(Priority.NORMAL) {

            @Override
            public boolean runOnlyOnMaster() {
                return true;
            }
            
            @Override
            public ClusterState execute(ClusterState currentState)
                    throws Exception {
                TenantMetadata tenantMetadata = currentState.metaData().tenantMetadata();
                long tenantId = currentState.metaData().tenantMetadata().tenantId(request.tenantName());
                if (tenantMetadata.getTenantProperty(tenantId) == null) {
                    throw new Exception("cluster [" + request.tenantName() + "] does not exist");
                }
                TenantProperty.Builder tenantPropertyBuilder = new TenantProperty.Builder(currentState.metaData().tenantMetadata().tenant(tenantId));
                AllocatedNodeInfo nodeId = new AllocatedNodeInfo(request.getNodeIp(), request.getNodePort());
                // this is a add request
                if (tenantMetadata.getNodeTenant(nodeId) != null && request.getOperation().equals(TenantModificationOperation.ADD_NODES)) {
                    if (tenantMetadata.getNodeTenant(nodeId).tenantId() != tenantId) {
                        throw new Exception(nodeId + " is already allocated to [" + tenantMetadata.tenant(tenantMetadata.getNodeTenant(nodeId).tenantId()).tenantName() 
                                + "], could not be allocated to [" + request.tenantName() + "] again");
                    } else {
                        // ignore the request, since the node is allocated to tenant
                        // and this is a duplicate request
                        return currentState;
                    }
                }
                // this is a delete request
                if (!request.getOperation().equals(TenantModificationOperation.ADD_NODES)) {
                    if (tenantMetadata.getNodeTenant(nodeId) == null) {
                        throw new Exception(nodeId + " is not allocated to any cluster");
                    }
                    if (tenantMetadata.getNodeTenant(nodeId).tenantId() != tenantId) {
                        throw new Exception(nodeId + " is allocated to [" + tenantMetadata.getNodeTenant(nodeId).tenantId() 
                                + "], not to [" + request.tenantName() + "] so could not be removed");
                    }
                }
                
                if (request.getOperation().equals(TenantModificationOperation.ADD_NODES)) {
                    tenantPropertyBuilder.addNode(nodeId);
                    tenantPropertyBuilder.addInstanceNum(1);
                } else if (request.getOperation().equals(TenantModificationOperation.DECOMMISSION_NODES)){
                    AllocatedNodeInfo newNodeInfo = new AllocatedNodeInfo(nodeId.getIpAddress(), nodeId.getPort(), AllocatedNodeStatus.DECOMMISSIONING);
                    tenantPropertyBuilder.addNode(newNodeInfo);
                    tenantPropertyBuilder.minusInstanceNum(1);
                } else if (request.getOperation().equals(TenantModificationOperation.DROP_NODES)) {
                    tenantPropertyBuilder.removeNode(nodeId);
                    tenantPropertyBuilder.minusInstanceNum(1);
                }
                // add modified tenant to builder
                TenantMetadata.Builder tenantMetadataBuilder = new TenantMetadata.Builder(tenantMetadata);
                tenantMetadataBuilder.addOrChangeTenantProperty(tenantPropertyBuilder);
                MetaData.Builder metadataBuilder = new MetaData.Builder(currentState.metaData());
                metadataBuilder.tenantMetadata(tenantMetadataBuilder);
                return ClusterState.builder(currentState).metaData(metadataBuilder).build();
            }

            @Override
            public void onFailure(String source, Throwable t) {
                listener.onFailure(t);
            }
            
            @Override
            public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
                listener.onResponse(new ModifyTenantNodesResponse(true));
            }
        });
        return;
    }

    @Override
    protected ClusterBlockException checkBlock(ModifyTenantNodesRequest request,
            ClusterState state) {
        return null;
    }
}
