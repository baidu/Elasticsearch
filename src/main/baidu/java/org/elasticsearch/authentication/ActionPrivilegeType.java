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

package org.elasticsearch.authentication;

import io.crate.executor.transport.TransportShardDeleteAction;
import io.crate.executor.transport.TransportShardUpsertAction;

import org.elasticsearch.action.admin.cluster.node.tasks.cancel.CancelTasksAction;
import org.elasticsearch.action.admin.cluster.repositories.delete.DeleteRepositoryAction;
import org.elasticsearch.action.admin.cluster.repositories.get.GetRepositoriesAction;
import org.elasticsearch.action.admin.cluster.repositories.put.PutRepositoryAction;
import org.elasticsearch.action.admin.cluster.repositories.verify.VerifyRepositoryAction;
import org.elasticsearch.action.admin.cluster.reroute.ClusterRerouteAction;
import org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsAction;
import org.elasticsearch.action.admin.cluster.shards.ClusterSearchShardsAction;
import org.elasticsearch.action.admin.cluster.snapshots.create.CreateSnapshotAction;
import org.elasticsearch.action.admin.cluster.snapshots.delete.DeleteSnapshotAction;
import org.elasticsearch.action.admin.cluster.snapshots.get.GetSnapshotsAction;
import org.elasticsearch.action.admin.cluster.snapshots.restore.RestoreSnapshotAction;
import org.elasticsearch.action.admin.cluster.snapshots.status.SnapshotsStatusAction;
import org.elasticsearch.action.admin.cluster.tenant.create.CreateTenantAction;
import org.elasticsearch.action.admin.cluster.tenant.delete.DeleteTenantAction;
import org.elasticsearch.action.admin.cluster.tenant.migrate.MigrateIndexTenantAction;
import org.elasticsearch.action.admin.cluster.tenant.modify.ModifyTenantNodesAction;
import org.elasticsearch.action.admin.cluster.user.add.AddUserOrResetPasswdAction;
import org.elasticsearch.action.admin.cluster.user.grant.GrantOrRevokeUserPrivilegeAction;
import org.elasticsearch.action.admin.cluster.user.show.ShowUserPropertyAction;
import org.elasticsearch.action.admin.cluster.validate.template.RenderSearchTemplateAction;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesAction;
import org.elasticsearch.action.admin.indices.alias.exists.AliasesExistAction;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.cache.clear.ClearIndicesCacheAction;
import org.elasticsearch.action.admin.indices.close.CloseIndexAction;
import org.elasticsearch.action.admin.indices.create.CreateIndexAction;
import org.elasticsearch.action.admin.indices.create.TransportBulkCreateIndicesAction;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexAction;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsAction;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsAction;
import org.elasticsearch.action.admin.indices.flush.FlushAction;
import org.elasticsearch.action.admin.indices.flush.SyncedFlushAction;
import org.elasticsearch.action.admin.indices.forcemerge.ForceMergeAction;
import org.elasticsearch.action.admin.indices.get.GetIndexAction;
import org.elasticsearch.action.admin.indices.mapping.get.GetFieldMappingsAction;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsAction;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingAction;
import org.elasticsearch.action.admin.indices.open.OpenIndexAction;
import org.elasticsearch.action.admin.indices.refresh.RefreshAction;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsAction;
import org.elasticsearch.action.admin.indices.template.delete.DeleteIndexTemplateAction;
import org.elasticsearch.action.admin.indices.template.get.GetIndexTemplatesAction;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateAction;
import org.elasticsearch.action.admin.indices.upgrade.post.UpgradeAction;
import org.elasticsearch.action.admin.indices.validate.query.ValidateQueryAction;
import org.elasticsearch.action.admin.indices.warmer.delete.DeleteWarmerAction;
import org.elasticsearch.action.admin.indices.warmer.get.GetWarmersAction;
import org.elasticsearch.action.admin.indices.warmer.put.PutWarmerAction;
import org.elasticsearch.cluster.metadata.PrivilegeType;

import java.util.HashMap;
import java.util.Map;

public class ActionPrivilegeType {

    private static Map<String, PrivilegeType> actionTypeMap  = new HashMap<>();
    static {
        // indices admin action
        actionTypeMap.put(AliasesExistAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(AnalyzeAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(ClearIndicesCacheAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(CloseIndexAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(ClusterSearchShardsAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(CreateIndexAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(DeleteIndexAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(DeleteIndexTemplateAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(DeleteWarmerAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(FlushAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(ForceMergeAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(GetAliasesAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(GetFieldMappingsAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(GetIndexAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(GetIndexTemplatesAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(GetMappingsAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(GetWarmersAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(IndicesAliasesAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(IndicesExistsAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(OpenIndexAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(PutIndexTemplateAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(PutMappingAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(PutWarmerAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(RefreshAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(SyncedFlushAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(TransportBulkCreateIndicesAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(TypesExistsAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(UpdateSettingsAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(UpgradeAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(ValidateQueryAction.NAME, PrivilegeType.READ_ONLY);

        // cluster admin action
        actionTypeMap.put(CancelTasksAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(ClusterRerouteAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(ClusterUpdateSettingsAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(CreateSnapshotAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(DeleteRepositoryAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(DeleteSnapshotAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(GetRepositoriesAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(GetSnapshotsAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(PutRepositoryAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(RenderSearchTemplateAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(RestoreSnapshotAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(SnapshotsStatusAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(VerifyRepositoryAction.NAME, PrivilegeType.READ_ONLY);

        actionTypeMap.put(AddUserOrResetPasswdAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(GrantOrRevokeUserPrivilegeAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(ShowUserPropertyAction.NAME, PrivilegeType.READ_ONLY);
        
        // tenants action
        actionTypeMap.put(CreateTenantAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(DeleteTenantAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(MigrateIndexTenantAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(ModifyTenantNodesAction.NAME, PrivilegeType.READ_WRITE);

        /*
        // indices data action
        actionTypeMap.put(BulkAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(ClearScrollAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(CountAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(DeleteAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(DeleteIndexedScriptAction.NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(ExistsAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(ExplainAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(FieldStatsAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(GetAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(GetIndexedScriptAction.NAME, PrivilegeType.READ_ONLY);
        actionTypeMap.put(IndexAction.NAME, PrivilegeType.READ_WRITE);
        */

        // crate action
        actionTypeMap.put(TransportShardUpsertAction.ACTION_NAME, PrivilegeType.READ_WRITE);
        actionTypeMap.put(TransportShardDeleteAction.ACTION_NAME, PrivilegeType.READ_WRITE);
    }

    public static PrivilegeType getActionPrivilegeType(String actionName) {
        if (actionName.startsWith("indices:data/read/")) {
            return PrivilegeType.READ_ONLY;
        } else if (actionName.startsWith("indices:data/write/")) {
            return PrivilegeType.READ_WRITE;
        } else if (actionName.startsWith("indices:monitor/") || actionName.startsWith("cluster:monitor/")) {
            return PrivilegeType.READ_ONLY;
        } else if (actionName.startsWith("indices:admin/") || actionName.startsWith("cluster:admin/")) {
            return actionTypeMap.get(actionName);
        } else if (actionName.startsWith("indices:crate/")) {
            return actionTypeMap.get(actionName);
        }

        return PrivilegeType.READ_WRITE;
    }
}

