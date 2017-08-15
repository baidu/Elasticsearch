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

package org.elasticsearch.action.admin.cluster.tenant.modify;

import io.crate.sql.tree.TenantModificationOperation;

import java.io.IOException;

import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.support.master.MasterNodeRequest;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
public class ModifyTenantNodesRequest extends MasterNodeRequest<ModifyTenantNodesRequest> {

    private String nodeIp;
    private int nodePort;
    private String tenantName;
    private TenantModificationOperation operation;

    public ModifyTenantNodesRequest() {
        this.nodeIp = null;
        this.nodePort = -1;
        this.tenantName = "";
        this.operation = TenantModificationOperation.ADD_NODES;
    }
    
    public ModifyTenantNodesRequest(String nodeIpPort, String tenantName, TenantModificationOperation operation) {
        super();
        String[] ipPort = nodeIpPort.split(":");
        this.nodeIp = ipPort[0].trim();
        this.nodePort = Integer.parseInt(ipPort[1]);
        this.tenantName = tenantName;
        this.operation = operation;
    }

    public ModifyTenantNodesRequest(String nodeIp, int nodePort, String tenantName, TenantModificationOperation operation) {
        this.nodeIp = nodeIp;
        this.nodePort = nodePort;
        this.tenantName = tenantName;
        this.operation = operation;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        nodeIp = in.readNullableString();
        nodePort = in.readInt();
        tenantName = in.readNullableString();
        operation = TenantModificationOperation.fromValue(in.readInt());
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeNullableString(nodeIp);
        out.writeInt(nodePort);
        out.writeNullableString(tenantName);
        out.writeInt(operation.getValue());
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }

    public String getNodeIp() {
        return nodeIp;
    }

    public int getNodePort() {
        return nodePort;
    }
    
    public String tenantName() {
        return this.tenantName;
    }
    
    public TenantModificationOperation getOperation() {
        return this.operation;
    }
}
