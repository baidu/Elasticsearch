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

package org.elasticsearch.action.admin.cluster.user.add;

import io.crate.metadata.information.InformationSchemaInfo;

import org.elasticsearch.ElasticsearchCorruptionException;
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
import org.elasticsearch.cluster.metadata.PrivilegeType;
import org.elasticsearch.cluster.metadata.UserMetadata;
import org.elasticsearch.cluster.metadata.UserProperty;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

public class TransportAddUserOrResetPasswdAction extends TransportMasterNodeAction<AddUserOrResetPasswdRequest, AddUserOrResetPasswdResponse> {

    @Inject
    public TransportAddUserOrResetPasswdAction(Settings settings, TransportService transportService, ClusterService clusterService,
            ThreadPool threadPool, ActionFilters actionFilters,
            IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, AddUserOrResetPasswdAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, AddUserOrResetPasswdRequest.class);
    }

    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected AddUserOrResetPasswdResponse newResponse() {
        return new AddUserOrResetPasswdResponse();
    }

    @Override
    protected ClusterBlockException checkBlock(AddUserOrResetPasswdRequest request, ClusterState state) {
        return state.blocks().globalBlockedException(ClusterBlockLevel.METADATA_WRITE);
    }

    @Override
    protected void masterOperation(final AddUserOrResetPasswdRequest request, ClusterState state, final ActionListener<AddUserOrResetPasswdResponse> listener) {

        clusterService.submitStateUpdateTask("[add user]", new AckedClusterStateUpdateTask<AddUserOrResetPasswdResponse>(request, listener) {
            
            @Override
            protected AddUserOrResetPasswdResponse newResponse(
                    boolean acknowledged) {
                return new AddUserOrResetPasswdResponse(acknowledged);
            }

            @Override
            public ClusterState execute(ClusterState currentState)
                    throws Exception {
                MetaData metaData = currentState.metaData();
                UserMetadata userMetadata = metaData.getUserMetadata();
                UserMetadata.Builder userMetadataBuilder = new UserMetadata.Builder(userMetadata);
                boolean isChanged = false;
                if (!request.isNewUser()) {
                    if (!userMetadata.getUserProperties().containsKey(request.getUsername())) {
                        throw new ElasticsearchException("user not exists: " + request.getUsername());
                    } else {
                        UserProperty userProperty = new UserProperty.Builder(userMetadata.getUserProperties().get(request.getUsername())).resetPassword(request.getPassword()).build();
                        userMetadataBuilder.addOrChangeUserProperty(request.getUsername(), userProperty);
                        isChanged = true;
                    }
                } else {
                    if (userMetadata.getUserProperties().containsKey(request.getUsername())) {
                        throw new ElasticsearchException("user already exists: " + request.getUsername());
                    } else {
                        // a new user could access db from anywhere
                        UserProperty.Builder builder = new UserProperty.Builder(request.getUsername(), request.getPassword());
                        builder.addIpToWhiteList("*");
                        builder.grantDBPrivilege(InformationSchemaInfo.NAME, PrivilegeType.READ_ONLY);
                        UserProperty userProperty = builder.build();
                        String usernameWithoutTenant = userProperty.getUsernameWithoutTenant();
                        if (UserProperty.ROOT_NAME.equalsIgnoreCase(usernameWithoutTenant)) {
                            throw new ElasticsearchCorruptionException("root is unique, could not add root user to any tenant");
                        }
                        userMetadataBuilder.addOrChangeUserProperty(request.getUsername(), userProperty);
                        isChanged = true;
                    }
                }
                if (isChanged) {
                    MetaData.Builder metaDataBuilder = MetaData.builder(metaData);
                    metaDataBuilder.putUserMetadata(userMetadataBuilder);
                    return ClusterState.builder(currentState).metaData(metaDataBuilder).build();
                } else {
                    return currentState;
                }
            }
            }
        );
    }

}