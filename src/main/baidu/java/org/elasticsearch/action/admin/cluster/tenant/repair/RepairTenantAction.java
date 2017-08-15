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

package org.elasticsearch.action.admin.cluster.tenant.repair;

import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

public class RepairTenantAction extends Action<RepairTenantRequest, RepairTenantResponse, RepairTenantRequestBuilder> {

    public static final RepairTenantAction INSTANCE = new RepairTenantAction();
    public static final String NAME = "tenant:admin/repair";

    private RepairTenantAction() {
        super(NAME);
    }

    @Override
    public RepairTenantResponse newResponse() {
        return new RepairTenantResponse();
    }

    @Override
    public RepairTenantRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new RepairTenantRequestBuilder(client, this);
    }
}