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
import io.crate.sql.tree.Node;
import io.crate.sql.tree.ResetWhitelist;

import org.elasticsearch.authentication.AuthService;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.metadata.UserProperty;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.rest.RestStatus;

public class ResetWhitelistStatementAnalyzer extends DefaultTraversalVisitor<ResetWhitelistAnalyzedStatement, Analysis> {

    private ClusterService clusterService;
    
    @Inject
    public ResetWhitelistStatementAnalyzer(ClusterService clusterService) {
        this.clusterService = clusterService;
    }

    @Override
    public ResetWhitelistAnalyzedStatement visitResetWhitelist(ResetWhitelist node, Analysis context) {
        UserProperty currentOperateUser = context.parameterContext().userProperty();
        if (!currentOperateUser.getUsernameWithoutTenant().equalsIgnoreCase(UserProperty.SUPER_USER_NAME) 
                && !currentOperateUser.getUsernameWithoutTenant().equalsIgnoreCase(UserProperty.ROOT_NAME)) {
            throw new NoPermissionException(RestStatus.FORBIDDEN.getStatus(), "only superuser and root have permission to modify user's whitelist");
        }
        String fullUserName = node.getUsername();
        if (!currentOperateUser.getUsernameWithoutTenant().equalsIgnoreCase(UserProperty.ROOT_NAME)
                && UserProperty.haveTenantInfo(fullUserName)) {
            throw new NoPermissionException(RestStatus.FORBIDDEN.getStatus(), "only root user could set user's whitelist by specifying the tenant name");
        }
        if (UserProperty.haveTenantInfo(fullUserName)) {
            fullUserName = clusterService.state().metaData().getUserMetadata()
                .getUserPropertyFromLoginUser(node.getUsername(), clusterService.state().metaData().getTenantMetadata())
                .getUsernameWithTenant();
        } else {
            fullUserName = UserProperty.generateUsernameWithTenant(fullUserName, currentOperateUser.getTenantId());
        }
        if (AuthService.compareUserPrivilege(currentOperateUser.getUsernameWithoutTenant(), fullUserName) < 0) {
            throw new NoPermissionException(RestStatus.FORBIDDEN.getStatus(), 
                    String.format("user [%s] have no permission to modify user[%s]'s whitelist", 
                            currentOperateUser.getUsernameWithoutTenant(), node.getUsername()));
        }
        ResetWhitelistAnalyzedStatement statement = new ResetWhitelistAnalyzedStatement(fullUserName, node.getWhitelist());
        return statement;
    }

    public AnalyzedStatement analyze(Node node, Analysis analysis) {
        analysis.expectsAffectedRows(true);
        return process(node, analysis);
    }
}