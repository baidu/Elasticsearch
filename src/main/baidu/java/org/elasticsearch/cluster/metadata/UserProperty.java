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

import io.crate.metadata.Schemas;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.elasticsearch.common.collect.Tuple;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.io.stream.Writeable;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentParser;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class UserProperty implements Writeable<UserProperty> {
    public static final String USER_TENANT_SEPERATOR = "@";
    public static final String DB_TABLE_SEPERATOR = ".";
    public static final String ROOT_NAME = "root";
    public static final String SUPER_USER_NAME = "superuser";
    public static final String DEFAULT_ROOT_PASSWD = "root";
    public static final String DEFAULT_SUPERUSER_PASSWD = "superuser";
    public static UserProperty PROTO = new Builder("", "").build();
    
    private long tenantId;
    private String usernameWithTenant;
    private String usernameWithoutTenant;
    private String password;
    private ImmutableMap<String, ImmutableSet<PrivilegeType>> dbPrivileges;
    private ImmutableMap<String, ImmutableSet<PrivilegeType>> tablePrivileges;
    private ImmutableSet<String> ipWhiteList;
    private ImmutableSet<String> hostnameWhiteList;
    private ImmutableSet<String> hostIpWhiteList;
    
    private UserProperty(String usernameWithTenant, String password,
            ImmutableMap<String, ImmutableSet<PrivilegeType>> dbPrivileges,
            ImmutableMap<String, ImmutableSet<PrivilegeType>> tablePrivileges,
            ImmutableSet<String> ipWhiteList, ImmutableSet<String> hostnameWhiteList,
            ImmutableSet<String> hostIpWhiteList) {
        super();
        this.usernameWithTenant = usernameWithTenant;
        if (usernameWithTenant.contains(USER_TENANT_SEPERATOR)) {
            String[] userTenant = usernameWithTenant.split(USER_TENANT_SEPERATOR);
            this.usernameWithoutTenant = userTenant[0];
            this.tenantId = Long.parseLong(userTenant[1]);
        } else {
            this.usernameWithoutTenant = usernameWithTenant;
            this.tenantId = TenantProperty.ROOT_TENANT_ID;
        }
        
        this.password = password;
        this.dbPrivileges = dbPrivileges;
        this.tablePrivileges = tablePrivileges;
        this.ipWhiteList = ipWhiteList;
        this.hostnameWhiteList = hostnameWhiteList;
        this.hostIpWhiteList = hostIpWhiteList;
    }
    
    public static boolean haveTenantInfo(String originalUserName) {
        return originalUserName.contains(USER_TENANT_SEPERATOR);
    }
    
    public static String generateUsernameWithTenant(String originalUserName, long tenantId) {
        if (originalUserName.contains(USER_TENANT_SEPERATOR)) {
            return originalUserName;
        }
        if (tenantId == TenantProperty.ROOT_TENANT_ID) {
            return originalUserName;
        }
        return originalUserName + USER_TENANT_SEPERATOR + tenantId;
    }
    
    public static String generateUsernameWithTenant(String originalUserName, UserProperty operatingUser) {
        return generateUsernameWithTenant(originalUserName, operatingUser.tenantId);
    }
    
    public static long getTenantIdFromFullUserName(String fullUsername) {
        if (!fullUsername.contains(USER_TENANT_SEPERATOR)) {
            return TenantProperty.ROOT_TENANT_ID;
        }
        return Long.parseLong(fullUsername.split(USER_TENANT_SEPERATOR)[1]);
    }
    
    public static long getTenantIdFromLoginUserName(String loginUsername, TenantMetadata tenantMetadata) {
        if (!loginUsername.contains(USER_TENANT_SEPERATOR)) {
            return TenantProperty.ROOT_TENANT_ID;
        }
        return tenantMetadata.tenantId(loginUsername.split(USER_TENANT_SEPERATOR)[1]);
    }    
    public static boolean isDBPrivilege(String privilege) {
        return privilege.contains(DB_TABLE_SEPERATOR);
    }
    
    public static String getUsernameWithoutTenantFromFullUsername(String fullUsername) {
        if (!fullUsername.contains(USER_TENANT_SEPERATOR)) {
            return fullUsername;
        }
        return fullUsername.split(USER_TENANT_SEPERATOR)[0];
    }
    
    public static boolean isRootLogin(String username) {
        if (UserProperty.ROOT_NAME.equalsIgnoreCase(UserProperty.getUsernameWithoutTenantFromFullUsername(username))) {
            return true;
        }
        return false;
    }
    
    public static Tuple<String, String> getDBAndTableName(String fullTableName) {
        String[] splitNames = fullTableName.split("\\.");
        String dbName = "";
        String tableName = "";
        if (splitNames.length == 1) {
            dbName = Schemas.DEFAULT_SCHEMA_NAME;
            tableName = splitNames[0];
        } else if (splitNames.length > 1) {
            dbName = splitNames[0];
            tableName = splitNames[1];
        }
        return new Tuple<String, String>(dbName, tableName);
    }
    
    @Override
    public UserProperty readFrom(StreamInput in) throws IOException {
        String username = in.readString();
        String password = in.readString();
        Map<String, HashSet<PrivilegeType>> dbPrivileges = new HashMap<String, HashSet<PrivilegeType>>();
        Map<String, HashSet<PrivilegeType>> tablePrivileges = new HashMap<String, HashSet<PrivilegeType>>();
        Set<String> ipWhiteList = new HashSet<String>();
        Set<String> hostnameWhiteList = new HashSet<String>();
        Set<String> hostIpWhiteList = new HashSet<String>();
        
        int dbPrivilegeSize = in.readInt();
        for (int dbNo = 0; dbNo < dbPrivilegeSize; ++dbNo) {
            String dbName = in.readString();
            HashSet<PrivilegeType> privilegeList = new HashSet<PrivilegeType>();
            int privilegeNum = in.readInt();
            for (int i = 0; i < privilegeNum; ++i) {
                PrivilegeType privilegeType = PrivilegeType.fromValue(in.readInt());
                privilegeList.add(privilegeType);
            }
            dbPrivileges.put(dbName, privilegeList);
        }
        
        int tablePrivilegeSize = in.readInt();
        for (int tableNo = 0; tableNo < tablePrivilegeSize; ++tableNo) {
            String tableName = in.readString();
            HashSet<PrivilegeType> privilegeList = new HashSet<PrivilegeType>();
            int privilegeNum = in.readInt();
            for (int i = 0; i < privilegeNum; ++i) {
                PrivilegeType privilegeType = PrivilegeType.fromValue(in.readInt());
                privilegeList.add(privilegeType);
            }
            tablePrivileges.put(tableName, privilegeList);
        }
        
        int ipWhilteListSize = in.readInt();
        for (int i = 0; i < ipWhilteListSize; ++i) {
            ipWhiteList.add(in.readString());
        }
        
        int hostnameWhilteListSize = in.readInt();
        for (int i = 0; i < hostnameWhilteListSize; ++i) {
            hostnameWhiteList.add(in.readString());
        }

        int hostIpWhilteListSize = in.readInt();
        for (int i = 0; i < hostIpWhilteListSize; ++i) {
            hostIpWhiteList.add(in.readString());
        }

        Builder builder = new Builder(username, password, dbPrivileges, tablePrivileges, ipWhiteList,
                hostnameWhiteList, hostIpWhiteList);
        return builder.build();
    }
    
    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeString(usernameWithTenant);
        out.writeString(password);
        out.writeInt(dbPrivileges.size());
        for (String dbName : dbPrivileges.keySet()) {
            out.writeString(dbName);
            ImmutableSet<PrivilegeType> dbPrivilegeList = dbPrivileges.get(dbName);
            out.writeInt(dbPrivilegeList.size());
            for(PrivilegeType privilege : dbPrivilegeList) {
                out.writeInt(privilege.getValue());
            }
        }
        out.writeInt(tablePrivileges.size());
        for (String tableName : tablePrivileges.keySet()) {
            out.writeString(tableName);
            ImmutableSet<PrivilegeType> tablePrivilegeList = tablePrivileges.get(tableName);
            out.writeInt(tablePrivilegeList.size());
            for(PrivilegeType privilege : tablePrivilegeList) {
                out.writeInt(privilege.getValue());
            }
        }
        out.writeInt(ipWhiteList.size());
        for (String ip : ipWhiteList) {
            out.writeString(ip);
        }
        out.writeInt(hostnameWhiteList.size());
        for (String hostname : hostnameWhiteList) {
            out.writeString(hostname);
        }
        out.writeInt(hostIpWhiteList.size());
        for (String hostIp : hostIpWhiteList) {
            out.writeString(hostIp);
        }
    }
    
    public static Builder builder(String username, String password) {
        return new Builder(username, password);
    }
    
    public static Builder builder(UserProperty userProperty) {
        return new Builder(userProperty);
    }
    
    public String getUsernameWithTenant() {
        return usernameWithTenant;
    }
    
    public String getUsernameWithoutTenant() {
        return usernameWithoutTenant;
    }
    
    public String getPassword() {
        return password;
    }
    
    public long getTenantId() {
        return this.tenantId;
    }
    
    public Set<String> getIpWhiteList() {
        return ipWhiteList;
    }
    
    public Set<String> getHostnameWhiteList() {
        return hostnameWhiteList;
    }

    public Set<String> getHostIpWhiteList() {
        return hostIpWhiteList;
    }

    public ImmutableMap<String, ImmutableSet<PrivilegeType>> getDbPrivileges() {
        return dbPrivileges;
    }

    public ImmutableMap<String, ImmutableSet<PrivilegeType>> getTablePrivileges() {
        return tablePrivileges;
    }
    
    public static class Builder {

        private String usernameWithTenant;
        private String password;
        private Map<String, HashSet<PrivilegeType>> dbPrivileges;
        private Map<String, HashSet<PrivilegeType>> tablePrivileges;
        private Set<String> ipWhiteList;
        private Set<String> hostnameWhiteList;

        private Set<String> hostIpWhiteList;
        
        public Builder() {
            usernameWithTenant = "";
            password = "";
            dbPrivileges = new HashMap<String, HashSet<PrivilegeType>>();
            tablePrivileges = new HashMap<String, HashSet<PrivilegeType>>();
            ipWhiteList = new HashSet<String>();
            hostnameWhiteList = new HashSet<String>();
            hostIpWhiteList = new HashSet<String>();
        }
        
        public Builder(UserProperty userProperty) {
            this.init(userProperty.usernameWithTenant, userProperty.password);
            for (String dbName : userProperty.dbPrivileges.keySet()) {
                HashSet<PrivilegeType> oneDBPrivilege = new HashSet<PrivilegeType>();
                for(PrivilegeType privilegeType : userProperty.dbPrivileges.get(dbName)) {
                    oneDBPrivilege.add(privilegeType);
                }
                dbPrivileges.put(dbName, oneDBPrivilege);
            }
            for (String tableName : userProperty.tablePrivileges.keySet()) {
                HashSet<PrivilegeType> oneTablePrivilege = new HashSet<PrivilegeType>();
                for(PrivilegeType privilegeType : userProperty.tablePrivileges.get(tableName)) {
                    oneTablePrivilege.add(privilegeType);
                }
                tablePrivileges.put(tableName, oneTablePrivilege);
            }
            for(String ipAddress : userProperty.ipWhiteList) {
                this.ipWhiteList.add(ipAddress);
            }
            for(String hostAddress : userProperty.hostnameWhiteList) {
                this.hostnameWhiteList.add(hostAddress);
            }
            for(String hostIp : userProperty.hostIpWhiteList) {
                this.hostIpWhiteList.add(hostIp);
            }
        }
        
        public Builder(String usernameWithTenant, String password, Map<String, HashSet<PrivilegeType>> dbPrivileges, 
                Map<String, HashSet<PrivilegeType>> tablePrivileges, Set<String> ipWhiteList,
                Set<String> hostnameWhiteList, Set<String> hostIpWhiteList) {
            this.usernameWithTenant = usernameWithTenant;
            this.password = password;
            this.dbPrivileges = dbPrivileges;
            this.tablePrivileges = tablePrivileges;
            this.ipWhiteList = ipWhiteList;
            this.hostnameWhiteList = hostnameWhiteList;
            this.hostIpWhiteList = hostIpWhiteList;
        }
        
        public Builder(String username, String password) {
            this.init(username, password);
        }
        
        public UserProperty build() {
            Map<String, ImmutableSet<PrivilegeType>> tmpDBPrivileges = new HashMap<String, ImmutableSet<PrivilegeType>>();
            for (String dbName : dbPrivileges.keySet()) {
                if (dbPrivileges.get(dbName).size() > 0) {
                    tmpDBPrivileges.put(dbName, ImmutableSet.copyOf(dbPrivileges.get(dbName)));
                }
            }
            
            Map<String, ImmutableSet<PrivilegeType>> tmpTablePrivileges = new HashMap<String, ImmutableSet<PrivilegeType>>();
            for (String tableName : tablePrivileges.keySet()) {
                if (tablePrivileges.get(tableName).size() > 0) {
                    tmpTablePrivileges.put(tableName, ImmutableSet.copyOf(tablePrivileges.get(tableName)));
                }
            }
            
            return new UserProperty(usernameWithTenant, password, 
                    ImmutableMap.copyOf(tmpDBPrivileges), ImmutableMap.copyOf(tmpTablePrivileges), 
                    ImmutableSet.copyOf(ipWhiteList), ImmutableSet.copyOf(hostnameWhiteList),
                    ImmutableSet.copyOf(hostIpWhiteList));
        }
        
        private void init(String usernameWithTenant, String password) {
            this.usernameWithTenant = usernameWithTenant;
            this.password = password;
            dbPrivileges = new HashMap<String, HashSet<PrivilegeType>>();
            tablePrivileges = new HashMap<String, HashSet<PrivilegeType>>();
            ipWhiteList = new HashSet<String>();
            hostnameWhiteList = new HashSet<String>();
            hostIpWhiteList = new HashSet<String>();
        }
        
        public Builder changeUsername(String username, long tenantId) {
            String usernameWithoutTenant = UserProperty.getUsernameWithoutTenantFromFullUsername(username);
            this.usernameWithTenant = UserProperty.generateUsernameWithTenant(usernameWithoutTenant, tenantId);
            return this;
        }
        
        public static void toXContent(UserProperty userProperty, XContentBuilder builder, ToXContent.Params params) throws IOException {
            builder.startObject(userProperty.usernameWithTenant, XContentBuilder.FieldCaseConversion.NONE);
            builder.field("password", userProperty.password);
            {
                builder.startObject("db_privileges");
                for (String dbName : userProperty.dbPrivileges.keySet()) {
                    builder.array(dbName, userProperty.dbPrivileges.get(dbName).toArray());
                }
                builder.endObject();

                builder.startObject("table_privileges");
                for (String tableName : userProperty.tablePrivileges.keySet()) {
                    builder.array(tableName, userProperty.tablePrivileges.get(tableName).toArray());
                }
                builder.endObject();
            }
            builder.array("ip_whitelist", userProperty.ipWhiteList.toArray());
            builder.array("hostname_whitelist", userProperty.hostnameWhiteList.toArray());
            builder.array("hostip_whitelist", userProperty.hostIpWhiteList.toArray());
            builder.endObject();
        }
        
        public static UserProperty fromXContent(XContentParser parser) throws IOException {
            Builder builder = new Builder();
            XContentParser.Token token = parser.currentToken();
            builder.usernameWithTenant = parser.currentName();
            String currentFieldName = "";
            while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
                if (token == XContentParser.Token.FIELD_NAME) {
                    currentFieldName = parser.currentName();
                } else if (token == XContentParser.Token.VALUE_STRING) {
                    if ("password".equals(currentFieldName)) {
                        builder.password = parser.text();
                    }
                } else if (token == XContentParser.Token.START_OBJECT) {
                    if ("db_privileges".equals(currentFieldName)) {
                        while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
                            if (token == XContentParser.Token.FIELD_NAME) {
                                currentFieldName = parser.currentName();
                            } else if (token == XContentParser.Token.VALUE_STRING){
                                builder.grantDBPrivilege(currentFieldName, PrivilegeType.valueOf(parser.text()));
                            }
                        }
                    } else if ("table_privileges".equals(currentFieldName)) {
                        while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
                            if (token == XContentParser.Token.FIELD_NAME) {
                                currentFieldName = parser.currentName();
                            } else if (token == XContentParser.Token.VALUE_STRING){
                                builder.grantTablePrivilege(currentFieldName, PrivilegeType.valueOf(parser.text()));
                            }
                        }
                    }
                } else if (token == XContentParser.Token.START_ARRAY) {
                    if ("ip_whitelist".equals(currentFieldName)) {
                        while (parser.nextToken() != XContentParser.Token.END_ARRAY) {
                            builder.addIpToWhiteList(parser.text());
                        }
                    } else if ("hostname_whitelist".equals(currentFieldName)) {
                        while (parser.nextToken() != XContentParser.Token.END_ARRAY) {
                            builder.addHostnameToWhiteList(parser.text());
                        }
                    } else if ("hostip_whitelist".equals(currentFieldName)) {
                        while (parser.nextToken() != XContentParser.Token.END_ARRAY) {
                            builder.addHostIpToWhiteList(parser.text());
                        }
                    }
                }
            }
            return builder.build();
        }
        public Builder resetPassword(String newPassword) {
            this.password = newPassword;
            return this;
        }
        
        public Builder grantDBPrivilege(String dbName, PrivilegeType privilegeType) {
            if (!this.dbPrivileges.containsKey(dbName)) {
                this.dbPrivileges.put(dbName, new HashSet<PrivilegeType>());
            }
            this.dbPrivileges.get(dbName).add(privilegeType);
            return this;
        }
        
        public Builder revokeDBPrivilege(String dbName, PrivilegeType privilegeType) {
            if (!this.dbPrivileges.containsKey(dbName)) {
                return this;
            }
            this.dbPrivileges.get(dbName).remove(privilegeType);
            return this;
        }
        
        public Builder grantTablePrivilege(String tableName, PrivilegeType privilegeType) {
            if (!this.tablePrivileges.containsKey(tableName)) {
                this.tablePrivileges.put(tableName, new HashSet<PrivilegeType>());
            }
            this.tablePrivileges.get(tableName).add(privilegeType);
            return this;
        }
        
        public Builder revokeTablePrivilege(String tableName, PrivilegeType privilegeType) {
            if (!this.tablePrivileges.containsKey(tableName)) {
                return this;
            }
            this.tablePrivileges.get(tableName).remove(privilegeType);
            return this;
        }
        
        public Builder addIpToWhiteList(String ipAddress) {
            this.ipWhiteList.add(ipAddress);
            return this;
        }
        
        public Builder deleteIpFromWhiteList(String ipAddress) {
            this.ipWhiteList.remove(ipAddress);
            return this;
        }
        
        public Builder addHostnameToWhiteList(String hostAddress) {
            this.hostnameWhiteList.add(hostAddress);
            return this;
        }
        
        public Builder deleteHostnameFromWhiteList(String hostAddress) {
            this.hostnameWhiteList.remove(hostAddress);
            return this;
        }

        public Builder addHostIpToWhiteList(String hostIp) {
            this.hostIpWhiteList.add(hostIp);
            return this;
        }

        public Builder addHostIpListToWhiteList(Set<String> hostIpWhiteList) {
            this.hostIpWhiteList.addAll(hostIpWhiteList);
            return this;
        }

        public Builder cleanHostnameWhiteList() {
            this.hostnameWhiteList.clear();
            return this;
        }

        public Builder cleanHostIpWhiteList() {
            this.hostIpWhiteList.clear();
            return this;
        }
        
        public Builder cleanIpWhiteList() {
            this.ipWhiteList.clear();
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProperty that = (UserProperty) o;

        if (usernameWithTenant != null ? !usernameWithTenant.equals(that.usernameWithTenant) : that.usernameWithTenant != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (dbPrivileges != null ? !dbPrivileges.equals(that.dbPrivileges) : that.dbPrivileges != null) return false;
        if (tablePrivileges != null ? !tablePrivileges.equals(that.tablePrivileges) : that.tablePrivileges != null)
            return false;
        if (ipWhiteList != null ? !ipWhiteList.equals(that.ipWhiteList) : that.ipWhiteList != null) return false;
        if (hostnameWhiteList != null ? !hostnameWhiteList.equals(that.hostnameWhiteList) : that.hostnameWhiteList != null)
            return false;
        return !(hostIpWhiteList != null ? !hostIpWhiteList.equals(that.hostIpWhiteList) : that.hostIpWhiteList != null);

    }

    @Override
    public int hashCode() {
        int result = usernameWithTenant != null ? usernameWithTenant.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (dbPrivileges != null ? dbPrivileges.hashCode() : 0);
        result = 31 * result + (tablePrivileges != null ? tablePrivileges.hashCode() : 0);
        result = 31 * result + (ipWhiteList != null ? ipWhiteList.hashCode() : 0);
        result = 31 * result + (hostnameWhiteList != null ? hostnameWhiteList.hashCode() : 0);
        result = 31 * result + (hostIpWhiteList != null ? hostIpWhiteList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\ndbPrivileges: " + getStringFromMap(dbPrivileges)
                + "\ntablePrivileges:" + getStringFromMap(tablePrivileges)
                + "\nipWhiteList: " + getStringFromArray(ipWhiteList)
                + "\nhostnameWhiteList: " + getStringFromArray(hostnameWhiteList)
                + "\nhostIpWhiteList: " + getStringFromArray(hostIpWhiteList)
                + "\npasssword:" + password;
    }

    public static String getStringFromMap(ImmutableMap<String, ImmutableSet<PrivilegeType>> mapSet) {
        String result = "";
        if (mapSet == null || mapSet.size() == 0) {
            return "";
        }
        boolean isFirst = false;
        for(Entry<String, ImmutableSet<PrivilegeType>> ele : mapSet.entrySet()) {
            String dbName = ele.getKey();
            String privileges = getStringFromArray2(ele.getValue());
            if (isFirst) {
                result = result + dbName + ": (" + privileges + "); ";
                isFirst = false;
            } else {
                result = result + dbName + ": (" + privileges + "); ";
            }
        }
        
        return result;
        
    }
    
    private static String getStringFromArray2(Set<PrivilegeType> privilegeSet) {
        if (privilegeSet == null || privilegeSet.size() == 0) {
            return "";
        }
        String result = "";
        int i = 0;
        for(PrivilegeType ele : privilegeSet) {
            if (i != 0) {
                result = result + "," + ele.toString();
            } else {
                result = ele.toString();
            }
            ++i;
        }
        
        return result;
    }
    

    
    public static String getStringFromArray(Set<String> stringSet) {
        if (stringSet == null || stringSet.size() == 0) {
            return "";
        }
        String result = "";
        int i = 0;
        for(String ele : stringSet) {
            if (i != 0) {
                result = result + "," + ele.toString();
            } else {
                result = ele.toString();
            }
            ++i;
        }
        
        return result;
    }
}
