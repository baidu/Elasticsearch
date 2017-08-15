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

package org.elasticsearch.action.admin.cluster.tenant.alter;

import java.io.IOException;

import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.support.master.MasterNodeRequest;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

import com.google.common.base.Strings;

public class AlterTenantPropertyRequest extends MasterNodeRequest<AlterTenantPropertyRequest> {

    private String tenantName;
    private int nodeNum;
    private String password;
    
    public AlterTenantPropertyRequest(){}
    
    public AlterTenantPropertyRequest(String tenantName, int nodeNum, String password) {
        this.tenantName = tenantName;
        this.nodeNum = nodeNum;
        this.password = password;
    }
    
    public String tenantName() {
        return tenantName;
    }
    
    public int nodeNum() {
        return nodeNum;
    }
    
    public String password() {
        return password;
    }
    
    @Override
    public ActionRequestValidationException validate() {
        if (nodeNum < 0) {
            ActionRequestValidationException validationException = new ActionRequestValidationException();
            validationException.addValidationError("instance num must > 0");
        }
        return null;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        tenantName = in.readNullableString();
        nodeNum = in.readInt();
        password = in.readNullableString();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeNullableString(tenantName);
        out.writeInt(nodeNum);
        out.writeNullableString(password);
    }
}
