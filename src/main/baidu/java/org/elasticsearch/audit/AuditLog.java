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

package org.elasticsearch.audit;

import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;

public class AuditLog {
    private static final String AUDIT_LOG_NAME = "auditlog";

    public long threshold = 0;
    public final ESLogger logger = Loggers.getLogger(AUDIT_LOG_NAME);

    public void log(long startTimestamp, String user, String sourceIp, String requestBody, long tookInMillis, String message) {
        if (threshold >= 0 && tookInMillis >= threshold) {
            logger.info("startTimestamp[{}], user[{}], sourceIp[{}], requestBody[{}], took_millis[{}], message[{}]",
                    startTimestamp, user, sourceIp, requestBody, tookInMillis, message);
        }
    }
}
