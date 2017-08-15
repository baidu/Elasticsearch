/*
 * Modifications copyright (C) 2017, Baidu.com, Inc.
 * 
 * Licensed to CRATE Technology GmbH ("Crate") under one or more contributor
 * license agreements.  See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.  Crate licenses
 * this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.  You may
 * obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
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
package io.crate.analyze;

import io.crate.sql.tree.*;

import org.elasticsearch.common.inject.Inject;

import java.util.Locale;

public class Analyzer {

    private final AnalyzerDispatcher dispatcher = new AnalyzerDispatcher();

    private final DropTableStatementAnalyzer dropTableStatementAnalyzer;
    private final CreateTableStatementAnalyzer createTableStatementAnalyzer;
    private final ShowCreateTableAnalyzer showCreateTableAnalyzer;
    private final ExplainStatementAnalyzer explainStatementAnalyzer;
    private final ShowStatementAnalyzer showStatementAnalyzer;
    private final CreateBlobTableStatementAnalyzer createBlobTableStatementAnalyzer;
    private final CreateAnalyzerStatementAnalyzer createAnalyzerStatementAnalyzer;
    private final DropBlobTableStatementAnalyzer dropBlobTableStatementAnalyzer;
    private final RefreshTableAnalyzer refreshTableAnalyzer;
    private final AlterTableAnalyzer alterTableAnalyzer;
    private final AlterBlobTableAnalyzer alterBlobTableAnalyzer;
    private final AlterTableAddColumnAnalyzer alterTableAddColumnAnalyzer;
    private final InsertFromValuesAnalyzer insertFromValuesAnalyzer;
    private final InsertFromSubQueryAnalyzer insertFromSubQueryAnalyzer;
    private final CopyStatementAnalyzer copyStatementAnalyzer;
    private final SelectStatementAnalyzer selectStatementAnalyzer;
    private final UpdateStatementAnalyzer updateStatementAnalyzer;
    private final DeleteStatementAnalyzer deleteStatementAnalyzer;
    private final DropRepositoryStatementAnalyzer dropRepositoryAnalyzer;
    private final CreateRepositoryAnalyzer createRepositoryAnalyzer;
    private final DropSnapshotAnalyzer dropSnapshotAnalyzer;
    private final CreateSnapshotStatementAnalyzer createSnapshotStatementAnalyzer;
    private final RestoreSnapshotStatementAnalyzer restoreSnapshotStatementAnalyzer;
    private final CreateUserStatementAnalyzer createUserStatementAnalyzer;
    private final DropUserStatementAnalyzer dropUserStatementAnalyzer;
    private final ResetPasswordStatementAnalyzer resetPasswordStatementAnalyzer;
    private final ResetWhitelistStatementAnalyzer resetWhitelistStatementAnalyzer;
    private final GrantPrivilegeStatementAnalyzer grantPrivilegeStatementAnalyzer;
    private final RevokePrivilegeStatementAnalyzer revokePrivilegeStatementAnalyzer;
    private final ShowGrantsStatementAnalyzer showGrantsStatementAnalyzer;
    private final CreateTenantStatementAnalyzer createTenantStatementAnalyzer;
    private final DropTenantStatementAnalyzer dropTenantStatementAnalyzer;
    private final ShowTenantsStatementAnalyzer showTenantsStatementAnalyzer;
    private final AlterTenantPropertyStatementAnalyzer alterTenantPropertyStatementAnalyzer;
    private final MigrateTableStatementAnalyzer migrateTableStatementAnalyzer;
    private final AlterTenantModifyNodesStatementAnalyzer alterTenantModifyNodesStatementAnalyzer;

    @Inject
    public Analyzer(SelectStatementAnalyzer selectStatementAnalyzer,
                    DropTableStatementAnalyzer dropTableStatementAnalyzer,
                    CreateTableStatementAnalyzer createTableStatementAnalyzer,
                    ShowCreateTableAnalyzer showCreateTableAnalyzer,
                    CreateBlobTableStatementAnalyzer createBlobTableStatementAnalyzer,
                    CreateAnalyzerStatementAnalyzer createAnalyzerStatementAnalyzer,
                    DropBlobTableStatementAnalyzer dropBlobTableStatementAnalyzer,
                    RefreshTableAnalyzer refreshTableAnalyzer,
                    AlterTableAnalyzer alterTableAnalyzer,
                    AlterBlobTableAnalyzer alterBlobTableAnalyzer,
                    AlterTableAddColumnAnalyzer alterTableAddColumnAnalyzer,
                    InsertFromValuesAnalyzer insertFromValuesAnalyzer,
                    InsertFromSubQueryAnalyzer insertFromSubQueryAnalyzer,
                    CopyStatementAnalyzer copyStatementAnalyzer,
                    UpdateStatementAnalyzer updateStatementAnalyzer,
                    DeleteStatementAnalyzer deleteStatementAnalyzer,
                    DropRepositoryStatementAnalyzer dropRepositoryAnalyzer,
                    CreateRepositoryAnalyzer createRepositoryAnalyzer,
                    DropSnapshotAnalyzer dropSnapshotAnalyzer,
                    CreateSnapshotStatementAnalyzer createSnapshotStatementAnalyzer,
                    RestoreSnapshotStatementAnalyzer restoreSnapshotStatementAnalyzer, 
                    CreateUserStatementAnalyzer createUserStatementAnalyzer, 
                    DropUserStatementAnalyzer dropUserStatementAnalyzer, 
                    ResetPasswordStatementAnalyzer resetPasswordStatementAnalyzer, 
                    ResetWhitelistStatementAnalyzer resetWhitelistStatementAnalyzer, 
                    GrantPrivilegeStatementAnalyzer grantPrivilegeStatementAnalyzer, 
                    RevokePrivilegeStatementAnalyzer revokePrivilegeStatementAnalyzer, 
                    ShowGrantsStatementAnalyzer showGrantsStatementAnalyzer, 
                    CreateTenantStatementAnalyzer createTenantStatementAnalyzer,
                    DropTenantStatementAnalyzer dropTenantStatementAnalyzer,
                    ShowTenantsStatementAnalyzer showTenantsStatementAnalyzer,
                    AlterTenantPropertyStatementAnalyzer alterTenantPropertyStatementAnalyzer,
                    MigrateTableStatementAnalyzer migrateTableStatementAnalyzer,
                    AlterTenantModifyNodesStatementAnalyzer alterTenantModifyNodesStatementAnalyzer) {
        this.selectStatementAnalyzer = selectStatementAnalyzer;
        this.dropTableStatementAnalyzer = dropTableStatementAnalyzer;
        this.createTableStatementAnalyzer = createTableStatementAnalyzer;
        this.showCreateTableAnalyzer = showCreateTableAnalyzer;
        this.explainStatementAnalyzer = new ExplainStatementAnalyzer(this);
        this.showStatementAnalyzer = new ShowStatementAnalyzer(this);
        this.createBlobTableStatementAnalyzer = createBlobTableStatementAnalyzer;
        this.createAnalyzerStatementAnalyzer = createAnalyzerStatementAnalyzer;
        this.dropBlobTableStatementAnalyzer = dropBlobTableStatementAnalyzer;
        this.refreshTableAnalyzer = refreshTableAnalyzer;
        this.alterTableAnalyzer = alterTableAnalyzer;
        this.alterBlobTableAnalyzer = alterBlobTableAnalyzer;
        this.alterTableAddColumnAnalyzer = alterTableAddColumnAnalyzer;
        this.insertFromValuesAnalyzer = insertFromValuesAnalyzer;
        this.insertFromSubQueryAnalyzer = insertFromSubQueryAnalyzer;
        this.copyStatementAnalyzer = copyStatementAnalyzer;
        this.updateStatementAnalyzer = updateStatementAnalyzer;
        this.deleteStatementAnalyzer = deleteStatementAnalyzer;
        this.dropRepositoryAnalyzer = dropRepositoryAnalyzer;
        this.createRepositoryAnalyzer = createRepositoryAnalyzer;
        this.dropSnapshotAnalyzer = dropSnapshotAnalyzer;
        this.createSnapshotStatementAnalyzer = createSnapshotStatementAnalyzer;
        this.restoreSnapshotStatementAnalyzer = restoreSnapshotStatementAnalyzer;
        this.createUserStatementAnalyzer = createUserStatementAnalyzer;
        this.dropUserStatementAnalyzer = dropUserStatementAnalyzer;
        this.resetPasswordStatementAnalyzer = resetPasswordStatementAnalyzer;
        this.resetWhitelistStatementAnalyzer = resetWhitelistStatementAnalyzer;
        this.grantPrivilegeStatementAnalyzer = grantPrivilegeStatementAnalyzer;
        this.revokePrivilegeStatementAnalyzer = revokePrivilegeStatementAnalyzer;
        this.showGrantsStatementAnalyzer = showGrantsStatementAnalyzer;
        this.createTenantStatementAnalyzer = createTenantStatementAnalyzer;
        this.dropTenantStatementAnalyzer = dropTenantStatementAnalyzer;
        this.showTenantsStatementAnalyzer = showTenantsStatementAnalyzer;
        this.alterTenantPropertyStatementAnalyzer = alterTenantPropertyStatementAnalyzer;
        this.migrateTableStatementAnalyzer = migrateTableStatementAnalyzer;
        this.alterTenantModifyNodesStatementAnalyzer = alterTenantModifyNodesStatementAnalyzer;
    }

    public Analysis analyze(Statement statement, ParameterContext parameterContext) {
        Analysis analysis = new Analysis(parameterContext);
        AnalyzedStatement analyzedStatement = analyzedStatement(statement, analysis);
        analyzedStatement.setParameterContext(parameterContext);
        analysis.analyzedStatement(analyzedStatement);
        return analysis;
    }

    AnalyzedStatement analyzedStatement(Statement statement, Analysis analysis) {
        AnalyzedStatement analyzedStatement = dispatcher.process(statement, analysis);
        assert analyzedStatement != null : "analyzed statement must not be null";
        return analyzedStatement;
    }

    private class AnalyzerDispatcher extends AstVisitor<AnalyzedStatement, Analysis> {

        @Override
        protected AnalyzedStatement visitQuery(Query node, Analysis analysis) {
            return selectStatementAnalyzer.process(node, analysis);
        }

        @Override
        public AnalyzedStatement visitDelete(Delete node, Analysis context) {
            return deleteStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitInsertFromValues(InsertFromValues node, Analysis context) {
            return insertFromValuesAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitInsertFromSubquery(InsertFromSubquery node, Analysis context) {
            return insertFromSubQueryAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitUpdate(Update node, Analysis context) {
            return updateStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitCopyFrom(CopyFrom node, Analysis context) {
            return copyStatementAnalyzer.convertCopyFrom(node, context);
        }

        @Override
        public AnalyzedStatement visitCopyTo(CopyTo node, Analysis context) {
            return copyStatementAnalyzer.convertCopyTo(node, context);
        }

        @Override
        public AnalyzedStatement visitDropTable(DropTable node, Analysis context) {
            return dropTableStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitCreateTable(CreateTable node, Analysis analysis) {
            return createTableStatementAnalyzer.analyze(node, analysis);
        }

        public AnalyzedStatement visitShowCreateTable(ShowCreateTable node, Analysis analysis) {
            ShowCreateTableAnalyzedStatement showCreateTableStatement =
                    showCreateTableAnalyzer.analyze(node.table(), analysis);
            analysis.rootRelation(showCreateTableStatement);
            return showCreateTableStatement;
        }

        public AnalyzedStatement visitShowSchemas(ShowSchemas node, Analysis analysis) {
            return showStatementAnalyzer.analyze(node, analysis);
        }

        public AnalyzedStatement visitShowTables(ShowTables node, Analysis analysis) {
            return showStatementAnalyzer.analyze(node, analysis);
        }

        @Override
        protected AnalyzedStatement visitShowColumns(ShowColumns node, Analysis context) {
            return showStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitCreateAnalyzer(CreateAnalyzer node, Analysis context) {
            return createAnalyzerStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitCreateBlobTable(CreateBlobTable node, Analysis context) {
            return createBlobTableStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitDropBlobTable(DropBlobTable node, Analysis context) {
            return dropBlobTableStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitAlterBlobTable(AlterBlobTable node, Analysis context) {
            return alterBlobTableAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitRefreshStatement(RefreshStatement node, Analysis context) {
            return refreshTableAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitAlterTable(AlterTable node, Analysis context) {
            return alterTableAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitAlterTableAddColumnStatement(AlterTableAddColumn node, Analysis context) {
            return alterTableAddColumnAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitSetStatement(SetStatement node, Analysis context) {
            context.expectsAffectedRows(true);
            return SetStatementAnalyzer.analyze(node, context.parameterContext());
        }

        @Override
        public AnalyzedStatement visitResetStatement(ResetStatement node, Analysis context) {
            context.expectsAffectedRows(true);
            return SetStatementAnalyzer.analyze(node, context.parameterContext());
        }

        @Override
        public AnalyzedStatement visitKillStatement(KillStatement node, Analysis context) {
            context.expectsAffectedRows(true);
            return KillAnalyzer.analyze(node, context.parameterContext());
        }

        @Override
        public AnalyzedStatement visitDropRepository(DropRepository node, Analysis context) {
            return dropRepositoryAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitCreateRepository(CreateRepository node, Analysis context) {
            return createRepositoryAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitDropSnapshot(DropSnapshot node, Analysis context) {
            return dropSnapshotAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitCreateSnapshot(CreateSnapshot node, Analysis context) {
            return createSnapshotStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitRestoreSnapshot(RestoreSnapshot node, Analysis context) {
            return restoreSnapshotStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitCreateUser(CreateUser node, Analysis context) {
            return createUserStatementAnalyzer.analyze(node, context);
        }
        
        @Override
        public AnalyzedStatement visitDropUser(DropUser node, Analysis context) {
            return dropUserStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitResetPassword(ResetPassword node, Analysis context) {
            return resetPasswordStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitResetWhitelist(ResetWhitelist node, Analysis context) {
            return resetWhitelistStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitGrantPrivilege(GrantPrivilege node, Analysis context) {
            return grantPrivilegeStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitRevokePrivilege(RevokePrivilege node, Analysis context) {
            return revokePrivilegeStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitShowGrants(ShowGrants node, Analysis analysis) {

            ShowGrantsAnalyzedStatement showGrantsAnalyzedStatement = showGrantsStatementAnalyzer.analyze(node, analysis);
            analysis.rootRelation(showGrantsAnalyzedStatement);
            return showGrantsAnalyzedStatement;
        }
        /*
        public AnalyzedStatement visitShowCreateTable(ShowCreateTable node, Analysis analysis) {
            ShowCreateTableAnalyzedStatement showCreateTableStatement =
                    showCreateTableAnalyzer.analyze(node.table(), analysis.parameterContext().defaultSchema());
            analysis.rootRelation(showCreateTableStatement);
            return showCreateTableStatement;
        }*/
        
        @Override
        protected AnalyzedStatement visitExplain(Explain node, Analysis context) {
            return explainStatementAnalyzer.analyze(node, context);
        }

        @Override
        protected AnalyzedStatement visitNode(Node node, Analysis context) {
            throw new UnsupportedOperationException(String.format(Locale.ENGLISH, "cannot analyze statement: '%s'", node));
        }
        
        @Override
        public AnalyzedStatement visitCreateTenant(CreateTenant node, Analysis context) {
            return createTenantStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitDropTenant(DropTenant node, Analysis context) {
            return dropTenantStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitShowTenants(ShowTenants node, Analysis context) {
            ShowTenantsAnalyzedStatement showTenantsAnalyzedStatement = showTenantsStatementAnalyzer.analyze(node, context);
            context.rootRelation(showTenantsAnalyzedStatement);
            return showTenantsAnalyzedStatement;
        }

        @Override
        public AnalyzedStatement visitAlterTenantProperty(AlterTenantProperty node, Analysis context) {
            return alterTenantPropertyStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitAlterTenantModifyNodes(AlterTenantModifyNodes node, Analysis context) {
            return alterTenantModifyNodesStatementAnalyzer.analyze(node, context);
        }

        @Override
        public AnalyzedStatement visitMigrateTable(MigrateTable node, Analysis context) {
            return migrateTableStatementAnalyzer.analyze(node, context);
        }
    }
}
