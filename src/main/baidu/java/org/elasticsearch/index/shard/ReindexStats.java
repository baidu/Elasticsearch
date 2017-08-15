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

package org.elasticsearch.index.shard;

import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.io.stream.Streamable;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentBuilderString;

import java.io.IOException;

/**
 */
public class ReindexStats implements Streamable, ToXContent {

    long rate = 100;

    public ReindexStats() {

    }

    public long getRate() {
        return rate;
    }

    public ReindexStats(long rate) {
        this.rate = rate;
    }


    public static ReindexStats readReindexStats(StreamInput in) throws IOException {
        ReindexStats reindexStats = new ReindexStats();
        reindexStats.readFrom(in);
        return reindexStats;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        rate = in.readVLong();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeVLong(rate);
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject(Fields.REINDEX);
        builder.field(Fields.rate, rate);
        builder.endObject();
        return builder;
    }

    static final class Fields {
        static final XContentBuilderString REINDEX = new XContentBuilderString("reindex");
        static final XContentBuilderString rate = new XContentBuilderString("rate");
    }

    @Override
    public String toString() {
        return "ReindexStats [rate=" + rate + "]";
    }
}
