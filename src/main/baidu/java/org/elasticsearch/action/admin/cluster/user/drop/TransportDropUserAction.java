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

package org.elasticsearch.action.admin.cluster.user.drop;

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
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

public class TransportDropUserAction extends TransportMasterNodeAction<DropUserRequest, DropUserResponse> {

    @Inject
    public TransportDropUserAction(Settings settings, TransportService transportService, ClusterService clusterService,
            ThreadPool threadPool, ActionFilters actionFilters,
            IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, DropUserAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, DropUserRequest.class);
    }

    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected DropUserResponse newResponse() {
        return new DropUserResponse();
    }

    @Override
    protected ClusterBlockException checkBlock(DropUserRequest request, ClusterState state) {
        return state.blocks().globalBlockedException(ClusterBlockLevel.METADATA_WRITE);
    }

    @Override
    protected void masterOperation(final DropUserRequest request, ClusterState state, final ActionListener<DropUserResponse> listener) {

        clusterService.submitStateUpdateTask("[drop user]", new AckedClusterStateUpdateTask<DropUserResponse>(request, listener) {
            
            @Override
            protected DropUserResponse newResponse(
                    boolean acknowledged) {
                return new DropUserResponse(acknowledged);
            }

            @Override
            public ClusterState execute(ClusterState currentState)
                    throws Exception {
                MetaData metaData = currentState.metaData();
                UserMetadata userMetadata = metaData.getUserMetadata();
                UserMetadata.Builder userMetadataBuilder = new UserMetadata.Builder(userMetadata);
                if (!userMetadata.getUserProperties().containsKey(request.getUsername())) {
                    throw new ElasticsearchException("user not exists: " + request.getUsername());
                }
                userMetadataBuilder.deleteUser(request.getUsername());
                MetaData.Builder metaDataBuilder = MetaData.builder(metaData);
                metaDataBuilder.putUserMetadata(userMetadataBuilder);
                return ClusterState.builder(currentState).metaData(metaDataBuilder).build();
            }
            }
        );
    }

}