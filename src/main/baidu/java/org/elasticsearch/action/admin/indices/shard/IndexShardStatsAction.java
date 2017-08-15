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

package org.elasticsearch.action.admin.indices.shard;

import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

public class IndexShardStatsAction extends Action<IndexShardStatsRequest, IndexShardStatsResponse, IndexShardStatsRequestBuilder> {

    public static final IndexShardStatsAction INSTANCE = new IndexShardStatsAction();
    public static final String NAME = "indices:monitor/shard/stats";

    private IndexShardStatsAction() {
        super(NAME);
    }

    @Override
    public IndexShardStatsResponse newResponse() {
        return new IndexShardStatsResponse();
    }

    @Override
    public IndexShardStatsRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new IndexShardStatsRequestBuilder(client, this);
    }
}
