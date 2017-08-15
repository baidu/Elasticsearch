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

import java.io.IOException;

import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.support.master.AcknowledgedRequest;
import org.elasticsearch.cluster.metadata.PrivilegeType;
import org.elasticsearch.cluster.metadata.UserProperty;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

public class GrantOrRevokeUserPrivilegeRequest extends AcknowledgedRequest<GrantOrRevokeUserPrivilegeRequest> {

    private String username;
    private String dbOrTableName;
    private boolean isDBPrivilege;
    private PrivilegeType privilegeType;
    private boolean isGrant;
    
    public GrantOrRevokeUserPrivilegeRequest() {}
    
    public GrantOrRevokeUserPrivilegeRequest(String username,
            String dbOrTableName, PrivilegeType privilegeType,
            boolean isDBPrivilege, boolean isGrant) {
        this.username = username;
        this.dbOrTableName = dbOrTableName;
        this.privilegeType = privilegeType;
        this.isDBPrivilege = isDBPrivilege;
        this.isGrant = isGrant;
    }

    @Override
    public ActionRequestValidationException validate() {
        String usernameWithoutTenant = UserProperty.getUsernameWithoutTenantFromFullUsername(username);
        if (UserProperty.ROOT_NAME.equalsIgnoreCase(usernameWithoutTenant)) {
            ActionRequestValidationException invalidRequestError = new ActionRequestValidationException();
            invalidRequestError.addValidationError("could not grant or revoke privilege to root user");
            return invalidRequestError;
        }
        return null;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        username = in.readString();
        dbOrTableName = in.readString();
        isDBPrivilege = in.readBoolean();
        privilegeType = PrivilegeType.fromValue(in.readInt());
        isGrant = in.readBoolean();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeString(username);
        out.writeString(dbOrTableName);
        out.writeBoolean(isDBPrivilege);
        out.writeInt(privilegeType.getValue());
        out.writeBoolean(isGrant);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDbOrTableName() {
        return dbOrTableName;
    }

    public void setDbOrTableName(String dbOrTableName) {
        this.dbOrTableName = dbOrTableName;
    }

    public boolean isDBPrivilege() {
        return isDBPrivilege;
    }

    public void setDBPrivilege(boolean isDBPrivilege) {
        this.isDBPrivilege = isDBPrivilege;
    }

    public PrivilegeType getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(PrivilegeType privilegeType) {
        this.privilegeType = privilegeType;
    }

    public boolean isGrant() {
        return isGrant;
    }

    public void setGrant(boolean isGrant) {
        this.isGrant = isGrant;
    }
}
