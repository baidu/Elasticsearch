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

package org.elasticsearch.action.admin.cluster.user.show;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.master.TransportMasterNodeReadAction;
import org.elasticsearch.authentication.AuthService;
import org.elasticsearch.authentication.LoginUserContext;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.block.ClusterBlockLevel;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.metadata.MetaData;
import org.elasticsearch.cluster.metadata.UserProperty;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

import com.google.common.collect.ImmutableMap;

public class TransportShowUserPropertyAction extends TransportMasterNodeReadAction<ShowUserPropertyRequest, ShowUserPropertyResponse> {

    private final String ALL_USER_NAME = "*";
    
    @Inject
    public TransportShowUserPropertyAction(Settings settings, TransportService transportService, ClusterService clusterService,
            ThreadPool threadPool, ActionFilters actionFilters,
            IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, ShowUserPropertyAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, ShowUserPropertyRequest.class);
    }

    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected ShowUserPropertyResponse newResponse() {
        return new ShowUserPropertyResponse();
    }

    @Override
    protected ClusterBlockException checkBlock(ShowUserPropertyRequest request, ClusterState state) {
        return state.blocks().globalBlockedException(ClusterBlockLevel.METADATA_WRITE);
    }

    @Override
    protected void masterOperation(final ShowUserPropertyRequest request, ClusterState state, final ActionListener<ShowUserPropertyResponse> listener) {
        MetaData metaData = clusterService.state().metaData();
        String username = request.getUsername();
        if (!ALL_USER_NAME.equalsIgnoreCase(username)) {
            UserProperty userProperty = metaData.getUserMetadata().getUserPropertyFromLoginUser(request.getUsername(), metaData.getTenantMetadata());
            if (userProperty != null) {
                username = userProperty.getUsernameWithTenant();
            }
        }
        if (request.getHeader(LoginUserContext.TENANT_FILTER) != null) {
            state = AuthService.filterState(state, metaData, (Long) request.getHeader(LoginUserContext.TENANT_FILTER));
        }
        if (state.metaData().getUserMetadata().getUserProperties().containsKey(username) || ALL_USER_NAME.equalsIgnoreCase(username)) {
            ShowUserPropertyResponse response = new ShowUserPropertyResponse();
            List<UserProperty> userProperties = new ArrayList<UserProperty>();
            if (username.equalsIgnoreCase(ALL_USER_NAME)) {
                ImmutableMap<String, UserProperty> allUsers = state.metaData().getUserMetadata().getUserProperties();
                for (String specificUsername : allUsers.keySet()) {
                    userProperties.add(allUsers.get(specificUsername));
                }
            } else {
                userProperties.add(state.metaData().getUserMetadata().getUserProperties().get(username));
            }
            response.setUserProperty(userProperties);
            listener.onResponse(response);
        } else {
            listener.onFailure(new ElasticsearchException("user not exist: " + username));
        }
    }

}