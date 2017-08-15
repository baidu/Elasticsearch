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

public enum VirtualTableNames {
    sys, monitor, cluster, nodes, jobs, users, snapshots, repositories, tenants;

    public static String[] getVirtualTableName(String actionName) {
        if (actionName.startsWith("cluster:monitor/nodes")) {
            return new String[]{VirtualTableNames.sys.name(), VirtualTableNames.nodes.name()};
        } else if (actionName.startsWith("cluster:monitor/task")
                || actionName.startsWith("cluster:admin/tasks")
                || actionName.equals("cluster:admin/reroute")) {
            return new String[]{VirtualTableNames.sys.name(), VirtualTableNames.jobs.name()};
        } else if (actionName.startsWith("cluster:admin/snapshot")) {
            return new String[]{VirtualTableNames.sys.name(), VirtualTableNames.snapshots.name()};
        } else if (actionName.startsWith("cluster:admin/repository")) {
            return new String[]{VirtualTableNames.sys.name(), VirtualTableNames.repositories.name()};
        } else if (actionName.startsWith("cluster:admin/user")) {
            return new String[]{VirtualTableNames.sys.name(), VirtualTableNames.users.name()};
        } else if (actionName.startsWith("cluster:admin/tenant")) {
            return new String[]{VirtualTableNames.sys.name(), VirtualTableNames.tenants.name()};
        } else if (actionName.startsWith("cluster:monitor/")) {
            return new String[]{VirtualTableNames.sys.name(), VirtualTableNames.monitor.name()};
        } else if (actionName.startsWith("cluster:admin/")) {
            return new String[]{VirtualTableNames.sys.name(), VirtualTableNames.cluster.name()};
        } else {
            return null;
        }
    }
}
