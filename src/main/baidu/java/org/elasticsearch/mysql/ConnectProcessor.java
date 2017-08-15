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

package org.elasticsearch.mysql;

import com.google.common.base.Strings;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;

/**
 * Process one mysql connection, receive one pakcet, process, send one packet.
 */
public class ConnectProcessor {
    private static final ESLogger logger = Loggers.getLogger(ConnectProcessor.class);

    private final ConnectContext ctx;
    private ByteBuffer packetBuf;

    private StmtExecutor executor = null;

    public ConnectProcessor(ConnectContext context) {
        this.ctx = context;
    }

	// COM_INIT_DB: change current database of this session.
    private void handleInitDb() {
        String dbName = new String(packetBuf.array(), 1, packetBuf.limit() - 1);
        ctx.getState().setOk();
    }

    // COM_QUIT: set killed flag and then return OK packet.
    private void handleQuit() {
        ctx.setKilled();
        ctx.getState().setOk();
    }

    // process COM_PING statement, do nothing, just return one OK packet.
    private void handlePing() {
        ctx.getState().setOk();
    }


    // process COM_QUERY statement,
    // when 
    private void handleQuery() {
        // convert statement to Java string
        String stmt = null;
        try {
            byte[] bytes = packetBuf.array();
            int ending = packetBuf.limit() - 1;
            while (ending >= 1 && bytes[ending] == '\0') {
                ending--;
            }
            stmt = new String(bytes, 1, ending, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // impossible
            logger.error("UTF8 is not supported in this environment.");
            ctx.getState().setError("Unsupported character set(UTF-8)");
            return;
        }

        // execute this query.
        try {
            executor = new StmtExecutor(ctx, stmt);
            executor.execute();
            // needForward = executor.isForwardtoMaster();
            // outputPacket = executor.getOutputPacket();
        } catch (Throwable e) {
            // Catch all throwable.
            // If reach here, maybe palo bug.
            logger.warn("Process one query failed because unknown reason: ", e);
            ctx.getState().setError(e.getMessage());
        }
    }

    // Get the column definitions of a table
    private void handleFieldList() throws IOException {
        // Already get command code.
        String tableName = null;
        String pattern = null;
        try {
            tableName = new String(MysqlProto.readNulTerminateString(packetBuf), "UTF-8");
            pattern = new String(MysqlProto.readEofString(packetBuf), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // Impossible!!!
            logger.error("Unknown UTF-8 character set.");
            return;
        }
        if (Strings.isNullOrEmpty(tableName)) {
            ctx.getState().setError("Empty tableName");
            return;
        }
   
		MysqlSerializer serializer = ctx.getSerializer();
		MysqlChannel channel = ctx.getMysqlChannel();

		// Send fields
		// NOTE: Field list doesn't send number of fields
		serializer.reset();
		serializer.writeField("DB", "TABLE", new Column("KEY", PrimitiveType.STRING, ""), true);
        serializer.writeField("DB", "TABLE", new Column("VALUE", PrimitiveType.INT, ""), true);
		//serializer.writeField("DB", "TABLE", new Column(new String(TMemoryKVImpl.COLUMN_NAME), PrimitiveType.INT, ""), true);
		channel.sendOnePacket(serializer.toByteBuffer());

        ctx.getState().setEof();
    }

    private void dispatch() throws IOException {
        int code = packetBuf.get();
        MysqlCommand command = MysqlCommand.fromCode(code);
        if (command == null) {
            ErrorReport.report(ErrorCode.ERR_UNKNOWN_COM_ERROR);
            ctx.getState().setError("Unknown command(" + command + ")");
            logger.warn("Unknown command(" + command + ")");
            return;
        }
        ctx.setCommand(command);
        ctx.setStartTime();
        switch (command) {
            case COM_INIT_DB:
                handleInitDb();
                break;
            case COM_QUIT:
                handleQuit();
                break;
            case COM_QUERY:
                handleQuery();
                ctx.setStartTime();
                break;
            case COM_FIELD_LIST:
                handleFieldList();
                break;
            case COM_PING:
                handlePing();
                break;
            default:
                ctx.getState().setError("Unsupported command(" + command + ")");
                logger.warn("Unsupported command(" + command + ")");
                break;
        }
    }

    private ByteBuffer getResultPacket() {
        MysqlPacket packet = ctx.getState().toResponsePacket();
        if (packet == null) {
            logger.warn(" getResult packet = null");
            // 当出现此种情况可能有两种可能
            // 1. 处理函数已经发送请求
            // 2. 这个协议不需要发送任何响应包
            return null;
        }

        MysqlSerializer serializer = ctx.getSerializer();
        serializer.reset();
        packet.writeTo(serializer);
        return serializer.toByteBuffer();
    }

    // 当任何一个请求完成后，一般都会需要发送一个响应包给客户端
    // 这个函数用于发送响应包给客户端
    private void finalizeCommand() throws IOException {
        ByteBuffer packet = null;
       
		packet = getResultPacket();
		if (packet == null) {
            logger.warn(" packet == null");
			return;
		}
        MysqlChannel channel = ctx.getMysqlChannel();
        channel.sendAndFlush(packet);
    }

    // 处理一个MySQL请求，接收，处理，返回
    public void processOnce() throws IOException {
        // set status of query to OK.
        ctx.getState().reset();
        executor = null;

        // reset sequence id of MySQL protocol
        final MysqlChannel channel = ctx.getMysqlChannel();
        channel.setSequenceId(0);
        // read packet from channel
        try {
            packetBuf = channel.fetchOnePacket();
            if (packetBuf == null) {
                logger.warn("Null packet received from network. remote: {}", channel.getRemote());
                throw new IOException("Error happened when receiving packet.");
            }
        } catch (AsynchronousCloseException e) {
            // when this happened, timeout checker close this channel
            // killed flag in ctx has been already set, just return
            return;
        }

        // dispatch
        dispatch();
        // finalize
        finalizeCommand();

        ctx.setCommand(MysqlCommand.COM_SLEEP);
    }

    public void loop() {
        while (!ctx.isKilled()) {
            try {
                processOnce();
            } catch (Exception e) {
                // TODO(zhaochun): something wrong
                logger.warn("Exception happened in one seesion(" + ctx + ").", e);
                ctx.setKilled();
                break;
            }
        }
    }
}