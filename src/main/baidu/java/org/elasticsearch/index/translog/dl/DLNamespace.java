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

package org.elasticsearch.index.translog.dl;

import java.io.IOException;
import java.net.URI;

import org.elasticsearch.common.settings.Settings;

import org.apache.distributedlog.DistributedLogConfiguration;
import org.apache.distributedlog.DistributedLogConstants;
import org.apache.distributedlog.namespace.DistributedLogNamespace;
import org.apache.distributedlog.namespace.DistributedLogNamespaceBuilder;

public class DLNamespace {

    public static final String LOG_SERVICE_ENDPOINT = "dl.endpoint";
    public static final String ZK_SESSION_TIMEOUT = "dl.zk_session_timeout";
    public static final String DL_REPLICA_NUM = "dl.replica_num";
    public static final String DL_ACK_QUORUM_SIZE = "dl.quorum_num";
    public static final String DL_ENSEMBLE_SIZE = "dl.ensemble_size";
    public static final String DL_SEGMENT_SIZE_MB = "dl.segment_size_mb";
    public static final String DL_MERGE_BUFFER_SIZE = "dl.merge_buffer_size";
    private static DistributedLogNamespace logNamespace = null;
    
    public static synchronized DistributedLogNamespace getNamespace(Settings settings, String localNodeId) throws IllegalArgumentException, NullPointerException, IOException {
        if (logNamespace == null) {
            String logServiceUrl = settings.get(LOG_SERVICE_ENDPOINT);
            URI uri = URI.create(logServiceUrl);
            DistributedLogConfiguration conf = new DistributedLogConfiguration();
            conf.setOutputBufferSize(settings.getAsInt(DL_MERGE_BUFFER_SIZE, 4 * 1024));
            // immediate flush means write the user record and write a control record immediately, so that current client could get the record immediately
            // but this means write two record into bookkeeper
            // in our case we do not need that because replica replay it and not need read it immediately
            // if primary failed, if it recovering, it will write a control record into bk and could read it again
            conf.setImmediateFlushEnabled(false);
            // set write enabled == false, because lease already confirmed there is only one writer
            conf.setWriteLockEnabled(false);
            // this enables move lac after 10 seconds so that other node could see the latest records
            conf.setPeriodicFlushFrequencyMilliSeconds(2);
            // batch write to bookkeeper is disabled
            conf.setMinDelayBetweenImmediateFlushMs(0);
            conf.setZKSessionTimeoutSeconds(settings.getAsInt(ZK_SESSION_TIMEOUT, 10));
            conf.setLockTimeout(DistributedLogConstants.LOCK_IMMEDIATE);
            conf.setLogSegmentRollingIntervalMinutes(0); // has to set to 0 to disable time based rolling policy and enable size based rolling policy
            conf.setMaxLogSegmentBytes(1 << 20 << settings.getAsInt(DL_SEGMENT_SIZE_MB, 8)); // set it to 256MB
            conf.setEnsembleSize(settings.getAsInt(DL_ENSEMBLE_SIZE, 3));
            conf.setAckQuorumSize(settings.getAsInt(DL_ACK_QUORUM_SIZE, 2));
            conf.setWriteQuorumSize(settings.getAsInt(DL_REPLICA_NUM, 3));
            conf.setRowAwareEnsemblePlacementEnabled(false);
            conf.setReadAheadMaxRecords(100);
            conf.setReadAheadBatchSize(3);
            conf.setExplicitTruncationByApplication(true); // set it to true to disable auto truncate
            conf.setRetentionPeriodHours(1); // dl will purge truncated log segments after 1 hour
            logNamespace = DistributedLogNamespaceBuilder.newBuilder()
                    .conf(conf)
                    .uri(uri)
                    .regionId(DistributedLogConstants.LOCAL_REGION_ID)
                    .clientId(localNodeId)
                    .build();
        }
        return logNamespace;
    }
}
