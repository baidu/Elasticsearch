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

package org.elasticsearch.action.admin.cluster.user.whitelist;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.master.TransportMasterNodeAction;
import org.elasticsearch.cluster.AckedClusterStateUpdateTask;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.block.ClusterBlockLevel;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.metadata.MetaData;
import org.elasticsearch.cluster.metadata.UserMetadata;
import org.elasticsearch.cluster.metadata.UserProperty;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

public class TransportResetWhitelistAction extends TransportMasterNodeAction<ResetWhitelistRequest, ResetWhitelistResponse> {

    @Inject
    public TransportResetWhitelistAction(Settings settings, TransportService transportService, ClusterService clusterService,
            ThreadPool threadPool, ActionFilters actionFilters,
            IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, ResetWhitelistAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, ResetWhitelistRequest.class);
    }

    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected ResetWhitelistResponse newResponse() {
        return new ResetWhitelistResponse();
    }

    @Override
    protected ClusterBlockException checkBlock(ResetWhitelistRequest request, ClusterState state) {
        return state.blocks().globalBlockedException(ClusterBlockLevel.METADATA_WRITE);
    }

    @Override
    protected void masterOperation(final ResetWhitelistRequest request, ClusterState state, final ActionListener<ResetWhitelistResponse> listener) {

        clusterService.submitStateUpdateTask("[alter user]", new AckedClusterStateUpdateTask<ResetWhitelistResponse>(request, listener) {
            
            @Override
            protected ResetWhitelistResponse newResponse(
                    boolean acknowledged) {
                return new ResetWhitelistResponse(acknowledged);
            }

            @Override
            public ClusterState execute(ClusterState currentState)
                    throws Exception {
                MetaData metaData = currentState.metaData();
                UserMetadata userMetadata = metaData.getUserMetadata();
                UserMetadata.Builder userMetadataBuilder = new UserMetadata.Builder(userMetadata);
                if (!userMetadata.getUserProperties().containsKey(request.getUsername())) {
                    throw new ElasticsearchException("user not exists: " + request.getUsername());
                } else {
                    UserProperty.Builder userPropertyBuilder = new UserProperty.Builder(userMetadata.getUserProperties().get(request.getUsername()));
                    userPropertyBuilder.cleanHostnameWhiteList();
                    userPropertyBuilder.cleanIpWhiteList();
                    String[] whiteLists = request.getWhitelist().split(",");
                    for (int i = 0; i < whiteLists.length; ++i) {
                        String host = whiteLists[i];
                        if (isHostName(host)) {
                            userPropertyBuilder.addHostnameToWhiteList(host);
                        } else {
                            userPropertyBuilder.addIpToWhiteList(host);
                        }
                    }
                    UserProperty userProperty = userPropertyBuilder.build();
                    userMetadataBuilder.addOrChangeUserProperty(request.getUsername(), userProperty);
                }
                MetaData.Builder metaDataBuilder = MetaData.builder(metaData);
                metaDataBuilder.putUserMetadata(userMetadataBuilder);
                return ClusterState.builder(currentState).metaData(metaDataBuilder).build();
            }
            }
        );
    }

    private boolean isHostName(String host) {
        if (host == null) {
            return false;
        }

        for(char ch : host.toCharArray()) {
            if (Character.isLetter(ch)) {
                return true;
            }
        }
        return false;
    }
}