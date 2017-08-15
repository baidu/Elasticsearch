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

package org.elasticsearch.action.admin.cluster.tenant.migrate;

import java.io.IOException;

import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.IndicesRequest;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.MasterNodeRequest;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

public class MigrateIndexTenantRequest extends MasterNodeRequest<MigrateIndexTenantRequest> implements IndicesRequest.Replaceable {

    private String tenantName;
    private String[] indices;
    
    public MigrateIndexTenantRequest(){}
    
    public MigrateIndexTenantRequest(String tenantName, String[] indices) {
        this.tenantName = tenantName;
        this.indices = indices;
    }
    
    /**
     * indices is a string split by ,
     * @param tenantId
     * @param indices
     */
    public MigrateIndexTenantRequest(String tenantName, String indices) {
        this.tenantName = tenantName;
        this.indices = indices.split(",");
    }
    
    public String tenantName() {
        return this.tenantName;
    }
    
    @Override
    public ActionRequestValidationException validate() {
        return null;
    }

    @Override
    public String[] indices() {
        return indices;
    }

    @Override
    public IndicesOptions indicesOptions() {
        return null;
    }

    @Override
    public IndicesRequest indices(String[] indices) {
        this.indices = indices;
        return this;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        tenantName = in.readNullableString();
        int indexNum = in.readInt();
        indices = new String[indexNum];
        for (int i = 0; i < indexNum; ++i) {
            indices[i] = in.readString();
        }
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeNullableString(tenantName);
        int indexNum = indices == null ? 0 : indices.length;
        out.writeInt(indexNum);
        for (int i = 0; i < indexNum; ++i) {
            out.writeString(indices[i]);
        }
    }
}