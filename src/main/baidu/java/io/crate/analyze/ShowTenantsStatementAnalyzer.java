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
import io.crate.sql.tree.ShowTenants;

import org.elasticsearch.cluster.metadata.UserProperty;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.rest.RestStatus;

public class ShowTenantsStatementAnalyzer extends DefaultTraversalVisitor<ShowTenantsAnalyzedStatement, Analysis> {

    @Inject
    public ShowTenantsStatementAnalyzer() {
    }

    @Override
    public ShowTenantsAnalyzedStatement visitShowTenants(ShowTenants node, Analysis context) {
        UserProperty currentOperateUser = context.parameterContext().userProperty();
        if (!currentOperateUser.getUsernameWithoutTenant().equalsIgnoreCase(UserProperty.ROOT_NAME)) {
            throw new NoPermissionException(RestStatus.FORBIDDEN.getStatus(), "only root have permission to show tenant details");
        }
        ShowTenantsAnalyzedStatement statement = new ShowTenantsAnalyzedStatement();
        return statement;
    }

    public ShowTenantsAnalyzedStatement analyze(Node node, Analysis analysis) {
        analysis.expectsAffectedRows(false);
        return process(node, analysis);
    }
}