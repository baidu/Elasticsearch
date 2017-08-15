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

package org.elasticsearch.action.admin.cluster.tenant.alter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.master.TransportMasterNodeAction;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.metadata.AllocatedNodeStatus;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.metadata.MetaData;
import org.elasticsearch.cluster.metadata.AllocatedNodeInfo;
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

public class TransportAlterTenantPropertyAction extends TransportMasterNodeAction<AlterTenantPropertyRequest, AlterTenantPropertyResponse> {
    
    @Inject
    public TransportAlterTenantPropertyAction(Settings settings, TransportService transportService, ClusterService clusterService,
                                      ThreadPool threadPool, ActionFilters actionFilters,
                                      IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, AlterTenantPropertyAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, AlterTenantPropertyRequest.class);
    }
    
    @Override
    protected String executor() {
        return ThreadPool.Names.GENERIC;
    }

    @Override
    protected AlterTenantPropertyResponse newResponse() {
        return new AlterTenantPropertyResponse();
    }

    @Override
    protected void masterOperation(final AlterTenantPropertyRequest request,
            final ClusterState state, final ActionListener<AlterTenantPropertyResponse> listener)
            throws Exception {

        clusterService.submitStateUpdateTask("admin_alter_exist_tenant " + request.tenantName(), new ClusterStateUpdateTask(Priority.NORMAL) {

            @Override
            public boolean runOnlyOnMaster() {
                return true;
            }
            
            @Override
            public ClusterState execute(ClusterState currentState)
                    throws Exception {
                TenantMetadata tenantMetadata = currentState.metaData().tenantMetadata();
                long tenantId = tenantMetadata.tenantId(request.tenantName());
                TenantProperty existTenantProperty = tenantMetadata.getTenantProperty(tenantId);
                MetaData.Builder metadataBuilder = new MetaData.Builder(currentState.metaData());
                // if means user want to change node num
                if (request.nodeNum() >= 0) {
                    TenantMetadata.Builder tenantMetadataBuilder = new TenantMetadata.Builder(tenantMetadata);
                    // compare actual allocated instance num not desire num
                    if (request.nodeNum() > existTenantProperty.getNoneDecomissionNodesNum()) {
                        int numNodesToAdd = request.nodeNum() - existTenantProperty.getNoneDecomissionNodesNum();
                        // allocate node to this tenant
                        List<DiscoveryNode> nodeList = new ArrayList<>();
                        for (ObjectCursor<DiscoveryNode> activeNode : currentState.nodes().dataNodes().values()) {
                            nodeList.add(activeNode.value);
                        }
                        Collections.shuffle(nodeList);
                        List<DiscoveryNode> allocatedNodeList = new ArrayList<>();
                        // first change all decomission nodes to normal
                        TenantProperty.Builder existTenantBuilder = new TenantProperty.Builder(existTenantProperty);
                        for (AllocatedNodeInfo nodeInfo : existTenantProperty.nodes().values()) {
                            if (numNodesToAdd <= 0) break;
                            if (nodeInfo.getAllocatedNodeStatus().equals(AllocatedNodeStatus.DECOMMISSIONING)) {
                                AllocatedNodeInfo newNodeInfo = new AllocatedNodeInfo(nodeInfo.getIpAddress(), nodeInfo.getPort(), AllocatedNodeStatus.NORMAL);
                                existTenantBuilder.addNode(newNodeInfo);
                                --numNodesToAdd;
                            }
                        }
                        for (int i = 0; i < nodeList.size() && allocatedNodeList.size() < numNodesToAdd; ++i) {
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
                                for (AllocatedNodeInfo nodeIdTag : existTenantProperty.nodes().values()) {
                                    if (nodeIdTag.sameHost(node)) {
                                        isValidNode = false;
                                        break;
                                    }
                                }
                            } else {
                                isValidNode = false;
                            }
                            if (!node.isDataNode()) {
                                isValidNode = false;
                            }
                            if (isValidNode) {
                                allocatedNodeList.add(node);
                            }
                        }
                        // allocated not is less than required node, throw exception
                        if (numNodesToAdd > 0 && allocatedNodeList.size() < numNodesToAdd) {
                            throw new Exception("current allocated node num [" + existTenantProperty.actualInstanceNum() + "], user's desire num is ["+ request.nodeNum() + "], so that needs to find [" + numNodesToAdd + "] nodes, but only find [" + allocatedNodeList.size() + "] nodes.");
                        }
                        // create a new tenant in metadata
                        for (int i = 0; i < allocatedNodeList.size(); ++i) {
                            existTenantBuilder.addNode(AllocatedNodeInfo.getNodeGroupFromNode(allocatedNodeList.get(i)));
                        }
                        existTenantBuilder.instanceNum(request.nodeNum());
                        tenantMetadataBuilder.addOrChangeTenantProperty(existTenantBuilder);
                    } else {
                        int numNodesToDelete = existTenantProperty.getNoneDecomissionNodesNum() - request.nodeNum();
                        ArrayList<AllocatedNodeInfo> allNodes = new ArrayList<>(existTenantProperty.nodes().values());
                        Collections.shuffle(allNodes);
                        TenantProperty.Builder existTenantBuilder = new TenantProperty.Builder(existTenantProperty);
                        for (int i = 0; i < allNodes.size(); ++i) {
                            if (numNodesToDelete <= 0) {
                                break;
                            }
                            AllocatedNodeInfo tempNodeInfo = allNodes.get(i);
                            if (!tempNodeInfo.getAllocatedNodeStatus().equals(AllocatedNodeStatus.DECOMMISSIONING)) {
                                AllocatedNodeInfo newNodeInfo = new AllocatedNodeInfo(tempNodeInfo.getIpAddress(), tempNodeInfo.getPort(), AllocatedNodeStatus.DECOMMISSIONING);
                                existTenantBuilder.addNode(newNodeInfo);
                                -- numNodesToDelete;
                            }
                        }
                        existTenantBuilder.instanceNum(request.nodeNum());
                        tenantMetadataBuilder.addOrChangeTenantProperty(existTenantBuilder);
                    }
                    metadataBuilder.tenantMetadata(tenantMetadataBuilder);
                }
                
                if (!Strings.isNullOrEmpty(request.password())) {
                    UserMetadata userMetadata = currentState.metaData().getUserMetadata();
                    UserMetadata.Builder userMetadataBuilder = new UserMetadata.Builder(userMetadata);
                    String superuserEncryptPass =
                            new String(MysqlPassword.makeScrambledPassword(request.password()));
                    UserProperty superuserProperty = userMetadata.getUserProperty(UserProperty.SUPER_USER_NAME, tenantId);
                    UserProperty.Builder superUserPropertyBuilder =
                            new UserProperty.Builder(superuserProperty);
                    superUserPropertyBuilder.resetPassword(superuserEncryptPass);
                    UserProperty superUserProperty = superUserPropertyBuilder.build();
                    userMetadataBuilder.addOrChangeUserProperty(superUserProperty.getUsernameWithTenant(), superUserProperty);
                    metadataBuilder.userMetadata(userMetadataBuilder.build());
                }
                return ClusterState.builder(currentState).metaData(metadataBuilder).build();
            }

            @Override
            public void onFailure(String source, Throwable t) {
                listener.onFailure(t);
            }
            
            @Override
            public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
                listener.onResponse(new AlterTenantPropertyResponse(true));
            }
        });
        return;
    }

    @Override
    protected ClusterBlockException checkBlock(AlterTenantPropertyRequest request,
            ClusterState state) {
        return null;
    }

}
