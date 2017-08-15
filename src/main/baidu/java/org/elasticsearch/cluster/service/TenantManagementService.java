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

package org.elasticsearch.cluster.service;

import java.util.HashMap;

import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.metadata.AllocatedNodeInfo;
import org.elasticsearch.cluster.metadata.AllocatedNodeStatus;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.metadata.MetaData;
import org.elasticsearch.cluster.metadata.TenantMetadata;
import org.elasticsearch.cluster.metadata.TenantProperty;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.cluster.routing.RoutingNode;
import org.elasticsearch.cluster.routing.RoutingNodes;
import org.elasticsearch.cluster.routing.ShardRouting;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.threadpool.ThreadPool;

import com.carrotsearch.hppc.cursors.ObjectCursor;


public class TenantManagementService extends AbstractLifecycleComponent<TenantManagementService> {
    
    private final ClusterService clusterService;
    private final ThreadPool threadPool;
    private final int decommissionCheckIntervalSecs = 60;
    private boolean isStopped = false;

    @Inject
    public TenantManagementService(Settings settings, ClusterService clusterService, ThreadPool threadPool) {
        super(settings);
        this.clusterService = clusterService;
        this.threadPool = threadPool;
    }
    
    @Override
    public void doStart() {
        threadPool.schedule(TimeValue.timeValueSeconds(decommissionCheckIntervalSecs), ThreadPool.Names.SAME, new TenantNodeChecker());
    }
    
    private class TenantNodeChecker implements Runnable {

        @Override
        public void run() {
            if (!clusterService.state().nodes().localNodeMaster()) {
                if (!isStopped) {
                    threadPool.schedule(TimeValue.timeValueSeconds(2 * decommissionCheckIntervalSecs), ThreadPool.Names.SAME, new TenantNodeChecker());
                }
                return;
            }
            logger.info("begin to check decommission nodes");
            clusterService.submitStateUpdateTask("check decomission nodes", new ClusterStateUpdateTask(Priority.NORMAL) {

                @Override
                public boolean runOnlyOnMaster() {
                    return true;
                }
                
                @Override
                public void onFailure(String source, Throwable t) {
                    logger.error("errors while check decommission status", t, source);
                    if (!isStopped) {
                        threadPool.schedule(TimeValue.timeValueSeconds(decommissionCheckIntervalSecs), ThreadPool.Names.SAME, new TenantNodeChecker());
                    }
                }
                
                @Override
                public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
                    if (!isStopped) {
                        threadPool.schedule(TimeValue.timeValueSeconds(decommissionCheckIntervalSecs), ThreadPool.Names.SAME, new TenantNodeChecker());
                    }
                }
                
                @Override
                public ClusterState execute(ClusterState currentState) throws Exception {
                    HashMap<Long, TenantProperty.Builder> tenantsChanged = new HashMap<Long, TenantProperty.Builder>();
                    TenantMetadata tenantMetadata = currentState.metaData().tenantMetadata();
                    RoutingNodes routingNodes = new RoutingNodes(currentState, true);
                    for (RoutingNode routingNode : routingNodes) {
                        AllocatedNodeInfo nodeInfo = new AllocatedNodeInfo(routingNode.node());
                        TenantProperty tenantProperty = tenantMetadata.getNodeTenant(nodeInfo);
                        if (tenantProperty == null) {
                            // this means this node is not allocated to any tenant
                            continue;
                        }
                        AllocatedNodeInfo nodeInfoInTenant = tenantProperty.getAllocatedNodeInfo(nodeInfo.getAllocatedNodeId());
                        if (nodeInfoInTenant.getAllocatedNodeStatus().equals(AllocatedNodeStatus.DECOMMISSIONING)) {
                            boolean foundIndex = false;
                            for (ShardRouting shardRouting : routingNode) {
                                IndexMetaData indexMetaData = currentState.metaData().getIndices().get(shardRouting.getIndex());
                                if (indexMetaData.indexOwnerTenantId() == tenantProperty.tenantId()) {
                                    foundIndex = true;
                                }
                            }
                            if (!foundIndex) {
                                if (!tenantsChanged.containsKey(tenantProperty.tenantId())) {
                                    tenantsChanged.put(tenantProperty.tenantId(), new TenantProperty.Builder(tenantProperty));
                                }
                                TenantProperty.Builder builder = tenantsChanged.get(tenantProperty.tenantId());
                                builder.removeNode(nodeInfoInTenant); 
                                logger.info("node [{}] has no shards, remove it from tenant [{}]", nodeInfoInTenant, tenantProperty.tenantName());
                            }
                        }
                        
                    }
                    HashMap<String, DiscoveryNode> allNodes = new HashMap<>();
                    for (ObjectCursor<DiscoveryNode> discoveryNode : currentState.nodes().getNodes().values()) {
                        allNodes.put(discoveryNode.value.getIpPortAddress(), discoveryNode.value);
                    }
                    for (TenantProperty tenantProperty : tenantMetadata.tenantProperties().values()) {
                        for (AllocatedNodeInfo nodeInfo : tenantProperty.nodes().values()) {
                            // if node is not in decomission status, then check if it is dead or not data node any more
                            if (!nodeInfo.getAllocatedNodeStatus().equals(AllocatedNodeStatus.DECOMMISSIONING)) {
                                if (allNodes.containsKey(nodeInfo.getIpPortAddress())) {
                                    DiscoveryNode discoveryNodeInNodeList = allNodes.get(nodeInfo.getIpPortAddress());
                                    if (!discoveryNodeInNodeList.dataNode()) {
                                        if (!tenantsChanged.containsKey(tenantProperty.tenantId())) {
                                            tenantsChanged.put(tenantProperty.tenantId(), new TenantProperty.Builder(tenantProperty));
                                        }
                                        TenantProperty.Builder builder = tenantsChanged.get(tenantProperty.tenantId());
                                        AllocatedNodeInfo newNodeInfo = new AllocatedNodeInfo(nodeInfo.getIpAddress(), nodeInfo.getPort(), AllocatedNodeStatus.NOT_DATA_NODE);
                                        builder.addNode(newNodeInfo);
                                        logger.info("node [{}] in tenant [{}] is not a data node any more, set its status to [NOT_DATA_NODE]", nodeInfo, tenantProperty.tenantName());
                                    } else {
                                        if (!nodeInfo.getAllocatedNodeStatus().equals(AllocatedNodeStatus.NORMAL)) {
                                            if (!tenantsChanged.containsKey(tenantProperty.tenantId())) {
                                                tenantsChanged.put(tenantProperty.tenantId(), new TenantProperty.Builder(tenantProperty));
                                            }
                                            TenantProperty.Builder builder = tenantsChanged.get(tenantProperty.tenantId());
                                            AllocatedNodeInfo newNodeInfo = new AllocatedNodeInfo(nodeInfo.getIpAddress(), nodeInfo.getPort(), AllocatedNodeStatus.NORMAL);
                                            builder.addNode(newNodeInfo);
                                            logger.info("node [{}] in tenant [{}] current status is [{}], set it to [NORMAL]", nodeInfo, tenantProperty.tenantName(), nodeInfo.getAllocatedNodeStatus());
                                        }
                                    }
                                } else {
                                    if (!tenantsChanged.containsKey(tenantProperty.tenantId())) {
                                        tenantsChanged.put(tenantProperty.tenantId(), new TenantProperty.Builder(tenantProperty));
                                    }
                                    TenantProperty.Builder builder = tenantsChanged.get(tenantProperty.tenantId());
                                    AllocatedNodeInfo newNodeInfo = new AllocatedNodeInfo(nodeInfo.getIpAddress(), nodeInfo.getPort(), AllocatedNodeStatus.DEAD);
                                    builder.addNode(newNodeInfo);
                                    logger.info("node [{}] in tenant [{}] could not find in node list, set its status to [DEAD]", nodeInfo, tenantProperty.tenantName());
                                }
                            }
                        }
                    }
                    if (tenantsChanged.size() > 0) {
                        logger.info("find [{}] tenants have node status changed, rebuild cluster state", tenantsChanged.size());
                        TenantMetadata.Builder tenantMetadataBuilder = new TenantMetadata.Builder(tenantMetadata);
                        for (TenantProperty.Builder tenantBuilder : tenantsChanged.values()) {
                            tenantMetadataBuilder.addOrChangeTenantProperty(tenantBuilder);
                        }
                        ClusterState.Builder stateBuilder = new ClusterState.Builder(currentState);
                        MetaData.Builder metaDataBuilder = new MetaData.Builder(currentState.metaData());
                        metaDataBuilder.tenantMetadata(tenantMetadataBuilder);
                        stateBuilder.metaData(metaDataBuilder);
                        return stateBuilder.build();
                    } else {
                        return currentState;
                    }
                }
            });
        }
    }

    @Override
    protected void doStop() {
        isStopped = true;
    }

    @Override
    protected void doClose() {
        isStopped = true;
    }
}
