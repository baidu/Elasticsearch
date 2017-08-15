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

package org.elasticsearch.index.shard;

import org.elasticsearch.action.admin.cluster.lease.TransportGetOrChangePrimaryShardLeaseAction;
import org.elasticsearch.action.admin.indices.shard.TransportIndexShardStatsAction;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.util.BigArrays;
import org.elasticsearch.index.IndexService;
import org.elasticsearch.index.aliases.IndexAliasesService;
import org.elasticsearch.index.cache.IndexCache;
import org.elasticsearch.index.codec.CodecService;
import org.elasticsearch.index.deletionpolicy.SnapshotDeletionPolicy;
import org.elasticsearch.index.engine.DLBasedEngine;
import org.elasticsearch.index.engine.Engine;
import org.elasticsearch.index.engine.EngineConfig;
import org.elasticsearch.index.engine.EngineFactory;
import org.elasticsearch.index.engine.IndexSearcherWrappingService;
import org.elasticsearch.index.fielddata.IndexFieldDataService;
import org.elasticsearch.index.mapper.MapperService;
import org.elasticsearch.index.percolator.stats.ShardPercolateService;
import org.elasticsearch.index.query.IndexQueryParserService;
import org.elasticsearch.index.settings.IndexSettingsService;
import org.elasticsearch.index.similarity.SimilarityService;
import org.elasticsearch.index.store.Store;
import org.elasticsearch.index.termvectors.ShardTermVectorsService;
import org.elasticsearch.index.translog.Translog;
import org.elasticsearch.index.translog.TranslogStats;
import org.elasticsearch.indices.IndicesLifecycle;
import org.elasticsearch.indices.IndicesWarmer;
import org.elasticsearch.indices.cache.query.IndicesQueryCache;
import org.elasticsearch.indices.memory.IndexingMemoryController;
import org.elasticsearch.search.SearchService;
import org.elasticsearch.threadpool.ThreadPool;

import com.google.common.collect.ImmutableSet;

public class DLBasedIndexShard extends IndexShard {

    private static final ImmutableSet<String> SOURCE_TO_VALID = ImmutableSet.<String>builder()
            .add("count-operation")
            .add("search")
            .add("shared-shard-context")
            .add("get")
            .build();
    private String localNodeId;
    private TransportGetOrChangePrimaryShardLeaseAction checkLeaseAction;
    private TransportIndexShardStatsAction indexShardStatsAction;
    
    @Inject
    public DLBasedIndexShard(ShardId shardId,
            IndexSettingsService indexSettingsService,
            IndicesLifecycle indicesLifecycle, Store store,
            StoreRecoveryService storeRecoveryService, ThreadPool threadPool,
            MapperService mapperService,
            IndexQueryParserService queryParserService, IndexCache indexCache,
            IndexAliasesService indexAliasesService,
            IndicesQueryCache indicesQueryCache,
            ShardPercolateService shardPercolateService,
            CodecService codecService,
            ShardTermVectorsService termVectorsService,
            IndexFieldDataService indexFieldDataService,
            IndexService indexService, IndicesWarmer warmer,
            SnapshotDeletionPolicy deletionPolicy,
            SimilarityService similarityService, EngineFactory factory,
            ClusterService clusterService, ShardPath path, BigArrays bigArrays,
            IndexSearcherWrappingService wrappingService,
            IndexingMemoryController indexingMemoryController,
            TransportGetOrChangePrimaryShardLeaseAction checkLeaseAction,
            TransportIndexShardStatsAction indexShardStatsAction,
            SearchService shardSearchService) {
        super(shardId, indexSettingsService, indicesLifecycle, store,
                storeRecoveryService, threadPool, mapperService, queryParserService,
                indexCache, indexAliasesService, indicesQueryCache,
                shardPercolateService, codecService, termVectorsService,
                indexFieldDataService, indexService, warmer, deletionPolicy,
                similarityService, factory, clusterService, path, bigArrays,
                wrappingService, indexingMemoryController, shardSearchService);
        this.localNodeId = clusterService.state().nodes().localNodeId();
        this.checkLeaseAction = checkLeaseAction;
        this.indexShardStatsAction = indexShardStatsAction;
    }
    
    /**
     * this method is useless now, since we will skip translog recovery from remote node
     */
    public int performBatchRecovery(Iterable<Translog.Operation> operations) {
        if (state != IndexShardState.RECOVERING) {
            throw new IndexShardNotRecoveringException(shardId, state);
        }

        // This will activate our shard so we get our fair share of the indexing buffer during recovery:
        markLastWrite();
        return 0;
    }
    
    protected Engine newEngine(boolean skipTranslogRecovery, EngineConfig config) {
        return engineFactory.newDLBasedEngine(config, skipTranslogRecovery, localNodeId, checkLeaseAction, indexShardStatsAction, this);
    }
    
    @Override
    public long getTranslogOffset() {
        DLBasedEngine dlBasedEngine = (DLBasedEngine)engine();
        return dlBasedEngine.getLatestTxid();
    }
    
    @Override
    public void finalizeRecovery() {
        super.finalizeRecovery();
        DLBasedEngine dlBasedEngine = (DLBasedEngine)engine();
        dlBasedEngine.onTranslogRecoveryFinished();
    }
    /**
     * it is called by upper actions to ensure data is persisted into translog
     * currently it is useless
     */
    public void sync(Translog.Location location) {
        return;
    }
    
    public TranslogStats translogStats() {
        return new TranslogStats(1, 1);
    }
    
    public void readAllowed(String source) throws IllegalIndexShardStateException {
        IndexShardState state = this.state; // one time volatile read
        if (readAllowedStates.contains(state) == false) {
            throw new IllegalIndexShardStateException(shardId, state, "operations only allowed when shard state is one of " +
                    readAllowedStates.toString() + "");
        }
        // only some of the source should valid lease
        if (SOURCE_TO_VALID.contains(source)) {
            if (engine().readAllowed() == false) {
                throw new IllegalIndexShardStateException(shardId, state, "shard maybe not have primary lease");
            }
        }
    }
    
    public DLStats dlStats() {
        DLBasedEngine dlBasedEngine = (DLBasedEngine)engine();
        return new DLStats(dlBasedEngine.getLatestTxid(), dlBasedEngine.getLatestDLSN(), 
                dlBasedEngine.getCommitTxid(), dlBasedEngine.getCommitDLSN());
    }
}
