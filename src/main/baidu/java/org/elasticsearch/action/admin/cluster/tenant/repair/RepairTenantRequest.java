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

import java.io.IOException;

import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.support.master.MasterNodeRequest;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

public class RepairTenantRequest extends MasterNodeRequest<RepairTenantRequest> {

    private String[] tenantNames;
    
    public RepairTenantRequest(){}
    
    public RepairTenantRequest(String[] tenantNames) {
        this.tenantNames = tenantNames;
    }
    
    public String[] tenantNames() {
        return tenantNames;
    }
    
    
    @Override
    public ActionRequestValidationException validate() {
        if (tenantNames == null || tenantNames.length < 1) {
            ActionRequestValidationException validationException = new ActionRequestValidationException();
            validationException.addValidationError("should specify a tenant");
            return validationException;
        }
        return null;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        int tenantNum = in.readInt();
        this.tenantNames = new String[tenantNum];
        for (int i = 0; i < tenantNum; ++i) {
            tenantNames[i] = in.readString();
        }
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        int tenantNum = tenantNames == null ? 0 : tenantNames.length;
        out.writeInt(tenantNum);
        for (int i = 0; i < tenantNum; ++i) {
            out.writeString(tenantNames[i]);
        }
    }
}