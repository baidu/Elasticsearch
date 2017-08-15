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
import org.elasticsearch.action.support.master.AcknowledgedRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.cluster.metadata.PrivilegeType;

public class GrantOrRevokeUserPrivilegeRequestBuilder extends AcknowledgedRequestBuilder<GrantOrRevokeUserPrivilegeRequest, GrantOrRevokeUserPrivilegeResponse, GrantOrRevokeUserPrivilegeRequestBuilder> {

    protected GrantOrRevokeUserPrivilegeRequestBuilder(
            ElasticsearchClient client,
            Action<GrantOrRevokeUserPrivilegeRequest, GrantOrRevokeUserPrivilegeResponse, GrantOrRevokeUserPrivilegeRequestBuilder> action) {
        super(client, action, new GrantOrRevokeUserPrivilegeRequest("", "", PrivilegeType.READ_ONLY, false, true));
    }

    public void setUsername(String userName) {
        request.setUsername(userName);
    }

    public void setDbOrTableName(String dbOrTableName) {
        request.setDbOrTableName(dbOrTableName);
    }

    public void setDBPrivilege(boolean isDBPrivilege) {
        request.setDBPrivilege(isDBPrivilege);
    }

    public void setPrivilegeType(PrivilegeType privilegeType) {
        request.setPrivilegeType(privilegeType);
    }

    public void setGrant(boolean isGrant) {
        request.setGrant(isGrant);
    }
}
