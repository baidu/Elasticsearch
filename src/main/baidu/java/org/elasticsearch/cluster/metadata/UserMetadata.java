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

package org.elasticsearch.cluster.metadata;

import io.crate.metadata.information.InformationSchemaInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.cluster.AbstractDiffable;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.mysql.MysqlPassword;

import com.google.common.collect.ImmutableMap;

public class UserMetadata extends AbstractDiffable<UserMetadata> {

    public static UserMetadata PROTO = builder().build(); 
    private ImmutableMap<String, UserProperty> userProperties;
    
    private UserMetadata(ImmutableMap<String, UserProperty> userProperties) {
        this.userProperties = userProperties;
    }
    
    public List<UserProperty> getUserListForTenant(long tenantId) {
        List<UserProperty> userPropertyList =  new ArrayList<>();
        for(String fullUsername : userProperties.keySet()) {
            long userTenantId = UserProperty.getTenantIdFromFullUserName(fullUsername);
            if (userTenantId == tenantId) {
                userPropertyList.add(userProperties.get(fullUsername));
            }
        }
        return userPropertyList;
    }
    
    public UserProperty getUserPropertyFromLoginUser(String loginUsername, TenantMetadata tenantMetadata) {
        long tenantId = UserProperty.getTenantIdFromLoginUserName(loginUsername, tenantMetadata);
        String username = UserProperty.getUsernameWithoutTenantFromFullUsername(loginUsername);
        String fullUsername = username;
        if (UserProperty.ROOT_NAME.equalsIgnoreCase(username)) {
            fullUsername = UserProperty.ROOT_NAME;
        } else {
            fullUsername = UserProperty.generateUsernameWithTenant(username, tenantId);
        }
        return userProperties.get(fullUsername);
    }
    
    @Override
    public UserMetadata readFrom(StreamInput in) throws IOException {
        Builder builder = new Builder();
        int userNum = in.readInt();
        for (int i = 0; i < userNum; ++i) {
            String username = in.readString();
            UserProperty userProperty = UserProperty.PROTO.readFrom(in);
            builder.addOrChangeUserProperty(username, userProperty);
        }
        return builder.build();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeInt(userProperties.size());
        for(String username : userProperties.keySet()) {
            out.writeString(username);
            userProperties.get(username).writeTo(out);
        }
    }
    
    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(UserMetadata userMetadata) {
        return new Builder(userMetadata);
    }
    
    public static class Builder {

        private Map<String, UserProperty> userProperties;
        
        public Builder() {
            userProperties = new HashMap<String, UserProperty>();
            String rootEncryptPass =
                    new String(MysqlPassword.makeScrambledPassword(UserProperty.DEFAULT_ROOT_PASSWD));
            UserProperty rootUserProperty =
                    new UserProperty.Builder(UserProperty.ROOT_NAME, rootEncryptPass).build();
            userProperties.put(rootUserProperty.getUsernameWithTenant(), rootUserProperty);
            String superuserEncryptPass =
                    new String(MysqlPassword.makeScrambledPassword(UserProperty.DEFAULT_SUPERUSER_PASSWD));
            UserProperty.Builder superUserPropertyBuilder =
                    new UserProperty.Builder(UserProperty.SUPER_USER_NAME, superuserEncryptPass);
            superUserPropertyBuilder.addIpToWhiteList("*");
            superUserPropertyBuilder.grantDBPrivilege(InformationSchemaInfo.NAME, PrivilegeType.READ_ONLY);
            UserProperty superUserProperty = superUserPropertyBuilder.build();
            userProperties.put(superUserProperty.getUsernameWithTenant(), superUserProperty);
        }
        
        public Builder(UserMetadata userMetadata) {
            userProperties = new HashMap<String, UserProperty>();
            for (String username : userMetadata.userProperties.keySet()) {
                userProperties.put(username, userMetadata.userProperties.get(username));
            }
        }
        
        public Builder addOrChangeUserProperty(String username, UserProperty userProperty) {
            userProperties.put(username, userProperty);
            return this;
        }

        public boolean containsUser(String username) {
            return userProperties.containsKey(username);
        }
        
        public Builder deleteUser(String username) {
            userProperties.remove(username);
            return this;
        }
        
        public UserMetadata build() {
            ImmutableMap<String, UserProperty> newProperties = ImmutableMap.copyOf(userProperties);
            return new UserMetadata(newProperties);
        }
        
        public static void toXContent(UserMetadata userMetadata, XContentBuilder builder, ToXContent.Params params) throws IOException {
            
            builder.startObject("users");
            for (String userName : userMetadata.getUserProperties().keySet()) {
                UserProperty.Builder.toXContent(userMetadata.getUserProperties().get(userName), builder, params);
            }
            builder.endObject();
        }
        
        public static UserMetadata fromXContent(XContentParser parser) throws IOException {
            Builder builder = new Builder();
            XContentParser.Token token = parser.currentToken();
            String currentFieldName = parser.currentName();
            if (currentFieldName == "users") {
                while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
                    if (token == XContentParser.Token.FIELD_NAME) {
                        currentFieldName = parser.currentName();
                    } else if (token == XContentParser.Token.START_OBJECT) {
                        UserProperty userProperty = UserProperty.Builder.fromXContent(parser);
                        builder.addOrChangeUserProperty(userProperty.getUsernameWithTenant(), userProperty);
                    }
                }
            }
            return builder.build();
        }
    }
    public ImmutableMap<String, UserProperty> getUserProperties() {
        return userProperties;
    }
    
    public UserProperty getUserProperty(String username, long tenantId) {
        if (tenantId == TenantProperty.ROOT_TENANT_ID) {
            return userProperties.get(username);
        } else {
            return userProperties.get(username + UserProperty.USER_TENANT_SEPERATOR + tenantId);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((userProperties == null) ? 0 : userProperties.hashCode());
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
        UserMetadata other = (UserMetadata) obj;
        if (userProperties == null) {
            if (other.userProperties != null)
                return false;
        } else if (!userProperties.equals(other.userProperties))
            return false;
        return true;
    }
}
