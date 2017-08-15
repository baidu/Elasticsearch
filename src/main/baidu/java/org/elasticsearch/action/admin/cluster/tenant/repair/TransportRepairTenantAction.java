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

package org.elasticsearch.action.admin.cluster.tenant.repair;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.master.TransportMasterNodeAction;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

public class TransportRepairTenantAction  extends TransportMasterNodeAction<RepairTenantRequest, RepairTenantResponse> {
    
    @Inject
    public TransportRepairTenantAction(Settings settings, TransportService transportService, ClusterService clusterService,
                                      ThreadPool threadPool, ActionFilters actionFilters,
                                      IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, RepairTenantAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, RepairTenantRequest.class);
    }
    
    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected RepairTenantResponse newResponse() {
        return new RepairTenantResponse();
    }

    @Override
    protected void masterOperation(final RepairTenantRequest request,
            final ClusterState state, final ActionListener<RepairTenantResponse> listener)
            throws Exception {

        clusterService.submitStateUpdateTask("repair_tenant", new ClusterStateUpdateTask(Priority.NORMAL) {

            @Override
            public boolean runOnlyOnMaster() {
                return true;
            }
            
            @Override
            public ClusterState execute(ClusterState currentState)
                    throws Exception {
                return currentState;
            }

            @Override
            public void onFailure(String source, Throwable t) {
                listener.onFailure(t);
            }
            
            @Override
            public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
                listener.onResponse(new RepairTenantResponse(true));
            }
        });
        return;
    }

    @Override
    protected ClusterBlockException checkBlock(RepairTenantRequest request,
            ClusterState state) {
        return null;
    }

}
