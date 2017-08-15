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

public enum AllocatedNodeStatus {

    DECOMMISSIONING(0),
    NORMAL(1), 
    DEAD(2), 
    NOT_DATA_NODE(3);
    
    private int typeId;
    
    private AllocatedNodeStatus(int typeId) {
        this.typeId = typeId;
    }
    
    public int getValue() {
        return this.typeId;
    }
    
    public static AllocatedNodeStatus fromValue(int typeId) {
        switch (typeId) {
        case 0:
            return AllocatedNodeStatus.DECOMMISSIONING;
        case 1:
            return AllocatedNodeStatus.NORMAL;
        case 2:
            return AllocatedNodeStatus.DEAD;
        case 3:
            return AllocatedNodeStatus.NOT_DATA_NODE;
        default:
            return null;
        }
    }
}
