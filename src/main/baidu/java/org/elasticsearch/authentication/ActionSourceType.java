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

package org.elasticsearch.authentication;

import java.util.HashSet;

import org.elasticsearch.action.search.SearchScrollAction;

public enum ActionSourceType {
    OTHERS(0),
    SQL(1),
    INTERNAL(2),
    SELF_AUTHORIZED(3);

    private int typeId;
    
    @SuppressWarnings("serial")
    private static final HashSet<String> SELF_AUTHORIZED_ACTIONS = new HashSet<String>() {{
        add(SearchScrollAction.NAME);
    }};

    private ActionSourceType(int typeId) {
        this.typeId = typeId;
    }

    public int getValue() {
        return this.typeId;
    }

    public static ActionSourceType getActionSourceType(String actionName) {
        actionName = actionName.split("\\[")[0];
        if (actionName.startsWith("sql:")) {
            return ActionSourceType.SQL;
        } else if (actionName.startsWith("internal:")) {
            return ActionSourceType.INTERNAL;
        } else if (SELF_AUTHORIZED_ACTIONS.contains(actionName)) {
            return ActionSourceType.SELF_AUTHORIZED;
        } else {
            return ActionSourceType.OTHERS;
        }
    }
}
