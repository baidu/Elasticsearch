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

package io.crate.analyze;

import io.crate.exceptions.NoPermissionException;
import io.crate.sql.tree.DefaultTraversalVisitor;
import io.crate.sql.tree.GrantPrivilege;
import io.crate.sql.tree.Node;

import org.elasticsearch.authentication.AuthResult;
import org.elasticsearch.authentication.AuthService;
import org.elasticsearch.authentication.VirtualTableNames;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.metadata.PrivilegeType;
import org.elasticsearch.cluster.metadata.UserProperty;
import org.elasticsearch.common.collect.Tuple;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.rest.RestStatus;

public class GrantPrivilegeStatementAnalyzer extends DefaultTraversalVisitor<GrantPrivilegeAnalyzedStatement, Analysis> {

    private ClusterService clusterService;
    @Inject
    public GrantPrivilegeStatementAnalyzer(ClusterService clusterService) {
        this.clusterService = clusterService;
    }

    @Override
    public GrantPrivilegeAnalyzedStatement visitGrantPrivilege(GrantPrivilege node, Analysis context) {
        // Add SQL Authentication
        UserProperty currentOperateUser = context.parameterContext().userProperty();
        if (!currentOperateUser.getUsernameWithoutTenant().equalsIgnoreCase(UserProperty.SUPER_USER_NAME) 
                && !currentOperateUser.getUsernameWithoutTenant().equalsIgnoreCase(UserProperty.ROOT_NAME)) {
            throw new NoPermissionException(RestStatus.FORBIDDEN.getStatus(), "only superuser and root have permission to grant privilege");
        }
        if (UserProperty.ROOT_NAME.equalsIgnoreCase(node.getUsername())) {
            throw new NoPermissionException(RestStatus.FORBIDDEN.getStatus(), "could not modify root's privilege");
        }
        String fullUserName = node.getUsername();
        if (!currentOperateUser.getUsernameWithoutTenant().equalsIgnoreCase(UserProperty.ROOT_NAME)
                && UserProperty.haveTenantInfo(fullUserName)) {
            throw new NoPermissionException(RestStatus.FORBIDDEN.getStatus(), "only root user could grant the privilege by specifying the tenantid");
        }
        if (UserProperty.haveTenantInfo(fullUserName)) {
            UserProperty userProperty = clusterService.state().metaData().getUserMetadata().getUserPropertyFromLoginUser(node.getUsername(), clusterService.state().metaData().getTenantMetadata());
            if (userProperty == null) {
                throw new NoPermissionException(RestStatus.FORBIDDEN.getStatus(), "user not exist " + fullUserName);
            }
            fullUserName = userProperty.getUsernameWithTenant();
        } else {
            fullUserName = UserProperty.generateUsernameWithTenant(fullUserName, currentOperateUser.getTenantId());
        }
        // could not grant privilege on sys db
        String dbName = null;
        String tableName = null;
        if (node.getTable().getName().toString().contains(".")) {
            Tuple<String, String> dbAndTableName = UserProperty.getDBAndTableName(node.getTable().getName().toString());
            dbName = dbAndTableName.v1();
            tableName = dbAndTableName.v2();
        } else {
            dbName = node.getTable().getName().toString();
        }
        if (VirtualTableNames.sys.name().equalsIgnoreCase(dbName)) {
            throw new NoPermissionException(RestStatus.FORBIDDEN.getStatus(), "could not grant privilege on sys db");
        }
        // check if current operate user have privilege on the db or table
        PrivilegeType privilegeType = PrivilegeType.valueOf(node.getPrivilege().toUpperCase());
        AuthResult authResult = AuthService.internalAuthenticate(currentOperateUser, dbName, tableName, privilegeType);
        if (authResult.getStatus() != RestStatus.OK) {
            throw new NoPermissionException(authResult.getStatus().getStatus(), "errors while grant privilege on " 
                    + node.getTable().getName().toString() + ", error message is: " + authResult.getMessage());
        }
        context.parameterContext().getLoginUserContext().setAuthorized(true);
        GrantPrivilegeAnalyzedStatement statement = new GrantPrivilegeAnalyzedStatement(fullUserName, node.getPrivilege(), node.getTable());
        return statement;
    }

    public AnalyzedStatement analyze(Node node, Analysis analysis) {
        analysis.expectsAffectedRows(true);
        return process(node, analysis);
    }
}