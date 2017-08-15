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

package org.elasticsearch.cluster.metadata;

public enum PrivilegeType {

    READ_ONLY(0),
    READ_WRITE(1);
    
    private int typeId;
    
    private PrivilegeType(int typeId) {
        this.typeId = typeId;
    }
    
    public int getValue() {
        return this.typeId;
    }
    
    public static PrivilegeType fromValue(int typeId) {
        switch (typeId) {
        case 0:
            return PrivilegeType.READ_ONLY;
        case 1:
            return PrivilegeType.READ_WRITE;
        default:
            return null;
        }
    }
}
