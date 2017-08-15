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

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.store.AlreadyClosedException;
import org.apache.lucene.util.IOUtils;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.io.Channels;
import org.elasticsearch.common.io.stream.ByteBufferStreamInput;
import org.elasticsearch.common.util.concurrent.ReleasableLock;
import org.elasticsearch.index.shard.AbstractIndexShardComponent;
import org.elasticsearch.index.translog.Translog;
import org.elasticsearch.index.translog.TranslogConfig;
import org.elasticsearch.index.translog.Translog.Location;

public class LocalTranslog extends AbstractIndexShardComponent implements Closeable {

    private static final String TMP_TRANSLOG_FILE_PREFIX = "tmp-translog-";
    private static final String TMP_TRANSLOG_FILE_SUFFIX = ".tmplog";
    private static final Pattern PARSE_STRICT_ID_PATTERN = Pattern.compile("^" + TMP_TRANSLOG_FILE_PREFIX + "(\\d+)(\\.tmplog)$");
    private final long TRANSLOG_ROLLING_SIZE_BYTES = 100 << 20; // 100MB

    protected final ReleasableLock readLock;
    protected final ReleasableLock writeLock;
    private Path translogPath = null;
    private AtomicLong tmpTranslogGeneration = new AtomicLong(0);
    private FileChannel writeChannel = null;
    private ConcurrentHashMap<Long, FileChannel> readChannels = new ConcurrentHashMap<Long, FileChannel>();
    protected volatile long writtenOffset;
    private final AtomicBoolean closed = new AtomicBoolean(false);
    
    public LocalTranslog(TranslogConfig config) throws IOException {
        super(config.getShardId(), config.getIndexSettings());
        ReadWriteLock rwl = new ReentrantReadWriteLock();
        readLock = new ReleasableLock(rwl.readLock());
        writeLock = new ReleasableLock(rwl.writeLock());
        this.translogPath = config.getTranslogPath();
        // clean all files
        Files.createDirectories(this.translogPath);
        Files.walkFileTree(this.translogPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file,
                    BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }
        });
        
        // create a new directory
        writeChannel = FileChannel.open(this.translogPath.resolve(getFileNameFromId(tmpTranslogGeneration.get())), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
        writtenOffset = 0;
    }
    
    private void ensureOpen() {
        if (closed.get()) {
            throw new AlreadyClosedException("translog is already closed");
        }
    }
    
    public Location writeToLocal(BytesReference data) throws IOException {
        final long position;
        final long generation;
        try (ReleasableLock lock = writeLock.acquire()) {
            ensureOpen();
            if (writtenOffset > TRANSLOG_ROLLING_SIZE_BYTES) {
                IOUtils.close(writeChannel);
                tmpTranslogGeneration.incrementAndGet();
                writeChannel = FileChannel.open(this.translogPath.resolve(getFileNameFromId(tmpTranslogGeneration.get())), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
                writtenOffset = 0;
            }
            generation = tmpTranslogGeneration.get();
            position = writtenOffset;
            try {
                data.writeTo(writeChannel);
            } catch (Throwable e) {
                throw e;
            }
            writtenOffset = writtenOffset + data.length();
        }
        return new Translog.Location(generation, position, data.length());
    }
    
    public long getLocalTranslogGeneration() {
        return tmpTranslogGeneration.get();
    }
    
    public void deleteLogBeforeGeneration(final long generation) {
        try (ReleasableLock lock = writeLock.acquire()) {// clean all files
            Files.walkFileTree(this.translogPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file,
                        BasicFileAttributes attrs) throws IOException {
                    if (!Files.isDirectory(file)) {
                        try {
                            long id = parseIdFromFileName(file);
                            if (id < generation) {
                                // remove the channel from the map
                                FileChannel channel = readChannels.remove(generation);
                                if (channel != null) {
                                    IOUtils.close(channel);
                                }
                                Files.delete(file);
                            }
                        } catch (Throwable t) {
                            logger.warn("errors while check file {} and delete it", t, file);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (Throwable t) {
            logger.warn("errors while check translog path {} to delete log before {}", this.translogPath, generation);
        }
    }
    
    private FileChannel openReader(long generationId) throws IOException {
        ensureOpen();
        if (readChannels.containsKey(generationId)) {
            return readChannels.get(generationId);
        }
        try {
            Path translogFilePath = this.translogPath.resolve(getFileNameFromId(tmpTranslogGeneration.get()));
            if (!Files.exists(translogFilePath)) {
                return null;
            }
            // maybe a lot of readers try to open reader and put it to readChannel cache, because read lock is shared
            FileChannel readChannel = FileChannel.open(translogFilePath, StandardOpenOption.READ);
            FileChannel originReadChannel = readChannels.putIfAbsent(generationId, readChannel);
            if (originReadChannel != null) {
                IOUtils.close(readChannel);
                return originReadChannel;
            } else {
                return readChannel;
            }
        } catch (Throwable e) {
            throw e;
        }
    }
    
    public Translog.Operation readFromLocal(Location location) {
        try (ReleasableLock lock = readLock.acquire()) {
            ensureOpen();
            long fileGeneration = location.generation;
            FileChannel readChannel = readChannels.get(fileGeneration);
            if (readChannel == null) {
                readChannel = openReader(fileGeneration);
            }
            if (readChannel == null) {
                // throw exception since could not find reader
                throw new ElasticsearchException("could not open reader for file generation {}", fileGeneration);
            }
            ByteBuffer buffer = ByteBuffer.allocate(location.size);
            Channels.readFromFileChannelWithEofException(readChannel, location.translogLocation, buffer);
            buffer.flip();
            ByteBufferStreamInput in = new ByteBufferStreamInput(buffer);
            Translog.Operation.Type type = Translog.Operation.Type.fromId(in.readByte());
            Translog.Operation operation = DistributedTranslog.newOperationFromType(type);
            operation.readFrom(in);
            in.close();
            return operation;
        } catch (IOException e) {
            logger.error("errors while read from local", e);
            throw new ElasticsearchException("failed to read source from translog location " + location, e);
        }
    }
    
    public static long parseIdFromFileName(Path translogFile) {
        final String fileName = translogFile.getFileName().toString();
        final Matcher matcher = PARSE_STRICT_ID_PATTERN.matcher(fileName);
        if (matcher.matches()) {
            try {
                return Long.parseLong(matcher.group(1));
            } catch (NumberFormatException e) {
                throw new IllegalStateException("number formatting issue in a file that passed PARSE_STRICT_ID_PATTERN: " + fileName + "]", e);
            }
        }
        throw new IllegalArgumentException("can't parse id from file: " + fileName);
    }

    
    public static String getFileNameFromId(long id) {
        return TMP_TRANSLOG_FILE_PREFIX + id + TMP_TRANSLOG_FILE_SUFFIX;
    }

    @Override
    public void close() throws IOException {
        if (closed.compareAndSet(false, true)) {
            try (ReleasableLock lock = writeLock.acquire()) {
                IOUtils.close(writeChannel);
                for (FileChannel channel : readChannels.values()) {
                    IOUtils.close(channel);
                }
            } finally {
                logger.debug("translog closed");
            }
        }
    }
}
