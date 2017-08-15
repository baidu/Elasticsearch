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

package org.elasticsearch.action.admin.cluster.tenant.create;

import java.io.IOException;

import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.support.master.MasterNodeRequest;
import org.elasticsearch.cluster.metadata.TenantProperty;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

import com.google.common.base.Strings;

public class CreateTenantRequest extends MasterNodeRequest<CreateTenantRequest> {
    
    private String tenantName;
    private int nodeNum;
    private String password;
    private String instanceList;

    public CreateTenantRequest() {
    }

    public CreateTenantRequest(String tenantName, int nodeNum, String password,
            String instanceList) {
        this.tenantName = tenantName;
        this.nodeNum = nodeNum;
        this.password = password;
        this.instanceList = instanceList;
    }

    public String tenantName() {
        return this.tenantName;
    }

    public int nodeNum() {
        return this.nodeNum;
    }

    public String password() {
        return this.password;
    }

    public String instanceList() {
        return this.instanceList;
    }

    public ActionRequestValidationException validate() {
        if (TenantProperty.ROOT_TENANT_NAME.equalsIgnoreCase(this.tenantName)) {
            ActionRequestValidationException validationException = new ActionRequestValidationException();
            validationException
                    .addValidationError("could not create tenant with name "
                            + this.tenantName
                            + ", it is a reserved tenant name");
            return validationException;
        }
        if (Strings.isNullOrEmpty(this.tenantName)) {
            ActionRequestValidationException validationException = new ActionRequestValidationException();
            validationException
                    .addValidationError("tenant name could not be null or empty");
            return validationException;
        }
        return null;
    }

    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        this.tenantName = in.readNullableString();
        this.nodeNum = in.readInt();
        this.password = in.readNullableString();
        this.instanceList = in.readNullableString();
    }

    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeNullableString(this.tenantName);
        out.writeInt(this.nodeNum);
        out.writeNullableString(this.password);
        out.writeNullableString(this.instanceList);
    }
}
