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

package org.elasticsearch.action.admin.cluster.tenant.migrate;

import org.elasticsearch.action.support.master.MasterNodeOperationRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

public class MigrateIndexTenantRequestBuilder extends MasterNodeOperationRequestBuilder<MigrateIndexTenantRequest, MigrateIndexTenantResponse, MigrateIndexTenantRequestBuilder> {

    public MigrateIndexTenantRequestBuilder(ElasticsearchClient client, MigrateIndexTenantAction action, String tenantName, String[] indices) {
        super(client, action, new MigrateIndexTenantRequest(tenantName, indices));
    }
    
    public MigrateIndexTenantRequestBuilder(ElasticsearchClient client, MigrateIndexTenantAction action) {
        super(client, action, new MigrateIndexTenantRequest());
    }
}

