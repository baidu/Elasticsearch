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

package org.elasticsearch.action.admin.cluster.lease;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.master.TransportMasterNodeReadAction;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.routing.IndexRoutingTable;
import org.elasticsearch.common.collect.Tuple;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.discovery.dl.ClusterStateOpLog;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 */
public class TransportGetOrChangePrimaryShardLeaseAction extends TransportMasterNodeReadAction<GetOrChangePrimaryShardLeaseRequest, GetOrChangePrimaryShardLeaseResponse> {
    
    private Map<SimpleShardIdentifier, Tuple<String, Long>> shardOwnerTracker;
    
    @Inject
    public TransportGetOrChangePrimaryShardLeaseAction(Settings settings, TransportService transportService, ClusterService clusterService,
                                              ThreadPool threadPool, ActionFilters actionFilters, IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, GetOrChangePrimaryShardLeaseAction.NAME, transportService, clusterService, threadPool, actionFilters, indexNameExpressionResolver, GetOrChangePrimaryShardLeaseRequest.class);
        this.shardOwnerTracker = new ConcurrentHashMap<SimpleShardIdentifier, Tuple<String, Long>>();
    }

    @Override
    protected String executor() {
        return ThreadPool.Names.SAME;
    }

    @Override
    protected GetOrChangePrimaryShardLeaseResponse newResponse() {
        return new GetOrChangePrimaryShardLeaseResponse();
    }

    @Override
    protected void masterOperation(final GetOrChangePrimaryShardLeaseRequest request, final ClusterState state, final ActionListener<GetOrChangePrimaryShardLeaseResponse> listener) {
        try {
            List<SimpleShardIdentifier> shardIdentifiers = request.getShardIdentifiers();
            String requestNodeId = request.getNodeId();
            GetOrChangePrimaryShardLeaseResponse response = new GetOrChangePrimaryShardLeaseResponse(shardIdentifiers);
            if (ClusterStateOpLog.getInstance() != null && ClusterStateOpLog.getInstance().localNodeMaster()) {
                long masterLeaseVersion = ClusterStateOpLog.getInstance().masterLeaseVersion();
                if (request.isGetRequest()) {
                    for (SimpleShardIdentifier shardIdentifier : shardIdentifiers) {
                        Tuple<String, Long> leaseInfo = shardOwnerTracker.get(shardIdentifier);
                        if (leaseInfo != null && leaseInfo.v1().equalsIgnoreCase(requestNodeId) && masterLeaseVersion == leaseInfo.v2()) {
                            response.setShardStatus(shardIdentifier);
                        }
                    }
                } else {
                    for (SimpleShardIdentifier shardIdentifier : shardIdentifiers) {
                        IndexMetaData indexMetaData = state.metaData().index(shardIdentifier.getIndexName());
                        if (indexMetaData == null || !indexMetaData.isSameUUID(shardIdentifier.getIndexUUID())) {
                            continue;
                        }
                        IndexRoutingTable indexRoutingTable = state.routingTable().getIndicesRouting().get(shardIdentifier.getIndexName());
                        if (indexRoutingTable != null) {
                            String currentNodeId = indexRoutingTable.getShards().get(shardIdentifier.getShardId()).primaryShard().currentNodeId();
                            if (currentNodeId != null && currentNodeId.equalsIgnoreCase(requestNodeId)) {
                                shardOwnerTracker.put(shardIdentifier, new Tuple<String, Long>(requestNodeId, masterLeaseVersion));
                            }
                        }
                    }
                }
            } else if (ClusterStateOpLog.getInstance() == null && clusterService.state().nodes().localNodeMaster()) {
                // if master election service is null, it means dl master election is not enabled
                // then lease is a little weak lease implementation
                if (request.isGetRequest()) {
                    for (SimpleShardIdentifier shardIdentifier : shardIdentifiers) {
                        Tuple<String, Long> leaseInfo = shardOwnerTracker.get(shardIdentifier);
                        if (leaseInfo != null && leaseInfo.v1().equalsIgnoreCase(requestNodeId)) {
                            response.setShardStatus(shardIdentifier);
                        }
                    }
                } else {
                    for (SimpleShardIdentifier shardIdentifier : shardIdentifiers) {
                        IndexMetaData indexMetaData = state.metaData().index(shardIdentifier.getIndexName());
                        if (indexMetaData == null || !indexMetaData.isSameUUID(shardIdentifier.getIndexUUID())) {
                            continue;
                        }
                        IndexRoutingTable indexRoutingTable = state.routingTable().getIndicesRouting().get(shardIdentifier.getIndexName());
                        if (indexRoutingTable != null) {
                            String currentNodeId = indexRoutingTable.getShards().get(shardIdentifier.getShardId()).primaryShard().currentNodeId();
                            if (currentNodeId != null && currentNodeId.equalsIgnoreCase(requestNodeId)) {
                                shardOwnerTracker.put(shardIdentifier, new Tuple<String, Long>(requestNodeId, 0L));
                            }
                        }
                    }
                }
            } else {
                // local node is not the master leader node
                shardOwnerTracker.clear();
            }
            listener.onResponse(response);
        } catch (Throwable t) {
            listener.onFailure(t);
        }
    }

    @Override
    protected ClusterBlockException checkBlock(
            GetOrChangePrimaryShardLeaseRequest request, ClusterState state) {
        return null;
    }
}
