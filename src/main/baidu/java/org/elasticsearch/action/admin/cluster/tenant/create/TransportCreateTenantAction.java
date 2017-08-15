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

package org.elasticsearch.action.admin.cluster.tenant.create;

import io.crate.metadata.information.InformationSchemaInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.master.TransportMasterNodeAction;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.metadata.MetaData;
import org.elasticsearch.cluster.metadata.AllocatedNodeInfo;
import org.elasticsearch.cluster.metadata.PrivilegeType;
import org.elasticsearch.cluster.metadata.TenantMetadata;
import org.elasticsearch.cluster.metadata.TenantProperty;
import org.elasticsearch.cluster.metadata.UserMetadata;
import org.elasticsearch.cluster.metadata.UserProperty;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.mysql.MysqlPassword;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

import com.carrotsearch.hppc.cursors.ObjectCursor;
import com.google.common.base.Strings;

public class TransportCreateTenantAction extends TransportMasterNodeAction<CreateTenantRequest, CreateTenantResponse> {
    
    private static final String TENANT_PATTERN = "^\\w{1,15}$";
    
    @Inject
    public TransportCreateTenantAction(Settings settings, TransportService transportService, ClusterService clusterService,
                                      ThreadPool threadPool, ActionFilters actionFilters,
                                      IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, CreateTenantAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, CreateTenantRequest.class);
    }
    
    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected CreateTenantResponse newResponse() {
        return new CreateTenantResponse();
    }

    @Override
    protected void masterOperation(final CreateTenantRequest request,
            final ClusterState state, final ActionListener<CreateTenantResponse> listener)
            throws Exception {
        
        // check tenant name length and pattern
        if (!Pattern.matches(TENANT_PATTERN, request.tenantName())) {
            throw new ElasticsearchException("tenant name should only contain a-z 0-9 and _ , the length should between 1 and 15");
        }
        
        clusterService.submitStateUpdateTask("admin_add_new_tenant " + request.tenantName(), new ClusterStateUpdateTask(Priority.NORMAL) {

            private long allocatedTenantId = -1;
            @Override
            public boolean runOnlyOnMaster() {
                return true;
            }
            
            @Override
            public ClusterState execute(ClusterState currentState)
                    throws Exception {
                TenantMetadata tenantMetadata = currentState.metaData().tenantMetadata();
                if (tenantMetadata.tenantExist(request.tenantName())) {
                    throw new ElasticsearchException("tenant with name " + request.tenantName() + " already exist");
                }
                boolean userSpecifyNodes = false;
                if (!Strings.isNullOrEmpty(request.instanceList() == null ? "" : request.instanceList().trim())) {
                    if (request.nodeNum() > 0) {
                        throw new ElasticsearchException("could not specify both instance list and instance num when creating a cluster");
                    }
                    userSpecifyNodes = true;
                } else {
                    if (request.nodeNum() < 1) {
                        throw new ElasticsearchException("must specify instance list or set instance num > 1 when creating a cluster");
                    }
                }
                
                // allocate node to this tenant
                List<DiscoveryNode> nodeList = new ArrayList<>();
                Map<AllocatedNodeInfo, DiscoveryNode> nodeMap = new HashMap<AllocatedNodeInfo, DiscoveryNode>();
                for (ObjectCursor<DiscoveryNode> activeNode : currentState.nodes().dataNodes().values()) {
                    nodeList.add(activeNode.value);
                    nodeMap.put(new AllocatedNodeInfo(activeNode.value), activeNode.value);
                }
                Collections.shuffle(nodeList);
                List<DiscoveryNode> allocatedNodeList = new ArrayList<>();
                if (!userSpecifyNodes) {
                    for (int i = 0; i < nodeList.size() && allocatedNodeList.size() < request.nodeNum(); ++i) {
                        DiscoveryNode node = nodeList.get(i);
                        boolean isValidNode = true;
                        if (tenantMetadata.getNodeTenant(node) == null) {
                            // should not allocate on same host
                            for (int j = 0; j < allocatedNodeList.size(); ++j) {
                                if (allocatedNodeList.get(j).getAddress().sameHost(node.getAddress())) {
                                    isValidNode = false;
                                    break;
                                }
                            }
                        } else {
                            isValidNode = false;
                        }
                        if (isValidNode) {
                            allocatedNodeList.add(node);
                        }
                    }
                    // allocated num is less than required num, throw exception
                    if (allocatedNodeList.size() < request.nodeNum()) {
                        throw new Exception("only find [" + allocatedNodeList.size() + "] nodes, could not meet required [" + request.nodeNum() + "].");
                    }
                } else {
                    String[] userSpecifiedNodes = request.instanceList().trim().split(",");
                    for (String nodeInfo : userSpecifiedNodes) {
                        String[] ipPort = nodeInfo.split(":");
                        AllocatedNodeInfo allocatedNodeInfo = new AllocatedNodeInfo(ipPort[0].trim(), Integer.parseInt(ipPort[1]));
                        DiscoveryNode discoveryNode = nodeMap.get(allocatedNodeInfo);
                        if (discoveryNode == null) {
                            throw new ElasticsearchException("could not find node " + nodeInfo + " in active data nodes list");
                        }
                        if (tenantMetadata.getNodeTenant(discoveryNode) != null) {
                            throw new ElasticsearchException("node [" + nodeInfo 
                                    + "] already allocated to tenant [" 
                                    + tenantMetadata.getNodeTenant(discoveryNode).tenantName() + "]");
                        }
                        // should not allocate on same host
                        for (int j = 0; j < allocatedNodeList.size(); ++j) {
                            if (allocatedNodeList.get(j).getAddress().sameHost(discoveryNode.getAddress())) {
                                throw new ElasticsearchException("node [" + nodeInfo + "] is the host with another node [" + allocatedNodeList.get(j).getAddress() + "]");
                            }
                        }
                        allocatedNodeList.add(discoveryNode);
                    }
                }
                // create a new tenant in metadata
                TenantProperty.Builder tenantBuilder = new TenantProperty.Builder();
                tenantBuilder.tenantName(request.tenantName());
                allocatedTenantId = tenantMetadata.maxTenantId() + 1;
                tenantBuilder.tenantId(allocatedTenantId);
                tenantBuilder.instanceNum(allocatedNodeList.size());
                for (int i = 0; i < allocatedNodeList.size(); ++i) {
                    tenantBuilder.addNode(AllocatedNodeInfo.getNodeGroupFromNode(allocatedNodeList.get(i)));
                }
                TenantMetadata.Builder tenantMetadataBuilder = new TenantMetadata.Builder(tenantMetadata);
                tenantMetadataBuilder.maxTenantId(allocatedTenantId);
                tenantMetadataBuilder.addOrChangeTenantProperty(tenantBuilder.build());
                
                // add a default superuser to tenant
                UserMetadata.Builder userMetadataBuilder = new UserMetadata.Builder(currentState.metaData().getUserMetadata());
                String superuserEncryptPass =
                        new String(MysqlPassword.makeScrambledPassword(Strings.isNullOrEmpty(request.password()) ? UserProperty.DEFAULT_SUPERUSER_PASSWD : request.password()));
                String superuserName = UserProperty.generateUsernameWithTenant(UserProperty.SUPER_USER_NAME, allocatedTenantId);
                UserProperty.Builder superUserPropertyBuilder =
                        new UserProperty.Builder(superuserName, superuserEncryptPass);
                superUserPropertyBuilder.addIpToWhiteList("*");
                superUserPropertyBuilder.grantDBPrivilege(InformationSchemaInfo.NAME, PrivilegeType.READ_ONLY);
                UserProperty superUserProperty = superUserPropertyBuilder.build();
                userMetadataBuilder.addOrChangeUserProperty(superuserName, superUserProperty);
                
                MetaData.Builder metadataBuilder = new MetaData.Builder(currentState.metaData());
                metadataBuilder.tenantMetadata(tenantMetadataBuilder);
                metadataBuilder.userMetadata(userMetadataBuilder.build());
                return ClusterState.builder(currentState).metaData(metadataBuilder).build();
            }

            @Override
            public void onFailure(String source, Throwable t) {
                listener.onFailure(t);
            }
            
            @Override
            public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
                listener.onResponse(new CreateTenantResponse(allocatedTenantId, true));
            }
        });
        return;
    }

    @Override
    protected ClusterBlockException checkBlock(CreateTenantRequest request,
            ClusterState state) {
        return null;
    }

}
