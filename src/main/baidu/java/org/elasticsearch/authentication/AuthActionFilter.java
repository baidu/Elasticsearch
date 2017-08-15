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

import io.crate.exceptions.NoPermissionException;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.support.ActionFilter;
import org.elasticsearch.action.support.ActionFilterChain;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.tasks.Task;

public class AuthActionFilter extends AbstractComponent implements ActionFilter {

    private final AuthService authService;

    @Inject
    public AuthActionFilter(Settings settings, AuthService authService) {
        super(settings);
        this.authService = authService;
    }

    public void apply(Task task, String action, ActionRequest request,
                      ActionListener listener, ActionFilterChain chain) {
        try {
            // authentication
            ActionSourceType sourceType = ActionSourceType.getActionSourceType(action);
            LoginUserContext userInfoContext = request.getHeader(LoginUserContext.USER_INFO_KEY);
            // authentication and authorization only happened on the proxy node(the node user connected to)
            // for sql, the authorized value will be set to true
            // for rest,
            if (sourceType == ActionSourceType.OTHERS
                    && userInfoContext != null
                    && !userInfoContext.authorized()
                    && userInfoContext.getUserProperty() != null) {
                AuthResult authResult = authService.authenticate(request, action);
                if (authResult.getStatus() != RestStatus.OK) {
                    throw new NoPermissionException(authResult.getStatus().getStatus(), authResult.getMessage());
                }
                userInfoContext.setAuthorized(true);
            }
            // filter other tenants in clusterState if necessary
            AuthService.setStateFilter(request, action);
            chain.proceed(task, action, request, listener);
        } catch (Throwable t) {
            logger.error("errors while check auth", t);
            throw t;
        }
    }

    public void apply(String action, ActionResponse response, ActionListener listener, ActionFilterChain chain)
    {
        chain.proceed(action, response, listener);
    }

    public int order()
    {
        return Integer.MIN_VALUE;
    }
}
