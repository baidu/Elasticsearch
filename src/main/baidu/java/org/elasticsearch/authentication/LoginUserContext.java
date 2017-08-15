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

import org.apache.commons.codec.binary.Base64;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.metadata.MetaData;
import org.elasticsearch.cluster.metadata.TenantProperty;
import org.elasticsearch.cluster.metadata.UserProperty;
import org.elasticsearch.common.SerializableHeader;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestStatus;

import com.google.common.base.Strings;

import io.crate.exceptions.NoPermissionException;

import java.io.IOException;
import java.net.InetSocketAddress;

public class LoginUserContext implements SerializableHeader {
    
    public static final String USER_INFO_KEY = "_user_info";
    public static final String TENANT_FILTER = "_tenant_filter";
    public static final String USERNAME_KEY = "username";
    public static final String PASSWORD_KEY = "password";
    public static final String AUTHENTICATED_KEY = "authenticated";
    private static final int BASIC_LENGTH = 6;

    private String loginUsername = null; // username@tenant_name
    private String fullUsername = null; // username@tenant_id
    private String password = null;
    private String sourceAddrs = null;
    private String proxyAddrs = null;
    private long tenantId = TenantProperty.INVALID_TENANT_ID;
    private boolean authenticated = false;
    private boolean authorized= false;
    // this property is not serialized to remote
    private UserProperty userProperty = null;

    private LoginUserContext() {
        this.password = null;
        this.sourceAddrs = null;
        this.proxyAddrs = null;
        this.tenantId = TenantProperty.INVALID_TENANT_ID;
        this.authenticated = false;
        this.userProperty = null;
        this.fullUsername = null;
        this.authorized = false;
    }
    
    public LoginUserContext(RestRequest request, ClusterService clusterService) {
        authenticated = false;
        // get username and password
        String auth = request.header("Authorization");
        if (request.param(USERNAME_KEY) != null) {
            loginUsername = request.param(USERNAME_KEY);
            password = request.param(PASSWORD_KEY);
            if (request.hasParam(AUTHENTICATED_KEY)) {
                authenticated = Boolean.parseBoolean(request.param(AUTHENTICATED_KEY));
            }
        } else if ((auth != null) && (auth.length() > BASIC_LENGTH)) {
            auth = auth.substring(BASIC_LENGTH);
            String decodedAuth = new String(Base64.decodeBase64(auth));
            String[] nameAndPass = decodedAuth.split(":");
            if (nameAndPass.length > 0) {
                loginUsername = nameAndPass[0];
            }
            if (nameAndPass.length > 1) {
                password = nameAndPass[1];
            }
        }
        
        if (Strings.isNullOrEmpty(loginUsername)) {
            throw new NoPermissionException(RestStatus.UNAUTHORIZED.getStatus(), "could not parse username from http header or url");
        }
        
        MetaData metaData = clusterService.state().metaData();
        if (UserProperty.getUsernameWithoutTenantFromFullUsername(loginUsername).equalsIgnoreCase(UserProperty.ROOT_NAME)) {
            userProperty = metaData.getUserMetadata().getUserProperties().get(UserProperty.ROOT_NAME);
            UserProperty.Builder userBuilder = new UserProperty.Builder(userProperty);
            userBuilder.changeUsername(UserProperty.ROOT_NAME, UserProperty.getTenantIdFromLoginUserName(loginUsername, metaData.tenantMetadata()));
            userProperty = userBuilder.build();
        } else {
            userProperty = metaData.getUserMetadata().getUserPropertyFromLoginUser(loginUsername, metaData.tenantMetadata());
        }
        if (userProperty == null) {
            throw new NoPermissionException(RestStatus.UNAUTHORIZED.getStatus(), "could not find user " + loginUsername);
        }
        tenantId = userProperty.getTenantId();
        fullUsername = userProperty.getUsernameWithTenant();
        // get sourceAddrs and proxyAddrs
        InetSocketAddress addrs = (InetSocketAddress) request.getRemoteAddress();
        String forwardAddrsList = request.header("X-Forwarded-For");
        if (forwardAddrsList != null && forwardAddrsList.length() > 0) {
            proxyAddrs = addrs.getAddress().getHostAddress();
            sourceAddrs = forwardAddrsList.split(",")[0];
        } else {
            sourceAddrs = addrs.getAddress().getHostAddress();
        }
    }

    public LoginUserContext(String loginUsername, UserProperty userProperty, boolean isAuthenticated, boolean isAuthorized) {
        this.loginUsername = loginUsername;
        this.tenantId = userProperty.getTenantId();
        this.authenticated = isAuthenticated;
        this.userProperty = userProperty;
        this.fullUsername = this.userProperty.getUsernameWithTenant();
        this.authorized = isAuthorized;
    }
    
    public String loginUsername() {
        return loginUsername;
    }
    
    public String fullUsername() {
        return fullUsername;
    }
    
    public String password() {
        return password;
    }
    
    public String sourceAddrs() {
        return sourceAddrs;
    }
    
    public String proxyAddrs() {
        return proxyAddrs;
    }
    
    public long tenantId() {
        return tenantId;
    }
    
    public boolean authenticated() {
        return authenticated;
    }
    
    public boolean authorized() {
        return authorized;
    }
    
    public void setAuthorized(boolean isAuthorized) {
        this.authorized = isAuthorized;
    }
    
    public UserProperty getUserProperty() {
        return userProperty;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        this.loginUsername = in.readNullableString();
        this.tenantId = in.readLong();
        this.password = in.readNullableString();
        this.sourceAddrs = in.readNullableString();
        this.proxyAddrs = in.readNullableString();
        this.authenticated = in.readBoolean();
        this.authorized = in.readBoolean();
        this.userProperty = null;
        this.fullUsername = UserProperty.generateUsernameWithTenant(UserProperty.getUsernameWithoutTenantFromFullUsername(loginUsername), tenantId);
    }
    
    public static LoginUserContext readContext(StreamInput in) throws IOException {
        LoginUserContext userContext = new LoginUserContext();
        userContext.readFrom(in);
        return userContext;
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeNullableString(loginUsername);
        out.writeLong(tenantId);
        out.writeNullableString(password);
        out.writeNullableString(sourceAddrs);
        out.writeNullableString(proxyAddrs);
        out.writeBoolean(authenticated);
        out.writeBoolean(authorized);
    }
}
