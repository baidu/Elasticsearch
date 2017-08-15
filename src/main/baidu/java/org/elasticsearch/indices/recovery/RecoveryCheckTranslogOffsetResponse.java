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

package org.elasticsearch.indices.recovery;

import java.io.IOException;

import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.transport.TransportResponse;

public class RecoveryCheckTranslogOffsetResponse extends TransportResponse {
    
    private long currentOffset;

    public RecoveryCheckTranslogOffsetResponse(long currentOffset) {
        this.currentOffset = currentOffset;
    }
    
    public RecoveryCheckTranslogOffsetResponse() {}

    @Override
    public void readFrom(StreamInput in) throws IOException {
        currentOffset = in.readLong();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeLong(currentOffset);
    }
    
    public long currentOffset() {
        return currentOffset;
    }
}