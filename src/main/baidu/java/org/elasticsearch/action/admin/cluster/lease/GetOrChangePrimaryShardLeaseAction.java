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

package org.elasticsearch.action.admin.cluster.lease;

import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 */
public class GetOrChangePrimaryShardLeaseAction extends Action<GetOrChangePrimaryShardLeaseRequest, GetOrChangePrimaryShardLeaseResponse, GetOrChangePrimaryShardLeaseRequestBuilder> {

    public static final GetOrChangePrimaryShardLeaseAction INSTANCE = new GetOrChangePrimaryShardLeaseAction();
    public static final String NAME = "indices:admin/lease/get_primary_shard_lease";

    private GetOrChangePrimaryShardLeaseAction() {
        super(NAME);
    }

    @Override
    public GetOrChangePrimaryShardLeaseResponse newResponse() {
        return new GetOrChangePrimaryShardLeaseResponse();
    }

    @Override
    public GetOrChangePrimaryShardLeaseRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new GetOrChangePrimaryShardLeaseRequestBuilder(client, this);
    }
}
