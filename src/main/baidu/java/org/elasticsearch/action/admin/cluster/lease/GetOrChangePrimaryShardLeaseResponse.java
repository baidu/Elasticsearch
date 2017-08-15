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

import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class GetOrChangePrimaryShardLeaseResponse extends ActionResponse {

    private Map<SimpleShardIdentifier, Boolean> shardsLeaseStatus;

    public GetOrChangePrimaryShardLeaseResponse() {
        this.shardsLeaseStatus = new HashMap<SimpleShardIdentifier, Boolean>();
    }
    
    public GetOrChangePrimaryShardLeaseResponse(List<SimpleShardIdentifier> shardIdentifiers) {
        if (shardIdentifiers == null) {
            return;
        }
        shardsLeaseStatus = new HashMap<SimpleShardIdentifier, Boolean>(shardIdentifiers.size());
        for (SimpleShardIdentifier shardIdentifier : shardIdentifiers) {
            shardsLeaseStatus.put(shardIdentifier, false);
        }
    }
    
    public boolean checkShardStatus(String indexName, String indexUUID, int shardId) {
        SimpleShardIdentifier shardIdentifier = new SimpleShardIdentifier(indexName, indexUUID, shardId);
        if (shardsLeaseStatus.containsKey(shardIdentifier)) {
            return shardsLeaseStatus.get(shardIdentifier);
        }
        return false;
    }
    
    public void setShardStatus(SimpleShardIdentifier shardIdentifier) {
        shardsLeaseStatus.put(shardIdentifier, true);
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        int shardNum = in.readInt();
        for (int i = 0; i < shardNum; ++i) {
            SimpleShardIdentifier shardIdentifier = SimpleShardIdentifier.readFromInput(in);
            boolean status = in.readBoolean();
            shardsLeaseStatus.put(shardIdentifier, status);
        }
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeInt(shardsLeaseStatus.size());
        for (SimpleShardIdentifier shardIdentifier : shardsLeaseStatus.keySet()) {
            shardIdentifier.writeTo(out);
            out.writeBoolean(shardsLeaseStatus.get(shardIdentifier));
        }
    }
}
