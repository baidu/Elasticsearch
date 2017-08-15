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

package io.crate.analyze;

import io.crate.sql.tree.TenantModificationOperation;

import java.util.Arrays;
import java.util.List;

public class AlterTenantModifyNodesAnalyzedStatement extends AbstractDDLAnalyzedStatement {

    private final String tenantName;
    private final List<String> nodeList;
    private final TenantModificationOperation operation;
    

    public AlterTenantModifyNodesAnalyzedStatement(String tenantName, String nodes, TenantModificationOperation operation) {
        this.tenantName = tenantName;
        this.nodeList = Arrays.asList(nodes.split(","));
        if (this.nodeList.size() > 1) {
            throw new UnsupportedOperationException("only support add or remove just one node");
        }
        this.operation = operation;
    }

    @Override
    public <C, R> R accept(AnalyzedStatementVisitor<C, R> analyzedStatementVisitor, C context) {
        return analyzedStatementVisitor.visitAlterTenantModifyNodesAnalyzedStatement(this, context);
    }

    public String tenantName() {
        return tenantName;
    }

    public List<String> nodeList() {
        return nodeList;
    }
    
    public TenantModificationOperation getOperation() {
        return operation;
    }
}