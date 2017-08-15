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

package org.elasticsearch.mysql;

import java.util.HashMap;
import java.util.Map;

public class SessionVariable {
    private String name;
    private PrimitiveType type;
    private String value;

    public static Map<String, SessionVariable> sessionMap = new HashMap<>();

    static {
        sessionMap.put("auto_increment_increment",
                new SessionVariable("auto_increment_increment", "1", PrimitiveType.INT));
        sessionMap.put("character_set_client",
                new SessionVariable("character_set_client", "utf8", PrimitiveType.STRING));
        sessionMap.put("character_set_connection",
                new SessionVariable("character_set_connection", "utf8", PrimitiveType.STRING));
        sessionMap.put("character_set_results",
                new SessionVariable("character_set_results", "utf8", PrimitiveType.STRING));
        sessionMap.put("character_set_server ",
                new SessionVariable("character_set_server ", "utf8", PrimitiveType.STRING));
        sessionMap.put("init_connect",
                new SessionVariable("init_connect", "", PrimitiveType.STRING));
        sessionMap.put("interactive_timeout",
                new SessionVariable("interactive_timeout", "3600", PrimitiveType.INT));
        sessionMap.put("language",
                new SessionVariable("language", "English", PrimitiveType.STRING));
        sessionMap.put("license",
                new SessionVariable("license", "Baidu", PrimitiveType.STRING));
        sessionMap.put("lower_case_table_names",
                new SessionVariable("lower_case_table_names", "0", PrimitiveType.INT));
        sessionMap.put("max_allowed_packet",
                new SessionVariable("max_allowed_packet", "10485760", PrimitiveType.INT));
        sessionMap.put("net_buffer_length",
                new SessionVariable("net_buffer_length", "16384", PrimitiveType.INT));
        sessionMap.put("net_write_timeout",
                new SessionVariable("net_write_timeout", "60", PrimitiveType.INT));
        sessionMap.put("query_cache_size",
                new SessionVariable("query_cache_size", "10485760", PrimitiveType.INT));
        sessionMap.put("query_cache_type",
                new SessionVariable("query_cache_type", "0", PrimitiveType.INT));
        sessionMap.put("sql_mode",
                new SessionVariable("sql_mode", "", PrimitiveType.INT));
        sessionMap.put("system_time_zone",
                new SessionVariable("system_time_zone", "CST", PrimitiveType.INT));
        sessionMap.put("time_zone",
                new SessionVariable("time_zone", "CST", PrimitiveType.STRING));
        sessionMap.put("tx_isolation",
                new SessionVariable("tx_isolation", "REPEATABLE-READ", PrimitiveType.STRING));
        sessionMap.put("wait_timeout",
                new SessionVariable("wait_timeout", "28800", PrimitiveType.INT));

    }

    public SessionVariable(String name, String value, PrimitiveType type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PrimitiveType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
