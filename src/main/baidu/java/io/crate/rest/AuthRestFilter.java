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

package io.crate.rest;

import io.crate.exceptions.NoPermissionException;

import org.elasticsearch.authentication.ResponseUtil;
import org.elasticsearch.authentication.LoginUserContext;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestFilter;
import org.elasticsearch.rest.RestFilterChain;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestStatus;

public class AuthRestFilter extends RestFilter {
    private ClusterService clusterService;
    private ESLogger logger;
    
    public AuthRestFilter(ClusterService clusterService, ESLogger logger) {
        this.clusterService = clusterService;
        this.logger = logger;
    }
    
    public void process(RestRequest request, RestChannel channel, RestFilterChain filterChain) {
        LoginUserContext user = null;
        request.putInContext(RestRequest.REQUEST_START_TIME, System.currentTimeMillis());
        try {
            user = new LoginUserContext(request, clusterService);
        } catch (NoPermissionException e) {
            logger.info("errors while parsing userinfo from request, error msg is [{}]", e.getMessage());
            ResponseUtil.send(channel, RestStatus.UNAUTHORIZED, e.getMessage());
            return;
        }

        if (user.loginUsername() == null || user.loginUsername().length() ==0) {
            ResponseUtil.send(channel, RestStatus.UNAUTHORIZED, "Username is empty");
            logger.info("Username is empty");
        }
        if (!user.authenticated() && (user.password() == null || user.password().length() ==0)) {
            ResponseUtil.send(channel, RestStatus.UNAUTHORIZED, "Password is empty");
            logger.info("Password is empty");
        }
        // put user info into context
        request.putHeader(LoginUserContext.USER_INFO_KEY, user);
        filterChain.continueProcessing(request, channel);
    }
}
