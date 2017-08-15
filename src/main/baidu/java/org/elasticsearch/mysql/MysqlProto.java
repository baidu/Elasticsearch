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

import org.elasticsearch.action.admin.cluster.user.show.ShowUserPropertyAction;
import org.elasticsearch.action.admin.cluster.user.show.ShowUserPropertyRequest;
import org.elasticsearch.action.admin.cluster.user.show.ShowUserPropertyResponse;
import org.elasticsearch.audit.AuditLog;
import org.elasticsearch.audit.AuditService;
import org.elasticsearch.authentication.AuthResult;
import org.elasticsearch.authentication.AuthService;
import org.elasticsearch.bootstrap.BootstrapProxy;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.metadata.UserProperty;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

// MySQL protocol util
public class MysqlProto {
    private static final ESLogger logger = Loggers.getLogger(MysqlProto.class);
    private static final AuditLog auditLog = AuditService.getAuditLog();

    // scramble: data receive from server.
    // randomString: data send by server in plugin data field
    private static boolean authenticate(ConnectContext context, byte[] scramble, byte[] randomString, String user) {
        String usePass = scramble.length == 0 ? "NO" : "YES";

        if (user == null || user.isEmpty()) {
            ErrorReport.report(ErrorCode.ERR_ACCESS_DENIED_ERROR, "", usePass);
            logger.warn(" user == null");
            return false;
        }

        Client nodeClient = BootstrapProxy.getNodeClient();
        ShowUserPropertyRequest request = new ShowUserPropertyRequest(user);
        ShowUserPropertyResponse userPropertyResponse =
                nodeClient.execute(ShowUserPropertyAction.INSTANCE, request).actionGet();
        String userPassword = userPropertyResponse.getUserProperty().getPassword();
        if (userPassword == null) {
            ErrorReport.report(ErrorCode.ERR_ACCESS_DENIED_ERROR, user, usePass);
            return false;
        }

        byte[] mysqlPass = MysqlPassword.getSaltFromPassword(userPassword.getBytes());

        // when the length of password is zero, the user has no password
        if ((scramble.length == mysqlPass.length)
                && (scramble.length == 0 || MysqlPassword.checkScramble(scramble, randomString, mysqlPass))) {
            // authenticate success
            context.setUser(user);
        } else {
            // password check failed.
            ErrorReport.report(ErrorCode.ERR_ACCESS_DENIED_ERROR, user, usePass);
            return false;
        }

        String sourceAddrs = context.getMysqlChannel().getRemote().split(":")[0];
        long startTimestamp = System.currentTimeMillis();
        if (!UserProperty.getUsernameWithoutTenantFromFullUsername(user).equalsIgnoreCase(UserProperty.ROOT_NAME)) {
            Set<String> addrs = new HashSet<>();
            addrs.add(sourceAddrs);

            AuthResult whiteListResult = AuthService.checkWhiteList(user, addrs,
                    userPropertyResponse.getUserProperty().getHostIpWhiteList());
            if (whiteListResult.getStatus() != RestStatus.OK) {
                // whitelist check failed.
                ErrorReport.report(ErrorCode.ERR_IP_ACCESS_DENIED, sourceAddrs, usePass);
                String message = "Login denied, ip not in whitelist:"
                        + userPropertyResponse.getUserProperty().getHostIpWhiteList().toString();
                auditLog.log(startTimestamp, user, sourceAddrs, "login", 0, message);
                return false;
            }
        }

        auditLog.log(startTimestamp, user, sourceAddrs, "login", 0, "mysql authenticate successfully");
        return true;
    }

    // send response packet(OK/EOF/ERR).
    // before call this function, should set information in state of ConnectContext
    public static void sendResponsePacket(ConnectContext context) throws IOException {
        MysqlSerializer serializer = context.getSerializer();
        MysqlChannel channel = context.getMysqlChannel();
        MysqlPacket packet = context.getState().toResponsePacket();

        // send response packet to client
        serializer.reset();
        packet.writeTo(serializer);
        channel.sendAndFlush(serializer.toByteBuffer());
    }

    /**
     * negotiate with client, use MySQL protocol
     * server ---handshake---> client
     * server <--- authenticate --- client
     * server --- response(OK/ERR) ---> client
     * Exception:
     * IOException:
     */
    public static boolean negotiate(ConnectContext context) throws IOException {
        MysqlSerializer serializer = context.getSerializer();
        MysqlChannel channel = context.getMysqlChannel();
        context.getState().setOk();

        // Server send handshake packet to client.
        serializer.reset();
        MysqlHandshakePacket handshakePacket = new MysqlHandshakePacket(context.getConnectionId());
        handshakePacket.writeTo(serializer);
        channel.sendAndFlush(serializer.toByteBuffer());
        
        // Server receive authenticate packet from client.
        ByteBuffer handshakeResponse = channel.fetchOnePacket();
        if (handshakeResponse == null) {
            // receive response failed.
            logger.warn(" receive handshake response failed");
            return false;
        }
        
        MysqlAuthPacket authPacket = new MysqlAuthPacket();
        if (!authPacket.readFrom(handshakeResponse)) {
            ErrorReport.report(ErrorCode.ERR_NOT_SUPPORTED_AUTH_MODE);
            sendResponsePacket(context);
            logger.warn(" err not suppored auth mode");
            return false;
        }
        
        // check capability
        if (!MysqlCapability.isCompatible(context.getServerCapability(), authPacket.getCapability())) {
            // TODO: client return capability can not support
            ErrorReport.report(ErrorCode.ERR_NOT_SUPPORTED_AUTH_MODE);
            sendResponsePacket(context);
            logger.warn(" err not suppored auth mode");
            return false;
        }
        // change the capability of serializer
        context.setCapability(context.getServerCapability());
        serializer.setCapability(context.getCapability());

        // NOTE: when we behind proxy, we need random string sent by proxy.
        byte[] randomString = handshakePacket.getAuthPluginData();

        // check authenticate
        if (!authenticate(context, authPacket.getAuthResponse(), randomString, authPacket.getUser())) {
            sendResponsePacket(context);
            logger.warn("auth false, user={}", authPacket.getUser());
            return false;
        }

        // set database
//        String db = authPacket.getDb();
//        if (!Strings.isNullOrEmpty(db)) {
//            try {
//                Catalog.getInstance().changeDb(context, db);
//            } catch (DdlException e) {
//         sendResponsePacket(context);
//                return false;
//            }
//        }
        return true;
    }

    public static byte readByte(ByteBuffer buffer) {
        return buffer.get();
    }

    public static int readInt1(ByteBuffer buffer) {
        return readByte(buffer) & 0XFF;
    }

    public static int readInt2(ByteBuffer buffer) {
        return (readByte(buffer) & 0xFF) | ((readByte(buffer) & 0xFF) << 8);
    }

    public static int readInt3(ByteBuffer buffer) {
        return (readByte(buffer) & 0xFF) | ((readByte(buffer) & 0xFF) << 8) | ((readByte(
                buffer) & 0xFF) << 16);
    }

    public static int readInt4(ByteBuffer buffer) {
        return (readByte(buffer) & 0xFF) | ((readByte(buffer) & 0xFF) << 8) | ((readByte(
                buffer) & 0xFF) << 16) | ((readByte(buffer) & 0XFF) << 24);
    }

    public static long readInt6(ByteBuffer buffer) {
        return (readInt4(buffer) & 0XFFFFFFFFL) | (((long) readInt2(buffer)) << 32);
    }

    public static long readInt8(ByteBuffer buffer) {
        return (readInt4(buffer) & 0XFFFFFFFFL) | (((long) readInt4(buffer)) << 32);
    }

    public static long readVInt(ByteBuffer buffer) {
        int b = readInt1(buffer);

        if (b < 251) {
            return b;
        }
        if (b == 252) {
            return readInt2(buffer);
        }
        if (b == 253) {
            return readInt3(buffer);
        }
        if (b == 254) {
            return readInt8(buffer);
        }
        if (b == 251) {
            throw new NullPointerException();
        }
        return 0;
    }

    public static byte[] readFixedString(ByteBuffer buffer, int len) {
        byte[] buf = new byte[len];
        buffer.get(buf);
        return buf;
    }

    public static byte[] readEofString(ByteBuffer buffer) {
        byte[] buf = new byte[buffer.remaining()];
        buffer.get(buf);
        return buf;
    }

    public static byte[] readLenEncodedString(ByteBuffer buffer) {
        long length = readVInt(buffer);
        byte[] buf = new byte[(int) length];
        buffer.get(buf);
        return buf;
    }

    public static byte[] readNulTerminateString(ByteBuffer buffer) {
        int oldPos = buffer.position();
        int nullPos = oldPos;
        for (nullPos = oldPos; nullPos < buffer.limit(); ++nullPos) {
            if (buffer.get(nullPos) == 0) {
                break;
            }
        }
        byte[] buf = new byte[nullPos - oldPos];
        buffer.get(buf);
        // skip null byte.
        buffer.get();
        return buf;
    }

}
