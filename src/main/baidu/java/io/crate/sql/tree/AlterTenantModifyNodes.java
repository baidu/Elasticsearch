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

public class AlterTenantModifyNodes extends Statement {

    private final String tenantName;
    private final String nodes;
    private final TenantModificationOperation operation;

    public AlterTenantModifyNodes(String tenantName,
                        String nodes, TenantModificationOperation operation)
    {
        this.tenantName = tenantName;
        this.nodes = nodes;
        this.operation = operation;
    }

    public String name() {
        return tenantName;
    }
    
    public String nodes() {
        return nodes;
    }
    
    public TenantModificationOperation getOperationType() {
        return operation;
    }
    
    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitAlterTenantModifyNodes(this, context);
    }

    @Override
    public String toString() {
        return "AlterTenantModifyNodes [tenantName=" + tenantName + ", nodes="
                + nodes + ", operation=" + operation + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
        result = prime * result
                + ((operation == null) ? 0 : operation.hashCode());
        result = prime * result
                + ((tenantName == null) ? 0 : tenantName.hashCode());
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
        AlterTenantModifyNodes other = (AlterTenantModifyNodes) obj;
        if (nodes == null) {
            if (other.nodes != null)
                return false;
        } else if (!nodes.equals(other.nodes))
            return false;
        if (operation != other.operation)
            return false;
        if (tenantName == null) {
            if (other.tenantName != null)
                return false;
        } else if (!tenantName.equals(other.tenantName))
            return false;
        return true;
    }
}