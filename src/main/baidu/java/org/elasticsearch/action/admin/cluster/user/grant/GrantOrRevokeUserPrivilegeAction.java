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

package org.elasticsearch.action.admin.cluster.user.grant;

import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

public class GrantOrRevokeUserPrivilegeAction extends Action<GrantOrRevokeUserPrivilegeRequest, GrantOrRevokeUserPrivilegeResponse, GrantOrRevokeUserPrivilegeRequestBuilder> {

    public static final GrantOrRevokeUserPrivilegeAction INSTANCE = new GrantOrRevokeUserPrivilegeAction();
    public static final String NAME = "cluster:admin/user/grantorrevoke";

    private GrantOrRevokeUserPrivilegeAction() {
        super(NAME);
    }

    @Override
    public GrantOrRevokeUserPrivilegeResponse newResponse() {
        return new GrantOrRevokeUserPrivilegeResponse();
    }

    @Override
    public GrantOrRevokeUserPrivilegeRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new GrantOrRevokeUserPrivilegeRequestBuilder(client, this);
    }
}