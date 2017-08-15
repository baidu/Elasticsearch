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

package org.elasticsearch.index.translog.dl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.lucene.util.IOUtils;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.Base64;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.collect.Tuple;
import org.elasticsearch.common.io.stream.ByteBufferStreamInput;
import org.elasticsearch.common.io.stream.BytesStreamOutput;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.util.concurrent.ReleasableLock;
import org.elasticsearch.index.translog.Translog;
import org.elasticsearch.index.translog.TranslogConfig;
import org.apache.distributedlog.AsyncLogReader;
import org.apache.distributedlog.AsyncLogWriter;
import org.apache.distributedlog.DLSN;
import org.apache.distributedlog.DistributedLogManager;
import org.apache.distributedlog.LogRecord;
import org.apache.distributedlog.LogRecordWithDLSN;
import org.apache.distributedlog.exceptions.InvalidStreamNameException;
import org.apache.distributedlog.exceptions.LogEmptyException;
import org.apache.distributedlog.exceptions.TransactionIdOutOfOrderException;
import org.apache.distributedlog.namespace.DistributedLogNamespace;
import org.apache.distributedlog.util.FutureUtils;

import com.twitter.util.Duration;
import com.twitter.util.Future;
import com.twitter.util.FutureEventListener;
import com.twitter.util.TimeoutException;

/**
 * write the translog to distribute log system and also write it to local file
 * local log is not synced because it is used to get method and not call fsync any more
 */
public class DistributedTranslog extends Translog {
    private String logName = null;
    private DistributedLogNamespace logNamespace = null;
    private DistributedLogManager logManager = null;  
    private AsyncLogWriter logWriter = null;
    private AsyncLogReader logReader = null;
    private int numOperations;
    private long sizeInBytes;
    private int prepareNumOperations;
    private long prepareSizeInBytes;
    private DLSN truncatedDlsn = DLSN.InitialDLSN;
    private LocalTranslog localTranslog;
    private final int DL_OPERATION_TIMEOUT_SECS = 10;
    private final String localNodeId;
    private TranslogConfig translogConfig = null;
    
    public DistributedTranslog(TranslogConfig config, String nodeId) throws IOException {
        super(config, nodeId);
        this.translogConfig = config;
        this.localNodeId = nodeId;
        String indexUUID = this.indexSettings.get(IndexMetaData.SETTING_INDEX_UUID, IndexMetaData.INDEX_UUID_NA_VALUE);
        this.logName = getLogName(config.getShardId().getIndex(), indexUUID, this.shardId.getId());
        this.numOperations = 0;
        this.sizeInBytes = 0;
        this.prepareNumOperations = 0;
        this.prepareSizeInBytes = 0;
        this.localTranslog = new LocalTranslog(config);
    }
    
    public void init() throws IllegalArgumentException, NullPointerException, IOException {
        this.logNamespace = DLNamespace.getNamespace(translogConfig.getIndexSettings(), localNodeId);
        this.logManager = this.logNamespace.openLog(this.logName);
    }

    public static boolean isLogExist(Settings settings, String indexUUID, String indexName, int shardId, String localNodeId) throws IOException {
        DistributedLogNamespace logNamespace = DLNamespace.getNamespace(settings, localNodeId);
        String logName = getLogName(indexName, indexUUID, shardId);
        return logNamespace.logExists(logName);
    }
    
    /**
     * 
     * @return
     * @throws IOException 
     * @throws InvalidStreamNameException 
     */
    public static boolean deleteLog(Settings settings, String indexUUID, String indexName, int shardId, String localNodeId) throws IOException {
        DistributedLogNamespace logNamespace = DLNamespace.getNamespace(settings, localNodeId);
        String logName = getLogName(indexName, indexUUID, shardId);
        if (logNamespace.logExists(logName)) {
            DistributedLogManager logManager = logNamespace.openLog(logName);
            logManager.delete();
            return true;
        } else {
            return false;
        }
    }
    
    public static void createLog(Settings settings, String indexName, String indexUUID, int shardId, String localNodeId) throws IOException {
        int retryNum = 0;
        while (true) {
            try {
                String tmpLogName = getLogName(indexName, indexUUID, shardId);
                DistributedLogNamespace logNamespace = DLNamespace.getNamespace(settings, localNodeId);
                boolean isLogExist = logNamespace.logExists(tmpLogName);
                if (!isLogExist) {
                    logNamespace.createLog(tmpLogName);
                    continue;
                }
                return;
            } catch (Throwable t) {
                ++ retryNum;
                if ((t instanceof TimeoutException || t.getCause() instanceof TimeoutException) && retryNum <= 5) {
                    continue;
                } else {
                    throw t;
                }
            }
        }
    }
    
    public void openLogWriter() throws Throwable {
        try {
            closeLogWriter();
            logWriter = FutureUtils.result(logManager.openAsyncLogWriter(), Duration.fromSeconds(DL_OPERATION_TIMEOUT_SECS));
        } catch (Throwable t) {
            logger.error("errors while opening log writer", t);
            throw t;
        }
    }
    
    public DLSN getFirstLogDLSN() throws Throwable {
        try {
            return FutureUtils.result(logManager.getFirstDLSNAsync(), Duration.fromSeconds(DL_OPERATION_TIMEOUT_SECS));
        } catch (Throwable t) {
            if (t instanceof LogEmptyException) {
                logger.info("log is empty, just return initial dlsn");
                return DLSN.InitialDLSN;
            } else {
                throw t;
            }
        }
    }
    
    public void truncateLogBeforeDLSN(final DLSN dlsn) {
        if (dlsn.getLogSegmentSequenceNo() <= truncatedDlsn.getLogSegmentSequenceNo()) {
            logger.info("previous truncate dlsn is [{}], current dlsn is [{}], segment no not change not call truncate", truncatedDlsn, dlsn);
            return;
        }
        if (this.logWriter == null) {
            logger.error("log writer is closed, maybe not primary any more, skip truncate");
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
                    logger.info("truncate log before [{}] successfully", dlsn);
                }
            }
        });
    }
    
    public synchronized void incrementSizeAndOperation(long size, int operationNum) {
        this.sizeInBytes = this.sizeInBytes + size;
        this.numOperations = this.numOperations + operationNum;
    }
    
    /**
     * 
     * @param operation
     * @return
     * @throws IOException 
     */
    public Tuple<Future<DLSN>, Tuple<BytesReference, Long>> writeOperation(Translog.Operation operation, AtomicLong txid) throws IOException {
        BytesStreamOutput out = new BytesStreamOutput();
        try (ReleasableLock lock = writeLock.acquire()) {
            Future<DLSN> writeResult = null;
            out.writeByte(operation.opType().id());
            operation.writeTo(out);
            BytesReference bytes = out.bytes();
            LogRecord logRecord = new LogRecord(txid.incrementAndGet(), bytes.toBytes());
            writeResult = logWriter.write(logRecord);
            sizeInBytes += (20 + logRecord.getPayload().length);
            ++ numOperations;
            return new Tuple<Future<DLSN>, Tuple<BytesReference, Long>>(writeResult, new Tuple<BytesReference, Long>(bytes, txid.get()));
        } catch (TransactionIdOutOfOrderException e) {
            throw e;
        } finally {
            out.close();
        }
    }
    
    public void openLogReader(DLSN dlsn) throws IOException {
        try {
            logReader = FutureUtils.result(logManager.openAsyncLogReader(dlsn), Duration.fromSeconds(DL_OPERATION_TIMEOUT_SECS));
        } catch (IOException e) {
            logger.error("errors while open or create log service", e);
            throw e;
        }
    }
    
    public Future<List<LogRecordWithDLSN>> readBatchOperations(int batchSize) {
        Future<List<LogRecordWithDLSN>> recordFuture = logReader.readBulk(batchSize);
        return recordFuture;
    }
    
    public int totalOperations() {
        try (ReleasableLock lock = readLock.acquire()) {
            return numOperations;
        }
    }

    /**
     * Returns the size in bytes of the translog files that aren't committed to lucene.
     */
    public long sizeInBytes() {
        try (ReleasableLock lock = readLock.acquire()) {
            return sizeInBytes;
        }
    }
    
    @Override
    public void prepareCommit() throws IOException {
        try (ReleasableLock lock = writeLock.acquire()) {
            this.prepareNumOperations = numOperations;
            this.prepareSizeInBytes = sizeInBytes;
        }
    }
    
    @Override
    public void commit() throws IOException {
        try (ReleasableLock lock = writeLock.acquire()) {
            this.numOperations = this.numOperations - this.prepareNumOperations;
            this.sizeInBytes = this.sizeInBytes - this.prepareSizeInBytes;
        }
    }
    
    public AsyncLogWriter getLogWriter() {
        return this.logWriter;
    }
    
    public boolean isReaderClosed() {
        if (logReader == null) {
            return true;
        }
        return false;
    }
    
    public boolean isWriterClosed() {
        if (logWriter == null) {
            return true;
        }
        return false;
    }
    
    public void closeLogReader() {
        try (ReleasableLock lock = writeLock.acquire()) {
            if (logReader != null) {
                logReader.asyncClose();
            }
        } finally {
            logReader = null;
        }
    }
    
    public void closeLogWriter() {
        if (logWriter == null) return;
        try (ReleasableLock lock = writeLock.acquire()) {
            if (logWriter != null) {
                logWriter.asyncClose();
            }
        } finally {
            logWriter = null;
        }
    }
    
    public void updateBuffer(ByteSizeValue bufferSize) {
        // empty method just implement the interface
    }
    
    public static Operation getOperationFromLogRecord(LogRecordWithDLSN logRecord) throws IOException {
        ByteBuffer bf = ByteBuffer.wrap(logRecord.getPayload());
        ByteBufferStreamInput in = new ByteBufferStreamInput(bf);
        Translog.Operation.Type type = Translog.Operation.Type.fromId(in.readByte());
        Translog.Operation operation = newOperationFromType(type);
        operation.readFrom(in);
        in.close();
        return operation;
    }
    
    /**
     * Returns a new empty translog operation for the given {@link Translog.Operation.Type}
     */
    public static Translog.Operation newOperationFromType(Translog.Operation.Type type) throws IOException {
        switch (type) {
            case CREATE:
                return new Translog.Create();
            case DELETE:
                return new Translog.Delete();
            case SAVE:
                return new Translog.Index();
            default:
                throw new IOException("No type for [" + type + "]");
        }
    }

    private static String getLogName(String indexName, String indexUUID, int shardId) {
        // index name may contains invalid characters that dl not recognized
        String encodedIndexName = Base64.encodeBytes(indexName.getBytes());
        return String.format("%s_%s_%d", encodedIndexName, indexUUID, shardId);
    }
    
    @Override
    public void close() throws IOException {
        synchronized (this) {
            closeLogReader();
            closeLogWriter();
            if (logManager != null) {
                // should not call close, because it will hang when zk is crashed
                logManager.asyncClose();
                logManager = null;
            }
            IOUtils.close(localTranslog);
        }
    }
    
    public Location writeToLocal(BytesReference data) throws IOException {
        return localTranslog.writeToLocal(data);
    }
    
    public long getLocalTranslogGeneration() {
        return localTranslog.getLocalTranslogGeneration();
    }
    
    public void deleteLogBeforeGeneration(final long generation) {
        this.localTranslog.deleteLogBeforeGeneration(generation);
    }
    
    public Translog.Operation readFromLocal(Location location) {
        if (location == null) {
            return null;
        }
        return this.localTranslog.readFromLocal(location);
    }
}




