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

import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.support.master.MasterNodeReadRequest;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetOrChangePrimaryShardLeaseRequest extends MasterNodeReadRequest<GetOrChangePrimaryShardLeaseRequest> {
    
    private String nodeId;
    private List<SimpleShardIdentifier> shardIdentifiers;
    private boolean isGetRequest;
    
    public GetOrChangePrimaryShardLeaseRequest() {
    }

    public GetOrChangePrimaryShardLeaseRequest(String nodeId, boolean isGetRequest) {
        this.nodeId = nodeId;
        this.isGetRequest = isGetRequest;
        this.shardIdentifiers = new ArrayList<SimpleShardIdentifier>();
    }
    
    public GetOrChangePrimaryShardLeaseRequest(String nodeId, boolean isGetRequest, String indexName, String indexUUID, int shardId) {
        this.nodeId = nodeId;
        this.isGetRequest = isGetRequest;
        this.shardIdentifiers = new ArrayList<SimpleShardIdentifier>();
        this.shardIdentifiers.add(new SimpleShardIdentifier(indexName, indexUUID, shardId));
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }


    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        this.nodeId = in.readString();
        this.isGetRequest = in.readBoolean();
        int shardNum = in.readInt();
        this.shardIdentifiers = new ArrayList<SimpleShardIdentifier>(shardNum);
        for (int i = 0; i < shardNum; ++i) {
            this.shardIdentifiers.add(SimpleShardIdentifier.readFromInput(in));
        }
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeString(nodeId);
        out.writeBoolean(isGetRequest);
        out.writeInt(shardIdentifiers.size());
        for(int i = 0; i < shardIdentifiers.size(); ++i) {
            shardIdentifiers.get(i).writeTo(out);
        }
    }

    public String getNodeId() {
        return nodeId;
    }

    public List<SimpleShardIdentifier> getShardIdentifiers() {
        return shardIdentifiers;
    }

    public boolean isGetRequest() {
        return isGetRequest;
    }

}
