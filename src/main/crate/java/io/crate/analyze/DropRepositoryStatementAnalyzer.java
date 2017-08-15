/*
 * Licensed to Crate under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.  Crate licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.  You may
 * obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * However, if you have executed another commercial license agreement
 * with Crate these terms will supersede the license and you may use the
 * software solely pursuant to the terms of the relevant commercial
 * agreement.
 */

package io.crate.analyze;

import io.crate.exceptions.NoPermissionException;
import io.crate.executor.transport.RepositoryService;
import io.crate.sql.tree.DropRepository;
import org.elasticsearch.authentication.AuthResult;
import org.elasticsearch.authentication.AuthService;
import org.elasticsearch.authentication.VirtualTableNames;
import org.elasticsearch.cluster.metadata.PrivilegeType;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.Singleton;
import org.elasticsearch.rest.RestStatus;

@Singleton
public class DropRepositoryStatementAnalyzer extends AbstractRepositoryDDLAnalyzer {

    private final RepositoryService repositoryService;

    @Inject
    public DropRepositoryStatementAnalyzer(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Override
    public DropRepositoryAnalyzedStatement visitDropRepository(DropRepository node, Analysis analysis) {
        // Add SQL Authentication
        // GaoPan 2016/06/16
        AuthResult authResult = AuthService.sqlAuthenticate(analysis.parameterContext().getLoginUserContext(),
                VirtualTableNames.sys.name(), VirtualTableNames.repositories.name(), PrivilegeType.READ_WRITE);
        if (authResult.getStatus() != RestStatus.OK) {
            throw new NoPermissionException(authResult.getStatus().getStatus(), authResult.getMessage());
        }

        String repositoryName = node.repository();
        repositoryService.failIfRepositoryDoesNotExist(repositoryName);
        return new DropRepositoryAnalyzedStatement(repositoryName);
    }

}
