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

package org.elasticsearch.authentication;

import java.io.IOException;

import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestStatus;

public class ResponseUtil {
    private static final ESLogger logger = Loggers.getLogger(ResponseUtil.class);

    private ResponseUtil() {
    }

    public static void send(final RestChannel channel, final RestStatus status, final String message) {
        try {
            final XContentBuilder builder = JsonXContent.contentBuilder();
            builder.startObject()
                    .field("status", status.getStatus())
                    .field("message", message).endObject();
            BytesRestResponse bytesRestResponse = new BytesRestResponse(status, builder);
            if (status == RestStatus.UNAUTHORIZED) {
                bytesRestResponse.addHeader("WWW-authenticate", "Basic realm=\"Elasticsearch Authentication\"");
            }
            channel.sendResponse(bytesRestResponse);
        } catch (final IOException e) {
            logger.error("Failed to send a response.", e);
            try {
                channel.sendResponse(new BytesRestResponse(channel, e));
            } catch (final IOException e1) {
                logger.error("Failed to send a failure response.", e1);
            }
        }
    }
}
