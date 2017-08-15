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

package org.elasticsearch.discovery.dl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterState.ClusterStateDiff;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.block.ClusterBlock;
import org.elasticsearch.cluster.block.ClusterBlockLevel;
import org.elasticsearch.cluster.block.ClusterBlocks;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.cluster.routing.RoutingService;
import org.elasticsearch.cluster.routing.allocation.RoutingAllocation;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.io.stream.ByteBufferStreamInput;
import org.elasticsearch.common.io.stream.BytesStreamOutput;
import org.elasticsearch.common.io.stream.InputStreamStreamInput;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.discovery.DiscoverySettings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.engine.StoppableTask;
import org.elasticsearch.index.translog.dl.DLNamespace;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.threadpool.ThreadPool;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import com.google.common.base.Optional;

import org.apache.bookkeeper.stats.StatsLogger;
import org.apache.distributedlog.AsyncLogReader;
import org.apache.distributedlog.AsyncLogWriter;
import org.apache.distributedlog.DLSN;
import org.apache.distributedlog.DistributedLogConfiguration;
import org.apache.distributedlog.DistributedLogManager;
import org.apache.distributedlog.LogRecord;
import org.apache.distributedlog.LogRecordWithDLSN;
import org.apache.distributedlog.config.DynamicDistributedLogConfiguration;
import org.apache.distributedlog.exceptions.LockingException;
import org.apache.distributedlog.exceptions.LogEmptyException;
import org.apache.distributedlog.exceptions.OwnershipAcquireFailedException;
import org.apache.distributedlog.exceptions.TransactionIdOutOfOrderException;
import org.apache.distributedlog.lock.DistributedLock;
import org.apache.distributedlog.namespace.DistributedLogNamespace;
import org.apache.distributedlog.util.FutureUtils;

import com.twitter.util.Duration;
import com.twitter.util.Future;
import com.twitter.util.FutureEventListener;

public class ClusterStateOpLog extends AbstractComponent {
    public static final ClusterBlock LOCAL_STATE_IN_RECOVERY_BLOCK = new ClusterBlock(100, "local state is recoverying", true, true, RestStatus.SERVICE_UNAVAILABLE, ClusterBlockLevel.ALL);

    private static final String CLUSTER_STATE_LOG_NAME = "cluster_state_log2";
    private static final String CLUSTER_STATE_IMAGE_FILE_NAME = "cluster_state_image";
    private static final String TMP_CLUSTER_STATE_IMAGE_FILE_NAME = "cluster_state_image.tmp";
    private static final String CLUSTER_STATE_IMAGE_FOLDER_NAME = "image";
    private static ClusterStateOpLog instance;
    private DiscoveryNode localNode;
    private DistributedLogNamespace logNamespace = null;
    private DistributedLogManager logManager = null;  
    private AsyncLogWriter logWriter = null;
    private ClusterService clusterService;
    private RoutingService routingService;
    private Path statePath;
    private DLSN latestDlsnProcessed;
    private DLSN dumpedDlsn;
    private DLSN recoveryLastDlsn = DLSN.InitialDLSN;
    private long persistInterval;
    private boolean isMasterLeader;
    private PullClusterStateFromDLTask pullClusterStateFromDLTask = null;
    private ClusterStateWithDLSN latestClusterStateWithDLSN;
    private boolean needPullFullSate;
    private DiscoverySettings discoverySettings;
    private DLSN truncatedDlsn;
    private ThreadPool threadPool;
    private long masterLeaseVersion = 0;
    
    public ClusterStateOpLog(Settings settings, DiscoverySettings discoverySettings, ClusterService clusterService, RoutingService routingService, Environment nodeEnvironment, ThreadPool threadPool) {
        super(settings);
        this.localNode = clusterService.localNode();
        this.pullClusterStateFromDLTask = new PullClusterStateFromDLTask();
        this.persistInterval = 10;
        this.discoverySettings = discoverySettings;
        this.latestClusterStateWithDLSN = new ClusterStateWithDLSN(clusterService.state(), DLSN.InitialDLSN);
        this.needPullFullSate = false;
        this.clusterService = clusterService;
        this.routingService = routingService;
        this.latestDlsnProcessed = DLSN.InitialDLSN;
        this.truncatedDlsn = DLSN.InitialDLSN;
        this.dumpedDlsn = DLSN.InitialDLSN;
        this.isMasterLeader = false;
        this.threadPool = threadPool;
        DistributedLogConfiguration masterLogSpecificConf = new DistributedLogConfiguration();
        // set write enabled == false, because lease already confirmed there is only one writer
        masterLogSpecificConf.setWriteLockEnabled(true);
        Optional<DistributedLogConfiguration> conf = Optional.of(masterLogSpecificConf); 
        Optional<DynamicDistributedLogConfiguration> emptyDyConf = Optional.absent();
        Optional<StatsLogger> statsLogger = Optional.absent();
        try {
            this.logNamespace = DLNamespace.getNamespace(settings, localNode.address().toString());
            if (logNamespace.logExists(CLUSTER_STATE_LOG_NAME)) {
                this.logManager = logNamespace.openLog(CLUSTER_STATE_LOG_NAME, conf, emptyDyConf, statsLogger);
            } else {
                this.logNamespace.createLog(CLUSTER_STATE_LOG_NAME);
                this.logManager = logNamespace.openLog(CLUSTER_STATE_LOG_NAME, conf, emptyDyConf, statsLogger);
            }
        } catch (Throwable t) {
            logger.error("could not get log namespace, exit", t);
            exitProcess(t, "errors while init distributed log service");
        }
        // find a valid data path to store image
        Path[] clusterPaths = nodeEnvironment.dataWithClusterFiles();
        statePath = null;
        // check if there are more than one dl image data paths
        int numImageDataPathFound = 0;
        for (Path clusterPath : clusterPaths) {
            Path tmpStatePath = clusterPath.resolve(CLUSTER_STATE_IMAGE_FOLDER_NAME);
            if (Files.exists(tmpStatePath)) {
                logger.info("find a local path [{}] using it as image data path", tmpStatePath);
                ++ numImageDataPathFound;
                statePath = tmpStatePath;
            }
        }
        if (numImageDataPathFound > 1) {
            Throwable t = new IllegalStateException("find ["+ numImageDataPathFound + "] image data paths, it is fatal error, stop current instance");
            exitProcess(t, "found more than one state path");
        }
        if (numImageDataPathFound == 0) {
            statePath = clusterPaths[0].resolve(CLUSTER_STATE_IMAGE_FOLDER_NAME);
            try {
                Files.createDirectories(statePath);
                logger.info("could not find a valid path under folders, create a new one [{}]", statePath);
            } catch (IOException e) {
                Throwable t = new IllegalStateException("could not create a folder to store image state under " + statePath);
                exitProcess(t, "");
            }
        }
        
        ClusterStateWithDLSN clusterStateFromImage = null;
        try {
            clusterStateFromImage = loadClusterStateFromImage();
        } catch (IOException e) {
            exitProcess(e, "errors while load cluster state from image");
        }
        if (clusterStateFromImage != null) {
            this.latestClusterStateWithDLSN = clusterStateFromImage;
            this.latestDlsnProcessed = clusterStateFromImage.dlsn();
        }
        threadPool.schedule(TimeValue.timeValueMillis(0), ThreadPool.Names.GENERIC, new CheckAndAcquireMasterTask());
        instance = this;
    }
    
    public static ClusterStateOpLog getInstance() {
        return instance;
    }
    
    public boolean localNodeMaster() {
        return checkMasterLogOwnership() && isMasterLeader;
    }
    
    public boolean checkMasterLogOwnership() {
        if (this.logWriter == null) {
            return false;
        }
        DistributedLock lock = this.logWriter.getLock();
        if (lock == null) {
            return false;
        }
        try {
            lock.checkOwnership();
        } catch (LockingException e) {
            return false;
        }
        return true;
    }
    
    public void truncateLogBeforeDLSN(final DLSN dlsn) {
        if (dlsn.getLogSegmentSequenceNo() <= truncatedDlsn.getLogSegmentSequenceNo()) {
            return;
        }
        this.logWriter.truncate(dlsn).addEventListener(new FutureEventListener<Boolean>() {

            @Override
            public void onFailure(Throwable t) {
                logger.error("errors while truncate log after DLSN [{}]", t, dlsn);
            }

            @Override
            public void onSuccess(Boolean isSuccess) {
                if (isSuccess) {
                    truncatedDlsn = dlsn;
                    logger.info("truncate log before [{}]", dlsn);
                }
            }
        });
    }
    
    public ClusterStateWithDLSN getLatestClusterState() {
        return latestClusterStateWithDLSN;
    }
    
    public boolean shouldPullFullStateFromMaster() {
        return needPullFullSate;
    }
    
    public DLSN getDumpedDlsn() {
        return this.dumpedDlsn;
    }
    
    public long masterLeaseVersion() {
        return this.masterLeaseVersion;
    }
    
    public DLSN writeDiff(ClusterState curClusterState, ClusterState previousState, long timeoutMillis) throws IOException {
        try {
            ClusterStateDiff clusterStateDiff = (ClusterStateDiff)curClusterState.diff(previousState);
            Future<DLSN> writeResult = null;
            BytesStreamOutput out = new BytesStreamOutput();
            clusterStateDiff.writeTo(out);
            BytesReference bytes = out.bytes();
            // use new cluster state version as txid
            LogRecord logRecord = new LogRecord(clusterStateDiff.getToVersion(), bytes.toBytes());
            writeResult = logWriter.write(logRecord);
            DLSN logDlsn = FutureUtils.result(writeResult);
            // write a control record to flush previous write
            writeControlRecord(clusterStateDiff.getToVersion());
            this.latestClusterStateWithDLSN = new ClusterStateWithDLSN(curClusterState, logDlsn);
            if (latestDlsnProcessed == null || logDlsn.compareTo(latestDlsnProcessed) > 0) {
                this.latestDlsnProcessed = logDlsn;
            } else {
                logger.error("published dlsn [{}] is little than the dlsn [{}] pulled from dl", logDlsn, latestDlsnProcessed);
            }
            this.latestDlsnProcessed = logDlsn;
            if (shouldDump(logDlsn, dumpedDlsn, persistInterval) || dumpedDlsn.equals(DLSN.InitialDLSN) 
                    || curClusterState.metaData().version() != previousState.metaData().version()) {
                logger.info("latest dlsn is [{}], previous dlsn is [{}], larger than threshold [{}], so dump into image file", 
                        logDlsn, dumpedDlsn, persistInterval);
                dumpClusterStateImage(curClusterState, logDlsn);
            }
            logger.info("write cluster state diff into dl successfully with dlsn [{}], version [{}]", logDlsn, curClusterState.version());
            return logDlsn;
        } catch (TransactionIdOutOfOrderException e) {
            closeLogWriter();
            logger.error("errors while ", e);
            throw e;
        } catch (IOException e) {
            closeLogWriter();
            logger.error("errors while write diff state into dl", e);
            throw e;
        }
    }
    
    private void closeLogWriter() {
        if (logWriter != null) {
            logWriter.asyncClose();
            logWriter = null;
        }
    }
    
    private void dumpClusterStateImage(ClusterState clusterState, DLSN dlsn) throws IOException {
        // TODO change it to a async thread
        File tmpFile = statePath.resolve(TMP_CLUSTER_STATE_IMAGE_FILE_NAME).toFile();
        logger.debug("try to create to tmp image file: [{}]", tmpFile.getAbsolutePath());
        if (!tmpFile.exists()) {
            if (!tmpFile.createNewFile()) {
                throw new IOException("could not create temp cluster state file " + tmpFile.getName());
            }
        }
        ClusterStateWithDLSN clusterStateWithDLSN = new ClusterStateWithDLSN(clusterState, dlsn);
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile);
        BytesStreamOutput bStream = new BytesStreamOutput();
        clusterStateWithDLSN.writeTo(bStream);
        fileOutputStream.write(bStream.bytes().toBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
        File clusterStateFile = statePath.resolve(CLUSTER_STATE_IMAGE_FILE_NAME).toFile();
        if (clusterStateFile.exists()) {
            clusterStateFile.delete();
        }
        if (!tmpFile.renameTo(clusterStateFile)) {
            throw new IOException("failed to rename cluster state file from " + tmpFile.getName() 
                    + " to current " + clusterStateFile.getName());
        }
        logger.info("dump log image into file, info version [{}], DLSN [{}], sequenceno [{}]", clusterState.version(), dlsn, dlsn.getLogSegmentSequenceNo());
        dumpedDlsn = dlsn;
    }
    
    private ClusterStateWithDLSN loadClusterStateFromImage() throws IOException {
        try {
            File clusterStateFile = statePath.resolve(CLUSTER_STATE_IMAGE_FILE_NAME).toFile();
            if (!clusterStateFile.exists()) {
                return null;
            }
            FileInputStream clusterStateInputStream = new FileInputStream(clusterStateFile);
            InputStreamStreamInput streamInput = new InputStreamStreamInput(clusterStateInputStream);
            ClusterStateWithDLSN clusterStateWithDLSN = ClusterStateWithDLSN.PROTO.readFrom(streamInput, clusterService.localNode());
            streamInput.close();
            clusterStateInputStream.close();
            
            ClusterState clusterStateFromImage = clusterStateWithDLSN.state();
            DiscoveryNodes.Builder nodesBuilder = DiscoveryNodes.builder(clusterStateFromImage.getNodes());
            if (clusterStateFromImage.nodes().localNodeMaster()) {
                /** 
                 * if local node is the master leader node in cluster state has to set to unknown because it is recovering from image
                 * so that it must not be the master leader. 
                 * if not the master leader node, then should not set to unknown because maybe local node is a data node and it restarts
                 */
                nodesBuilder.masterNodeId("unknown");
            }
            // if local node restarts, then the id has changed, so node in the state with the same ip and port should be removed
            // and add the new node to the state
            for (DiscoveryNode activeNode : clusterStateWithDLSN.state().nodes()) {
                if (activeNode.address().equals(clusterService.localNode().address())) {
                    nodesBuilder.remove(activeNode.getId());
                }
            }
            for (ObjectObjectCursor<String, DiscoveryNode> nodeIdValuePair: clusterStateWithDLSN.state().nodes().deadNodes()) {
                if (nodeIdValuePair.value.address().equals(clusterService.localNode().address())) {
                    nodesBuilder.removeDeadNodeByIpPort(nodeIdValuePair.value);
                }
            }
            nodesBuilder.put(clusterService.localNode());
            nodesBuilder.localNodeId(clusterService.localNode().getId());
            // should add blocks from init state
            ClusterBlocks.Builder blocks = ClusterBlocks.builder();
            blocks.blocks(clusterService.state().blocks());
            blocks.addGlobalBlock(LOCAL_STATE_IN_RECOVERY_BLOCK);
            ClusterState newClusterState = ClusterState.builder(clusterStateFromImage)
                    .blocks(blocks)
                    .nodes(nodesBuilder).build();
            clusterStateWithDLSN.state(newClusterState);
            LogRecordWithDLSN lastLogRecordInLog = getLastLogRecord();
            if (lastLogRecordInLog != null) {
                recoveryLastDlsn = lastLogRecordInLog.getDlsn();
            }
            return clusterStateWithDLSN;
        } catch (FileNotFoundException e) {
            logger.error("could not find cluster state file", e);
        } catch (IOException e) {
            logger.error("errors while load cluster state from image file", e);
            throw e;
        }
        return null;
    }
    
    private LogRecordWithDLSN getLastLogRecord() throws IOException {
        
        try {
            LogRecordWithDLSN logRecordWithDLSN = logManager.getLastLogRecord();
            return logRecordWithDLSN;
        } catch (LogEmptyException e) {
            return null;
        } catch (IOException e) {
            throw e;
        }
    }
    
    private void writeControlRecord(long version) throws IOException {
        try {
            LogRecord logRecord = new LogRecord(version, new byte[1]);
            logRecord.setControl();
            Future<DLSN> result = logWriter.write(logRecord);
            FutureUtils.result(result);
            return;
        } catch (TransactionIdOutOfOrderException e) {
            throw e;
        }
    }
    
    public void exitProcess(Throwable t, String reason) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String methodStack = "";
        for (int i = 0; i < stackTraceElements.length; ++i) {
            methodStack += stackTraceElements[i] + "\n";
        }
        logger.error("something wrong with bdb, exit process: " + methodStack);
        if (t == null) {
            logger.error(reason);
        } else {
            logger.error(reason, t);
        }
        System.exit(1);
    }
    
    /**
     * when the node is selected as leader, then it should add all nodes in the setting to list
     * @param configuredNodes
     */
    public void changeToLeader() {
        pullClusterStateFromDLTask.stop(true);
        final CountDownLatch latch = new CountDownLatch(1);
        clusterService.submitStateUpdateTask("masterservice elect me as master leader", new ClusterStateUpdateTask(Priority.IMMEDIATE) {
            
            @Override
            public boolean runOnlyOnMaster() {
                return false;
            }
            
            @Override
            public void onFailure(String source, Throwable t) {
                logger.error("errors while change local node to master", t);
                latch.countDown();
            }
            
            @Override
            public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
                needPullFullSate = false;
                latch.countDown();
                isMasterLeader = true;
                masterLeaseVersion ++;
            }
            
            @Override
            public ClusterState execute(ClusterState currentState) throws Exception {
                if (currentState.nodes().localNodeMaster()) {
                    return currentState;
                }
                DiscoveryNodes.Builder nodesBuilder = new DiscoveryNodes.Builder(currentState.nodes()).masterNodeId(localNode.getId());
                /**
                 *  should remove the no master block
                 *  also remove state not recover block, because all state is in dl, if i am elected as master, then i have all state, so that remove the block
                 *  if not remove the block, it will recover the state from other nodes
                 */
                ClusterBlocks.Builder clusterBlocks = ClusterBlocks.builder().blocks(currentState.blocks())
                        .removeGlobalBlock(discoverySettings.getNoMasterBlock());
                clusterBlocks.removeGlobalBlock(LOCAL_STATE_IN_RECOVERY_BLOCK);
                ClusterState clusterState = ClusterState.builder(currentState)
                        .nodes(nodesBuilder)
                        .blocks(clusterBlocks)
                        .build();
                /**
                 * if local node become to master leader, then should reroute eagerly to cover a case:
                 *     there is only one master node, the node is stopped and started, the cluster state will not 
                 *     detect that is is dead and rejoined the cluster, so some shards status is started but the nodeid is 
                 *     the old master node id.
                 * call reroute will remove nodes that already dead or id changed.
                 */
                RoutingAllocation.Result routingResult = routingService.getAllocationService().reroute(
                        clusterState,
                        "local node change to master leader");
                return ClusterState.builder(clusterState).routingResult(routingResult).build();
            }
        });
        try {
            // the publish timeout is 30s, so set it to 40s
            latch.await(40, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("errors while wait for latch", e);
        }
        // if the state is not processed correctly, then close writer and try to change to leader again
        if (!isMasterLeader) {
            closeLogWriter();
        }
    }
    
    public synchronized void restartPullTask(ClusterState newClusterState, DLSN latestDlsn) {
        if (newClusterState != null) {
            this.latestClusterStateWithDLSN = new ClusterStateWithDLSN(newClusterState, latestDlsn);
            this.latestDlsnProcessed = latestClusterStateWithDLSN.dlsn();
        }
        this.pullClusterStateFromDLTask.stop(true);
        this.pullClusterStateFromDLTask.start("restart pull task");
    }
    
    public void checkPullClusterStateTask() {
        if (!pullClusterStateFromDLTask.isActualRunning() && !isMasterLeader && !needPullFullSate) {
            this.restartPullTask(null, null);
        }
    }
    
    public static boolean shouldDump(DLSN currentDlsn, DLSN previousDlsn, long threshold) {
        if (previousDlsn == null || currentDlsn == null) {
            return true;
        }
        if (currentDlsn.getLogSegmentSequenceNo() != previousDlsn.getLogSegmentSequenceNo()) {
            return true;
        }
        if (currentDlsn.getEntryId() - previousDlsn.getEntryId() > threshold) {
            return true;
        }
        return false;
    }
    
    class CheckAndAcquireMasterTask implements Runnable {
        public void run() {
            try {
                // when local node's role is master node, try to get master
                if (clusterService.state().nodes().localNode().isMasterNode()) {
                    if (!isMasterLeader) {
                        try {
                            if (!checkMasterLogOwnership()) {
                                closeLogWriter();
                                logWriter = FutureUtils.result(logManager.openAsyncLogWriter(), Duration.fromSeconds(3));
                            }
                            // check the logwriter's last log record is equal to pull state task
                            LogRecordWithDLSN tmpLastLogRecordWithDLSN = getLastLogRecord();
                            if (tmpLastLogRecordWithDLSN == null) {
                                // it means log is empty, i can be master
                                pullClusterStateFromDLTask.stop(true);
                                changeToLeader();
                            } else if (latestDlsnProcessed.compareTo(tmpLastLogRecordWithDLSN.getDlsn()) >= 0) {
                                pullClusterStateFromDLTask.stop(true);
                                changeToLeader();
                            } else {
                                checkPullClusterStateTask();
                            }
                        } catch (OwnershipAcquireFailedException e) { 
                            // not own the lock, then check pull state task
                            // using trace here, since it is an ordinary exception
                            logger.trace("check owner ship error", e);
                            checkPullClusterStateTask();
                        } catch (IOException e) {
                            logger.error("errors while check master state", e);
                            checkPullClusterStateTask();
                        }
                    } else {
                        // if local node not own the log any more, set isMasterLeader to false
                        if (!checkMasterLogOwnership()) {
                            isMasterLeader = false;
                            checkPullClusterStateTask();
                        } else {
                            // check log writer? not need since check ownership will check log writer
                        }
                    }
                } else {
                    isMasterLeader = false;
                    checkPullClusterStateTask();
                }
            } catch (Throwable t) {
                logger.error("errors while check and acquire master task", t);
            } finally {
                threadPool.schedule(TimeValue.timeValueSeconds(3), ThreadPool.Names.GENERIC, this);
            }
        }
    }
    
    class PullClusterStateFromDLTask extends StoppableTask {
        private AsyncLogReader logReader = null;
        private LogRecordWithDLSN logRecordWithDLSN = null;
        
        public PullClusterStateFromDLTask() {
            super("pull-cluster-state-from-dl", logger);
        }
        
        public void actualTask() {
            needPullFullSate = false;
            if (logReader != null) {
                logReader.asyncClose();
                logReader = null;
            }
            try {
                logReader = FutureUtils.result(logManager.openAsyncLogReader(latestDlsnProcessed));
                DLSN firstDlsn = FutureUtils.result(logManager.getFirstDLSNAsync());
                if (firstDlsn.compareTo(latestDlsnProcessed) > 0) {
                    throw new IOException(String.format("first dlsn in dl is %s is larger than the dlsn %s in local image, so that should pull full state from master", 
                            firstDlsn, latestDlsnProcessed));
                }
            } catch (Throwable t) {
                logger.error("errors while open log, at dlsn [{}], set pull full state to true", t, latestDlsnProcessed);
                needPullFullSate = true;
                return;
            }
            Future<LogRecordWithDLSN> logRecordWithDlsnFuture = null;
            while (desireRunning) {
                logRecordWithDlsnFuture = logReader.readNext();
                try {
                    logRecordWithDLSN  = FutureUtils.result(logRecordWithDlsnFuture);
                } catch (IOException e) {
                    logger.error("errors while wait and pull diff from dl", e);
                    needPullFullSate = true;
                    return;
                }
                
                /**
                 * when the pull task is closed, log record dlsn is set to null, and the read process is cancelled
                 * it will go to here. so that should test if log record with dlsn is null
                 */
                if (logRecordWithDLSN == null) {
                    continue;
                }
                
                if (logRecordWithDLSN.isControl()) {
                    latestDlsnProcessed = logRecordWithDLSN.getDlsn();
                    continue;
                }
                final CountDownLatch latch = new CountDownLatch(1);
                clusterService.submitStateUpdateTask("read diff from dl and apply to local state", new ClusterStateUpdateTask() {
                    private int processTimes = 1;
                    
                    @Override
                    public boolean runOnlyOnMaster() {
                        return false;
                    }
                    
                    @Override
                    public void onFailure(String source, Throwable t) {
                        logger.error("errors while apply cluster state from dl", t);
                        if (desireRunning) {
                            clusterService.submitStateUpdateTask("read diff from dl and apply to local state  " + processTimes, this);
                            processTimes ++;
                        } else {
                            latch.countDown();
                        }
                    }

                    @Override
                    public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
                        logger.info("pull diff from dl and apply to local state successfully with dlsn [{}], previous version [{}], new version [{}]", 
                                logRecordWithDLSN.getDlsn(), oldState.version(), newState.version());
                        /**
                         * some times version not changed and write many logs to dl with same version
                         * for example, when dump to image failed, but write to dl successfully
                         */
                        if (oldState.version() <= newState.version()) { 
                            // when a master starts, its dlsn is equal to the latest dlsn, it will stop the pull task
                            // so the logrecordwith dlsn == null
                            if (logRecordWithDLSN != null) {
                                latestDlsnProcessed = logRecordWithDLSN.getDlsn();
                            }
                        }
                        latch.countDown();
                    }
                    
                    @Override
                    public ClusterState execute(ClusterState currentState) throws Exception {
                        if (isMasterLeader) {
                            logger.warn("local node has become master leader, so that not apply the diff any more");
                            return currentState;
                        }
                        if (logRecordWithDLSN == null) {
                            logger.warn("log record is null, maybe local task is canceled");
                            return currentState;
                        }
                        StreamInput input = new ByteBufferStreamInput(ByteBuffer.wrap(logRecordWithDLSN.getPayload()));
                        ClusterStateDiff clusterStateDiff = (ClusterStateDiff) currentState.readDiffFrom(input);
                        ClusterState newClusterState = null;
                        if (clusterStateDiff.getToVersion() <= currentState.getVersion()) {
                            // local image is deleted, then it does not know the dlsn, it will read from start, then the 
                            return currentState;
                        } 
                        if (clusterStateDiff.getToVersion() == currentState.getVersion() + 1) {
                            newClusterState = clusterStateDiff.applyIgnoreUUID(currentState);
                        } else {
                            String exceptionMsg = "current cluster state's version is [" + currentState.getVersion() + "], while the diff version is [" + clusterStateDiff.getToVersion() + "], gap is larger than 1, should pull full state from master";
                            needPullFullSate = true;
                            desireRunning = false;
                            throw new Exception(exceptionMsg);
                        }
                        
                        /**
                         *  1. master could not connect to local node, but local node could connect to dl service
                         *  2. if local node is pull diff from dl, then it should not be the master node, if not set master node to unknown then it will
                         *  try to publish the state and dl fencing it, it could move forward.
                         */
                        if (!newClusterState.nodes().nodeExists(localNode.getId()) || newClusterState.nodes().localNodeMaster()) {
                            DiscoveryNodes.Builder nodesBuilder = DiscoveryNodes.builder(newClusterState.getNodes()); 
                            // if local node restarts, then the id has changed, so node in the state with the same ip and port should be removed
                            // and add the new node to the state
                            for (DiscoveryNode activeNode : newClusterState.nodes()) {
                                if (activeNode.address().equals(localNode.address())) {
                                    nodesBuilder.remove(activeNode.getId());
                                }
                            }
                            for (ObjectObjectCursor<String, DiscoveryNode> nodeIdValuePair: newClusterState.nodes().deadNodes()) {
                                if (nodeIdValuePair.value.address().equals(clusterService.localNode().address())) {
                                    nodesBuilder.removeDeadNodeByIpPort(nodeIdValuePair.value);
                                }
                            }
                            nodesBuilder.put(clusterService.localNode());
                            nodesBuilder.localNodeId(clusterService.localNode().getId());
                            nodesBuilder.masterNodeId("unknown");
                            newClusterState = ClusterState.builder(newClusterState).nodes(nodesBuilder).build();
                        }
                        /**
                         * if local state have not catchup the latest dlsn in log, then should add a state not recovered block
                         * if not, the old state will be consumed by indicesclusterstateservice and apply to local
                         */

                        ClusterBlocks.Builder blocks = ClusterBlocks.builder()
                                .blocks(newClusterState.blocks());
                        if (newClusterState.nodes().masterNodeId() != null) {
                            blocks.removeGlobalBlock(discoverySettings.getNoMasterBlock());
                        }
                        if (logRecordWithDLSN.getDlsn().compareTo(recoveryLastDlsn) < 0) {
                            blocks.addGlobalBlock(LOCAL_STATE_IN_RECOVERY_BLOCK);
                        } else {
                            blocks.removeGlobalBlock(LOCAL_STATE_IN_RECOVERY_BLOCK);
                        }
                        newClusterState = ClusterState.builder(newClusterState).blocks(blocks).build();
                        latestClusterStateWithDLSN = new ClusterStateWithDLSN(newClusterState, logRecordWithDLSN.getDlsn());
                        /**
                         * should always persist cluster state when metadata changed because when catchup will trigger delete indices
                         */
                        if (shouldDump(logRecordWithDLSN.getDlsn(), dumpedDlsn, persistInterval) 
                                || newClusterState.metaData().version() != currentState.metaData().version()) {
                            logger.info("latest dlsn is [{}], previous dlsn is [{}], larger than threshold [{}], so dump into image file", 
                                    logRecordWithDLSN.getDlsn(), dumpedDlsn, persistInterval);
                            dumpClusterStateImage(newClusterState, logRecordWithDLSN.getDlsn());
                        }
                        return newClusterState;
                    }
                });
                while (desireRunning) {
                    try {
                        latch.await(5, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        continue;
                    }
                    if (latch.getCount() == 0) {
                        break;
                    }
                }
            }
            try {
                if (logReader != null) {
                    logReader.asyncClose();
                }
                logRecordWithDLSN = null;
            } catch (Throwable t) {
                logger.error("errors while stop pull cluster state from dl task", t);
            }
        }
    }
}

