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
import com.google.common.collect.Lists;

import io.crate.action.sql.SQLAction;
import io.crate.action.sql.SQLRequestBuilder;
import io.crate.action.sql.SQLResponse;
import io.crate.types.BooleanType;
import io.crate.types.ByteType;
import io.crate.types.DataType;
import io.crate.types.DoubleType;
import io.crate.types.FloatType;
import io.crate.types.IntegerType;
import io.crate.types.IpType;
import io.crate.types.LongType;
import io.crate.types.ShortType;
import io.crate.types.StringType;

import io.crate.types.TimestampType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.entity.ContentType;
import org.elasticsearch.audit.AuditLog;
import org.elasticsearch.audit.AuditService;
import org.elasticsearch.authentication.LoginUserContext;
import org.elasticsearch.bootstrap.BootstrapProxy;
import org.elasticsearch.client.Client;
import org.elasticsearch.rest.LocalRestChannel;
import org.elasticsearch.rest.LocalRestRequest;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;


//Do one COM_QEURY process.
//first: Parse receive byte array to statement struct.
//second: Do handle function for statement.
public class StmtExecutor {
    private final AuditLog auditLog;
	private ConnectContext context;
	private MysqlSerializer serializer;
	private String originStmt;
    private final RestController restController;
    private final Client client;

	public StmtExecutor(ConnectContext context, String stmt) {
		this.context = context;
		this.originStmt = stmt;
		this.serializer = context.getSerializer();
        this.client = BootstrapProxy.getNodeClient();
        this.auditLog = AuditService.getAuditLog();
        this.restController = BootstrapProxy.getInjector().getInstance(RestController.class);
	}

	// Execute one statement.
	// Exception:
	// IOException: talk with client failed.
	public void execute() throws Exception {
		// execute
        long startTime = System.currentTimeMillis();
        try {
            handleQueryStmt();
        } catch (Exception e) {
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            String sourceAddrs = context.getMysqlChannel().getRemote().split(":")[0];
            auditLog.log(startTime, context.getUser(), sourceAddrs, originStmt, endTime - startTime, "");
        }
	}
	
    public static PrimitiveType getPrimitiveTypeFromESType(DataType t) {
        if (t.getName().equals(StringType.INSTANCE.getName())) {
            return PrimitiveType.STRING;
        } else if (t.getName().equals(BooleanType.INSTANCE.getName())) {
            return PrimitiveType.BOOL;
        } else if (t.getName().equals(ByteType.INSTANCE.getName())) {
            return PrimitiveType.BYTE;
        } else if (t.getName().equals(DoubleType.INSTANCE.getName())) {
            return PrimitiveType.DOUBLE;
        } else if (t.getName().equals(FloatType.INSTANCE.getName())) {
            return PrimitiveType.FLOAT;
        } else if (t.getName().equals(IntegerType.INSTANCE.getName())) {
            return PrimitiveType.INT;
        } else if (t.getName().equals(LongType.INSTANCE.getName())) {
            return PrimitiveType.LONG;
        } else if (t.getName().equals(ShortType.INSTANCE.getName())) {
            return PrimitiveType.SHORT;
        } else if (t.getName().equals(TimestampType.INSTANCE.getName())) {
            return PrimitiveType.TIMESTAMP;
        }
        return PrimitiveType.STRING;
    }
    
	// Process a select statement.
    private void handleQueryStmt() throws Exception {
        // Every time set no send flag and clean all data in buffer
        context.getMysqlChannel().reset();
        originStmt = originStmt.trim();

        if (originStmt.substring(0,4).toLowerCase().equals("http")) {
            String[] array = originStmt.split("\\s+|\\n+", 4);
            if (array.length < 3) {
                context.getState().setError("Not enough parameters");
                return;
            }
            RestRequest.Method method;
            switch (array[1].toLowerCase()) {
                case "get" :
                    method = RestRequest.Method.GET;
                    break;
                case "put" :
                    method = RestRequest.Method.PUT;
                    break;
                case "post" :
                    method = RestRequest.Method.POST;
                    break;
                case "delete" :
                    method = RestRequest.Method.DELETE;
                    break;
                case "head" :
                    method = RestRequest.Method.HEAD;
                    break;
                default:
                    context.getState().setError("Unsupported method");
                    return;
            }

            LocalRestRequest localRestRequest = new LocalRestRequest(array[2], method);
            localRestRequest.addParameter(LoginUserContext.USERNAME_KEY, context.getUser())
                    .addParameter(LoginUserContext.AUTHENTICATED_KEY, Boolean.TRUE.toString());
            if (array.length > 3) {
                 localRestRequest.setContent(array[3], ContentType.APPLICATION_JSON);
            }
            LocalRestChannel localRestChannel = new LocalRestChannel(localRestRequest, false);
            restController.dispatchRequest(localRestRequest, localRestChannel);

            String result;
            if (array[1].toLowerCase().equals("head")) {
                result = Integer.toString(localRestChannel.getStatus());
            } else {
                result = localRestChannel.getContent();
            }

            sendSingleField(array[1] + " RESULT", result);
            return;
        } else if (originStmt.toLowerCase().startsWith("select @@version_comment")) {
            sendSingleField("@@version_comment", "Baidu Elasticsearch Edition");
            return;
        } else if (originStmt.toLowerCase().equals("select database()")) {
            sendSingleField("DATABASE()", "unsupported command");
            return;
        } else if (originStmt.toLowerCase().startsWith("/* mysql-connector-java") ||
                originStmt.trim().toLowerCase().startsWith("select  @@session")) {
            sendSessionVariable();
            return;
        } else if (originStmt.substring(0,3).toLowerCase().startsWith("set")) {
            String[] array = originStmt.split("\\s+|\\n+", 3);
            if (!array[1].toLowerCase().equals("global")) {
                context.getState().setAffectRows(1);
                context.getState().setOk();
                return;
            }
        }

        // if python's MysqlDb get error after sendfields, it can't catch the
        // excpetion
        // so We need to send fields after first batch arrived
        final SQLRequestBuilder requestBuilder = new SQLRequestBuilder(client, SQLAction.INSTANCE);
        requestBuilder.stmt(originStmt);
        requestBuilder.setLoginUsername(context.getUser());
        requestBuilder.includeTypesOnResponse(true);
        SQLResponse sqlResponse = requestBuilder.execute().get();

        int colNum = sqlResponse.columnTypes().length;
        if (colNum == 0) {
            if (sqlResponse.hasRowCount()) {
                context.getState().setAffectRows(sqlResponse.rowCount());
            }
            context.getState().setOk();
        } else {
            // send field
            List<PrimitiveType> returnTypes = Lists.newArrayList();
            List<String> returnColumnNames = Lists.newArrayList();
            for (int typeIndex = 0; typeIndex < colNum; ++typeIndex) {
                returnTypes.add(getPrimitiveTypeFromESType(sqlResponse.columnTypes()[typeIndex]));
                returnColumnNames.add(sqlResponse.cols()[typeIndex]);
            }
            sendFields(returnColumnNames, returnTypes);

            // send result
            for (int rowIndex = 0; rowIndex < sqlResponse.rowCount(); ++rowIndex) {
                serializer.reset();
                for (int colIndex = 0; colIndex < colNum; ++colIndex) {
                    Object obj = sqlResponse.rows()[rowIndex][colIndex];
                    if (obj != null) {
                        String type = sqlResponse.columnTypes()[colIndex].getName();
                        if (type.equals(StringType.INSTANCE.getName())
                                || type.equals(LongType.INSTANCE.getName())
                                || type.equals(BooleanType.INSTANCE.getName())
                                || type.equals(ByteType.INSTANCE.getName())
                                || type.equals(DoubleType.INSTANCE.getName())
                                || type.equals(FloatType.INSTANCE.getName())
                                || type.equals(IntegerType.INSTANCE.getName())
                                || type.equals(ShortType.INSTANCE.getName())
                                || type.equals(TimestampType.INSTANCE.getName())
                                || type.equals(IpType.INSTANCE.getName())
                                ) {
                            serializer.writeLenEncodedString(obj.toString());
                        } else if (obj.getClass().isArray()) {
                            JSONArray jsonArray = JSONArray.fromObject(obj);
                            serializer.writeLenEncodedString(jsonArray.toString());
                        } else {
                            JSONObject json = JSONObject.fromObject(obj);
                            serializer.writeLenEncodedString(json.toString());
                        }
                    } else {
                        serializer.writeNull();
                    }
                }
                context.getMysqlChannel().sendOnePacket(serializer.toByteBuffer());
            }
            context.getState().setEof();
         }
    }

	private void sendFields(List<String> colNames, List<PrimitiveType> types)
			throws IOException {
		// sends how many columns
		serializer.reset();
		serializer.writeVInt(colNames.size());
		context.getMysqlChannel().sendOnePacket(serializer.toByteBuffer());
		// send field one by one
		for (int i = 0; i < colNames.size(); ++i) {
			serializer.reset();
			serializer.writeField(colNames.get(i), types.get(i));
			context.getMysqlChannel().sendOnePacket(serializer.toByteBuffer());
		}
		// send EOF
		serializer.reset();
		MysqlEofPacket eofPacket = new MysqlEofPacket(context.getState());
		eofPacket.writeTo(serializer);
		context.getMysqlChannel().sendOnePacket(serializer.toByteBuffer());
	}

    private void sendSingleField(String colNames, String result) throws IOException {
        List<PrimitiveType> returnTypes = Lists.newArrayList();
        returnTypes.add(PrimitiveType.STRING);
        List<String> returnColumnNames = Lists.newArrayList();
        returnColumnNames.add(colNames);
        sendFields(returnColumnNames, returnTypes);

        serializer.reset();
        serializer.writeLenEncodedString(result);
        context.getMysqlChannel().sendOnePacket(serializer.toByteBuffer());
        context.getState().setEof();
    }

    private void sendSessionVariable() throws IOException {
        Map<String, SessionVariable> sessionMap = SessionVariable.sessionMap;
        List<PrimitiveType> returnTypes = Lists.newArrayList();
        List<String> returnColumnNames = Lists.newArrayList();
        List<String> returnValues = Lists.newArrayList();
        for (String sessionKey : sessionMap.keySet()) {
            returnTypes.add(sessionMap.get(sessionKey).getType());
            returnColumnNames.add(sessionMap.get(sessionKey).getName());
            returnValues.add(sessionMap.get(sessionKey).getValue());
        }
        sendFields(returnColumnNames, returnTypes);

        serializer.reset();
        for (String value : returnValues) {
            serializer.writeLenEncodedString(value);
        }
        context.getMysqlChannel().sendOnePacket(serializer.toByteBuffer());
        context.getState().setEof();
    }

	public void cancel() {
		throw new RuntimeException("not implement");
		// TODO Auto-generated method stub
	}
}
