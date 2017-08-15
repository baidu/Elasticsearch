/*
 * Licensed to Crate.IO GmbH ("Crate") under one or more contributor
 * license agreements.  See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.  Crate licenses
 * this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.  You may
 * obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * However, if you have executed another commercial license agreement
 * with Crate these terms will supersede the license and you may use the
 * software solely pursuant to the terms of the relevant commercial agreement.
 */

package io.crate.action.sql;

import io.crate.analyze.AnalyzedStatement;
import io.crate.analyze.AnalyzedStatementVisitor;
import io.crate.analyze.MetaDataToASTNodeResolver;
import io.crate.analyze.ShowCreateTableAnalyzedStatement;
import io.crate.analyze.ShowGrantsAnalyzedStatement;
import io.crate.analyze.ShowTenantsAnalyzedStatement;
import io.crate.core.collections.ArrayBucket;
import io.crate.core.collections.Row1;
import io.crate.core.collections.SingleRowBucket;
import io.crate.executor.QueryResult;
import io.crate.executor.TaskResult;
import io.crate.executor.transport.TransportActionProvider;
import io.crate.sql.SqlFormatter;
import io.crate.sql.tree.CreateTable;

import org.elasticsearch.action.admin.cluster.tenant.show.ShowTenantsRequest;
import org.elasticsearch.action.admin.cluster.tenant.show.ShowTenantsResponse;
import org.elasticsearch.action.admin.cluster.user.show.ShowUserPropertyRequest;
import org.elasticsearch.action.admin.cluster.user.show.ShowUserPropertyResponse;
import org.elasticsearch.authentication.LoginUserContext;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.metadata.MetaData;
import org.elasticsearch.cluster.metadata.TenantMetadata;
import org.elasticsearch.cluster.metadata.TenantProperty;
import org.elasticsearch.cluster.metadata.UserProperty;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.Singleton;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Singleton
public class ShowStatementDispatcher extends AnalyzedStatementVisitor<UUID, TaskResult>  {

    private TransportActionProvider transportActionProvider;
    private ClusterService clusterService;
    
    @Inject
    public ShowStatementDispatcher(TransportActionProvider transportActionProvider, ClusterService clusterService) {
        this.transportActionProvider = transportActionProvider;
        this.clusterService = clusterService;
    }
    
    @Override
    protected TaskResult visitAnalyzedStatement(AnalyzedStatement analysis, UUID job) {
        throw new UnsupportedOperationException(String.format(Locale.ENGLISH, "Can't handle \"%s\"", analysis));
    }

    @Override
    public TaskResult visitShowCreateTableAnalyzedStatement(ShowCreateTableAnalyzedStatement analysis, UUID job) {
        CreateTable createTable = MetaDataToASTNodeResolver.resolveCreateTable(analysis.tableInfo());
        String formattedSqlStatement = SqlFormatter.formatSql(createTable);
        return new QueryResult(new SingleRowBucket(new Row1(formattedSqlStatement)));
    }
    
    @Override
    public TaskResult visitShowGrantsAnalyzedStatement(ShowGrantsAnalyzedStatement analysis, UUID job) {
        String username = analysis.getUsername();
        if (username == null || username == "") {
            username = "*";
        }
        ShowUserPropertyRequest showUserPropertyRequest = new ShowUserPropertyRequest(username);
        ShowUserPropertyResponse showUserPropertyResponse = new ShowUserPropertyResponse();
        int columnNum = analysis.fields().size();
        try {
            if (analysis.getParameterContext() != null) {
                showUserPropertyRequest.putHeader(LoginUserContext.USER_INFO_KEY, analysis.getParameterContext().getLoginUserContext());
            }
            showUserPropertyResponse = transportActionProvider.transportShowUserPropertyAction().execute(showUserPropertyRequest).get();
            List<UserProperty> userProperties = showUserPropertyResponse.getUserProperties();
            int rowNum = userProperties.size();
            String[][] result = new String[rowNum][columnNum];
            MetaData metaData = clusterService.state().metaData();
            for (int i = 0; i < rowNum; ++i) {
                UserProperty userProperty = userProperties.get(i);
                result[i][0] = userProperty.getUsernameWithoutTenant();
                if (userProperty.getTenantId() == TenantProperty.ROOT_TENANT_ID) {
                    result[i][1] = TenantProperty.ROOT_TENANT_NAME;
                } else {
                    if (metaData.getTenantMetadata().getTenantProperty(userProperty.getTenantId()) == null) {
                        result[i][1] = "";
                    } else {
                        result[i][1] = metaData.getTenantMetadata().getTenantProperty(userProperty.getTenantId()).tenantName();
                    }
                }
                result[i][2] = UserProperty.getStringFromMap(userProperty.getDbPrivileges());
                result[i][3] = UserProperty.getStringFromMap(userProperty.getTablePrivileges());
                result[i][4] = UserProperty.getStringFromArray(userProperty.getIpWhiteList());
                result[i][5] = UserProperty.getStringFromArray(userProperty.getHostnameWhiteList());
                result[i][6] = UserProperty.getStringFromArray(userProperty.getHostIpWhiteList());
            }
            return new QueryResult(new ArrayBucket(result));
        } catch (InterruptedException | ExecutionException e) {
        }
        return new QueryResult(new ArrayBucket(new String[0][columnNum]));
    }
    
    @Override
    public TaskResult visitShowTenantsAnalyzedStatement(ShowTenantsAnalyzedStatement analysis, UUID job) {
        ShowTenantsRequest showTenantsRequest = new ShowTenantsRequest();
        ShowTenantsResponse showTenantsResponse = new ShowTenantsResponse();
        int columnNum = analysis.fields().size();
        try {
            showTenantsResponse = transportActionProvider.transportShowTenantsAction().execute(showTenantsRequest).get();
            List<TenantProperty> tenantProperties = showTenantsResponse.getTenantProperties();
            int rowNum = tenantProperties.size();
            String[][] result = new String[rowNum][columnNum];
            for (int i = 0; i < rowNum; ++i) {
                TenantProperty tenantProperty = tenantProperties.get(i);
                result[i][0] = String.valueOf(tenantProperty.tenantId());
                result[i][1] = tenantProperty.tenantName();
                result[i][2] = tenantProperty.nodes().values().toString();
                result[i][3] = String.valueOf(tenantProperty.desireInstanceNum());
                result[i][4] = String.valueOf(tenantProperty.nodes().size());
            }
            return new QueryResult(new ArrayBucket(result));
        } catch (InterruptedException | ExecutionException e) {
        }
        return new QueryResult(new ArrayBucket(new String[0][columnNum]));
    }
}

