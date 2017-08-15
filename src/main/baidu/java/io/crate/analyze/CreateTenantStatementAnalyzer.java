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

import io.crate.exceptions.NoPermissionException;
import io.crate.sql.tree.CreateTenant;
import io.crate.sql.tree.DefaultTraversalVisitor;
import io.crate.sql.tree.Node;

import org.elasticsearch.cluster.metadata.UserProperty;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.RestStatus;

import com.google.common.collect.ImmutableMap;

public class CreateTenantStatementAnalyzer extends DefaultTraversalVisitor<CreateTenantAnalyzedStatement, Analysis> {

    public static final ImmutableMap<String, SettingsApplier> SETTINGS = ImmutableMap.<String, SettingsApplier>builder()
            .put(TenantSettings.NUMBER_OF_INSTANCES.name(), new SettingsAppliers.IntSettingsApplier(TenantSettings.NUMBER_OF_INSTANCES))
            .put(TenantSettings.SUPERUSER_PASSWORD.name(), new SettingsAppliers.StringSettingsApplier(TenantSettings.SUPERUSER_PASSWORD))
            .put(TenantSettings.INSTANCE_LIST.name(), new SettingsAppliers.StringSettingsApplier(TenantSettings.INSTANCE_LIST))
            .build();
    
    @Inject
    public CreateTenantStatementAnalyzer() {
    }

    @Override
    public CreateTenantAnalyzedStatement visitCreateTenant(CreateTenant node, Analysis context) {
        // Add SQL Authentication
        UserProperty currentOperateUser = context.parameterContext().userProperty();
        if (!currentOperateUser.getUsernameWithoutTenant().equalsIgnoreCase(UserProperty.ROOT_NAME)) {
            throw new NoPermissionException(RestStatus.FORBIDDEN.getStatus(), "only root have permission to create tenant");
        }
        Settings settings = GenericPropertiesConverter.settingsFromProperties(
                node.properties(), context.parameterContext(), SETTINGS).build();
        CreateTenantAnalyzedStatement statement = new CreateTenantAnalyzedStatement(node.name(), 
                settings.get(TenantSettings.SUPERUSER_PASSWORD.name()), 
                settings.getAsInt(TenantSettings.NUMBER_OF_INSTANCES.name(), TenantSettings.NUMBER_OF_INSTANCES.defaultValue()), 
                settings.get(TenantSettings.INSTANCE_LIST.name()));
        return statement;   
    }

    public AnalyzedStatement analyze(Node node, Analysis analysis) {
        analysis.expectsAffectedRows(true);
        return process(node, analysis);
    }
}