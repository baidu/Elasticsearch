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

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public enum PrimitiveType {
    BOOL("BOOLEAN"),
    BYTE("BYTE"),
    DOUBLE("DOUBLE"),
    FLOAT("FLOAT"),
    INT("INT"),
    LONG("LONG"),
    SHORT("SHORT"),
    STRING("STRING"),
    TIMESTAMP("TIMESTAMP");


	final String description;

	private PrimitiveType(String description) {
		this.description = description;
	}


	// TODO(zhaochun): Add Mysql Type to it's private field
    public MysqlColType toMysqlType() {
        switch (this) {
            // MySQL use Tinyint(1) to represent boolean
            case BOOL:
                return MysqlColType.MYSQL_TYPE_TINY;
            case BYTE:
                return MysqlColType.MYSQL_TYPE_TINY;
            case DOUBLE:
                return MysqlColType.MYSQL_TYPE_DOUBLE;
            case FLOAT:
                return MysqlColType.MYSQL_TYPE_FLOAT;
            case INT:
                return MysqlColType.MYSQL_TYPE_INT24;
            case LONG:
                return MysqlColType.MYSQL_TYPE_LONG;
            case SHORT:
                return MysqlColType.MYSQL_TYPE_SHORT;
            case STRING:
                return MysqlColType.MYSQL_TYPE_STRING;
            case TIMESTAMP:
                return MysqlColType.MYSQL_TYPE_TIMESTAMP;
            default:
                throw new RuntimeException("Unsupported data type!");
        }
    }
}
