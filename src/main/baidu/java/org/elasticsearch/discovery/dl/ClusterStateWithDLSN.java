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

package org.elasticsearch.discovery.dl;

import java.io.IOException;

import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.io.stream.Writeable;

import org.apache.distributedlog.DLSN;

public class ClusterStateWithDLSN implements Writeable<ClusterStateWithDLSN> {
    
    public static ClusterStateWithDLSN PROTO = new ClusterStateWithDLSN(null, null);
    
    private ClusterState clusterState;
    private DLSN dlsn;
    
    public ClusterStateWithDLSN(ClusterState clusterState, DLSN dlsn) {
        this.clusterState = clusterState;
        this.dlsn = dlsn;
    }
    
    public ClusterState state() {
        return clusterState;
    }
    
    public void state(ClusterState state) {
        this.clusterState = state;
    }
    
    public DLSN dlsn() {
        return dlsn;
    }
    
    public ClusterStateWithDLSN readFrom(StreamInput in, DiscoveryNode localNode) throws IOException {
        clusterState = ClusterState.PROTO.readFrom(in, localNode);
        dlsn = DLSN.deserializeBytes(in.readByteArray());
        return this;
    }
    
    @Override
    public ClusterStateWithDLSN readFrom(StreamInput in) throws IOException {
        clusterState = ClusterState.PROTO.readFrom(in);
        dlsn = DLSN.deserializeBytes(in.readByteArray());
        return this;
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        clusterState.writeTo(out);
        out.writeByteArray(dlsn.serializeBytes());
    }
    
}
