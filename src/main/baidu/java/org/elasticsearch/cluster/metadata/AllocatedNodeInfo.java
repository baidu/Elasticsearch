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

package org.elasticsearch.cluster.metadata;

import java.io.IOException;

import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.io.stream.Writeable;

public class AllocatedNodeInfo implements Writeable<AllocatedNodeInfo> {

    public static final AllocatedNodeInfo PROTO = new AllocatedNodeInfo("unknown", 0);
    private final String ipAddress;
    private final int port;
    private AllocatedNodeStatus allocatedNodeStatus;
    
    public AllocatedNodeInfo(String ipAddress, int port, AllocatedNodeStatus status) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.allocatedNodeStatus = status;
    }
    
    public AllocatedNodeInfo(String ipAddress, int port) {
        this(ipAddress, port, AllocatedNodeStatus.NORMAL);
    }
    
    public AllocatedNodeInfo(DiscoveryNode node) {
        this(node.getAddress().getAddress(), node.getAddress().getPort());
    }
    
    public String getAllocatedNodeId() {
        return ipAddress + ":" + port;
    }
    
    public String getIpPortAddress() {
        return getAllocatedNodeId();
    }
    
    public AllocatedNodeStatus getAllocatedNodeStatus() {
        return allocatedNodeStatus;
    }
    
    public String getIpAddress() {
        return ipAddress;
    }
    public int getPort() {
        return port;
    }
    
    public boolean sameHost(DiscoveryNode node) {
        return ipAddress.equals(node.getAddress().getAddress());
    }

    @Override
    public AllocatedNodeInfo readFrom(StreamInput in) throws IOException {
        String ipAddress = in.readString();
        int port = in.readInt();
        AllocatedNodeStatus allocatedNodeStatus = AllocatedNodeStatus.fromValue(in.readInt());
        return new AllocatedNodeInfo(ipAddress, port, allocatedNodeStatus);
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeString(ipAddress);
        out.writeInt(port);
        out.writeInt(allocatedNodeStatus.getValue());
    }
    
    public static AllocatedNodeInfo getNodeGroupFromNode(DiscoveryNode node) {
        return new AllocatedNodeInfo(node);
    }

    @Override
    public String toString() {
        return "(" + ipAddress + ":" + port + "," + allocatedNodeStatus.toString() + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((ipAddress == null) ? 0 : ipAddress.hashCode());
        result = prime * result + port;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AllocatedNodeInfo other = (AllocatedNodeInfo) obj;
        if (allocatedNodeStatus != other.allocatedNodeStatus)
            return false;
        if (ipAddress == null) {
            if (other.ipAddress != null)
                return false;
        } else if (!ipAddress.equals(other.ipAddress))
            return false;
        if (port != other.port)
            return false;
        return true;
    }
}
