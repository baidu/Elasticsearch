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

public class CreateTenantAnalyzedStatement extends AbstractDDLAnalyzedStatement {

    private final String tenantName;
    private final int instanceNum;
    private final String superuserPassword;
    private final String instanceList;
    
    public CreateTenantAnalyzedStatement(String tenantName, String superuserPassword, int instanceNum, String instanceList)
    {
        this.tenantName = tenantName;
        this.superuserPassword = superuserPassword;
        this.instanceNum = instanceNum;
        this.instanceList = instanceList;
    }


    @Override
    public <C, R> R accept(AnalyzedStatementVisitor<C, R> analyzedStatementVisitor, C context) {
        return analyzedStatementVisitor.visitCreateTenantAnalyzedStatement(this, context);
    }

    public String tenantName() {
        return tenantName;
    }

    public String superuserPassword() {
        return superuserPassword;
    }
    
    public int instanceNum() {
        return instanceNum;
    }
    
    public String instanceList()
    {
        return this.instanceList;
    }
}
