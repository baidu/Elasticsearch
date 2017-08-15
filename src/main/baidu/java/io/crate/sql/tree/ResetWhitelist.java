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

public class ResetWhitelist extends Statement {

    private String username;
    private String whitelist;
    
    public ResetWhitelist(String username, String whitelist) {
        this.username = username;
        this.whitelist = whitelist;
    }
    
    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitResetWhitelist(this, context);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("username", username)
                .add("whitelist", whitelist)
                .toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((username == null) ? 0 : username.hashCode());
        result = prime * result
                + ((whitelist == null) ? 0 : whitelist.hashCode());
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
        ResetWhitelist other = (ResetWhitelist) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (whitelist == null) {
            if (other.whitelist != null)
                return false;
        } else if (!whitelist.equals(other.whitelist))
            return false;
        return true;
    }

    public String getUsername() {
        return username;
    }

    public String getWhitelist() {
        return whitelist;
    }
}