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

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.node.settings.NodeSettingsService;

public class AuditService {
    public static final String AUDIT_LOG_THRESHOLD = "cluster.auditlog.threshold";

    private static AuditLog auditLog;

    public AuditService(NodeSettingsService nodeSettingsService) {
        nodeSettingsService.addListener(new ApplySettings());
        auditLog = new AuditLog();
    }

    private class ApplySettings implements NodeSettingsService.Listener {
        @Override
        public void onRefreshSettings(Settings settings) {
            long newThreshold = settings.getAsTime(AUDIT_LOG_THRESHOLD,
                    TimeValue.timeValueMillis(auditLog.threshold)).millis();
            if (newThreshold != auditLog.threshold) {
                auditLog.threshold = newThreshold;
                auditLog.logger.info("auditlog threshold updated to " + TimeValue.timeValueMillis(newThreshold));
            }
        }
    }

    public static synchronized AuditLog getAuditLog() {
        if (auditLog == null) {
            auditLog = new AuditLog();
        }
        return auditLog;
    }
}