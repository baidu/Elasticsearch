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

import com.google.common.base.MoreObjects;

public class MigrateTable extends Statement {

    private Table tableName;
    private String destTenantName;
    
    public MigrateTable(Table tableName, String destTenantName) {
        this.tableName = tableName;
        this.destTenantName = destTenantName;
    }
    
    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitMigrateTable(this, context);
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("tableName", tableName)
                .add("destTenantName", destTenantName)
                .toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((destTenantName == null) ? 0 : destTenantName.hashCode());
        result = prime * result
                + ((tableName == null) ? 0 : tableName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MigrateTable other = (MigrateTable) obj;
        if (destTenantName == null) {
            if (other.destTenantName != null)
                return false;
        } else if (!destTenantName.equals(other.destTenantName))
            return false;
        if (tableName == null) {
            if (other.tableName != null)
                return false;
        } else if (!tableName.equals(other.tableName))
            return false;
        return true;
    }

    public Table getTableName() {
        return tableName;
    }

    public String getDestTenantName() {
        return destTenantName;
    }
}