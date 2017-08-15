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

package org.elasticsearch.action.admin.cluster.tenant.delete;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.master.TransportMasterNodeAction;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.metadata.IndexTemplateMetaData;
import org.elasticsearch.cluster.metadata.MetaData;
import org.elasticsearch.cluster.metadata.TenantMetadata;
import org.elasticsearch.cluster.metadata.UserMetadata;
import org.elasticsearch.cluster.metadata.UserProperty;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

import com.carrotsearch.hppc.cursors.ObjectCursor;

/**
 * the tenant could be deleted iff there is no index allocated on the index
 * 
 * @author yiguolei
 */
public class TransportDeleteTenantAction extends TransportMasterNodeAction<DeleteTenantRequest, DeleteTenantResponse> {
    
    @Inject
    public TransportDeleteTenantAction(Settings settings, TransportService transportService, ClusterService clusterService,
                                      ThreadPool threadPool, ActionFilters actionFilters,
                                      IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, DeleteTenantAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, DeleteTenantRequest.class);
    }
    
    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected DeleteTenantResponse newResponse() {
        return new DeleteTenantResponse();
    }

    @Override
    protected void masterOperation(final DeleteTenantRequest request,
            final ClusterState state, final ActionListener<DeleteTenantResponse> listener)
            throws Exception {

        clusterService.submitStateUpdateTask("delete_tenant " + request.tenantName(), new ClusterStateUpdateTask(Priority.NORMAL) {

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
                    throw new Exception("could not find tenant with id: " + request.tenantName());
                }
                ArrayList<String> indicesExist = new ArrayList<>();
                for (ObjectCursor<IndexMetaData> cursor : currentState.metaData().indices().values()) {
                    if (cursor.value.indexOwnerTenantId() == tenantId) {
                        indicesExist.add(cursor.value.getIndex());
                    }
                }
                if (indicesExist.size() > 0) {
                    throw new Exception("tenant " + request.tenantName() + " is not empty, please delete all indices before delete it, current existing indices: " + StringUtils.join(indicesExist, ","));
                }
                TenantMetadata.Builder tenantMetadataBuilder = new TenantMetadata.Builder(tenantMetadata);
                tenantMetadataBuilder.deleteTenant(tenantId);
                MetaData.Builder metadataBuilder = new MetaData.Builder(currentState.metaData());
                metadataBuilder.tenantMetadata(tenantMetadataBuilder);
                // delete all users under this tenant
                List<UserProperty> userProperties = currentState.metaData().getUserMetadata().getUserListForTenant(tenantId);
                UserMetadata.Builder usermetadataBuilder = new UserMetadata.Builder(currentState.metaData().getUserMetadata());
                for (UserProperty userProperty : userProperties) {
                    usermetadataBuilder.deleteUser(userProperty.getUsernameWithTenant());
                }
                metadataBuilder.userMetadata(usermetadataBuilder.build());
                
                // delete all indices under this tenant
                for (ObjectCursor<IndexMetaData> indexMetaData : currentState.metaData().getIndices().values()) {
                    if (indexMetaData.value.indexOwnerTenantId() == tenantId) {
                        metadataBuilder.remove(indexMetaData.value.getIndex());
                    }
                }
                
                // delete all templates under this tenant
                for (ObjectCursor<IndexTemplateMetaData> indexTemplateMetaData : currentState.metaData().getTemplates().values()) {
                    if (indexTemplateMetaData.value.templateOwnerTenantId() == tenantId) {
                        metadataBuilder.remove(indexTemplateMetaData.value.getName());
                    }
                }
                
                // delete all settings related to the tenant
                String tenantSettingPrefix = TenantMetadata.TENANT_SETTING_PREFIX + "." + request.tenantName() + ".";
                Settings.Builder persistentSettings = Settings.settingsBuilder();
                persistentSettings.put(currentState.metaData().persistentSettings());
                for (Map.Entry<String, String> entry : currentState.metaData().persistentSettings().getAsMap().entrySet()) {
                    if (entry.getKey().startsWith(tenantSettingPrefix)) {
                        persistentSettings.remove(entry.getKey());
                    }
                }
                Settings.Builder transientSettings = Settings.settingsBuilder();
                transientSettings.put(currentState.metaData().transientSettings());
                for (Map.Entry<String, String> entry : currentState.metaData().transientSettings().getAsMap().entrySet()) {
                    if (entry.getKey().startsWith(tenantSettingPrefix)) {
                        transientSettings.remove(entry.getKey());
                    }
                }
                metadataBuilder.persistentSettings(persistentSettings.build());
                metadataBuilder.transientSettings(transientSettings.build());
                
                return ClusterState.builder(currentState).metaData(metadataBuilder).build();
            }

            @Override
            public void onFailure(String source, Throwable t) {
                listener.onFailure(t);
            }
            
            @Override
            public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
                listener.onResponse(new DeleteTenantResponse(true));
            }
        });
        return;
    }

    @Override
    protected ClusterBlockException checkBlock(DeleteTenantRequest request,
            ClusterState state) {
        return null;
    }

}
