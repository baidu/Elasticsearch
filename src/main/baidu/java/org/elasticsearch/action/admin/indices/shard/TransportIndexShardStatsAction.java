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

package org.elasticsearch.action.admin.indices.shard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ShardOperationFailedException;
import org.elasticsearch.action.admin.indices.stats.CommonStats;
import org.elasticsearch.action.admin.indices.stats.CommonStatsFlags;
import org.elasticsearch.action.admin.indices.stats.ShardStats;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.broadcast.node.TransportBroadcastByNodeAction;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.routing.IndexRoutingTable;
import org.elasticsearch.cluster.routing.IndexShardRoutingTable;
import org.elasticsearch.cluster.routing.PlainShardsIterator;
import org.elasticsearch.cluster.routing.ShardRouting;
import org.elasticsearch.cluster.routing.ShardsIterator;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.IndexService;
import org.elasticsearch.index.shard.IndexShard;
import org.elasticsearch.index.shard.IndexShardState;
import org.elasticsearch.index.shard.ShardNotFoundException;
import org.elasticsearch.indices.IndicesService;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

public class TransportIndexShardStatsAction extends TransportBroadcastByNodeAction<IndexShardStatsRequest, IndexShardStatsResponse, ShardStats> {

    private final IndicesService indicesService;

    @Inject
    public TransportIndexShardStatsAction(Settings settings, ThreadPool threadPool, ClusterService clusterService,
                                       TransportService transportService, IndicesService indicesService,
                                       ActionFilters actionFilters, IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings, IndexShardStatsAction.NAME, threadPool, clusterService, transportService, actionFilters, indexNameExpressionResolver,
                IndexShardStatsRequest.class, ThreadPool.Names.MANAGEMENT);
        this.indicesService = indicesService;
    }

    /**
     * Status goes across *all* shards.
     */
    @Override
    protected ShardsIterator shards(ClusterState clusterState, IndexShardStatsRequest request, String[] concreteIndices) {
        List<ShardRouting> shards = new ArrayList<>();
        IndexRoutingTable indexRoutingTable = clusterState.routingTable().index(request.getIndexName());
        if (indexRoutingTable != null) {
            for (IndexShardRoutingTable indexShardRoutingTable : indexRoutingTable) {
                if (indexShardRoutingTable.getShardId().id() == request.getShardId() && indexShardRoutingTable.allShardsStarted()) {
                    for (ShardRouting shardRouting : indexShardRoutingTable) {
                        shards.add(shardRouting);
                    }
                }
            }
        }
        return new PlainShardsIterator(shards);
    }

    @Override
    protected ClusterBlockException checkGlobalBlock(ClusterState state, IndexShardStatsRequest request) {
        return null;
    }

    @Override
    protected ClusterBlockException checkRequestBlock(ClusterState state, IndexShardStatsRequest request, String[] concreteIndices) {
        return null;
    }

    @Override
    protected ShardStats readShardResult(StreamInput in) throws IOException {
        return ShardStats.readShardStats(in);
    }

    @Override
    protected IndexShardStatsResponse newResponse(IndexShardStatsRequest request, int totalShards, int successfulShards, int failedShards, List<ShardStats> responses, List<ShardOperationFailedException> shardFailures, ClusterState clusterState) {
        return new IndexShardStatsResponse(responses.toArray(new ShardStats[responses.size()]), totalShards, successfulShards, failedShards, shardFailures);
    }

    @Override
    protected IndexShardStatsRequest readRequestFrom(StreamInput in) throws IOException {
        IndexShardStatsRequest request = new IndexShardStatsRequest();
        request.readFrom(in);
        return request;
    }

    @Override
    protected ShardStats shardOperation(IndexShardStatsRequest request, ShardRouting shardRouting) {
        IndexService indexService = indicesService.indexServiceSafe(shardRouting.shardId().getIndex());
        IndexShard indexShard = indexService.shardSafe(shardRouting.shardId().id());
        // if we don't have the routing entry yet, we need it stats wise, we treat it as if the shard is not ready yet
        if (indexShard.routingEntry() == null) {
            throw new ShardNotFoundException(indexShard.shardId());
        }

        if (!indexShard.state().equals(IndexShardState.STARTED)) {
            throw new ElasticsearchException(indexShard.shardId().toString() + " state is " + indexShard.state() + ", not started");
        }
        
        CommonStatsFlags flags = new CommonStatsFlags().clear();

        if (request.dl()) {
            flags.set(CommonStatsFlags.Flag.DL);
        }

        return new ShardStats(indexShard.routingEntry(), indexShard.shardPath(), new CommonStats(indexShard, flags), indexShard.commitStats());
    }
}
