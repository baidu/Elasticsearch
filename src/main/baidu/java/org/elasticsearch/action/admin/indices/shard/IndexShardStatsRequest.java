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

package org.elasticsearch.action.admin.indices.shard;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.stats.CommonStatsFlags;
import org.elasticsearch.action.admin.indices.stats.CommonStatsFlags.Flag;
import org.elasticsearch.action.support.broadcast.BroadcastRequest;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

public class IndexShardStatsRequest extends BroadcastRequest<IndexShardStatsRequest> {

    private CommonStatsFlags flags = new CommonStatsFlags();

    private String indexName;
    private int shardId;
    
    public IndexShardStatsRequest() {}
    
    public IndexShardStatsRequest(String indexName, int shardId) {
        this.indexName = indexName;
        this.shardId = shardId;
        super.indices(indexName);
    }
    
    /**
     * Sets all flags to return all stats.
     */
    public IndexShardStatsRequest all() {
        flags.all();
        return this;
    }

    /**
     * Clears all stats.
     */
    public IndexShardStatsRequest clear() {
        flags.clear();
        return this;
    }

    /**
     * Document types to return stats for. Mainly affects {@link #indexing(boolean)} when
     * enabled, returning specific indexing stats for those types.
     */
    public IndexShardStatsRequest types(String... types) {
        flags.types(types);
        return this;
    }

    /**
     * Document types to return stats for. Mainly affects {@link #indexing(boolean)} when
     * enabled, returning specific indexing stats for those types.
     */
    public String[] types() {
        return this.flags.types();
    }

    /**
     * Sets specific search group stats to retrieve the stats for. Mainly affects search
     * when enabled.
     */
    public IndexShardStatsRequest groups(String... groups) {
        flags.groups(groups);
        return this;
    }

    public String[] groups() {
        return this.flags.groups();
    }

    public IndexShardStatsRequest docs(boolean docs) {
        flags.set(Flag.Docs, docs);
        return this;
    }

    public boolean docs() {
        return flags.isSet(Flag.Docs);
    }

    public IndexShardStatsRequest store(boolean store) {
        flags.set(Flag.Store, store);
        return this;
    }

    public boolean store() {
        return flags.isSet(Flag.Store);
    }

    public IndexShardStatsRequest indexing(boolean indexing) {
        flags.set(Flag.Indexing, indexing);

        return this;
    }

    public boolean indexing() {
        return flags.isSet(Flag.Indexing);
    }

    public IndexShardStatsRequest get(boolean get) {
        flags.set(Flag.Get, get);
        return this;
    }

    public boolean get() {
        return flags.isSet(Flag.Get);
    }

    public IndexShardStatsRequest search(boolean search) {
        flags.set(Flag.Search, search);
        return this;
    }

    public boolean search() {
        return flags.isSet(Flag.Search);
    }

    public IndexShardStatsRequest merge(boolean merge) {
        flags.set(Flag.Merge, merge);
        return this;
    }

    public boolean merge() {
        return flags.isSet(Flag.Merge);
    }

    public IndexShardStatsRequest refresh(boolean refresh) {
        flags.set(Flag.Refresh, refresh);
        return this;
    }

    public boolean refresh() {
        return flags.isSet(Flag.Refresh);
    }

    public IndexShardStatsRequest flush(boolean flush) {
        flags.set(Flag.Flush, flush);
        return this;
    }

    public boolean flush() {
        return flags.isSet(Flag.Flush);
    }

    public IndexShardStatsRequest warmer(boolean warmer) {
        flags.set(Flag.Warmer, warmer);
        return this;
    }

    public boolean warmer() {
        return flags.isSet(Flag.Warmer);
    }

    public IndexShardStatsRequest queryCache(boolean queryCache) {
        flags.set(Flag.QueryCache, queryCache);
        return this;
    }

    public boolean queryCache() {
        return flags.isSet(Flag.QueryCache);
    }

    public IndexShardStatsRequest fieldData(boolean fieldData) {
        flags.set(Flag.FieldData, fieldData);
        return this;
    }

    public boolean fieldData() {
        return flags.isSet(Flag.FieldData);
    }

    public IndexShardStatsRequest percolate(boolean percolate) {
        flags.set(Flag.Percolate, percolate);
        return this;
    }

    public boolean percolate() {
        return flags.isSet(Flag.Percolate);
    }

    public IndexShardStatsRequest segments(boolean segments) {
        flags.set(Flag.Segments, segments);
        return this;
    }

    public boolean segments() {
        return flags.isSet(Flag.Segments);
    }

    public IndexShardStatsRequest fieldDataFields(String... fieldDataFields) {
        flags.fieldDataFields(fieldDataFields);
        return this;
    }

    public String[] fieldDataFields() {
        return flags.fieldDataFields();
    }

    public IndexShardStatsRequest completion(boolean completion) {
        flags.set(Flag.Completion, completion);
        return this;
    }

    public boolean completion() {
        return flags.isSet(Flag.Completion);
    }

    public IndexShardStatsRequest completionFields(String... completionDataFields) {
        flags.completionDataFields(completionDataFields);
        return this;
    }

    public String[] completionFields() {
        return flags.completionDataFields();
    }

    public IndexShardStatsRequest translog(boolean translog) {
        flags.set(Flag.Translog, translog);
        return this;
    }

    public boolean translog() {
        return flags.isSet(Flag.Translog);
    }

    public IndexShardStatsRequest suggest(boolean suggest) {
        flags.set(Flag.Suggest, suggest);
        return this;
    }

    public boolean suggest() {
        return flags.isSet(Flag.Suggest);
    }

    public IndexShardStatsRequest requestCache(boolean requestCache) {
        flags.set(Flag.RequestCache, requestCache);
        return this;
    }

    public boolean requestCache() {
        return flags.isSet(Flag.RequestCache);
    }

    public IndexShardStatsRequest recovery(boolean recovery) {
        flags.set(Flag.Recovery, recovery);
        return this;
    }

    public boolean recovery() {
        return flags.isSet(Flag.Recovery);
    }
    
    public IndexShardStatsRequest dl(boolean dl) {
        flags.set(Flag.DL, dl);
        return this;
    }

    public boolean dl() {
        return flags.isSet(Flag.DL);
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        flags.writeTo(out);
        out.writeString(indexName);
        out.writeInt(shardId);
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        flags = CommonStatsFlags.readCommonStatsFlags(in);
        indexName = in.readString();
        shardId = in.readInt();
    }

    public String getIndexName() {
        return indexName;
    }

    public int getShardId() {
        return shardId;
    }
}
