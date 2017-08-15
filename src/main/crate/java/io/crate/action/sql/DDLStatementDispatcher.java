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

package io.crate.action.sql;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

import io.crate.action.ActionListeners;
import io.crate.analyze.*;
import io.crate.blob.v2.BlobIndices;
import io.crate.executor.task.DDLTask;
import io.crate.executor.transport.*;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.cluster.tenant.alter.AlterTenantPropertyRequest;
import org.elasticsearch.action.admin.cluster.tenant.alter.AlterTenantPropertyResponse;
import org.elasticsearch.action.admin.cluster.tenant.create.CreateTenantRequest;
import org.elasticsearch.action.admin.cluster.tenant.create.CreateTenantResponse;
import org.elasticsearch.action.admin.cluster.tenant.delete.DeleteTenantRequest;
import org.elasticsearch.action.admin.cluster.tenant.delete.DeleteTenantResponse;
import org.elasticsearch.action.admin.cluster.tenant.migrate.MigrateIndexTenantRequest;
import org.elasticsearch.action.admin.cluster.tenant.migrate.MigrateIndexTenantResponse;
import org.elasticsearch.action.admin.cluster.tenant.modify.ModifyTenantNodesRequest;
import org.elasticsearch.action.admin.cluster.tenant.modify.ModifyTenantNodesResponse;
import org.elasticsearch.action.admin.cluster.user.add.AddUserOrResetPasswdRequest;
import org.elasticsearch.action.admin.cluster.user.add.AddUserOrResetPasswdResponse;
import org.elasticsearch.action.admin.cluster.user.drop.DropUserRequest;
import org.elasticsearch.action.admin.cluster.user.drop.DropUserResponse;
import org.elasticsearch.action.admin.cluster.user.grant.GrantOrRevokeUserPrivilegeRequest;
import org.elasticsearch.action.admin.cluster.user.grant.GrantOrRevokeUserPrivilegeResponse;
import org.elasticsearch.action.admin.cluster.user.whitelist.ResetWhitelistRequest;
import org.elasticsearch.action.admin.cluster.user.whitelist.ResetWhitelistResponse;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.authentication.LoginUserContext;
import org.elasticsearch.cluster.metadata.PrivilegeType;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.Singleton;
import org.elasticsearch.mysql.MysqlPassword;

import javax.annotation.Nullable;

import java.util.Locale;
import java.util.UUID;

/**
 * visitor that dispatches requests based on Analysis class to different actions.
 *
 * Its methods return a future returning a Long containing the response rowCount.
 * If the future returns <code>null</code>, no row count shall be created.
 */
@Singleton
public class DDLStatementDispatcher {

    private final Long ONE = new Long(1);
    private final BlobIndices blobIndices;
    private final TransportActionProvider transportActionProvider;
    private final TableCreator tableCreator;
    private final AlterTableOperation alterTableOperation;
    private final RepositoryService repositoryService;
    private final SnapshotRestoreDDLDispatcher snapshotRestoreDDLDispatcher;

    private final InnerVisitor innerVisitor = new InnerVisitor();


    @Inject
    public DDLStatementDispatcher(BlobIndices blobIndices,
                                  TableCreator tableCreator,
                                  AlterTableOperation alterTableOperation,
                                  RepositoryService repositoryService,
                                  SnapshotRestoreDDLDispatcher snapshotRestoreDDLDispatcher,
                                  TransportActionProvider transportActionProvider) {
        this.blobIndices = blobIndices;
        this.tableCreator = tableCreator;
        this.alterTableOperation = alterTableOperation;
        this.transportActionProvider = transportActionProvider;
        this.repositoryService = repositoryService;
        this.snapshotRestoreDDLDispatcher = snapshotRestoreDDLDispatcher;
    }

    public ListenableFuture<Long> dispatch(AnalyzedStatement analyzedStatement, UUID jobId, DDLTask task) {
        return innerVisitor.process(analyzedStatement, new SingleJobTask(jobId, task));
    }

    private static class SingleJobTask {
        public final UUID uuid;
        public final DDLTask ddlTask;
        
        public SingleJobTask(UUID uuid, DDLTask ddlTask) {
            this.uuid = uuid;
            this.ddlTask = ddlTask;
        }
    }
    
    private class InnerVisitor extends AnalyzedStatementVisitor<SingleJobTask, ListenableFuture<Long>> {

        @Override
        protected ListenableFuture<Long> visitAnalyzedStatement(AnalyzedStatement analyzedStatement, SingleJobTask jobId) {
            throw new UnsupportedOperationException(String.format(Locale.ENGLISH, "Can't handle \"%s\"", analyzedStatement));
        }

        @Override
        public ListenableFuture<Long> visitCreateTableStatement(CreateTableAnalyzedStatement analysis, SingleJobTask jobId) {
            return tableCreator.create(analysis);
        }

        @Override
        public ListenableFuture<Long> visitAlterTableStatement(final AlterTableAnalyzedStatement analysis, SingleJobTask jobId) {
            return alterTableOperation.executeAlterTable(analysis);
        }

        @Override
        public ListenableFuture<Long> visitAddColumnStatement(AddColumnAnalyzedStatement analysis, SingleJobTask context) {
            return alterTableOperation.executeAlterTableAddColumn(analysis);
        }

        @Override
        public ListenableFuture<Long> visitRefreshTableStatement(RefreshTableAnalyzedStatement analysis, SingleJobTask jobId) {
            if (analysis.indexNames().isEmpty()) {
                return Futures.immediateFuture(null);
            }
            RefreshRequest request = new RefreshRequest(analysis.indexNames().toArray(
                    new String[analysis.indexNames().size()]));
            request.indicesOptions(IndicesOptions.lenientExpandOpen());

            final SettableFuture<Long> future = SettableFuture.create();
            ActionListener<RefreshResponse> listener = ActionListeners.wrap(future, Functions.<Long>constant(new Long(analysis.indexNames().size())));
            transportActionProvider.transportRefreshAction().execute(request, listener);
            return future;
        }
        
        @Override
        public ListenableFuture<Long> visitCreateUserAnalyzedStatement(CreateUserAnalyzedStatement analysis, SingleJobTask jobId) {
            String encryptPasswd = new String(MysqlPassword.makeScrambledPassword(analysis.getPassword()));
            AddUserOrResetPasswdRequest addUserRequest = new AddUserOrResetPasswdRequest(analysis.getUsername(), encryptPasswd, true);
            final SettableFuture<Long> future = SettableFuture.create();
            ActionListener<AddUserOrResetPasswdResponse> listener = ActionListeners.wrap(future, Functions.<Long>constant(ONE));
            transportActionProvider.transportAddUserOrResetPasswdAction().execute(addUserRequest, listener);
            return future;
        }
        
        @Override
        public ListenableFuture<Long> visitResetPasswordAnalyzedStatement(ResetPasswordAnalyzedStatement analysis, SingleJobTask jobId) {
            String encryptPasswd = new String(MysqlPassword.makeScrambledPassword(analysis.getPassword()));
            AddUserOrResetPasswdRequest addUserRequest = new AddUserOrResetPasswdRequest(analysis.getUsername(), encryptPasswd, false);
            final SettableFuture<Long> future = SettableFuture.create();
            ActionListener<AddUserOrResetPasswdResponse> listener = ActionListeners.wrap(future, Functions.<Long>constant(ONE));
            transportActionProvider.transportAddUserOrResetPasswdAction().execute(addUserRequest, listener);
            return future;
        }
        
        @Override
        public ListenableFuture<Long> visitResetWhitelistAnalyzedStatement(ResetWhitelistAnalyzedStatement analysis, SingleJobTask jobId) {
            ResetWhitelistRequest resetWhitelistRequest = new ResetWhitelistRequest(analysis.getUsername(), analysis.getWhitelist());
            final SettableFuture<Long> future = SettableFuture.create();
            ActionListener<ResetWhitelistResponse> listener = ActionListeners.wrap(future, Functions.<Long>constant(ONE));
            transportActionProvider.transportResetWhitelistAction().execute(resetWhitelistRequest, listener);
            return future;
        }
        
        @Override
        public ListenableFuture<Long> visitDropUserAnalyzedStatement(DropUserAnalyzedStatement analysis, SingleJobTask jobId) {
            DropUserRequest dropUserRequest = new DropUserRequest(analysis.getUsername());
            final SettableFuture<Long> future = SettableFuture.create();
            ActionListener<DropUserResponse> listener = ActionListeners.wrap(future, Functions.<Long>constant(ONE));
            transportActionProvider.transportDropUserAction().execute(dropUserRequest, listener);
            return future;
        }

        @Override
        public ListenableFuture<Long> visitGrantPrivilegeAnalyzedStatement(GrantPrivilegeAnalyzedStatement analysis, SingleJobTask jobId) {
            String tableName = analysis.getTable();
            boolean isDBPrivilege = true;
            if (tableName.contains(".")) {
                isDBPrivilege = false;
            }
            GrantOrRevokeUserPrivilegeRequest grantRequest = new GrantOrRevokeUserPrivilegeRequest(analysis.getUsername(),
                    tableName, PrivilegeType.valueOf(analysis.getPrivilege().toUpperCase()),
                    isDBPrivilege, true);
            grantRequest.putHeader(LoginUserContext.USER_INFO_KEY, analysis.getParameterContext().getLoginUserContext());
            final SettableFuture<Long> future = SettableFuture.create();
            ActionListener<GrantOrRevokeUserPrivilegeResponse> listener = ActionListeners.wrap(future, Functions.<Long>constant(ONE));
            transportActionProvider.transportGrantOrRevokeUserPrivilegeAction().execute(grantRequest, listener);
            return future;
        }
        
        @Override
        public ListenableFuture<Long> visitRevokePrivilegeAnalyzedStatement(RevokePrivilegeAnalyzedStatement analysis, SingleJobTask jobId) {
            String tableName = analysis.getTable();
            boolean isDBPrivilege = true;
            if (tableName.contains(".")) {
                isDBPrivilege = false;
            }
            GrantOrRevokeUserPrivilegeRequest grantRequest = new GrantOrRevokeUserPrivilegeRequest(analysis.getUsername(),
                    tableName, PrivilegeType.valueOf(analysis.getPrivilege().toUpperCase()),
                    isDBPrivilege, false);
            grantRequest.putHeader(LoginUserContext.USER_INFO_KEY, analysis.getParameterContext().getLoginUserContext());
            final SettableFuture<Long> future = SettableFuture.create();
            ActionListener<GrantOrRevokeUserPrivilegeResponse> listener = ActionListeners.wrap(future, Functions.<Long>constant(ONE));
            transportActionProvider.transportGrantOrRevokeUserPrivilegeAction().execute(grantRequest, listener);
            return future;
        }
        
        @Override
        public ListenableFuture<Long> visitCreateTenantAnalyzedStatement(CreateTenantAnalyzedStatement analysis, SingleJobTask jobId) {
            CreateTenantRequest createTenantRequest = new CreateTenantRequest(analysis.tenantName(), analysis.instanceNum(), analysis.superuserPassword(), analysis.instanceList());
            final SettableFuture<Long> future = SettableFuture.create();
            ActionListener<CreateTenantResponse> listener = ActionListeners.wrap(future, Functions.<Long>constant(ONE));
            transportActionProvider.transportCreateTenantAction().execute(createTenantRequest, listener);
            return future;
        }
        
        @Override
        public ListenableFuture<Long> visitDropTenantAnalyzedStatement(DropTenantAnalyzedStatement analysis, SingleJobTask jobId) {
            DeleteTenantRequest dropTenantRequest = new DeleteTenantRequest(analysis.tenantName());
            final SettableFuture<Long> future = SettableFuture.create();
            ActionListener<DeleteTenantResponse> listener = ActionListeners.wrap(future, Functions.<Long>constant(ONE));
            transportActionProvider.transportDeleteTenantAction().execute(dropTenantRequest, listener);
            return future;
        }
        
        @Override
        public ListenableFuture<Long> visitMigrateTableAnalyzedStatement(MigrateTableAnalyzedStatement analysis, SingleJobTask jobId) {
            MigrateIndexTenantRequest migrateIndexRequest = new MigrateIndexTenantRequest(analysis.destTenantName(), analysis.tableName());
            final SettableFuture<Long> future = SettableFuture.create();
            ActionListener<MigrateIndexTenantResponse> listener = ActionListeners.wrap(future, Functions.<Long>constant(ONE));
            transportActionProvider.transportMigrateIndexTenantAction().execute(migrateIndexRequest, listener);
            return future;
        }
        
        @Override
        public ListenableFuture<Long> visitAlterTenantModifyNodesAnalyzedStatement(AlterTenantModifyNodesAnalyzedStatement analysis, SingleJobTask jobId) {
            ModifyTenantNodesRequest modifyTenantNodesRequest = new ModifyTenantNodesRequest(analysis.nodeList().get(0), analysis.tenantName(), analysis.getOperation());
            final SettableFuture<Long> future = SettableFuture.create();
            ActionListener<ModifyTenantNodesResponse> listener = ActionListeners.wrap(future, Functions.<Long>constant(ONE));
            transportActionProvider.transportModifyTenantNodesAction().execute(modifyTenantNodesRequest, listener);
            return future;
        }
        
        @Override
        public ListenableFuture<Long> visitAlterTenantPropertyAnalyzedStatement(AlterTenantPropertyAnalyzedStatement analysis, SingleJobTask jobId) {
            AlterTenantPropertyRequest alterTenantPropertyRequest = new AlterTenantPropertyRequest(analysis.tenantName(), analysis.instanceNum(), analysis.superuserPassword());
            final SettableFuture<Long> future = SettableFuture.create();
            ActionListener<AlterTenantPropertyResponse> listener = ActionListeners.wrap(future, Functions.<Long>constant(ONE));
            transportActionProvider.transportAlterTenantPropertyAction().execute(alterTenantPropertyRequest, listener);
            return future;
        }

        @Override
        public ListenableFuture<Long> visitCreateBlobTableStatement(
                CreateBlobTableAnalyzedStatement analysis, SingleJobTask jobId) {
            return wrapRowCountFuture(
                    blobIndices.createBlobTable(
                            analysis.tableName(),
                            analysis.tableParameter().settings()
                    ),
                    1L
            );
        }

        @Override
        public ListenableFuture<Long> visitAlterBlobTableStatement(AlterBlobTableAnalyzedStatement analysis, SingleJobTask jobId) {
            return wrapRowCountFuture(
                    blobIndices.alterBlobTable(analysis.table().ident().name(), analysis.tableParameter().settings()),
                    1L);
        }

        @Override
        public ListenableFuture<Long> visitDropBlobTableStatement(DropBlobTableAnalyzedStatement analysis, SingleJobTask jobId) {
            return wrapRowCountFuture(blobIndices.dropBlobTable(analysis.table().ident().name()), 1L);
        }

        @Override
        public ListenableFuture<Long> visitDropRepositoryAnalyzedStatement(DropRepositoryAnalyzedStatement analysis, SingleJobTask jobId) {
            return repositoryService.execute(analysis);
        }

        @Override
        public ListenableFuture<Long> visitCreateRepositoryAnalyzedStatement(CreateRepositoryAnalyzedStatement analysis, SingleJobTask jobId) {
            return repositoryService.execute(analysis);
        }

        @Override
        public ListenableFuture<Long> visitDropSnapshotAnalyzedStatement(DropSnapshotAnalyzedStatement analysis, SingleJobTask jobId) {
            return snapshotRestoreDDLDispatcher.dispatch(analysis);
        }

        public ListenableFuture<Long> visitCreateSnapshotAnalyzedStatement(CreateSnapshotAnalyzedStatement analysis, SingleJobTask jobId) {
            return snapshotRestoreDDLDispatcher.dispatch(analysis);
        }

        @Override
        public ListenableFuture<Long> visitRestoreSnapshotAnalyzedStatement(RestoreSnapshotAnalyzedStatement analysis, SingleJobTask context) {
            return snapshotRestoreDDLDispatcher.dispatch(analysis);
        }
    }

    private ListenableFuture<Long> wrapRowCountFuture(ListenableFuture<?> wrappedFuture, final Long rowCount) {
        return Futures.transform(wrappedFuture, new Function<Object, Long>() {
            @Nullable
            @Override
            public Long apply(@Nullable Object input) {
                return rowCount;
            }
        });
    }
}
