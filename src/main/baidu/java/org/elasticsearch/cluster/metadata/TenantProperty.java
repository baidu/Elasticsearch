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
import java.util.HashMap;

import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.io.stream.Writeable;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentParser;

public class TenantProperty implements Writeable<TenantProperty> {
    
    public static final long ROOT_TENANT_ID = 0;
    public static final String ROOT_TENANT_NAME = "default";
    public static final long INVALID_TENANT_ID = -1;
    public static TenantProperty PROTO = builder().build();
    
    private final long tenantId;
    private final String tenantName;
    private final int desireInstanceNum;
    // current node list
    private final HashMap<String, AllocatedNodeInfo> nodes;
    
    private TenantProperty(long tenantId, String tenantName, int desireInstanceNum, HashMap<String, AllocatedNodeInfo> nodes) {
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.desireInstanceNum = desireInstanceNum;
        this.nodes = nodes;
    }

    public String tenantName() {
        return tenantName;
    }
    
    public long tenantId() {
        return tenantId;
    }
    
    public HashMap<String, AllocatedNodeInfo> nodes() {
        return nodes;
    }
    
    public AllocatedNodeInfo getAllocatedNodeInfo(String allocatedNodeInfoId) {
        return nodes.get(allocatedNodeInfoId);
    }
    
    public int desireInstanceNum() {
        return desireInstanceNum;
    }
    
    public int actualInstanceNum() {
        return  nodes == null ? 0 : nodes.size();
    }
    
    public int getNoneDecomissionNodesNum() {
        int noneDecommissionNodeNum = 0;
        for (AllocatedNodeInfo nodeInfo : nodes.values()) {
            if (!nodeInfo.getAllocatedNodeStatus().equals(AllocatedNodeStatus.DECOMMISSIONING)) {
                ++ noneDecommissionNodeNum;
            }
        }
        return noneDecommissionNodeNum;
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeLong(tenantId);
        out.writeString(tenantName);
        out.writeInt(desireInstanceNum);
        out.writeInt(nodes.size());
        for (AllocatedNodeInfo nodeId : nodes.values()) {
            nodeId.writeTo(out);
        }
    }

    @Override
    public TenantProperty readFrom(StreamInput in) throws IOException {
        long tenantId = in.readLong();
        String tenantName = in.readString();
        int desireInstanceNum = in.readInt();
        int nodeSize = in.readInt();
        HashMap<String, AllocatedNodeInfo> nodes = new HashMap<String, AllocatedNodeInfo>();
        for (int nodeIndex = 0; nodeIndex < nodeSize; ++nodeIndex) {
            AllocatedNodeInfo nodeInfo = AllocatedNodeInfo.PROTO.readFrom(in);
            nodes.put(nodeInfo.getAllocatedNodeId(), nodeInfo);
        }
        return new TenantProperty(tenantId, tenantName, desireInstanceNum, nodes);
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        
        private long tenantId;
        private String tenantName;
        private int desireInstanceNum;
        private HashMap<String, AllocatedNodeInfo> nodes;
        
        public Builder() {
            this.tenantId = -1;
            this.tenantName = "";
            this.desireInstanceNum = 0;
            this.nodes = new HashMap<>();
        }
        
        public Builder(TenantProperty tenantProperty) {
            this.tenantId = tenantProperty.tenantId;
            this.tenantName = tenantProperty.tenantName;
            this.desireInstanceNum = tenantProperty.desireInstanceNum;
            this.nodes = new HashMap<>(tenantProperty.nodes.size());
            for(AllocatedNodeInfo nodeInfo : tenantProperty.nodes.values()) {
                this.nodes.put(nodeInfo.getAllocatedNodeId(),  nodeInfo);
            }
        }
        
        public TenantProperty build() {
            return new TenantProperty(tenantId, tenantName, desireInstanceNum, nodes);
        }
        
        public Builder tenantName(String tenantName) {
            this.tenantName = tenantName;
            return this;
        }
        
        public Builder tenantId(long id) {
            this.tenantId = id;
            return this;
        }
        
        public long tenantId() {
            return this.tenantId;
        }
        
        public Builder instanceNum(int instanceNum) {
            this.desireInstanceNum = instanceNum;
            return this;
        }
        
        public Builder addInstanceNum(int numInstancesToAdd) {
            this.desireInstanceNum = this.desireInstanceNum + numInstancesToAdd;
            return this;
        }
        
        public Builder minusInstanceNum(int numInstancesToMinus) {
            this.desireInstanceNum = this.desireInstanceNum - numInstancesToMinus;
            return this;
        }
        
        public void removeNode(AllocatedNodeInfo nodeInfo) {
            this.nodes.remove(nodeInfo.getAllocatedNodeId());
        }
        
        public void removeNode(DiscoveryNode node) {
            this.nodes.remove(new AllocatedNodeInfo(node).getAllocatedNodeId());
        }
        
        public void addNode(AllocatedNodeInfo nodeInfo) {
            this.nodes.put(nodeInfo.getAllocatedNodeId(), nodeInfo);
        }
        
        public static void toXContent(TenantProperty tenantProperty, XContentBuilder builder, ToXContent.Params params) throws IOException {
            builder.startObject(tenantProperty.tenantName);
                builder.field("id", tenantProperty.tenantId);
                builder.field("name", tenantProperty.tenantName);
                builder.field("desire_node_num", tenantProperty.desireInstanceNum);
                builder.startArray("nodes");
                    for (AllocatedNodeInfo nodeInfo : tenantProperty.nodes.values()) {
                        builder.startObject();
                            builder.field("ip", nodeInfo.getIpAddress());
                            builder.field("port", nodeInfo.getPort());
                            builder.field("status", nodeInfo.getAllocatedNodeStatus());
                        builder.endObject();
                    }
                builder.endArray();
            builder.endObject();
        }
        
        public static TenantProperty fromXContent(XContentParser parser) throws IOException {
            Builder builder = new Builder();
            XContentParser.Token token = parser.currentToken();
            String currentFieldName = parser.currentName();
            while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
                if (token == XContentParser.Token.FIELD_NAME) {
                    currentFieldName = parser.currentName();
                } else if (token == XContentParser.Token.VALUE_STRING) {
                    if ("name".equalsIgnoreCase(currentFieldName)) {
                        builder.tenantName = parser.text();
                    }
                } else if (token == XContentParser.Token.VALUE_NUMBER) {
                    if ("id".equalsIgnoreCase(currentFieldName)) {
                        builder.tenantId = parser.longValue();
                    } else if ("desire_node_num".equalsIgnoreCase(currentFieldName)) {
                        builder.desireInstanceNum = parser.intValue();
                    }
                } else if (token == XContentParser.Token.START_ARRAY) {
                    if ("nodes".equalsIgnoreCase(currentFieldName)) {
                        while ((token = parser.nextToken()) != XContentParser.Token.END_ARRAY) {
                            if (token == XContentParser.Token.START_OBJECT) {
                                String ipaddress = "";
                                int port = 0;
                                AllocatedNodeStatus allocatedNodeStatus = AllocatedNodeStatus.NORMAL;
                                while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
                                    if (token == XContentParser.Token.FIELD_NAME) {
                                        currentFieldName = parser.currentName();
                                    } else if (token == XContentParser.Token.VALUE_STRING) {
                                        if ("ip".equalsIgnoreCase(currentFieldName)) {
                                            ipaddress = parser.text();
                                        } else if ("status".equalsIgnoreCase(currentFieldName)) {
                                            allocatedNodeStatus = AllocatedNodeStatus.valueOf(parser.text());
                                        }
                                    } else if (token == XContentParser.Token.VALUE_NUMBER) {
                                        if ("port".equalsIgnoreCase(currentFieldName)) {
                                            port = parser.intValue();
                                        }
                                    }
                                }
                                AllocatedNodeInfo nodeId = new AllocatedNodeInfo(ipaddress, port, allocatedNodeStatus);
                                builder.addNode(nodeId);
                            }
                        }
                    }
                }
            }
            return builder.build();
        }
    }

    @Override
    public String toString() {
        return "TenantProperty [tenantId=" + tenantId + ", tenantName="
                + tenantName + ", desireInstanceNum=" + desireInstanceNum
                + ", nodes=" + nodes + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + desireInstanceNum;
        result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
        result = prime * result + (int) (tenantId ^ (tenantId >>> 32));
        result = prime * result
                + ((tenantName == null) ? 0 : tenantName.hashCode());
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
        TenantProperty other = (TenantProperty) obj;
        if (desireInstanceNum != other.desireInstanceNum)
            return false;
        if (nodes == null) {
            if (other.nodes != null)
                return false;
        } else if (!nodes.equals(other.nodes))
            return false;
        if (tenantId != other.tenantId)
            return false;
        if (tenantName == null) {
            if (other.tenantName != null)
                return false;
        } else if (!tenantName.equals(other.tenantName))
            return false;
        return true;
    }
}