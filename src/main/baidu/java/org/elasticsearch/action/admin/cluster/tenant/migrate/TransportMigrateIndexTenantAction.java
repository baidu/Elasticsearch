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

package org.elasticsearch.action.admin.cluster.tenant.migrate;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.master.TransportMasterNodeAction;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.metadata.MetaData;
import org.elasticsearch.cluster.metadata.TenantMetadata;
import org.elasticsearch.cluster.routing.allocation.AllocationService;
import org.elasticsearch.cluster.routing.allocation.RoutingAllocation;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

/**
 * migrate an index from tenant a to tenant b
 * for source tenant: only change shard allocation, not change other things
 * for dest tenant: change shard allocation to itself, and add index privilege to superuser
 * 
 * @author yiguolei
 *
 */
public class TransportMigrateIndexTenantAction extends TransportMasterNodeAction<MigrateIndexTenantRequest, MigrateIndexTenantResponse> {
    
    private final AllocationService allocationService;
    
    @Inject
    public TransportMigrateIndexTenantAction(Settings settings, TransportService transportService, ClusterService clusterService,
                                      ThreadPool threadPool, ActionFilters actionFilters,
                                      IndexNameExpressionResolver indexNameExpressionResolver, AllocationService allocationService) {
        super(settings, MigrateIndexTenantAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, MigrateIndexTenantRequest.class);
        this.allocationService = allocationService;
    }
    
    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected MigrateIndexTenantResponse newResponse() {
        return new MigrateIndexTenantResponse();
    }

    @Override
    protected void masterOperation(final MigrateIndexTenantRequest request,
            final ClusterState state, final ActionListener<MigrateIndexTenantResponse> listener)
            throws Exception {

        clusterService.submitStateUpdateTask("migrate index to tenant " + request.tenantName(), new ClusterStateUpdateTask(Priority.NORMAL) {

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
                    throw new Exception("tenant [" + request.tenantName() + "] does not exist");
                }
                MetaData.Builder metadataBuilder = new MetaData.Builder(currentState.metaData());
                boolean changed = false;
                for (String indexName : request.indices()) {
                    IndexMetaData.Builder builder = new IndexMetaData.Builder(currentState.metaData().index(indexName));
                    if (builder.indexOwnerTenantId() == tenantId) {
                        continue;
                    }
                    builder.indexOwnerTenantId(tenantId);
                    metadataBuilder.put(builder);
                    changed = true;
                }
                if (!changed) {
                    return currentState;
                }
                // if some index is migrated successfully then should call reroute to change routing table
                ClusterState updatedState = ClusterState.builder(currentState).metaData(metadataBuilder).build();
                RoutingAllocation.Result routingResult = allocationService.reroute(updatedState, "reroute after migrate table");
                if (!routingResult.changed()) {
                    return updatedState;
                }
                return ClusterState.builder(updatedState).routingResult(routingResult).build();
            }

            @Override
            public void onFailure(String source, Throwable t) {
                listener.onFailure(t);
            }
            
            @Override
            public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
                listener.onResponse(new MigrateIndexTenantResponse(true));
            }
        });
        return;
    }

    @Override
    protected ClusterBlockException checkBlock(MigrateIndexTenantRequest request,
            ClusterState state) {
        return null;
    }

}
