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

package org.elasticsearch.action.admin.cluster.tenant.show;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.cluster.metadata.TenantProperty;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

public class ShowTenantsResponse extends ActionResponse {
    
    private List<TenantProperty> tenantProperties;
    
    public ShowTenantsResponse() {
        super();
        tenantProperties = new ArrayList<TenantProperty>();
    }
    
    public void setTenantProperty(List<TenantProperty> tenantProperties) {
        this.tenantProperties = tenantProperties;
    }


    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        int tenantNum = in.readInt();
        for(int i = 0; i < tenantNum; ++i) {
            tenantProperties.add(TenantProperty.PROTO.readFrom(in));
        }
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeInt(tenantProperties.size());
        for (TenantProperty tenantProperty : tenantProperties) {
            tenantProperty.writeTo(out);
        }
    }

    public TenantProperty getTenantProperty() {
        if (tenantProperties == null || tenantProperties.size() == 0) {
            return null;
        }
        return tenantProperties.get(0);
    }
    
    public List<TenantProperty> getTenantProperties() {
        return this.tenantProperties;
    }

}
