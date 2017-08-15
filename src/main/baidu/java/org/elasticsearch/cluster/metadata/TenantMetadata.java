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
import java.util.Map;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.cluster.AbstractDiffable;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentParser;

import com.google.common.collect.ImmutableMap;

public class TenantMetadata extends AbstractDiffable<TenantMetadata> {
    
    public static TenantMetadata PROTO = builder().build(); 
    public static final String TENANT_SETTING_PREFIX = "tenants";
    private final ImmutableMap<Long, TenantProperty> tenantProperties;
    private final ImmutableMap<String, Long> nodeToTenant;
    private final ImmutableMap<String, Long> tenantNameToId;
    private long maxTenantId = 0;
    
    private TenantMetadata(ImmutableMap<Long, TenantProperty> tenantProperties, long maxTenantId) {
        this.tenantProperties = tenantProperties;
        
        Map<String, Long> nameToId = new HashMap<>();
        for (Long id : this.tenantProperties.keySet()) {
            nameToId.put(this.tenantProperties.get(id).tenantName(), id);
        }
        this.tenantNameToId = ImmutableMap.copyOf(nameToId);
        
        Map<String, Long> tmpNodeToTenant = new HashMap<>();
        for (Long id : this.tenantProperties.keySet()) {
            for (AllocatedNodeInfo node : this.tenantProperties.get(id).nodes().values()) {
                if (tmpNodeToTenant.containsKey(node)) {
                    throw new IllegalStateException("");
                } else {
                    tmpNodeToTenant.put(node.getAllocatedNodeId(), id);
                }
            }
        }
        this.nodeToTenant = ImmutableMap.copyOf(tmpNodeToTenant);
        this.maxTenantId = maxTenantId;
    }
    
    public TenantProperty getTenantProperty(long tenantId) {
        if (tenantProperties.containsKey(tenantId)) {
            return tenantProperties.get(tenantId);
        }
        return null;
    }
    
    public TenantProperty getTenantPropertyForLoginUser(String loginUsername) {
        long tenantId = UserProperty.getTenantIdFromLoginUserName(loginUsername, this);
        return tenantProperties.get(tenantId);
    }
    
    public TenantProperty getNodeTenant(DiscoveryNode node) {
        AllocatedNodeInfo nodeGroupTag = AllocatedNodeInfo.getNodeGroupFromNode(node);
        Long tenantId = nodeToTenant.get(nodeGroupTag.getAllocatedNodeId());
        if (tenantId == null) {
            return null;
        }
        return tenantProperties.get(tenantId);
    }
    
    public boolean isNodeAllocatedToTenant(DiscoveryNode node, Long tenantId) {
        AllocatedNodeInfo nodeInfo = AllocatedNodeInfo.getNodeGroupFromNode(node);
        Long nodeTenantId = nodeToTenant.get(nodeInfo.getAllocatedNodeId());
        if (nodeTenantId == null || !nodeTenantId.equals(tenantId)) {
            return false;
        }
        TenantProperty tenantProperty = tenantProperties.get(nodeTenantId);
        AllocatedNodeInfo nodeInfoInTenant = tenantProperty.nodes().get(nodeInfo.getAllocatedNodeId());
        if (nodeInfoInTenant != null && nodeInfoInTenant.getAllocatedNodeStatus().equals(AllocatedNodeStatus.DECOMMISSIONING)) {
            return false;
        }
        return true;
    }
    
    public TenantProperty getNodeTenant(AllocatedNodeInfo nodeGroupTag) {
        Long tenantId = nodeToTenant.get(nodeGroupTag.getAllocatedNodeId());
        return tenantProperties.get(tenantId);
    }
    
    public boolean tenantExist(long tenantId) {
        if (tenantId == TenantProperty.ROOT_TENANT_ID) {
            return true;
        }
        return tenantProperties.containsKey(tenantId);
    }
    
    public boolean tenantExist(String tenantName) {
        return tenantNameToId.containsKey(tenantName);
    }
    
    public long tenantId(String tenantName) {
        if (!tenantNameToId.containsKey(tenantName)) {
            throw new ElasticsearchException("could not find tenant: " + tenantName);
        }
        return tenantNameToId.get(tenantName);
    }
    
    public long maxTenantId() {
        return maxTenantId;
    }
    
    public ImmutableMap<Long, TenantProperty> tenantProperties() {
        return tenantProperties;
    }
    
    public TenantProperty tenant(long tenantId) {
        if (!tenantProperties.containsKey(tenantId)) {
            throw new ElasticsearchException("could not find tenant with id: " + tenantId);
        }
        return tenantProperties.get(tenantId);
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeInt(tenantProperties.size());
        for (Long tenantId : tenantProperties.keySet()) {
            TenantProperty tenantProperty = tenantProperties.get(tenantId);
            tenantProperty.writeTo(out);
        }
        out.writeLong(maxTenantId);
    }

    @Override
    public TenantMetadata readFrom(StreamInput in) throws IOException {
        int tenantNum = in.readInt();
        Builder builder = new Builder();
        for (int tenantIndex = 0; tenantIndex < tenantNum; ++tenantIndex) {
            TenantProperty tenantProperty = TenantProperty.PROTO.readFrom(in);
            builder.addOrChangeTenantProperty(tenantProperty);
        }
        builder.maxTenantId(in.readLong());
        return builder.build();
    }
    
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Map<Long, TenantProperty> tenantProperties;
        private long maxTenantId = 1;
        
        public Builder() {
            tenantProperties = new HashMap<Long, TenantProperty>();
        }
        
        public Builder(TenantMetadata tenantMetadata) {
            maxTenantId = tenantMetadata.maxTenantId;
            tenantProperties = new HashMap<Long, TenantProperty>();
            for (Long tenantId : tenantMetadata.tenantProperties.keySet()) {
                tenantProperties.put(tenantId, tenantMetadata.tenantProperties.get(tenantId));
            }
        }
        
        public Builder addOrChangeTenantProperty(TenantProperty tenantProperty) {
            if (tenantProperty.tenantId() != TenantProperty.INVALID_TENANT_ID) {
                tenantProperties.put(tenantProperty.tenantId(), tenantProperty);
            }
            return this;
        }
        
        public Builder addOrChangeTenantProperty(TenantProperty.Builder tenantPropertyBuilder) {
            tenantProperties.put(tenantPropertyBuilder.tenantId(), tenantPropertyBuilder.build());
            return this;
        }
        
        public TenantProperty getTenantProperty(long tenantId) {
            return tenantProperties.get(tenantId);
        }

        public boolean containsTenant(Long tenantId) {
            return tenantProperties.containsKey(tenantId);
        }
        
        public boolean deleteTenant(Long tenantId) {
            if (tenantProperties.containsKey(tenantId)) {
                tenantProperties.remove(tenantId);
            }
            return true;
        }
        
        public void maxTenantId(long id) {
            this.maxTenantId = id;
        }
        
        public long maxTenantId() {
            return this.maxTenantId;
        }
        
        public TenantMetadata build() {
            ImmutableMap<Long, TenantProperty> newProperties = ImmutableMap.copyOf(tenantProperties);
            return new TenantMetadata(newProperties, maxTenantId);
        }
        
        public static void toXContent(TenantMetadata tenantMetadata, XContentBuilder builder, ToXContent.Params params) throws IOException {
            builder.startObject("tenants");
                builder.field("max_tenant_id", tenantMetadata.maxTenantId);
                builder.startObject("tenant_list");
                    for (Long tenantId : tenantMetadata.tenantProperties.keySet()) {
                        TenantProperty.Builder.toXContent(tenantMetadata.tenantProperties.get(tenantId), builder, params);
                    }
                builder.endObject();
            builder.endObject();
        }
        
        public static TenantMetadata fromXContent(XContentParser parser) throws IOException {
            Builder builder = new Builder();
            XContentParser.Token token = parser.currentToken();
            String currentFieldName = parser.currentName();
            while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
                if (token == XContentParser.Token.FIELD_NAME) {
                    currentFieldName = parser.currentName();
                } else if (token == XContentParser.Token.START_OBJECT) {
                    if ("tenant_list".equalsIgnoreCase(currentFieldName)) {
                        while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
                            if (token == XContentParser.Token.FIELD_NAME) {
                                currentFieldName = parser.currentName();
                            } else if (token == XContentParser.Token.START_OBJECT) {
                                TenantProperty tenantProperty = TenantProperty.Builder.fromXContent(parser);
                                builder.addOrChangeTenantProperty(tenantProperty);
                            }
                        }
                    }
                } else if (token == XContentParser.Token.VALUE_NUMBER) {
                    if ("max_tenant_id".equalsIgnoreCase(currentFieldName)) {
                        builder.maxTenantId(parser.longValue());
                    }
                }
            }
            return builder.build();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (maxTenantId ^ (maxTenantId >>> 32));
        result = prime
                * result
                + ((tenantProperties == null) ? 0 : tenantProperties.hashCode());
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
        TenantMetadata other = (TenantMetadata) obj;
        if (maxTenantId != other.maxTenantId)
            return false;
        if (tenantProperties == null) {
            if (other.tenantProperties != null)
                return false;
        } else if (!tenantProperties.equals(other.tenantProperties))
            return false;
        return true;
    }
}
