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

package org.elasticsearch.action.admin.cluster.lease;

import java.io.IOException;

import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.io.stream.Streamable;

public class SimpleShardIdentifier implements Streamable {

    private String indexUUID;
    private String indexName;
    private int shardId;
    
    private SimpleShardIdentifier() {}
    
    public SimpleShardIdentifier(String indexName, String indexUUID, int shardId) {
        this.indexName = indexName;
        this.indexUUID = indexUUID;
        this.shardId = shardId;
    }
    
    @Override
    public void readFrom(StreamInput in) throws IOException {
        indexName = in.readString();
        indexUUID = in.readString();
        shardId = in.readInt();
    }
    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeString(indexName);
        out.writeString(indexUUID);
        out.writeInt(shardId);
    }
    
    public static SimpleShardIdentifier readFromInput(StreamInput in) throws IOException {
        SimpleShardIdentifier shardIdentifier = new SimpleShardIdentifier();
        shardIdentifier.readFrom(in);
        return shardIdentifier;
    }

    public String getIndexName() {
        return indexName;
    }
    
    public String getIndexUUID() {
        return indexUUID;
    }

    public int getShardId() {
        return shardId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((indexName == null) ? 0 : indexName.hashCode());
        result = prime * result
                + ((indexUUID == null) ? 0 : indexUUID.hashCode());
        result = prime * result + shardId;
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
        SimpleShardIdentifier other = (SimpleShardIdentifier) obj;
        if (indexName == null) {
            if (other.indexName != null)
                return false;
        } else if (!indexName.equals(other.indexName))
            return false;
        if (indexUUID == null) {
            if (other.indexUUID != null)
                return false;
        } else if (!indexUUID.equals(other.indexUUID))
            return false;
        if (shardId != other.shardId)
            return false;
        return true;
    }
}
