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

package io.crate.sql.tree;

public enum TenantModificationOperation {
    ADD_NODES(0), 
    DROP_NODES(1), 
    DECOMMISSION_NODES(2);
    

    private int typeId;
    
    private TenantModificationOperation(int typeId) {
        this.typeId = typeId;
    }
    
    public int getValue() {
        return this.typeId;
    }
    
    public static TenantModificationOperation fromValue(int typeId) {
        switch (typeId) {
        case 0:
            return TenantModificationOperation.ADD_NODES;
        case 1:
            return TenantModificationOperation.DROP_NODES;
        case 2:
            return TenantModificationOperation.DECOMMISSION_NODES;
        default:
            return null;
        }
    }
}
