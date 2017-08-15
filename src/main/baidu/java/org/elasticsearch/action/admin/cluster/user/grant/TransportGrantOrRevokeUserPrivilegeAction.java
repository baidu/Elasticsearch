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

package org.elasticsearch.action.admin.cluster.user.grant;

import java.util.List;

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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class TransportGrantOrRevokeUserPrivilegeAction extends TransportMasterNodeAction<GrantOrRevokeUserPrivilegeRequest, GrantOrRevokeUserPrivilegeResponse> {

    @Inject
    public TransportGrantOrRevokeUserPrivilegeAction(Settings settings, TransportService transportService, ClusterService clusterService,
            ThreadPool threadPool, ActionFilters actionFilters,
            IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, GrantOrRevokeUserPrivilegeAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, GrantOrRevokeUserPrivilegeRequest.class);
    }

    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected GrantOrRevokeUserPrivilegeResponse newResponse() {
        return new GrantOrRevokeUserPrivilegeResponse();
    }

    @Override
    protected ClusterBlockException checkBlock(GrantOrRevokeUserPrivilegeRequest request, ClusterState state) {
        return state.blocks().globalBlockedException(ClusterBlockLevel.METADATA_WRITE);
    }

    @Override
    protected void masterOperation(final GrantOrRevokeUserPrivilegeRequest request, ClusterState state, final ActionListener<GrantOrRevokeUserPrivilegeResponse> listener) {

        clusterService.submitStateUpdateTask("[grant user]", new AckedClusterStateUpdateTask<GrantOrRevokeUserPrivilegeResponse>(request, listener) {
            
            @Override
            protected GrantOrRevokeUserPrivilegeResponse newResponse(
                    boolean acknowledged) {
                return new GrantOrRevokeUserPrivilegeResponse(acknowledged);
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
                if (request.isGrant()) {
                    if (request.isDBPrivilege()) {
                        UserProperty userProperty = new UserProperty.Builder(userMetadata.getUserProperties().get(request.getUsername())).grantDBPrivilege(request.getDbOrTableName(), request.getPrivilegeType()).build();
                        userMetadataBuilder.addOrChangeUserProperty(request.getUsername(), userProperty);
                    } else {
                        UserProperty userProperty = new UserProperty.Builder(userMetadata.getUserProperties().get(request.getUsername())).grantTablePrivilege(request.getDbOrTableName(), request.getPrivilegeType()).build();
                        userMetadataBuilder.addOrChangeUserProperty(request.getUsername(), userProperty);
                    }
                } else {
                    if (request.isDBPrivilege()) {
                        UserProperty userProperty = new UserProperty.Builder(userMetadata.getUserProperties().get(request.getUsername())).revokeDBPrivilege(request.getDbOrTableName(), request.getPrivilegeType()).build();
                        userMetadataBuilder.addOrChangeUserProperty(request.getUsername(), userProperty);
                        // if the root wants to revoke superuser@tenant_id's db privilege, then revoke all user's previlege under this tenant
                        String username = UserProperty.getUsernameWithoutTenantFromFullUsername(request.getUsername());
                        if (UserProperty.SUPER_USER_NAME.equalsIgnoreCase(username)) {
                            UserProperty superUserProperty = userProperty;
                            long tenantId = UserProperty.getTenantIdFromFullUserName(request.getUsername());
                            // cascade opertion only have affect on tenants
                            if (tenantId > 0) {
                                List<UserProperty> usersInTenant = userMetadata.getUserListForTenant(tenantId);
                                ImmutableSet<PrivilegeType> superuserDBPrivileges = superUserProperty.getDbPrivileges().get(request.getDbOrTableName());
                                for (UserProperty normalUserProperty : usersInTenant) {
                                    UserProperty.Builder normalUserPropertyBuilder = new UserProperty.Builder(normalUserProperty);
                                    ImmutableSet<PrivilegeType> dbPrivileges = normalUserProperty.getDbPrivileges().get(request.getDbOrTableName());
                                    boolean isChanged = false;
                                    // revoke normal user's db privilege
                                    for (PrivilegeType dbPrivilege : dbPrivileges) {
                                        if (dbPrivilege.equals(PrivilegeType.READ_WRITE) 
                                                && (superuserDBPrivileges == null || !superuserDBPrivileges.contains(PrivilegeType.READ_WRITE))) {
                                            normalUserPropertyBuilder.revokeDBPrivilege(request.getDbOrTableName(), PrivilegeType.READ_WRITE);
                                            isChanged = true;
                                        } else if (dbPrivilege.equals(PrivilegeType.READ_ONLY) 
                                                && (superuserDBPrivileges == null || superuserDBPrivileges.isEmpty())) {
                                            normalUserPropertyBuilder.revokeDBPrivilege(request.getDbOrTableName(), PrivilegeType.READ_ONLY);
                                            isChanged = true;
                                        }
                                    }
                                    // revoke normal user's table privilege
                                    ImmutableMap<String, ImmutableSet<PrivilegeType>> userTablePrivileges = normalUserProperty.getTablePrivileges();
                                    for (String tableName : userTablePrivileges.keySet()) {
                                        String dbName = UserProperty.getDBAndTableName(tableName).v1();
                                        // if the table's privilege is granted to this user because super user have the related db privilege, then we should check it
                                        if (request.getDbOrTableName().equalsIgnoreCase(dbName)) {
                                            ImmutableSet<PrivilegeType> tablePrivileges = userTablePrivileges.get(tableName);
                                            for (PrivilegeType tablePrivilege : tablePrivileges) {
                                                if (tablePrivilege.equals(PrivilegeType.READ_WRITE) 
                                                        && (superuserDBPrivileges == null || !superuserDBPrivileges.contains(PrivilegeType.READ_WRITE))
                                                        && (superUserProperty.getTablePrivileges() == null 
                                                            || superUserProperty.getTablePrivileges().get(tableName) == null 
                                                            || !superUserProperty.getTablePrivileges().get(tableName).contains(PrivilegeType.READ_WRITE))) {
                                                    normalUserPropertyBuilder.revokeTablePrivilege(tableName, PrivilegeType.READ_WRITE);
                                                    isChanged = true;
                                                } else if (tablePrivilege.equals(PrivilegeType.READ_ONLY) 
                                                        && (superuserDBPrivileges == null || superuserDBPrivileges.isEmpty())
                                                        && (superUserProperty.getTablePrivileges() == null 
                                                            || superUserProperty.getTablePrivileges().get(tableName) == null 
                                                            || superUserProperty.getTablePrivileges().get(tableName).isEmpty() )) {
                                                    normalUserPropertyBuilder.revokeTablePrivilege(tableName, PrivilegeType.READ_ONLY);
                                                    isChanged = true;
                                                }
                                            }
                                        }
                                    }
                                    if (isChanged) {
                                        userMetadataBuilder.addOrChangeUserProperty(normalUserProperty.getUsernameWithTenant(), normalUserPropertyBuilder.build());
                                    }
                                }
                            }
                        }
                    } else {
                        UserProperty userProperty = new UserProperty.Builder(userMetadata.getUserProperties().get(request.getUsername())).revokeTablePrivilege(request.getDbOrTableName(), request.getPrivilegeType()).build();
                        userMetadataBuilder.addOrChangeUserProperty(request.getUsername(), userProperty);
                        // if the root wants to revoke superuser@tenant_id's table privilege, then revoke all user's privilege under this tenant
                        String username = UserProperty.getUsernameWithoutTenantFromFullUsername(request.getUsername());
                        if (UserProperty.SUPER_USER_NAME.equalsIgnoreCase(username)) {
                            UserProperty superUserProperty = userProperty;
                            long tenantId = UserProperty.getTenantIdFromFullUserName(request.getUsername());
                            // cascade opertion only have affect on tenants
                            if (tenantId > 0) {
                                List<UserProperty> usersInTenant = userMetadata.getUserListForTenant(tenantId);
                                ImmutableSet<PrivilegeType> superuserTablePrivileges = superUserProperty.getTablePrivileges().get(request.getDbOrTableName());
                                for (UserProperty normalUserProperty : usersInTenant) {
                                    UserProperty.Builder normalUserPropertyBuilder = new UserProperty.Builder(normalUserProperty);
                                    ImmutableSet<PrivilegeType> tablePrivileges = normalUserProperty.getTablePrivileges().get(request.getDbOrTableName());
                                    boolean isChanged = false;
                                    // revoke normal user's table privilege
                                    for (PrivilegeType tablePrivilege : tablePrivileges) {
                                        if (tablePrivilege.equals(PrivilegeType.READ_WRITE) 
                                                && (superuserTablePrivileges == null || !superuserTablePrivileges.contains(PrivilegeType.READ_WRITE))) {
                                            normalUserPropertyBuilder.revokeTablePrivilege(request.getDbOrTableName(), PrivilegeType.READ_WRITE);
                                            isChanged = true;
                                        } else if (tablePrivilege.equals(PrivilegeType.READ_ONLY) 
                                                && (superuserTablePrivileges == null || superuserTablePrivileges.isEmpty())) {
                                            normalUserPropertyBuilder.revokeTablePrivilege(request.getDbOrTableName(), PrivilegeType.READ_ONLY);
                                            isChanged = true;
                                        }
                                    }
                                    if (isChanged) {
                                        userMetadataBuilder.addOrChangeUserProperty(normalUserProperty.getUsernameWithTenant(), normalUserPropertyBuilder.build());
                                    }
                                }
                            }
                        }
                    }
                }
                MetaData.Builder metaDataBuilder = MetaData.builder(metaData);
                metaDataBuilder.putUserMetadata(userMetadataBuilder);
                return ClusterState.builder(currentState).metaData(metaDataBuilder).build();
            }
        }
        );
    }

}