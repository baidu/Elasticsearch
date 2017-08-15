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

package org.elasticsearch.action.admin.cluster.tenant.show;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.master.TransportMasterNodeReadAction;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.metadata.TenantProperty;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

import com.google.common.collect.ImmutableMap;

public class TransportShowTenantsAction extends TransportMasterNodeReadAction<ShowTenantsRequest, ShowTenantsResponse> {
    
    @Inject
    public TransportShowTenantsAction(Settings settings, TransportService transportService, ClusterService clusterService,
            ThreadPool threadPool, ActionFilters actionFilters,
            IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, ShowTenantsAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, ShowTenantsRequest.class);
    }

    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected ShowTenantsResponse newResponse() {
        return new ShowTenantsResponse();
    }

    @Override
    protected ClusterBlockException checkBlock(ShowTenantsRequest request, ClusterState state) {
        return null;
    }

    @Override
    protected void masterOperation(final ShowTenantsRequest request, ClusterState state, final ActionListener<ShowTenantsResponse> listener) {

        ShowTenantsResponse response = new ShowTenantsResponse();
        List<TenantProperty> tenantProperties = new ArrayList<TenantProperty>();
        ImmutableMap<Long, TenantProperty> allTenants = state.metaData().getTenantMetadata().tenantProperties();
        for (Long tenantId : allTenants.keySet()) {
            tenantProperties.add(allTenants.get(tenantId));
        }
        response.setTenantProperty(tenantProperties);
        listener.onResponse(response);
    }

}
