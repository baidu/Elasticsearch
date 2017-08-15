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

import java.io.IOException;

import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.io.stream.Streamable;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentBuilderString;

import org.apache.distributedlog.DLSN;

public class DLStats implements Streamable, ToXContent {

    long latestTxid = 0;
    DLSN latestDlsn = DLSN.InvalidDLSN;
    
    long commitTxid = 0;
    DLSN commitDlsn = DLSN.InvalidDLSN;

    public DLStats() {

    }

    public DLStats(long latestTxid, DLSN latestDlsn, long commitTxid, DLSN commitDlsn) {
        this.latestDlsn = latestDlsn;
        this.latestTxid = latestTxid;
        this.commitDlsn = commitDlsn;
        this.commitTxid = commitTxid;
    }

    public static DLStats readDLStats(StreamInput in) throws IOException {
        DLStats dlStats = new DLStats();
        dlStats.readFrom(in);
        return dlStats;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        latestTxid = in.readVLong();
        latestDlsn = DLSN.deserializeBytes(in.readByteArray());
        commitTxid = in.readVLong();
        commitDlsn = DLSN.deserializeBytes(in.readByteArray());
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeVLong(latestTxid);
        out.writeByteArray(latestDlsn.serializeBytes());
        out.writeVLong(commitTxid);
        out.writeByteArray(commitDlsn.serializeBytes());
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject(Fields.DLStats);
        builder.field(Fields.LATEST_TXID, latestTxid);
        builder.field(Fields.LATEST_DLSN, latestDlsn.toString());
        builder.field(Fields.COMMIT_TXID, commitTxid);
        builder.field(Fields.COMMIT_DLSN, commitDlsn.toString());
        builder.endObject();
        return builder;
    }

    static final class Fields {
        static final XContentBuilderString DLStats = new XContentBuilderString("dlstats");
        static final XContentBuilderString LATEST_TXID = new XContentBuilderString("latest_txid");
        static final XContentBuilderString LATEST_DLSN = new XContentBuilderString("latest_dlsn");
        static final XContentBuilderString COMMIT_TXID = new XContentBuilderString("commit_txid");
        static final XContentBuilderString COMMIT_DLSN = new XContentBuilderString("commit_dlsn");
    }

    @Override
    public String toString() {
        return "DLStats [latest_txid=" + latestTxid + ", latest_dlsn=" + latestDlsn + ", commit_txid=" + commitTxid + ", commit_dlsn=" + commitDlsn + "]";
    }

    public long getLatestTxid() {
        return latestTxid;
    }

    public DLSN getLatestDlsn() {
        return latestDlsn;
    }

    public long getCommitTxid() {
        return commitTxid;
    }

    public DLSN getCommitDlsn() {
        return commitDlsn;
    }
}
