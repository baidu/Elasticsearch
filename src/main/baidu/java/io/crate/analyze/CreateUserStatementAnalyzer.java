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
import io.crate.sql.tree.CreateUser;
import io.crate.sql.tree.DefaultTraversalVisitor;
import io.crate.sql.tree.Node;

import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.metadata.UserProperty;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.rest.RestStatus;

public class CreateUserStatementAnalyzer extends DefaultTraversalVisitor<CreateUserAnalyzedStatement, Analysis> {

    private ClusterService clusterService;
    @Inject
    public CreateUserStatementAnalyzer(ClusterService clusterService) {
        this.clusterService = clusterService;
    }

    @Override
    public CreateUserAnalyzedStatement visitCreateUser(CreateUser node, Analysis context) {
        UserProperty currentOperateUser = context.parameterContext().userProperty();
        if (!currentOperateUser.getUsernameWithoutTenant().equalsIgnoreCase(UserProperty.SUPER_USER_NAME) 
                && !currentOperateUser.getUsernameWithoutTenant().equalsIgnoreCase(UserProperty.ROOT_NAME)) {
            throw new NoPermissionException(RestStatus.FORBIDDEN.getStatus(), "only superuser and root have permission to create user");
        }
        String fullUserName = node.getUsername();
        if (!currentOperateUser.getUsernameWithoutTenant().equalsIgnoreCase(UserProperty.ROOT_NAME)
                && UserProperty.haveTenantInfo(fullUserName)) {
            throw new NoPermissionException(RestStatus.FORBIDDEN.getStatus(), "only root user have permission to create user by specifying tenant name");
        }
        if (UserProperty.haveTenantInfo(fullUserName)) {
            long createUserTenantId = UserProperty.getTenantIdFromLoginUserName(fullUserName, clusterService.state().metaData().tenantMetadata());
            String userName = UserProperty.getUsernameWithoutTenantFromFullUsername(fullUserName);
            fullUserName = UserProperty.generateUsernameWithTenant(userName, createUserTenantId);
        } else {
            fullUserName = UserProperty.generateUsernameWithTenant(fullUserName, currentOperateUser.getTenantId());
        }
        if (UserProperty.ROOT_NAME.equalsIgnoreCase(UserProperty.getUsernameWithoutTenantFromFullUsername(fullUserName)) 
                || UserProperty.SUPER_USER_NAME.equalsIgnoreCase(UserProperty.getUsernameWithoutTenantFromFullUsername(fullUserName))) {
            throw new NoPermissionException(RestStatus.NOT_ACCEPTABLE.getStatus(), "could not create superuser or root in or out tenant, they are created by default");
        }
        CreateUserAnalyzedStatement statement = new CreateUserAnalyzedStatement(fullUserName, node.getPassword());
        return statement;
    }

    public AnalyzedStatement analyze(Node node, Analysis analysis) {
        analysis.expectsAffectedRows(true);
        return process(node, analysis);
    }
}
