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

import io.crate.sql.tree.Table;

public class RevokePrivilegeAnalyzedStatement extends AbstractDDLAnalyzedStatement {

    private final String username;
    private final String privilege;
    private final String table;

    public RevokePrivilegeAnalyzedStatement(String username, String privilege, Table table) {
        this.username = username;
        this.privilege = privilege;
        this.table = table.getName().toString();
    }

    @Override
    public <C, R> R accept(AnalyzedStatementVisitor<C, R> analyzedStatementVisitor, C context) {
        return analyzedStatementVisitor.visitRevokePrivilegeAnalyzedStatement(this, context);
    }

    public String getUsername() {
        return username;
    }

    public String getPrivilege() {
        return privilege;
    }

    public String getTable() {
        return table;
    }
}