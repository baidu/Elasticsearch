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

public class RevokePrivilege extends Statement {
    private final String username;
    private final String privilege;
    private final Table table;
    
    
    public RevokePrivilege(String username, String privilege, Table table) {
        this.username = username;
        this.privilege = privilege;
        this.table = table;
    }
    
    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitRevokePrivilege(this, context);
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("table", table)
                .add("username", username)
                .add("privilege", privilege)
                .toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((privilege == null) ? 0 : privilege.hashCode());
        result = prime * result + ((table == null) ? 0 : table.hashCode());
        result = prime * result
                + ((username == null) ? 0 : username.hashCode());
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
        RevokePrivilege other = (RevokePrivilege) obj;
        if (privilege == null) {
            if (other.privilege != null)
                return false;
        } else if (!privilege.equals(other.privilege))
            return false;
        if (table == null) {
            if (other.table != null)
                return false;
        } else if (!table.equals(other.table))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    public String getUsername() {
        return username;
    }

    public String getPrivilege() {
        return privilege;
    }

    public Table getTable() {
        return table;
    }


}