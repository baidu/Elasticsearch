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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;

public class CreateTenant extends Statement {

    private final String tenantName;
    private final Optional<GenericProperties> properties;

    public CreateTenant(String name,
                        @Nullable GenericProperties genericProperties)
    {
        this.tenantName = name;
        this.properties = Optional.fromNullable(genericProperties);
    }

    public String name() {
        return tenantName;
    }

    public Optional<GenericProperties> properties() {
        return properties;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context)
    {
        return visitor.visitCreateTenant(this, context);
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
                .add("name", tenantName)
                .add("properties", properties).toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((properties == null) ? 0 : properties.hashCode());
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
        CreateTenant other = (CreateTenant) obj;
        if (properties == null) {
            if (other.properties != null)
                return false;
        } else if (!properties.equals(other.properties))
            return false;
        if (tenantName == null) {
            if (other.tenantName != null)
                return false;
        } else if (!tenantName.equals(other.tenantName))
            return false;
        return true;
    }
}
