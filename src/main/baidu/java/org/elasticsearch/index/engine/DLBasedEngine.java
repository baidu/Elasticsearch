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

package org.elasticsearch.index.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.lucene.index.AbortingException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexCallBack;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.LeafReader;
import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.index.LiveIndexWriterConfig;
import org.apache.lucene.index.MergePolicy;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.index.SegmentCommitInfo;
import org.apache.lucene.index.SegmentInfos;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriter.IndexReaderWarmer;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.SearcherFactory;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.store.AlreadyClosedException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.IOUtils;
import org.apache.lucene.util.InfoStream;
import org.elasticsearch.ExceptionsHelper;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.cluster.lease.GetOrChangePrimaryShardLeaseRequest;
import org.elasticsearch.action.admin.cluster.lease.GetOrChangePrimaryShardLeaseResponse;
import org.elasticsearch.action.admin.cluster.lease.TransportGetOrChangePrimaryShardLeaseAction;
import org.elasticsearch.action.admin.indices.shard.IndexShardStatsRequest;
import org.elasticsearch.action.admin.indices.shard.IndexShardStatsResponse;
import org.elasticsearch.action.admin.indices.shard.TransportIndexShardStatsAction;
import org.elasticsearch.action.admin.indices.stats.CommonStats;
import org.elasticsearch.action.admin.indices.stats.ShardStats;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.routing.DjbHashFunction;
import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.collect.Tuple;
import org.elasticsearch.common.lease.Releasable;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.lucene.LoggerInfoStream;
import org.elasticsearch.common.lucene.Lucene;
import org.elasticsearch.common.lucene.index.ElasticsearchDirectoryReader;
import org.elasticsearch.common.lucene.index.ElasticsearchLeafReader;
import org.elasticsearch.common.lucene.uid.Versions;
import org.elasticsearch.common.math.MathUtils;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.util.concurrent.AbstractRunnable;
import org.elasticsearch.common.util.concurrent.EsRejectedExecutionException;
import org.elasticsearch.common.util.concurrent.ReleasableLock;
import org.elasticsearch.index.deletionpolicy.SnapshotIndexCommit;
import org.elasticsearch.index.indexing.ShardIndexingService;
import org.elasticsearch.index.mapper.Uid;
import org.elasticsearch.index.merge.MergeStats;
import org.elasticsearch.index.merge.OnGoingMerge;
import org.elasticsearch.index.shard.DLStats;
import org.elasticsearch.index.shard.ElasticsearchMergePolicy;
import org.elasticsearch.index.shard.IndexShard;
import org.elasticsearch.index.shard.MergeSchedulerConfig;
import org.elasticsearch.index.shard.ShardId;
import org.elasticsearch.index.shard.TranslogRecoveryPerformer;
import org.elasticsearch.index.translog.Translog;
import org.elasticsearch.index.translog.TranslogCorruptedException;
import org.elasticsearch.index.translog.dl.DistributedTranslog;
import org.elasticsearch.indices.IndicesWarmer;
import org.elasticsearch.threadpool.ThreadPool;
import org.apache.distributedlog.DLSN;
import org.apache.distributedlog.LogRecordWithDLSN;
import org.apache.distributedlog.util.FutureUtils;

import com.twitter.util.Duration;
import com.twitter.util.Future;
import com.twitter.util.FutureEventListener;

public class DLBasedEngine extends Engine {

    /**
     * When we last pruned expired tombstones from versionMap.deletes:
     */
    private volatile long lastDeleteVersionPruneTimeMSec;
    private final ShardIndexingService indexingService;
    @Nullable
    private final IndicesWarmer warmer;
    private DistributedTranslog dlTranslog;
    private final ElasticsearchConcurrentMergeScheduler mergeScheduler;
    private IndexWriter indexWriter;
    private SearcherFactory searcherFactory;
    private SearcherManager searcherManager;
    private final Lock flushLock = new ReentrantLock();
    private final ReentrantLock optimizeLock = new ReentrantLock();
    private final Lock resourceLock = new ReentrantLock();
    private final LiveVersionMap versionMap;
    private final Object[] dirtyLocks;
    private final AtomicBoolean versionMapRefreshPending = new AtomicBoolean();
    private volatile SegmentInfos lastCommittedSegmentInfos;
    private final IndexThrottle throttle;
    private ThreadPool threadPool;
    private TransportGetOrChangePrimaryShardLeaseAction checkLeaseAction;
    private TransportIndexShardStatsAction indexShardStatsAction;
    private IndexShard indexShard;
    
    private boolean isPrimaryRecoverFinished;
    private boolean isPrimaryInRouting;
    private DLSN latestDlsn;
    private AtomicLong latestTxid;
    private DLSN commitDlsn;
    private long commitTxid;
    private ReplayDLOperationTask recoverFromDLTask;
    private long leaseExpireTime;
    private long lastLeaseSendTime;
    private String localNodeId;
    private long dlWriteTimeoutMillis;
    private boolean isSnapshotRecoveryFinished;
    private CheckLeaseTask checkLeaseTask;
    
    public DLBasedEngine(EngineConfig engineConfig, boolean skipInitialTranslogRecovery, String nodeId, 
            TransportGetOrChangePrimaryShardLeaseAction checkLeaseAction, 
            TransportIndexShardStatsAction indexShardStatsAction, IndexShard indexShard) {
        super(engineConfig);
        this.dlWriteTimeoutMillis = 6000;
        this.indexShard = indexShard;
        this.isSnapshotRecoveryFinished = false;
        this.logger.debug("begin to created new DLBasedEngine");
        this.isPrimaryRecoverFinished = false;
        this.isPrimaryInRouting = false;
        this.lastLeaseSendTime = 0;
        this.localNodeId = nodeId;
        this.recoverFromDLTask = new ReplayDLOperationTask();
        this.leaseExpireTime = engineConfig.getIndexSettings().getAsInt("index.primary_lease_expire_seconds", 3) * 1000;
        this.checkLeaseAction = checkLeaseAction;
        this.indexShardStatsAction = indexShardStatsAction;
        this.latestDlsn = DLSN.InitialDLSN;
        this.latestTxid = new AtomicLong(0);
        this.commitDlsn = DLSN.InitialDLSN;
        this.commitTxid = 0L;
        this.versionMap = new LiveVersionMap();
        this.threadPool = engineConfig.getThreadPool();
        this.store.incRef();
        IndexWriter writer = null;
        Translog translog = null;
        SearcherManager manager = null;
        EngineMergeScheduler scheduler = null;
        boolean success = false;
        try {
            this.lastDeleteVersionPruneTimeMSec = engineConfig.getThreadPool().estimatedTimeInMillis();
            this.indexingService = engineConfig.getIndexingService();
            this.warmer = engineConfig.getWarmer();
            this.mergeScheduler = scheduler = new EngineMergeScheduler(engineConfig.getShardId(), engineConfig.getIndexSettings(), engineConfig.getMergeSchedulerConfig());
            this.dirtyLocks = new Object[Runtime.getRuntime().availableProcessors() * 10]; // we multiply it to have enough...
            for (int i = 0; i < this.dirtyLocks.length; i++) {
                this.dirtyLocks[i] = new Object();
            }
            boolean isCreateIndex = engineConfig.isCreate();
            if (isCreateIndex) {
                isPrimaryInRouting = true;
                isSnapshotRecoveryFinished = true;
            }
            try {
                writer = createWriter(isCreateIndex);
                this.indexWriter = writer;
                Map<String, String> commitData = this.indexWriter.getCommitData();
                this.commitDlsn = commitData.containsKey(Engine.COMMIT_DLSN) ? DLSN.deserialize(commitData.get(Engine.COMMIT_DLSN)) : DLSN.InitialDLSN;
                this.commitTxid = commitData.containsKey(Engine.COMMIT_TXID) ? Long.parseLong(commitData.get(Engine.COMMIT_TXID)) : 0;
                // create means
                //     1. for primary, it is force allocated to this node using allocate api or first created when create index
                //     2. for replica, it is always false
                // ignore translog status
                //     1. for primary, recovery from snapshot
                //     2. for replica it is always false
                if (isCreateIndex || engineConfig.getIngoreTranslogStatus()) {
                    // if this is a create shard request, then use commit to create segment files
                    // commit will also write latest dlsn and txid to commit file
                    // snapshot recovery will set ingore translog status to true, then also rewrite dlsn and txid data
                    commitIndexWriter();
                }
                // it means this is primary recovery
                if (!skipInitialTranslogRecovery || isCreateIndex || engineConfig.getIngoreTranslogStatus()) {
                    isSnapshotRecoveryFinished = true;
                }
                this.dlTranslog = new DistributedTranslog(engineConfig.getTranslogConfig(), localNodeId);
            } catch (IOException | TranslogCorruptedException e) {
                throw new EngineCreationFailureException(shardId, "failed to create engine", e);
            } catch (AssertionError e) {
                // IndexWriter throws AssertionError on init, if asserts are enabled, if any files don't exist, but tests that
                // randomly throw FNFE/NSFE can also hit this:
                if (ExceptionsHelper.stackTrace(e).contains("org.apache.lucene.index.IndexWriter.filesExist")) {
                    throw new EngineCreationFailureException(shardId, "failed to create engine", e);
                } else {
                    throw e;
                }
            }
            
            Map<String, String> commitUserData = this.indexWriter.getCommitData();
            this.latestDlsn = commitUserData.containsKey(Engine.COMMIT_DLSN) ? DLSN.deserialize(commitUserData.get(Engine.COMMIT_DLSN)) : DLSN.InitialDLSN;
            this.latestTxid.set(commitUserData.containsKey(Engine.COMMIT_TXID) ? Long.parseLong(commitUserData.get(Engine.COMMIT_TXID)) : 0);

            // open search manager after recovery
            this.throttle = new IndexThrottle();
            this.searcherFactory = new SearchFactory(this.logger, this.isClosed, engineConfig);
            manager = createSearcherManager();
            this.searcherManager = manager;
            this.versionMap.setManager(searcherManager);
            checkLeaseTask = new CheckLeaseTask(isCreateIndex);
            threadPool.schedule(TimeValue.timeValueMillis(0), ThreadPool.Names.GENERIC, checkLeaseTask);
            threadPool.schedule(TimeValue.timeValueMillis(0), ThreadPool.Names.GENERIC, new TruncateOldLog());
            success = true;
        } finally {
            if (success == false) {
                IOUtils.closeWhileHandlingException(writer, translog, manager, scheduler);
                this.versionMap.clear();
                if (this.isClosed.get() == false) {
                    // failure we need to dec the store reference
                    this.store.decRef();
                }
            } else {
                logger.info("engine started");
            }
        }
    }
    
    @Override
    public boolean readAllowed() {
        if (!isPrimaryInRouting) {
            throw new IllegalStateException("[" + localNodeId + "] " + shardId.toString() + "shard is not marked as primary shard in routing state");
        }
        
        if (!isPrimaryRecoverFinished) {
            throw new IllegalStateException("[" + localNodeId + "] " + shardId.toString() + "shard have not recovered as primary");
        }
        
        if (System.currentTimeMillis() - lastLeaseSendTime > leaseExpireTime) {
            throw new IllegalStateException("[" + localNodeId + "] " + shardId.toString() + "shard maybe not have primary lease");
        }
        return true;
    }
    
    public void onTranslogRecoveryFinished() {
        logger.debug("begin to start recovery from translog, current dlsn is [{}]", latestDlsn);
        this.isSnapshotRecoveryFinished = true;
    }
    
    public long getLatestTxid() {
        return this.latestTxid.get();
    }
    
    public DLSN getLatestDLSN() {
        return this.latestDlsn;
    }
    
    public long getCommitTxid() {
        return this.commitTxid;
    }
    
    public DLSN getCommitDLSN() {
        return this.commitDlsn;
    }
    
    public void setIsPrimaryInRouting(boolean isPrimary) {
        this.isPrimaryInRouting = isPrimary;
    }

    private void applyTranslogOperation(Translog.Operation operation) {
        indexShard.markLastWrite();
        final TranslogRecoveryPerformer handler = engineConfig.getTranslogRecoveryPerformer();
        handler.performRecoveryOperation(this, operation, true);
    }
    
    private SearcherManager createSearcherManager() throws EngineException {
        boolean success = false;
        SearcherManager searcherManager = null;
        try {
            try {
                final DirectoryReader directoryReader = ElasticsearchDirectoryReader.wrap(DirectoryReader.open(indexWriter, true), shardId);
                searcherManager = new SearcherManager(directoryReader, searcherFactory);
                lastCommittedSegmentInfos = readLastCommittedSegmentInfos(searcherManager, store);
                success = true;
                return searcherManager;
            } catch (IOException e) {
                maybeFailEngine("start", e);
                try {
                    indexWriter.rollback();
                } catch (IOException e1) { // iw is closed below
                    e.addSuppressed(e1);
                }
                throw new EngineCreationFailureException(shardId, "failed to open reader on writer", e);
            }
        } finally {
            if (success == false) { // release everything we created on a failure
                IOUtils.closeWhileHandlingException(searcherManager, indexWriter);
            }
        }
    }

    private void updateIndexWriterSettings() {
        try {
            final LiveIndexWriterConfig iwc = indexWriter.getConfig();
            iwc.setRAMBufferSizeMB(engineConfig.getIndexingBufferSize().mbFrac());
            iwc.setUseCompoundFile(engineConfig.isCompoundOnFlush());
        } catch (AlreadyClosedException ex) {
            // ignore
        }
    }

    @Override
    public GetResult get(Get get) throws EngineException {
        try (ReleasableLock lock = readLock.acquire()) {
            ensureOpen();
            if (get.realtime()) {
                VersionValue versionValue = versionMap.getUnderLock(get.uid().bytes());
                if (versionValue != null) {
                    if (versionValue.delete()) {
                        return GetResult.NOT_EXISTS;
                    }
                    if (get.versionType().isVersionConflictForReads(versionValue.version(), get.version())) {
                        Uid uid = Uid.createUid(get.uid().text());
                        throw new VersionConflictEngineException(shardId, uid.type(), uid.id(), versionValue.version(), get.version());
                    }
                    if (dlTranslog != null) {
                        Translog.Operation op = dlTranslog.readFromLocal(versionValue.translogLocation());
                        if (op != null) {
                            return new GetResult(true, versionValue.version(), op.getSource());
                        }
                    }
                }
            }
            return getFromSearcher(get);
        }
    }
    
    private void checkEngineStatus(Operation operation) {
        ensureOpen();
        if (operation.origin() == Operation.Origin.PRIMARY) { 
            if(!isPrimaryRecoverFinished) {
                throw new EngineException(shardId, "maybe the primary engine is in recovery");
            }
        } else if (operation.origin() != Operation.Origin.RECOVERY) {
            throw new EngineException(shardId, String.format("only primary origin and recovery origin is supported, current [%s]", 
                    operation.origin()));
        }
    }
 
    @Override
    public void create(Create create) throws EngineException {
        try (ReleasableLock lock = readLock.acquire()) {
            checkEngineStatus(create);
            if (create.origin() == Operation.Origin.RECOVERY) {
                // Don't throttle recovery operations
                innerCreate(create);
            } else {
                readAllowed();
                try (Releasable r = throttle.acquireThrottle()) {
                    innerCreate(create);
                }
            }
        } catch (OutOfMemoryError | IllegalStateException | IOException t) {
            maybeFailEngine("create", t);
            throw new CreateFailedEngineException(shardId, create.type(), create.id(), t);
        }
        checkVersionMapRefresh();
    }

    private void innerCreate(Create create) throws IOException {
        
        if (engineConfig.isOptimizeAutoGenerateId() && create.autoGeneratedId() && !create.canHaveDuplicates()) {
            innerCreateNoLock(create, Versions.NOT_FOUND, null);
        } else {
            synchronized (dirtyLock(create.uid())) {
                final long currentVersion;
                final VersionValue versionValue;
                versionValue = versionMap.getUnderLock(create.uid().bytes());
                if (versionValue == null) {
                    currentVersion = loadCurrentVersionFromIndex(create.uid());
                } else {
                    if (versionValue.delete() && (
                            create.origin() == Operation.Origin.RECOVERY 
                            || engineConfig.isEnableGcDeletes() && (engineConfig.getThreadPool().estimatedTimeInMillis() - versionValue.time()) > engineConfig.getGcDeletesInMillis())) {
                        currentVersion = Versions.NOT_FOUND; // deleted, and GC
                    } else {
                        currentVersion = versionValue.version();
                    }
                }
                innerCreateNoLock(create, currentVersion, versionValue);
            }
        }
    }
    
    private void innerCreateNoLock(Create create, long currentVersion, VersionValue versionValue) throws IOException {
        
            long updatedVersion;
            long expectedVersion = create.version();
            if (create.versionType().isVersionConflictForWrites(currentVersion, expectedVersion)) {
                // recovery means reindex or replay from dl
                // replay from dl: it never happens, because primary will check it
                // reindex: means user insert a new record, but reindex never go here
                if (create.origin() == Operation.Origin.RECOVERY) {
                    logger.info("create origin recovery but meet create conflicts, should not happern {} {} {}", create.versionType(), currentVersion, expectedVersion);
                    return;
                } else {
                    throw new VersionConflictEngineException(shardId, create.type(), create.id(), currentVersion, expectedVersion);
                }
            }
            
            updatedVersion = create.versionType().updateVersion(currentVersion, expectedVersion);
            boolean doUpdate = false;
            if (create.origin() == Operation.Origin.RECOVERY) {
                updatedVersion = create.version();
                if (versionValue != null || currentVersion != Versions.NOT_FOUND) {
                    doUpdate = true;
                }
            } else if (create.origin() == Operation.Origin.PRIMARY) {
                if ((versionValue != null && versionValue.delete() == false) || (versionValue == null && currentVersion != Versions.NOT_FOUND)) {
                    if (create.autoGeneratedId() && create.canHaveDuplicates() && currentVersion == 1 && create.version() == Versions.MATCH_ANY) {
                        /**
                         * If bulk index request fails due to a disconnect, unavailable shard etc. then the request is
                         * retried before it actually fails. However, the documents might already be indexed.
                         * For autogenerated ids this means that a version conflict will be reported in the bulk request
                         * although the document was indexed properly.
                         * To avoid this we have to make sure that the index request is treated as an update and set updatedVersion to 1.
                         * See also discussion on https://github.com/elasticsearch/elasticsearch/pull/9125
                         */
                        doUpdate = true;
                        updatedVersion = 1;
                    } else {
                        throw new DocumentAlreadyExistsException(shardId, create.type(), create.id());
                    }
                }
            }
    
            create.updateVersion(updatedVersion);
    
            // If it is a write request from primary, then should write the log first
            WriteDistributedLogCallBack callBack = null;
            if (create.origin() == Operation.Origin.PRIMARY) {
                callBack = new WriteDistributedLogCallBack(new Translog.Create(create));
            }
            if (doUpdate) {
                if (create.docs().size() > 1) {
                    indexWriter.updateDocuments(create.uid(), create.docs(), callBack);
                } else {
                    indexWriter.updateDocument(create.uid(), create.docs().get(0), callBack);
                }
            } else {
                if (create.docs().size() > 1) {
                    indexWriter.addDocuments(create.docs(), callBack);
                } else {
                    indexWriter.addDocument(create.docs().get(0), callBack);
                }
            }
            if (callBack != null) {
                versionMap.putUnderLock(create.uid().bytes(), new VersionValue(updatedVersion, callBack.location));
                create.setTranslogLocation(callBack.location);
            } else {
                versionMap.putUnderLock(create.uid().bytes(), new VersionValue(updatedVersion, null));
            }
            indexingService.postCreateUnderLock(create);
    }

    @Override
    public boolean index(Index index) throws EngineException {
        final boolean created;
        try (ReleasableLock lock = readLock.acquire()) {
            checkEngineStatus(index);
            if (index.origin() == Operation.Origin.RECOVERY) {
                // Don't throttle recovery operations
                created = innerIndex(index);
            } else {
                readAllowed();
                try (Releasable r = throttle.acquireThrottle()) {
                    created = innerIndex(index);
                }
            }
        } catch (OutOfMemoryError | IllegalStateException | IOException t) {
            maybeFailEngine("index", t);
            throw new IndexFailedEngineException(shardId, index.type(), index.id(), t);
        }
        checkVersionMapRefresh();
        return created;
    }

    /**
     * Forces a refresh if the versionMap is using too much RAM
     */
    private void checkVersionMapRefresh() {
        if (versionMap.ramBytesUsedForRefresh() > config().getVersionMapSize().bytes() && versionMapRefreshPending.getAndSet(true) == false) {
            try {
                if (isClosed.get()) {
                    // no point...
                    return;
                }
                // Now refresh to clear versionMap:
                engineConfig.getThreadPool().executor(ThreadPool.Names.REFRESH).execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            refresh("version_table_full");
                        } catch (EngineClosedException ex) {
                            // ignore
                        }
                    }
                });
            } catch (EsRejectedExecutionException ex) {
                // that is fine too.. we might be shutting down
            }
        }
    }

    private boolean innerIndex(Index index) throws IOException {
        synchronized (dirtyLock(index.uid())) {
            final long currentVersion;
            VersionValue versionValue = versionMap.getUnderLock(index.uid().bytes());
            if (versionValue == null) {
                currentVersion = loadCurrentVersionFromIndex(index.uid());
            } else {
                if (versionValue.delete() && (
                        index.origin() == Operation.Origin.RECOVERY || 
                        engineConfig.isEnableGcDeletes() && (engineConfig.getThreadPool().estimatedTimeInMillis() - versionValue.time()) > engineConfig.getGcDeletesInMillis())) {
                    currentVersion = Versions.NOT_FOUND; // deleted, and GC
                } else {
                    currentVersion = versionValue.version();
                }
            }

            long expectedVersion = index.version();
            long updatedVersion;
            // If it is a write request from primary, then should write the log first
            WriteDistributedLogCallBack callBack = null;
            if (index.origin() == Operation.Origin.PRIMARY  && !index.reindex()) {
                if (index.versionType().isVersionConflictForWrites(currentVersion, expectedVersion)) {
                    throw new VersionConflictEngineException(shardId, index.type(), index.id(), currentVersion, expectedVersion);
                }
                updatedVersion = index.versionType().updateVersion(currentVersion, expectedVersion);
                index.updateVersion(updatedVersion);
                callBack = new WriteDistributedLogCallBack(new Translog.Index(index));
            } else {
                // for reindex request, the expected version has to equal to current version
                if (index.reindex()) {
                    if (currentVersion != expectedVersion) {
                        VersionConflictEngineException exception = new VersionConflictEngineException(shardId, index.type(), index.id(), currentVersion, expectedVersion);
                        logger.info("reindex meet version conflict, maybe user deleted or updated the doc", exception);
                        throw exception;
                    }
                }
                updatedVersion = index.version();
                index.updateVersion(updatedVersion); // has to update version here
            }

            final boolean created;
            if (currentVersion == Versions.NOT_FOUND) {
                created = true;
                if (index.docs().size() > 1) {
                    indexWriter.addDocuments(index.docs(), callBack);
                } else {
                    indexWriter.addDocument(index.docs().get(0), callBack);
                }
            } else {
                if (versionValue != null) {
                    created = versionValue.delete(); // we have a delete which is not GC'ed...
                } else {
                    created = false;
                }
                if (index.docs().size() > 1) {
                    indexWriter.updateDocuments(index.uid(), index.docs(), callBack);
                } else {
                    indexWriter.updateDocument(index.uid(), index.docs().get(0), callBack);
                }
            }
            if (callBack != null) {
                versionMap.putUnderLock(index.uid().bytes(), new VersionValue(updatedVersion, callBack.location));
                index.setTranslogLocation(callBack.location);
            } else {
                versionMap.putUnderLock(index.uid().bytes(), new VersionValue(updatedVersion, null));
            }
            indexingService.postIndexUnderLock(index);
            return created;
        }
    }

    @Override
    public void delete(Delete delete) throws EngineException {
        try (ReleasableLock lock = readLock.acquire()) {
            checkEngineStatus(delete);
            synchronized (dirtyLock(delete.uid())) {
                final long currentVersion;
                VersionValue versionValue = versionMap.getUnderLock(delete.uid().bytes());
                if (versionValue == null) {
                    currentVersion = loadCurrentVersionFromIndex(delete.uid());
                } else {
                    if (versionValue.delete() && (
                            delete.origin() == Operation.Origin.RECOVERY || 
                            engineConfig.isEnableGcDeletes() && (engineConfig.getThreadPool().estimatedTimeInMillis() - versionValue.time()) > engineConfig.getGcDeletesInMillis())) {
                        currentVersion = Versions.NOT_FOUND; // deleted, and GC
                    } else {
                        currentVersion = versionValue.version();
                    }
                }
                WriteDistributedLogCallBack callBack = null;
                long updatedVersion;
                long expectedVersion = delete.version();
                if (delete.origin() == Operation.Origin.PRIMARY) {
                    readAllowed();
                    if (delete.versionType().isVersionConflictForWrites(currentVersion, expectedVersion)) {
                        throw new VersionConflictEngineException(shardId, delete.type(), delete.id(), currentVersion, expectedVersion);
                    }
                    updatedVersion = delete.versionType().updateVersion(currentVersion, expectedVersion);
                } else {
                    updatedVersion = delete.version();
                }
                final boolean found;
                if (currentVersion == Versions.NOT_FOUND) {
                    // doc does not exist and no prior deletes
                    found = false;
                } else if (versionValue != null && versionValue.delete()) {
                    // a "delete on delete", in this case, we still increment the version, log it, and return that version
                    found = false;
                } else {
                    // we deleted a currently existing document
                    if (delete.origin() == Operation.Origin.PRIMARY) {
                        callBack = new WriteDistributedLogCallBack(new Translog.Delete(delete));
                    }
                    indexWriter.deleteDocuments(callBack, delete.uid());
                    found = true;
                }
                delete.updateVersion(updatedVersion, found);
                if (callBack != null) {
                    versionMap.putUnderLock(delete.uid().bytes(), new DeleteVersionValue(updatedVersion, engineConfig.getThreadPool().estimatedTimeInMillis(), callBack.location));
                    delete.setTranslogLocation(callBack.location);
                } else {
                    versionMap.putUnderLock(delete.uid().bytes(), new DeleteVersionValue(updatedVersion, engineConfig.getThreadPool().estimatedTimeInMillis(), null));
                }
                indexingService.postDeleteUnderLock(delete);
            }
        
        } catch (OutOfMemoryError | IllegalStateException | IOException t) {
            maybeFailEngine("delete", t);
            throw new DeleteFailedEngineException(shardId, delete, t);
        }

        maybePruneDeletedTombstones();
        checkVersionMapRefresh();
    }

    private void maybePruneDeletedTombstones() {
        // It's expensive to prune because we walk the deletes map acquiring dirtyLock for each uid so we only do it
        // every 1/4 of gcDeletesInMillis:
        if (engineConfig.isEnableGcDeletes() && engineConfig.getThreadPool().estimatedTimeInMillis() - lastDeleteVersionPruneTimeMSec > engineConfig.getGcDeletesInMillis() * 0.25) {
            pruneDeletedTombstones();
        }
    }

    @Override
    public void delete(DeleteByQuery delete) throws EngineException {
        throw new EngineException(shardId, "delete by query is not supported");
    }

    @Override
    public void refresh(String source) throws EngineException {
        // we obtain a read lock here, since we don't want a flush to happen while we are refreshing
        // since it flushes the index as well (though, in terms of concurrency, we are allowed to do it)
        logger.info("begin to refresh due to [{}]", source);
        try (ReleasableLock lock = readLock.acquire()) {
            ensureOpen();
            if (dlTranslog != null) {
                long currentGen = dlTranslog.getLocalTranslogGeneration();
                searcherManager.maybeRefreshBlocking();
                dlTranslog.deleteLogBeforeGeneration(currentGen);
            } else {
                searcherManager.maybeRefreshBlocking();
            }
        } catch (AlreadyClosedException e) {
            ensureOpen();
            maybeFailEngine("refresh", e);
            logger.error("errors while refresh index", e);
        } catch (EngineClosedException e) {
            throw e;
        } catch (Throwable t) {
            failEngine("refresh failed", t);
            throw new RefreshFailedEngineException(shardId, t);
        }

        // TODO: maybe we should just put a scheduled job in threadPool?
        // We check for pruning in each delete request, but we also prune here e.g. in case a delete burst comes in and then no more deletes
        // for a long time:
        maybePruneDeletedTombstones();
        versionMapRefreshPending.set(false);
        mergeScheduler.refreshConfig();
    }

    @Override
    public SyncedFlushResult syncFlush(String syncId, CommitId expectedCommitId) throws EngineException {
        // syncFlush is used to optimise recovery process, it is useless when using dlsn
        // see https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-synced-flush.html
        return SyncedFlushResult.SUCCESS; 
    }

    private void commitIndexWriter() throws IOException {
        try {
            Map<String, String> commitData = new HashMap<>(2);
            // when commit, lucene will call flushAllThreads first, so dlsn and txid may be a litter smaller than 
            // data stored
            commitData.put(Engine.COMMIT_DLSN, latestDlsn.serialize());
            commitData.put(Engine.COMMIT_TXID, Long.toString(latestTxid.get()));
            indexWriter.setCommitData(commitData);
            indexWriter.commit();
            logger.trace("finished commit for flush");
        } catch (Throwable ex) {
            failEngine("lucene commit failed", ex);
            throw ex;
        }    
    }
    @Override
    public CommitId flush() throws EngineException {
        return flush(false, false);
    }

    @Override
    public CommitId flush(boolean force, boolean waitIfOngoing) throws EngineException {
        ensureOpen();
        final byte[] newCommitId;
        /*
         * Unfortunately the lock order is important here. We have to acquire the readlock first otherwise
         * if we are flushing at the end of the recovery while holding the write lock we can deadlock if:
         *  Thread 1: flushes via API and gets the flush lock but blocks on the readlock since Thread 2 has the writeLock
         *  Thread 2: flushes at the end of the recovery holding the writeLock and blocks on the flushLock owned by Thread 1
         */
        try (ReleasableLock lock = readLock.acquire()) {
            ensureOpen();
            if (flushLock.tryLock() == false) {
                // if we can't get the lock right away we block if needed otherwise barf
                if (waitIfOngoing) {
                    logger.debug("waiting for in-flight flush to finish");
                    flushLock.lock();
                    logger.debug("acquired flush lock after blocking");
                } else {
                    throw new FlushNotAllowedEngineException(shardId, "already flushing...");
                }
            } else {
                logger.debug("acquired flush lock immediately");
            }
            try {
                if (indexWriter.hasUncommittedChanges() || force) {
                    try {
                        logger.debug("prepare commit");
                        dlTranslog.prepareCommit();
                        logger.debug("commit writer");
                        commitIndexWriter();
                        // we need to refresh in order to clear older version values
                        refresh("version_table_flush");
                        logger.debug("commit finished");
                        dlTranslog.commit();
                    } catch (Throwable e) {
                        throw new FlushFailedEngineException(shardId, e);
                    }
                }
                /*
                 * we have to inc-ref the store here since if the engine is closed by a tragic event
                 * we don't acquire the write lock and wait until we have exclusive access. This might also
                 * dec the store reference which can essentially close the store and unless we can inc the reference
                 * we can't use it.
                 */
                store.incRef();
                try {
                    // reread the last committed segment infos
                    lastCommittedSegmentInfos = store.readLastCommittedSegmentsInfo();
                    Map<String, String> commitData = indexWriter.getCommitData();
                    this.commitDlsn = commitData.containsKey(Engine.COMMIT_DLSN) ? DLSN.deserialize(commitData.get(Engine.COMMIT_DLSN)) : DLSN.InitialDLSN;
                    this.commitTxid = commitData.containsKey(Engine.COMMIT_TXID) ? Long.parseLong(commitData.get(Engine.COMMIT_TXID)) : 0;
                } catch (Throwable e) {
                    logger.warn("errors while commit data", e);
                    if (isClosed.get() == false) {
                        logger.warn("failed to read latest segment infos on flush", e);
                        if (Lucene.isCorruptionException(e)) {
                            throw new FlushFailedEngineException(shardId, e);
                        }
                    }
                } finally {
                    store.decRef();
                }
                newCommitId = lastCommittedSegmentInfos.getId();
                if (newCommitId == null) {
                    logger.warn("commitid is null");
                }
            } catch (FlushFailedEngineException ex) {
                maybeFailEngine("flush", ex);
                throw ex;
            } finally {
                flushLock.unlock();
            }
        }
        // We don't have to do this here; we do it defensively to make sure that even if wall clock time is misbehaving
        // (e.g., moves backwards) we will at least still sometimes prune deleted tombstones:
        if (engineConfig.isEnableGcDeletes()) {
            pruneDeletedTombstones();
        }
        if (newCommitId == null) {
            logger.warn("commitid is null");
        }
        return new CommitId(newCommitId);
    }

    private void pruneDeletedTombstones() {
        long timeMSec = engineConfig.getThreadPool().estimatedTimeInMillis();

        // TODO: not good that we reach into LiveVersionMap here; can we move this inside VersionMap instead?  problem is the dirtyLock...
        // we only need to prune the deletes map; the current/old version maps are cleared on refresh:
        for (Map.Entry<BytesRef, VersionValue> entry : versionMap.getAllTombstones()) {
            BytesRef uid = entry.getKey();
            synchronized (dirtyLock(uid)) { // can we do it without this lock on each value? maybe batch to a set and get the lock once per set?

                // Must re-get it here, vs using entry.getValue(), in case the uid was indexed/deleted since we pulled the iterator:
                VersionValue versionValue = versionMap.getTombstoneUnderLock(uid);
                if (versionValue != null) {
                    if (timeMSec - versionValue.time() > engineConfig.getGcDeletesInMillis()) {
                        versionMap.removeTombstoneUnderLock(uid);
                    }
                }
            }
        }

        lastDeleteVersionPruneTimeMSec = timeMSec;
    }

    @Override
    public void forceMerge(final boolean flush, int maxNumSegments, boolean onlyExpungeDeletes,
                           final boolean upgrade, final boolean upgradeOnlyAncientSegments) throws EngineException, EngineClosedException, IOException {
        /*
         * We do NOT acquire the readlock here since we are waiting on the merges to finish
         * that's fine since the IW.rollback should stop all the threads and trigger an IOException
         * causing us to fail the forceMerge
         *
         * The way we implement upgrades is a bit hackish in the sense that we set an instance
         * variable and that this setting will thus apply to the next forced merge that will be run.
         * This is ok because (1) this is the only place we call forceMerge, (2) we have a single
         * thread for optimize, and the 'optimizeLock' guarding this code, and (3) ConcurrentMergeScheduler
         * syncs calls to findForcedMerges.
         */
        assert indexWriter.getConfig().getMergePolicy() instanceof ElasticsearchMergePolicy : "MergePolicy is " + indexWriter.getConfig().getMergePolicy().getClass().getName();
        ElasticsearchMergePolicy mp = (ElasticsearchMergePolicy) indexWriter.getConfig().getMergePolicy();
        optimizeLock.lock();
        try {
            ensureOpen();
            if (upgrade) {
                logger.info("starting segment upgrade upgradeOnlyAncientSegments={}", upgradeOnlyAncientSegments);
                mp.setUpgradeInProgress(true, upgradeOnlyAncientSegments);
            }
            store.incRef(); // increment the ref just to ensure nobody closes the store while we optimize
            try {
                if (onlyExpungeDeletes) {
                    assert upgrade == false;
                    indexWriter.forceMergeDeletes(true /* blocks and waits for merges*/);
                } else if (maxNumSegments <= 0) {
                    assert upgrade == false;
                    indexWriter.maybeMerge();
                } else {
                    indexWriter.forceMerge(maxNumSegments, true /* blocks and waits for merges*/);
                }
                if (flush) {
                    flush(true, true);
                }
                if (upgrade) {
                    logger.info("finished segment upgrade");
                }
            } finally {
                store.decRef();
            }
        } catch (Throwable t) {
            maybeFailEngine("force merge", t);
            throw t;
        } finally {
            try {
                mp.setUpgradeInProgress(false, false); // reset it just to make sure we reset it in a case of an error
            } finally {
                optimizeLock.unlock();
            }
        }
    }

    @Override
    public SnapshotIndexCommit snapshotIndex(final boolean flushFirst) throws EngineException {
        // we have to flush outside of the readlock otherwise we might have a problem upgrading
        // the to a write lock when we fail the engine in this operation
        if (flushFirst) {
            logger.trace("start flush for snapshot");
            flush(false, true);
            logger.trace("finish flush for snapshot");
        }
        try (ReleasableLock lock = readLock.acquire()) {
            ensureOpen();
            logger.trace("pulling snapshot");
            return deletionPolicy.snapshot();
        } catch (IOException e) {
            throw new SnapshotFailedEngineException(shardId, e);
        }
    }

    @Override
    protected boolean maybeFailEngine(String source, Throwable t) {
        boolean shouldFail = super.maybeFailEngine(source, t);
        if (shouldFail) {
            return true;
        }
        return false;
    }

    @Override
    protected SegmentInfos getLastCommittedSegmentInfos() {
        return lastCommittedSegmentInfos;
    }

    @Override
    protected final void writerSegmentStats(SegmentsStats stats) {
        stats.addVersionMapMemoryInBytes(versionMap.ramBytesUsed());
        stats.addIndexWriterMemoryInBytes(indexWriter.ramBytesUsed());
        stats.addIndexWriterMaxMemoryInBytes((long) (indexWriter.getConfig().getRAMBufferSizeMB() * 1024 * 1024));
    }

    @Override
    public long indexWriterRAMBytesUsed() {
        return indexWriter.ramBytesUsed();
    }

    @Override
    public List<Segment> segments(boolean verbose) {
        try (ReleasableLock lock = readLock.acquire()) {
            Segment[] segmentsArr = getSegmentInfo(lastCommittedSegmentInfos, verbose);

            // fill in the merges flag
            Set<OnGoingMerge> onGoingMerges = mergeScheduler.onGoingMerges();
            for (OnGoingMerge onGoingMerge : onGoingMerges) {
                for (SegmentCommitInfo segmentInfoPerCommit : onGoingMerge.getMergedSegments()) {
                    for (Segment segment : segmentsArr) {
                        if (segment.getName().equals(segmentInfoPerCommit.info.name)) {
                            segment.mergeId = onGoingMerge.getId();
                            break;
                        }
                    }
                }
            }
            return Arrays.asList(segmentsArr);
        }
    }

    /**
     * Closes the engine without acquiring the write lock. This should only be
     * called while the write lock is hold or in a disaster condition ie. if the engine
     * is failed.
     */
    @Override
    protected final void closeNoLock(String reason) {
        resourceLock.lock();
        try {
            if (isClosed.compareAndSet(false, true)) {
                assert rwl.isWriteLockedByCurrentThread() || failEngineLock.isHeldByCurrentThread() : "Either the write lock must be held or the engine must be currently be failing itself";
                try {
                    this.versionMap.clear();
                    try {
                        IOUtils.close(searcherManager);
                    } catch (Throwable t) {
                        logger.warn("Failed to close SearcherManager", t);
                    }
                    // if write lock is held, then we should not stop the recover thread here
                    // it will be a dead lock
                    if (!rwl.isWriteLockedByCurrentThread()) {
                        recoverFromDLTask.stop(true);
                    }
                    try {
                        IOUtils.close(dlTranslog);
                    } catch (Throwable t) {
                        logger.warn("Failed to close translog", t);
                    }
                    // no need to commit in this case!, we snapshot before we close the shard, so translog and all sync'ed
                    logger.trace("rollback indexWriter");
                    try {
                        indexWriter.rollback();
                    } catch (AlreadyClosedException e) {
                        // ignore
                    }
                    logger.trace("rollback indexWriter done");
                } catch (Throwable e) {
                    logger.warn("failed to rollback writer on close", e);
                } finally {
                    store.decRef();
                    logger.debug("engine closed [{}]", reason);
                }
            }
        } finally {
            resourceLock.unlock();
        }
    }
    
    protected void afterClose(String reason) {
        recoverFromDLTask.stop(true);
    }

    @Override
    public boolean hasUncommittedChanges() {
        return indexWriter.hasUncommittedChanges();
    }

    @Override
    protected SearcherManager getSearcherManager() {
        return searcherManager;
    }

    private Object dirtyLock(BytesRef uid) {
        int hash = DjbHashFunction.DJB_HASH(uid.bytes, uid.offset, uid.length);
        return dirtyLocks[MathUtils.mod(hash, dirtyLocks.length)];
    }

    private Object dirtyLock(Term uid) {
        return dirtyLock(uid.bytes());
    }

    private long loadCurrentVersionFromIndex(Term uid) throws IOException {
        try (final Searcher searcher = acquireSearcher("load_version", false)) {
            return Versions.loadVersion(searcher.reader(), uid);
        }
    }

    private IndexWriter createWriter(boolean create) throws IOException {
        try {
            final IndexWriterConfig iwc = new IndexWriterConfig(engineConfig.getAnalyzer());
            iwc.setCommitOnClose(false); // we by default don't commit on close
            iwc.setOpenMode(create ? IndexWriterConfig.OpenMode.CREATE : IndexWriterConfig.OpenMode.APPEND);
            iwc.setIndexDeletionPolicy(deletionPolicy);
            // with tests.verbose, lucene sets this up: plumb to align with filesystem stream
            boolean verbose = false;
            try {
                verbose = Boolean.parseBoolean(System.getProperty("tests.verbose"));
            } catch (Throwable ignore) {
            }
            iwc.setInfoStream(verbose ? InfoStream.getDefault() : new LoggerInfoStream(logger));
            iwc.setMergeScheduler(mergeScheduler);
            MergePolicy mergePolicy = config().getMergePolicy();
            // Give us the opportunity to upgrade old segments while performing
            // background merges
            mergePolicy = new ElasticsearchMergePolicy(mergePolicy);
            iwc.setMergePolicy(mergePolicy);
            iwc.setSimilarity(engineConfig.getSimilarity());
            iwc.setRAMBufferSizeMB(engineConfig.getIndexingBufferSize().mbFrac());
            iwc.setCodec(engineConfig.getCodec());
            /* We set this timeout to a highish value to work around
             * the default poll interval in the Lucene lock that is
             * 1000ms by default. We might need to poll multiple times
             * here but with 1s poll this is only executed twice at most
             * in combination with the default writelock timeout*/
            iwc.setWriteLockTimeout(5000);
            iwc.setUseCompoundFile(this.engineConfig.isCompoundOnFlush());
            // Warm-up hook for newly-merged segments. Warming up segments here is better since it will be performed at the end
            // of the merge operation and won't slow down _refresh
            iwc.setMergedSegmentWarmer(new IndexReaderWarmer() {
                @Override
                public void warm(LeafReader reader) throws IOException {
                    try {
                        LeafReader esLeafReader = new ElasticsearchLeafReader(reader, shardId);
                        assert isMergedSegment(esLeafReader);
                        if (warmer != null) {
                            final Engine.Searcher searcher = new Searcher("warmer", searcherFactory.newSearcher(esLeafReader, null));
                            final IndicesWarmer.WarmerContext context = new IndicesWarmer.WarmerContext(shardId, searcher);
                            warmer.warmNewReaders(context);
                        }
                    } catch (Throwable t) {
                        // Don't fail a merge if the warm-up failed
                        if (isClosed.get() == false) {
                            logger.warn("Warm-up failed", t);
                        }
                        if (t instanceof Error) {
                            // assertion/out-of-memory error, don't ignore those
                            throw (Error) t;
                        }
                    }
                }
            });
            return new IndexWriter(store.directory(), iwc);
        } catch (LockObtainFailedException ex) {
            boolean isLocked = IndexWriter.isLocked(store.directory());
            logger.warn("Could not lock IndexWriter isLocked [{}]", ex, isLocked);
            throw ex;
        }
    }
    
    public void activateThrottling() {
        throttle.activate();
    }

    public void deactivateThrottling() {
        throttle.deactivate();
    }

    long getGcDeletesInMillis() {
        return engineConfig.getGcDeletesInMillis();
    }

    LiveIndexWriterConfig getCurrentIndexWriterConfig() {
        return indexWriter.getConfig();
    }

    public void onSettingsChanged() {
        mergeScheduler.refreshConfig();
        updateIndexWriterSettings();
        // config().getVersionMapSize() may have changed:
        checkVersionMapRefresh();
        // config().isEnableGcDeletes() or config.getGcDeletesInMillis() may have changed:
        maybePruneDeletedTombstones();
    }

    public MergeStats getMergeStats() {
        return mergeScheduler.stats();
    }

    @Override
    public Translog getTranslog() {
        return dlTranslog;
    }
    
    /** Extended SearcherFactory that warms the segments if needed when acquiring a new searcher */
    final static class SearchFactory extends EngineSearcherFactory {
        private final IndicesWarmer warmer;
        private final ShardId shardId;
        private final ESLogger logger;
        private final AtomicBoolean isEngineClosed;

        SearchFactory(ESLogger logger, AtomicBoolean isEngineClosed, EngineConfig engineConfig) {
            super(engineConfig);
            warmer = engineConfig.getWarmer();
            shardId = engineConfig.getShardId();
            this.logger = logger;
            this.isEngineClosed = isEngineClosed;
        }

        @Override
        public IndexSearcher newSearcher(IndexReader reader, IndexReader previousReader) throws IOException {
            IndexSearcher searcher = super.newSearcher(reader, previousReader);
            if (reader instanceof LeafReader && isMergedSegment((LeafReader)reader)) {
                // we call newSearcher from the IndexReaderWarmer which warms segments during merging
                // in that case the reader is a LeafReader and all we need to do is to build a new Searcher
                // and return it since it does it's own warming for that particular reader.
                return searcher;
            }
            if (warmer != null) {
                // we need to pass a custom searcher that does not release anything on Engine.Search Release,
                // we will release explicitly
                IndexSearcher newSearcher = null;
                boolean closeNewSearcher = false;
                try {
                    if (previousReader == null) {
                        // we are starting up - no writer active so we can't acquire a searcher.
                        newSearcher = searcher;
                    } else {
                        // figure out the newSearcher, with only the new readers that are relevant for us
                        List<IndexReader> readers = new ArrayList<>();
                        for (LeafReaderContext newReaderContext : reader.leaves()) {
                            if (isMergedSegment(newReaderContext.reader())) {
                                // merged segments are already handled by IndexWriterConfig.setMergedSegmentWarmer
                                continue;
                            }
                            boolean found = false;
                            for (LeafReaderContext currentReaderContext : previousReader.leaves()) {
                                if (currentReaderContext.reader().getCoreCacheKey().equals(newReaderContext.reader().getCoreCacheKey())) {
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                readers.add(newReaderContext.reader());
                            }
                        }
                        if (!readers.isEmpty()) {
                            // we don't want to close the inner readers, just increase ref on them
                            IndexReader newReader = new MultiReader(readers.toArray(new IndexReader[readers.size()]), false);
                            newSearcher = super.newSearcher(newReader, null);
                            closeNewSearcher = true;
                        }
                    }

                    if (newSearcher != null) {
                        IndicesWarmer.WarmerContext context = new IndicesWarmer.WarmerContext(shardId, new Searcher("new_reader_warming", newSearcher));
                        warmer.warmNewReaders(context);
                    }
                    assert searcher.getIndexReader() instanceof ElasticsearchDirectoryReader : "this class needs an ElasticsearchDirectoryReader but got: " + searcher.getIndexReader().getClass();
                    warmer.warmTopReader(new IndicesWarmer.WarmerContext(shardId, new Searcher("top_reader_warming", searcher)));
                } catch (Throwable e) {
                    if (isEngineClosed.get() == false) {
                        logger.warn("failed to prepare/warm", e);
                    }
                } finally {
                    // no need to release the fullSearcher, nothing really is done...
                    if (newSearcher != null && closeNewSearcher) {
                        IOUtils.closeWhileHandlingException(newSearcher.getIndexReader()); // ignore
                    }
                }
            }
            return searcher;
        }
    }
    
    private class WriteDistributedLogCallBack implements IndexCallBack {
        
        private Translog.Operation operation;
        private DLSN allocatedDLSN;
        private Translog.Location location;
        
        public WriteDistributedLogCallBack(Translog.Operation operation) {
            this.operation = operation;
            this.allocatedDLSN = null;
        }
        
        @Override
        public void callback() throws AbortingException {
            try {
                Tuple<Future<DLSN>, Tuple<BytesReference, Long>> result = dlTranslog.writeOperation(operation, latestTxid);
                long startTimestamp = System.currentTimeMillis();
                try {
                    allocatedDLSN = FutureUtils.result(result.v1(), Duration.fromMilliseconds(dlWriteTimeoutMillis));
                    logger.debug("write data successfully, dlsn [{}], txid [{}]", allocatedDLSN, result.v2().v2());
                    latestDlsn = allocatedDLSN;
                    long endTimestamp = System.currentTimeMillis();
                    if (endTimestamp - startTimestamp > 50) {
                        logger.warn("cost [{}] millis to write to dl, more than 50 millis", endTimestamp - startTimestamp);
                    }
                    location = dlTranslog.writeToLocal(result.v2().v1());
                    return;
                } catch (Throwable t) {
                    logger.error("errors when write operation log to dl", t);
                    throw AbortingException.wrap(t);
                }
            } catch (Throwable t) {
                logger.error("errors while write log to distributed log service", t);
                DLBasedEngine.this.checkLeaseTask.forceRecovery();
                throw AbortingException.wrap(t);
            }
        }
    }
    
    private class CheckLeaseTask implements Runnable {
        private AtomicLong expireVersion = new AtomicLong(1);
        private long recoveryVersion = 0;
        private long recoveryTargetTxid = -1;
        private boolean waitRecoverTaskStop = false;
        private String indexUUID;
        private boolean isCreateIndex = false;
        
        public CheckLeaseTask(boolean isCreateIndex) {
            this.indexUUID = engineConfig.getIndexSettings().get(IndexMetaData.SETTING_INDEX_UUID, IndexMetaData.INDEX_UUID_NA_VALUE);
            this.isCreateIndex = isCreateIndex;
        }
        
        public void forceRecovery() {
            isPrimaryRecoverFinished = false;
            expireVersion.incrementAndGet();
        }
        
        @Override
        public void run() {
            if (isClosed.get()) {
                logger.debug("shard is closed, skip check lease process");
                return;
            }
            while (!isClosed.get()) {
                try {
                    DLBasedEngine.this.dlTranslog.init();
                    break;
                } catch (Throwable e) {
                    logger.warn("errors while create or delete log in dl, maybe zk or bk is done, sleep 1 second and check again", e);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        logger.error("errors while sleep", ie);
                    }
                    continue;
                }
            }
            long nextSendDelay = 500; 
            try {
                if (isPrimaryInRouting && isSnapshotRecoveryFinished) {
                    long sendTime = System.currentTimeMillis();
                    GetOrChangePrimaryShardLeaseRequest checkLeaseRequest = new GetOrChangePrimaryShardLeaseRequest(localNodeId, 
                            true, 
                            shardId.getIndex(),
                            indexUUID,
                            shardId.getId());
                    ActionFuture<GetOrChangePrimaryShardLeaseResponse> future = checkLeaseAction.execute(checkLeaseRequest);
                    GetOrChangePrimaryShardLeaseResponse response = future.actionGet(500);
                    boolean isShardOwnLease = response.checkShardStatus(shardId.getIndex(), indexUUID, shardId.getId());
                    if (!isShardOwnLease) {
                        GetOrChangePrimaryShardLeaseRequest updateLeaseOwnerRequest = new GetOrChangePrimaryShardLeaseRequest(localNodeId, false, shardId.getIndex(), indexUUID, shardId.getId());
                        ActionFuture<GetOrChangePrimaryShardLeaseResponse> updateFuture = checkLeaseAction.execute(updateLeaseOwnerRequest);
                        if (!this.isCreateIndex) {
                            Thread.sleep(leaseExpireTime);
                        }
                        updateFuture.actionGet(500);
                        nextSendDelay = 0;
                        logger.info("lease is changed, write the meta and wait for expired");
                        expireVersion.incrementAndGet();
                        waitRecoverTaskStop = false;
                    } else {
                        this.isCreateIndex = false;
                        lastLeaseSendTime = sendTime;
                    }
                    if (System.currentTimeMillis() - lastLeaseSendTime < leaseExpireTime / 2) {
                        nextSendDelay = Math.min(leaseExpireTime / 5, nextSendDelay);
                    }
                    if (System.currentTimeMillis() - lastLeaseSendTime > leaseExpireTime) {
                        isPrimaryRecoverFinished = false;
                        expireVersion.incrementAndGet();
                        waitRecoverTaskStop = false;
                        logger.debug("lease is expired");
                    } else {
                        long tempExpireVersion = this.expireVersion.get();
                        if (tempExpireVersion != recoveryVersion) {
                            isPrimaryRecoverFinished = false;
                            resourceLock.lock();
                            try {
                                if (!isClosed.get()) {
                                    DLBasedEngine.this.dlTranslog.closeLogWriter();
                                    DLBasedEngine.this.dlTranslog.openLogWriter();
                                }
                            } catch (Throwable t) {
                                logger.error("errors while close and reopen log writer", t);
                                throw t;
                            } finally {
                                resourceLock.unlock();
                            }
                            recoveryTargetTxid = DLBasedEngine.this.dlTranslog.getLogWriter().getLastTxId();
                            DLBasedEngine.this.recoverFromDLTask.checkResource(true);
                            logger.debug("last record in distributed log is txid:[{}], while local state is dlsn[{}], txid[{}]", 
                                    recoveryTargetTxid, DLBasedEngine.this.latestDlsn, DLBasedEngine.this.latestTxid);
                            // should reset latest dlsn and latest txid here, because the latest dlsn maybe larger than dlsn in log
                            Map<String, String> commitUserData = DLBasedEngine.this.indexWriter.getCommitData();
                            DLBasedEngine.this.latestDlsn = commitUserData.containsKey(Engine.COMMIT_DLSN) ? DLSN.deserialize(commitUserData.get(Engine.COMMIT_DLSN)) : DLSN.InitialDLSN;
                            DLBasedEngine.this.latestTxid.set(commitUserData.containsKey(Engine.COMMIT_TXID) ? Long.parseLong(commitUserData.get(Engine.COMMIT_TXID)) : 0);
                            logger.debug("last record in distributed log is txid:[{}], while local state is dlsn[{}], txid[{}]", 
                                    recoveryTargetTxid, DLBasedEngine.this.latestDlsn, DLBasedEngine.this.latestTxid);
                            DLBasedEngine.this.recoverFromDLTask.stop(true);
                            CheckLeaseTask.this.recoveryVersion = tempExpireVersion;
                        }
                
                        if (!isPrimaryRecoverFinished) {
                            if (!waitRecoverTaskStop && !DLBasedEngine.this.recoverFromDLTask.isActualRunning()) {
                                if (DLBasedEngine.this.latestTxid.get() < recoveryTargetTxid || !indexWriter.isOpen()) {
                                    DLBasedEngine.this.recoverFromDLTask.start("primary shard's lease is expired, should recover from dl first");
                                }
                            }
                            if (recoveryVersion == expireVersion.get()) {
                                if (DLBasedEngine.this.latestTxid.get() >= recoveryTargetTxid && indexWriter.isOpen()) {
                                    try (ReleasableLock lock = writeLock.acquire()) {
                                        DLBasedEngine.this.recoverFromDLTask.stop(true);
                                        waitRecoverTaskStop = true;
                                        if (!DLBasedEngine.this.recoverFromDLTask.isActualRunning()) {
                                            isPrimaryRecoverFinished = true;
                                            waitRecoverTaskStop = false;
                                            logger.debug("primary recover from dl finished");
                                        }
                                        refresh("changed_to_primary");
                                        versionMap.clear();
                                        versionMap.setManager(searcherManager);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    this.isCreateIndex = false;
                    // the primary maybe transfered to replica, then should call close writer to release the resource
                    dlTranslog.closeLogWriter();
                    long tempExpireVersion = this.expireVersion.get();
                    if (recoveryVersion != tempExpireVersion) {
                        DLBasedEngine.this.recoverFromDLTask.stop(true);
                        if (!recoverFromDLTask.isActualRunning()) {
                            DLBasedEngine.this.recoverFromDLTask.start("primary shard maybe transfered to replica, should start replay task");
                            recoveryVersion = tempExpireVersion;
                        }
                    } else if (!recoverFromDLTask.isActualRunning()) {
                        DLBasedEngine.this.recoverFromDLTask.stop(true);
                        DLBasedEngine.this.recoverFromDLTask.start("replay dl operation task is not running, start it");
                    }
                }
                
                logger.trace("check lease after [{}] millis", nextSendDelay);
                threadPool.schedule(TimeValue.timeValueMillis(nextSendDelay), ThreadPool.Names.GENERIC, this);
            } catch (Throwable t) {
                logger.error("errors while check lease", t);
                threadPool.schedule(TimeValue.timeValueMillis(nextSendDelay), ThreadPool.Names.GENERIC, this);
            }
        }
        
    }
    
    private class TruncateOldLog implements Runnable {

        @Override
        public void run() {
            try {
                if (isPrimaryInRouting) {
                    ActionFuture<IndexShardStatsResponse>  result = indexShardStatsAction.execute(new IndexShardStatsRequest(shardId.index().getName(), shardId.id()));
                    IndexShardStatsResponse response = result.actionGet(10000);
                    if (response.getTotalShards() == response.getSuccessfulShards() && response.getTotalShards() > 0) {
                        DLSN miniumDlsn = new DLSN(Long.MAX_VALUE, 0, 0);
                        boolean hasInvalidShardStats = false;
                        for (ShardStats shardStats : response.getShards()) {
                            boolean isInvalid = true;
                            CommonStats commonStats = shardStats.getStats();
                            if (commonStats != null) {
                                DLStats dlStats = commonStats.getDLStats();
                                if (dlStats != null && !dlStats.getCommitDlsn().equals(DLSN.InvalidDLSN)) {
                                    isInvalid = false;
                                    if (dlStats.getCommitDlsn().compareTo(miniumDlsn) < 0) {
                                        miniumDlsn = dlStats.getCommitDlsn();
                                    }
                                } else {
                                    logger.debug("dl [{}] status is invalid", dlStats);
                                }
                            } else {
                                logger.debug("common stats is null");
                            }
                            if (isInvalid) {
                                hasInvalidShardStats = true;
                                break;
                            }
                        }
                        if (!hasInvalidShardStats) {
                            if (!miniumDlsn.equals(DLSN.InitialDLSN)) {
                                logger.info("try to truncate log before [{}]", miniumDlsn);
                                dlTranslog.truncateLogBeforeDLSN(miniumDlsn);
                            }
                        }
                    } else {
                        logger.warn("some shards failed to get stats: total shards [{}], shards give reponse [{}], failure exceptions [{}], maybe some shard is abnormal so skip truncate log", 
                                response.getTotalShards(), 
                                response.getSuccessfulShards(),
                                response.getShardFailures().length > 0 ? response.getShardFailures()[0] : "");
                    }
                }
            } catch (Throwable t) {
                logger.error("catch exception when sleep to next lease check time", t);
            } finally {
                if (!isClosed.get()) {
                    // check in 1 minutes
                    threadPool.schedule(TimeValue.timeValueMinutes(1), ThreadPool.Names.GENERIC, this);
                }
            }
        }
        
    }
    
    private class ReplayDLOperationTask implements Runnable {
        
        private boolean needCheckResource = true;
        private boolean desireRunning = true;
        private Future<List<LogRecordWithDLSN>> readFuture;
        private long numberOfFailures = 0;
        private Throwable exception = null;
        private volatile boolean indexTaskRunning = false;
        private volatile boolean isCheckTaskRunning = false;
        private volatile ScheduledFuture<?> checkTaskFuture = null;
        private Lock checkTaskLock = new ReentrantLock();
        
        public void start(String reason) {
            logger.info(reason);
            this.desireRunning = true;
            this.needCheckResource = true;
            this.readFuture = null;
            checkTaskLock.lock();
            checkTaskFuture = threadPool.schedule(TimeValue.timeValueMillis(0), ThreadPool.Names.GENERIC, this);
            checkTaskLock.unlock();
        }
        
        public void stop(boolean waitToStop) {
            this.desireRunning = false;
            this.cancelReadFuture();
            if (dlTranslog != null) {
                dlTranslog.closeLogReader();
            }
            this.needCheckResource = true;
        }
        
        public boolean isActualRunning() {
            return indexTaskRunning || isCheckTaskRunning || checkTaskFuture != null || readFuture != null;
        }
        
        private void setCheckTaskFuture(ScheduledFuture<?> newCheckTaskFuture) {
            checkTaskLock.lock();
            checkTaskFuture = newCheckTaskFuture;
            checkTaskLock.unlock();
        }
        
        public void run() {
            isCheckTaskRunning = true;
            setCheckTaskFuture(null);
            try {
                if (!this.desireRunning || DLBasedEngine.this.isClosed.get()) { 
                    dlTranslog.closeLogReader();
                    setCheckTaskFuture(null);
                    return;
                }
                if (exception != null) {
                    throw exception;
                }
                checkResource(false);
                readAndReplayOperation();
                numberOfFailures = 0;
            } catch (Throwable t) {
                exception = null;
                needCheckResource = true;
                cancelReadFuture();
                dlTranslog.closeLogReader();
                ++ numberOfFailures;
                if (numberOfFailures > 100) {
                    t = new RecoveryFromDistributedLogFailedException(shardId, "failed to recovery from dl, retried 100 times", t);
                }
                boolean engineFailed = maybeFailEngine("[recover from distributed log service]", t);
                if (engineFailed) {
                    logger.error("errors in lucene engine, so fail this shard", t);
                    return;
                } else {
                    if (this.desireRunning && !DLBasedEngine.this.isClosed.get()) {
                        // exception occurs should wait 1 seconds and check again
                        // call refresh to clear version map
                        refresh("replay log failed, refresh it before replay again");
                        logger.error("errors while read operation from distributed log service and apply to local engine, fail no. [{}]", t, numberOfFailures);
                        checkTaskLock.lock();
                        checkTaskFuture = threadPool.schedule(TimeValue.timeValueMillis(500), ThreadPool.Names.GENERIC, this);
                        checkTaskLock.unlock();
                    } else {
                        logger.info("recover task is stopped, desire running status is [{}], is closed status is [{}]", this.desireRunning, DLBasedEngine.this.isClosed.get());
                        return;
                    }
                }
            
            } finally {
                isCheckTaskRunning = false;
            }
        }
        
        private void cancelReadFuture() {
            try {
                if (readFuture != null) {
                    FutureUtils.cancel(readFuture);
                }
            } catch (Throwable t) {
                logger.error("errors while cancel read future", t);
            } finally {
                readFuture = null;
            }
        }
        
        public void checkResource(boolean skipCheck) throws Throwable {
            if (!needCheckResource && !skipCheck) {
                return;
            }
            resourceLock.lock();
            try {
                if (isClosed.get()) {
                    logger.info("engine is closed, just skip check resource");
                    return;
                }
                // if dl service is down, could not open reader, but the index writer will always open
                // the index writer will not be opened and closed repeatly
                if (!indexWriter.isOpen()) {
                    versionMap.clear();
                    try {
                        IOUtils.close(searcherManager);
                    } catch (Throwable t) {
                        logger.warn("Failed to close SearcherManager when check resource", t);
                    }
                    indexWriter = createWriter(false);
                    searcherManager = createSearcherManager();
                    versionMap.setManager(searcherManager);
                }
                if (dlTranslog.isReaderClosed()) {
                    logger.info("try to open a new reader at [{}]", latestDlsn);
                    dlTranslog.openLogReader(latestDlsn);
                }
            } catch (Throwable t) {
                logger.error("errors while create writer or reader, throw the exception to test if need to fail the shard", t);
                throw t;
            } finally {
                resourceLock.unlock();
            }
            DLSN firstDlsn = DLSN.InitialDLSN;
            firstDlsn = dlTranslog.getFirstLogDLSN();
            logger.debug("current latest dlsn is [{}], the first dlsn in dl is [{}]", latestDlsn, firstDlsn);
            if (firstDlsn != null && firstDlsn.compareTo(latestDlsn) > 0) {
                throw new RecoveryFromDistributedLogFailedException(shardId, 
                        String.format("dlsn in lucene is [%s], while the first dlsn in log service is [%s], newer than current so that could not recovery", latestDlsn, firstDlsn));
            }
            needCheckResource = false;
        }
        
        private void readAndReplayOperation() throws Throwable {
            if (readFuture == null) {
                // 10 is suitable, it means will read at most 10 records, it maybe less than 10 records
                readFuture = dlTranslog.readBatchOperations(10);
                readFuture.addEventListener(new FutureEventListener<List<LogRecordWithDLSN>>() {
                    @Override
                    public void onFailure(Throwable t) {
                        exception = t;
                        if (desireRunning && !DLBasedEngine.this.isClosed.get()) {
                            checkTaskLock.lock();
                            checkTaskFuture = threadPool.schedule(TimeValue.timeValueMillis(0), ThreadPool.Names.GENERIC, ReplayDLOperationTask.this);
                            checkTaskLock.unlock();
                        }
                    }
    
                    @Override
                    public void onSuccess(final List<LogRecordWithDLSN> logRecordWithDLSNs) {
                        threadPool.schedule(TimeValue.timeValueMillis(0), ThreadPool.Names.GENERIC, new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (!desireRunning || DLBasedEngine.this.isClosed.get()) {
                                        return;
                                    }
                                    indexTaskRunning = true;
                                    if (logRecordWithDLSNs == null) {
                                        throw new Exception("read result from dl is empty, maybe the read thread is stopped or the engine is closed");
                                    }
                                    long sizeInBytes = 0;
                                    int numOperations = 0;
                                    for (LogRecordWithDLSN logRecordWithDLSN : logRecordWithDLSNs) {
                                        if (!logRecordWithDLSN.isControl()) {
                                            Translog.Operation operation = DistributedTranslog.getOperationFromLogRecord(logRecordWithDLSN);
                                            sizeInBytes += (20 + logRecordWithDLSN.getPayload().length);
                                            ++ numOperations;
                                            applyTranslogOperation(operation);
                                        }
                                        if (Math.abs(latestTxid.get() - logRecordWithDLSN.getTransactionId()) > 1) {
                                            logger.debug("the txid is not continuous, current is [{}], the new is [{}], current dlsn is [{}], previous dlsn is [{}]", 
                                                    latestTxid.get(), 
                                                    logRecordWithDLSN.getTransactionId(), 
                                                    latestDlsn, logRecordWithDLSN.getDlsn());
                                        }
                                        latestDlsn = logRecordWithDLSN.getDlsn();
                                        latestTxid.set(logRecordWithDLSN.getTransactionId());
                                    }
                                    dlTranslog.incrementSizeAndOperation(sizeInBytes, numOperations);
                                    readFuture = null;
                                } catch (Throwable t) {
                                    exception = t;
                                } finally {
                                    indexTaskRunning = false;
                                    if (desireRunning && !DLBasedEngine.this.isClosed.get()) {
                                        checkTaskLock.lock();
                                        checkTaskFuture = threadPool.schedule(TimeValue.timeValueMillis(0), ThreadPool.Names.GENERIC, ReplayDLOperationTask.this);
                                        checkTaskLock.unlock();
                                    }
                                }
                            }
                        });
                    }
                });
            }
        }
    }
    
    private final class EngineMergeScheduler extends ElasticsearchConcurrentMergeScheduler {
        private final AtomicInteger numMergesInFlight = new AtomicInteger(0);
        private final AtomicBoolean isThrottling = new AtomicBoolean();

        EngineMergeScheduler(ShardId shardId, Settings indexSettings, MergeSchedulerConfig config) {
            super(shardId, indexSettings, config);
        }

        @Override
        public synchronized void beforeMerge(OnGoingMerge merge) {
            int maxNumMerges = mergeScheduler.getMaxMergeCount();
            if (numMergesInFlight.incrementAndGet() > maxNumMerges) {
                if (isThrottling.getAndSet(true) == false) {
                    logger.info("now throttling indexing: numMergesInFlight={}, maxNumMerges={}", numMergesInFlight, maxNumMerges);
                    indexingService.throttlingActivated();
                    activateThrottling();
                }
            }
        }

        @Override
        public synchronized void afterMerge(OnGoingMerge merge) {
            int maxNumMerges = mergeScheduler.getMaxMergeCount();
            if (numMergesInFlight.decrementAndGet() < maxNumMerges) {
                if (isThrottling.getAndSet(false)) {
                    logger.info("stop throttling indexing: numMergesInFlight={}, maxNumMerges={}", numMergesInFlight, maxNumMerges);
                    indexingService.throttlingDeactivated();
                    deactivateThrottling();
                }
            }
        }
        @Override
        protected void handleMergeException(final Directory dir, final Throwable exc) {
            logger.error("failed to merge", exc);
            if (config().getMergeSchedulerConfig().isNotifyOnMergeFailure()) {
                engineConfig.getThreadPool().generic().execute(new AbstractRunnable() {
                    @Override
                    public void onFailure(Throwable t) {
                        logger.debug("merge failure action rejected", t);
                    }

                    @Override
                    protected void doRun() throws Exception {
                        MergePolicy.MergeException e = new MergePolicy.MergeException(exc, dir);
                        failEngine("merge failed", e);
                    }
                });
            }
        }
    }    
} 