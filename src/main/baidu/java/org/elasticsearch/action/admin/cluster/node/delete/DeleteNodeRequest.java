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

package org.elasticsearch.action.admin.cluster.node.delete;

import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.support.master.MasterNodeRequest;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.network.NetworkAddress;

import com.google.common.net.InetAddresses;

import java.io.IOException;
import java.net.InetAddress;

/**
 * A request to delete an index. Best created with {@link org.elasticsearch.client.Requests#deleteIndexRequest(String)}.
 */
public class DeleteNodeRequest extends MasterNodeRequest<DeleteNodeRequest> {

    private String nodeIp;
    private int nodePort;
    private InetAddress address;

    public DeleteNodeRequest() {
        this.nodeIp = null;
        this.nodePort = -1;
        this.address = null;
    }
    
    public DeleteNodeRequest(String nodeIpPort) {
        String[] ipPort = nodeIpPort.split(":");
        this.nodeIp = ipPort[0];
        this.nodePort = Integer.parseInt(ipPort[1]);
        this.address = InetAddresses.forString(nodeIp);
    }

    public DeleteNodeRequest(String nodeIp, int nodePort) {
        this.nodeIp = nodeIp;
        this.nodePort = nodePort;
        this.address = InetAddresses.forString(nodeIp);
    }
    
    public String getIpPortAddress() {
        return NetworkAddress.formatAddress(address) + ":" + nodePort;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        nodeIp = in.readString();
        nodePort = in.readInt();
        this.address = InetAddresses.forString(nodeIp);
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeString(nodeIp);
        out.writeInt(nodePort);
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
    
    public InetAddress getAddress() {
        return address;
    }
}
