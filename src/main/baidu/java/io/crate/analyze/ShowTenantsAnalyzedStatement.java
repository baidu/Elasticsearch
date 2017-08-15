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

import java.util.ArrayList;
import java.util.List;

import io.crate.analyze.relations.AnalyzedRelationVisitor;
import io.crate.analyze.symbol.Field;
import io.crate.metadata.OutputName;
import io.crate.types.DataTypes;

public class ShowTenantsAnalyzedStatement extends AbstractShowAnalyzedStatement {

    private final List<Field> fields;

    public ShowTenantsAnalyzedStatement() {
        this.fields = new ArrayList<Field>();
        this.fields.add(new Field(this, new OutputName("cluster_id"), DataTypes.STRING));
        this.fields.add(new Field(this, new OutputName("cluster_name"), DataTypes.STRING));
        this.fields.add(new Field(this, new OutputName("node_list"), DataTypes.STRING));
        this.fields.add(new Field(this, new OutputName("desire_node_num"), DataTypes.STRING));
        this.fields.add(new Field(this, new OutputName("allocated_node_num"), DataTypes.STRING));
    }

    @Override
    public <C, R> R accept(AnalyzedStatementVisitor<C, R> analyzedStatementVisitor, C context) {
        return analyzedStatementVisitor.visitShowTenantsAnalyzedStatement(this, context);
    }

    @Override
    public <C, R> R accept(AnalyzedRelationVisitor<C, R> visitor, C context) {
        return visitor.process(this, context);
    }

    @Override
    public List<Field> fields() {
        return fields;
    }

    @Override
    public boolean isWriteOperation() {
        return false;
    }
}