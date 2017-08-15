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

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import io.crate.exceptions.NoPermissionException;
import io.crate.rest.AuthRestFilter;

import org.elasticsearch.action.*;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthAction;
import org.elasticsearch.action.admin.cluster.user.show.ShowUserPropertyAction;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateRequest;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.metadata.*;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.cluster.routing.RoutingTable;
import org.elasticsearch.common.collect.Tuple;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.mysql.MysqlPassword;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestStatus;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AuthService extends AbstractLifecycleComponent<AuthService> {

    private static final Set<String> TABLE_BLACK_LIST =
            new HashSet<>(Arrays.asList(new String[]{
                    VirtualTableNames.cluster.name(),
                    VirtualTableNames.snapshots.name(),
                    VirtualTableNames.repositories.name()
            }));
    private static final int DEFAULT_WHITELIST_REFRESH_INTERVAL_SEC = 10;

    private final ClusterService clusterService;
    private final RestController restController;
    private IndexNameExpressionResolver indexNameExpressionResolver;
    private final ESLogger logger;
    private int whitelistRefreshInterval;
    private ScheduledExecutorService scheduledService = Executors.newSingleThreadScheduledExecutor();

    private static LoadingCache<String, Boolean> userIpCache =
            CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES)
                    .build(new CacheLoader<String, Boolean>() {
                        @Override
                        public Boolean load(String s) throws Exception {
                            return false;
                        }
                    });

    private static final Set<String> FILTER_REMOTE_ACTION = ImmutableSet.of(
            ClusterHealthAction.NAME, ShowUserPropertyAction.NAME);

    @Inject
    public AuthService(final ClusterService clusterService, final Settings settings,
                       final RestController restController, final IndexNameExpressionResolver indexNameExpressionResolver) {
        super(settings);
        this.clusterService = clusterService;
        this.restController = restController;
        this.indexNameExpressionResolver = indexNameExpressionResolver;
        this.logger = Loggers.getLogger(getClass(), settings);
        this.whitelistRefreshInterval = settings.getAsInt("auth.whitelist.refresh.interval_sec",
                DEFAULT_WHITELIST_REFRESH_INTERVAL_SEC);
    }

    public static void setStateFilter(ActionRequest request, String actionName) {
        request.putHeader(LoginUserContext.TENANT_FILTER, null);
        LoginUserContext userContext = request.getHeader(LoginUserContext.USER_INFO_KEY);
        if (userContext == null || userContext.loginUsername().equals(UserProperty.ROOT_NAME)) {
            return ;
        }
        // confirm if necessary to filter clusterState by tenant when response to user
        // filter is not necessary on remote node (userProperty is null), except some actions
        if ((actionName.startsWith("cluster:monitor/") || actionName.startsWith("indices:monitor/"))
             && (userContext.getUserProperty() != null || FILTER_REMOTE_ACTION.contains(actionName))) {
                request.putHeader(LoginUserContext.TENANT_FILTER, userContext.tenantId());
        }
    }

    public static ClusterState filterState(ClusterState oldState, MetaData completeMetaData, long tenantId){

        TenantProperty tenantProperty = completeMetaData.tenantMetadata().tenant(tenantId);

        ClusterState.Builder builder = ClusterState.builder(oldState.getClusterName());
        builder.version(oldState.version());
        builder.stateUUID(oldState.stateUUID());

        // nodes
        DiscoveryNodes.Builder nodesBuilder = DiscoveryNodes.builder();
        DiscoveryNodes oldNodes = oldState.nodes();
        nodesBuilder.masterNodeId(oldNodes.masterNodeId());
        nodesBuilder.localNodeId(oldNodes.localNodeId());
        Map<String, AllocatedNodeInfo> tenantNodes = tenantProperty.nodes();
        for (DiscoveryNode node : oldNodes) {
            if (tenantNodes.containsKey(node.address().toString())) {
                nodesBuilder.put(node);
            }
        }
        for (ObjectObjectCursor<String, DiscoveryNode> node : oldNodes.deadNodes()) {
            if (tenantNodes.containsKey(node.value.address().toString())) {
                nodesBuilder.putDeadNodeByIpPort(node.value);
            }
        }
        builder.nodes(nodesBuilder);

        // routingTable
        RoutingTable.Builder routingTableBuilder =  RoutingTable.builder();
        RoutingTable oldRoutingTable = oldState.routingTable();
        routingTableBuilder.version(oldRoutingTable.version());
        for (String index : oldRoutingTable.indicesRouting().keySet()) {
            if (completeMetaData.index(index).indexOwnerTenantId() == tenantId){
                routingTableBuilder.add(oldRoutingTable.index(index));
            }
        }
        builder.routingTable(routingTableBuilder);

        // blocks
        builder.blocks(oldState.blocks());

        // metadata
        MetaData.Builder metaDataBuilder = MetaData.builder();
        MetaData oldMetaData = oldState.metaData();
        metaDataBuilder.clusterUUID(oldMetaData.clusterUUID());
        metaDataBuilder.version(oldMetaData.version());

        // settings
        metaDataBuilder.transientSettings(oldMetaData.transientSettings().getByPrefix(TenantMetadata.TENANT_SETTING_PREFIX + "." + tenantProperty.tenantName() + "."));
        metaDataBuilder.persistentSettings(oldMetaData.persistentSettings().getByPrefix(TenantMetadata.TENANT_SETTING_PREFIX + "." + tenantProperty.tenantName() + "."));

        // indices
        for (String index : oldMetaData.concreteAllIndices()) {
            if (oldMetaData.index(index).indexOwnerTenantId() == tenantId) {
                metaDataBuilder.put(oldMetaData.index(index), false);
            }
        }

        // templates
        for (ObjectObjectCursor<String, IndexTemplateMetaData> template : oldMetaData.templates()) {
            if (template.value.templateOwnerTenantId() == tenantId) {
                metaDataBuilder.put(template.value);
            }
        }

        // userMetadata
        UserMetadata.Builder userMetadataBuilder = UserMetadata.builder(oldMetaData.getUserMetadata());
        for(UserProperty userProperty : oldMetaData.getUserMetadata().getUserProperties().values()) {
            if(userProperty.getTenantId() != tenantId) {
                userMetadataBuilder.deleteUser(userProperty.getUsernameWithTenant());
            }
        }
        metaDataBuilder.userMetadata(userMetadataBuilder.build());

        // tenantMetadata
        TenantMetadata.Builder tenantMetadataBuilder = TenantMetadata.builder();
        tenantMetadataBuilder.maxTenantId(oldMetaData.getTenantMetadata().maxTenantId());
        for(TenantProperty tenant : oldMetaData.getTenantMetadata().tenantProperties().values()) {
            if(tenant.tenantId() == tenantId) {
                tenantMetadataBuilder.addOrChangeTenantProperty(tenant);
            }
        }
        metaDataBuilder.tenantMetadata(tenantMetadataBuilder);

        // customs in metadata
        metaDataBuilder.customs(oldMetaData.customs());
        builder.metaData(metaDataBuilder);

        // customs
        builder.customs(oldState.customs());

        return builder.build();
    }

    public AuthResult authenticate(final ActionRequest request, String actionName) {

        LoginUserContext userInfo = (LoginUserContext)request.getHeader(LoginUserContext.USER_INFO_KEY);
        if (userInfo == null) {
            return new AuthResult(RestStatus.UNAUTHORIZED, "Can not find user info.");
        }
        UserProperty userProperty = userInfo.getUserProperty();

        if (!userInfo.authenticated()) {
            String encryptPass = new String(MysqlPassword.makeScrambledPassword(userInfo.password()));
            if (!encryptPass.equals(userProperty.getPassword())) {
                 return new AuthResult(RestStatus.UNAUTHORIZED, "Wrong password for user: " + userInfo.loginUsername());
            }
        }

        // root have all permission
        if (userProperty.getUsernameWithTenant().equals(UserProperty.ROOT_NAME)) {
            return new AuthResult(RestStatus.OK, null);
        }

        // check white list except sql user who is already connected
        if (!userInfo.authenticated()) {
            if (userInfo.sourceAddrs() == null || userInfo.sourceAddrs().length() == 0) {
                return new AuthResult(RestStatus.UNAUTHORIZED, "Source address is null");
            }

            Set<String> addrs = new HashSet<>();
            addrs.add(userInfo.sourceAddrs());

            if (userInfo.proxyAddrs() != null) {
                addrs.add(userInfo.proxyAddrs());
            }

            AuthResult whiteListResult = checkWhiteList(userInfo.fullUsername(), addrs, userProperty.getHostIpWhiteList());
            if (whiteListResult.getStatus() != RestStatus.OK) {
                return whiteListResult;
            }
        }

        PrivilegeType actionPrivilegeType = ActionPrivilegeType.getActionPrivilegeType(actionName);
        if (actionPrivilegeType == null) {
            logger.error("Authentication failed, Unchecked action: " + actionName);
            return new AuthResult(RestStatus.FORBIDDEN, "Unchecked action: " + actionName);
        }

        String dbName = null;
        String tableName = null;
        String[] names = VirtualTableNames.getVirtualTableName(actionName);
        if (names == null) {
            List<IndicesRequest> indicesRequests = new ArrayList<IndicesRequest>();
            if (request instanceof CompositeIndicesRequest) {
                indicesRequests.addAll(((CompositeIndicesRequest) request).subRequests());
            } else if (request instanceof IndicesRequest) {
                indicesRequests.add((IndicesRequest) request);
            } else {
                throw new NoPermissionException(RestStatus.UNAUTHORIZED.getStatus(),
                        "Can not handle this request: " + request.getClass().getName()
                        + " action: " + actionName + ". Please contact palo-rd.");
            }
            
            for (IndicesRequest indicesRequest : indicesRequests) {
                Set<String> indices = new HashSet<>();
                String[] resolvedIndices = new String[0];
                try {
                    resolvedIndices = indexNameExpressionResolver.concreteIndices(clusterService.state(), indicesRequest);
                } catch (IndexNotFoundException e) {
                    // not care index not found because it maybe a put index request or post a document to an un exist index
                    // so we could not use action name to authenticate here
                }
                // put index and template will also contains alias, deal with the two request by using a specific method
                if (indicesRequest instanceof CreateIndexRequest) {
                    // create index request should also check aliases
                    Set<Alias> alias = ((CreateIndexRequest)indicesRequest).aliases();
                    if (alias != null && !alias.isEmpty()) {
                        for (Alias aliasIter : alias) {
                            indices.add(aliasIter.name());
                        }
                    }
                } else if (indicesRequest instanceof PutIndexTemplateRequest) {
                    // template is very specific, the indices it returns is the regex pattern
                    // we should also check template name and aliases
                    PutIndexTemplateRequest putTemplateRequest = (PutIndexTemplateRequest)indicesRequest;
                    indices.add(putTemplateRequest.name());
                    Set<Alias> alias = putTemplateRequest.aliases();
                    if (alias != null && !alias.isEmpty()) {
                        for (Alias aliasIter : alias) {
                            indices.add(aliasIter.name());
                        }
                    }
                }
                
                indices.addAll(Arrays.asList(resolvedIndices));
                if (indicesRequest instanceof AliasesRequest) {
                    indices.addAll(Arrays.asList(((AliasesRequest)indicesRequest).aliases()));
                }
                if (indices.isEmpty()) {
                    // if could not find indices from request then it maybe a add index request, so that add the 
                    // original indices to authenticate list
                    indices.addAll(Arrays.asList(indicesRequest.indices()));
                }
                for (String index : indices) {
                    Tuple<String, String> dbAndTableName = UserProperty.getDBAndTableName(index);
                    dbName = dbAndTableName.v1();
                    tableName = dbAndTableName.v2();
                    AuthResult authResult = internalAuthenticate(userProperty, dbName, tableName,
                            actionPrivilegeType);
                    if (authResult.getStatus() != RestStatus.OK) {
                        return authResult;
                    }
                }
            }
        } else {
            dbName = names[0];
            tableName = names[1];
            return internalAuthenticate(userProperty, dbName, tableName, actionPrivilegeType);
        }

        return new AuthResult(RestStatus.OK, null);
    }

    public static int compareUserPrivilege(String currentOperateUserName, String userToBeModified) {
        currentOperateUserName = UserProperty.getUsernameWithoutTenantFromFullUsername(currentOperateUserName);
        userToBeModified = UserProperty.getUsernameWithoutTenantFromFullUsername(userToBeModified);
        if (currentOperateUserName.equalsIgnoreCase(userToBeModified)) {
            return 0;
        }
        if (currentOperateUserName.equalsIgnoreCase(UserProperty.ROOT_NAME)) {
            return 1;
        }
        if (currentOperateUserName.equalsIgnoreCase(UserProperty.SUPER_USER_NAME)) {
            if (UserProperty.ROOT_NAME.equalsIgnoreCase(userToBeModified)) {
                return -1;
            } else {
                return 1;
            }
        }
        return -1;
    }
    
    public static AuthResult sqlAuthenticate(final LoginUserContext loginUserContext, String dbName,
            String tableName, PrivilegeType type) {
        AuthResult authResult = internalAuthenticate(loginUserContext.getUserProperty(), dbName, tableName, type);
        if (authResult.getStatus().equals(RestStatus.OK)) {
            loginUserContext.setAuthorized(true);
        }
        return authResult;
    }
    
    public static AuthResult internalAuthenticate(final UserProperty userProperty, String dbName,
                                             String tableName, PrivilegeType type) {
        if (userProperty == null) {
            return new AuthResult(RestStatus.UNAUTHORIZED, "User may not exist.");
        }

        // root have all permission
        if (userProperty.getUsernameWithoutTenant().equals(UserProperty.ROOT_NAME)) {
            return new AuthResult(RestStatus.OK, null);
        }

        if (type == null) {
            return new AuthResult(RestStatus.FORBIDDEN, "PrivilegeType is null");
        }

        // check table black list
        String realTableName = dbName + "." + tableName;
        // only sys db should check superuser and ordinary user
        if (VirtualTableNames.sys.name().equals(dbName)) {
            if (type == PrivilegeType.READ_WRITE && TABLE_BLACK_LIST.contains(tableName)) {
                // only root have privilege to do write on tables in black list
                return new AuthResult(RestStatus.UNAUTHORIZED, "Only root have permission to WRITE on table: " + realTableName);
            } else if (userProperty.getUsernameWithoutTenant().equals(UserProperty.SUPER_USER_NAME) && !TABLE_BLACK_LIST.contains(tableName)) {
                // superuser have privilege on other tables in sys db
                return new AuthResult(RestStatus.OK, null);
            } else if (type == PrivilegeType.READ_ONLY) {
                // all user have permission to read cluster metadata
                return new AuthResult(RestStatus.OK, null);
            }
        }
        // if username is superuser and without tenant name then it has privileges on all tables
        // it is just to compatible to old privilege system
        if (userProperty.getUsernameWithoutTenant().equals(UserProperty.SUPER_USER_NAME) && userProperty.getTenantId() == TenantProperty.ROOT_TENANT_ID) {
            return new AuthResult(RestStatus.OK, null);
        }
        
        // for ordinary db, both superuser and ordinary user should check privilege 
        Set<PrivilegeType> dbPrivileges = userProperty.getDbPrivileges().get(dbName);
        Set<PrivilegeType> tablePrivileges = userProperty.getTablePrivileges().get(realTableName);

        if (type == PrivilegeType.READ_ONLY) {
            if ((dbPrivileges != null && (dbPrivileges.contains(PrivilegeType.READ_ONLY)
                                        || dbPrivileges.contains(PrivilegeType.READ_WRITE)))
                    || (tablePrivileges != null && (tablePrivileges.contains(PrivilegeType.READ_ONLY)
                                        || tablePrivileges.contains(PrivilegeType.READ_WRITE)))) {
                return new AuthResult(RestStatus.OK, null);
            }
        } else if ((dbPrivileges != null && dbPrivileges.contains(type))
                || (tablePrivileges != null && tablePrivileges.contains(type))) {
            return new AuthResult(RestStatus.OK, null);
        }
        String reason = userProperty.getUsernameWithTenant() 
                + " have no permission " + type.name() 
                + " on table: " + realTableName;
        return new AuthResult(RestStatus.UNAUTHORIZED, reason);
    }

    public static AuthResult checkWhiteList(String user, Set<String> addrs, Set<String> ipWhiteList) {
        for (String addr : addrs) {
            String userAndIp = user + "@" + addr;
            try {
                if (!userIpCache.get(userAndIp)) {
                    boolean addrInWhiteList = false;
                    for (String ip : ipWhiteList) {
                        if (matchIP(addr, ip)) {
                            addrInWhiteList = true;
                            userIpCache.put(userAndIp, true);
                            break;
                        }
                    }
                    if (!addrInWhiteList) {
                        return new AuthResult(RestStatus.UNAUTHORIZED, "proxy or source address is not in whitelist: " + addr);
                    }
                }
            } catch (Exception e) {
                return new AuthResult(RestStatus.FORBIDDEN, "load cache occurs exceptions");
            }
        }
        return new AuthResult(RestStatus.OK, null);
    }

    private static boolean matchIP(final String srcIP, final String dstIP) {
        if (srcIP == null || srcIP.length() <= 0 || dstIP == null || dstIP.length() <= 0) {
            return false;
        }
        if (dstIP.equals("*")) {
            return true;
        }
        String[] srcIpSegment = srcIP.split("\\.");
        String[] dstIpSegment = dstIP.split("\\.");
        if (srcIpSegment.length < 4 || dstIpSegment.length < 4) {
            return false;
        }
        if ( (srcIpSegment[0].equals(dstIpSegment[0]) || dstIpSegment[0].equals("*"))
                && (srcIpSegment[1].equals(dstIpSegment[1]) || dstIpSegment[1].equals("*"))
                && (srcIpSegment[2].equals(dstIpSegment[2]) || dstIpSegment[2].equals("*"))
                && (srcIpSegment[3].equals(dstIpSegment[3]) || dstIpSegment[3].equals("*"))) {
            return true;
        }
        return false;
    }

    private void refreshHostIpWhiteList() {
        if (clusterService.localNode().isMasterNode()) {
            MetaData metaData = clusterService.state().getMetaData();
            UserMetadata userMetadata = metaData.getUserMetadata();
            final UserMetadata.Builder userMetadataBuilder = new UserMetadata.Builder(userMetadata);
            ImmutableMap<String, UserProperty> userPropertyMap = userMetadata.getUserProperties();
            boolean changed = false;
            for (String user : userPropertyMap.keySet()) {
                UserProperty userProperty = userPropertyMap.get(user);
                Set<String> hostIpWhiteList = userProperty.getHostIpWhiteList();
                Set<String> hostnameWhiteList = userProperty.getHostnameWhiteList();
                Set<String> newHostIpWhiteList = new HashSet<>();
                boolean errorFlag = false;
                for (String hostname : hostnameWhiteList) {
                    try {
                        InetAddress[] address = InetAddress.getAllByName(hostname);
                        for (InetAddress addr : address) {
                            newHostIpWhiteList.add(addr.getHostAddress());
                        }
                    } catch (UnknownHostException e) {
                        logger.error("Unknown host or BNS name: " + hostname);
                        errorFlag = true;
                        break;
                    }
                }
                newHostIpWhiteList.addAll(userProperty.getIpWhiteList());
                if (!errorFlag && !hostIpWhiteList.equals(newHostIpWhiteList)) {
                    UserProperty.Builder userPropertyBuilder = new UserProperty.Builder(userProperty);
                    userPropertyBuilder.cleanHostIpWhiteList().addHostIpListToWhiteList(newHostIpWhiteList);
                    UserProperty newUserProperty = userPropertyBuilder.build();
                    userMetadataBuilder.addOrChangeUserProperty(user, newUserProperty);
                    changed = true;
                }
            }

            if (changed) {
                clusterService.submitStateUpdateTask("[refresh user hostIpWhiteList]", new ClusterStateUpdateTask() {

                    @Override
                    public boolean runOnlyOnMaster() {
                        return true;
                    }

                    @Override
                    public ClusterState execute(ClusterState currentState) throws Exception {
                        MetaData.Builder metaDataBuilder = MetaData.builder(currentState.metaData());
                        metaDataBuilder.putUserMetadata(userMetadataBuilder);
                        return ClusterState.builder(currentState).metaData(metaDataBuilder).build();
                    }

                    @Override
                    public void onFailure(String source, Throwable t) {
                        logger.error("unexpected failure during [{}]", t, source);
                    }
                });
            }
        }
    }

    @Override
    protected void doStart() {
        logger.info("Starting AuthService.");
        AuthRestFilter authRestFilter = new AuthRestFilter(clusterService, logger);
        restController.registerFilter(authRestFilter);

        // load scheduled
        if (whitelistRefreshInterval > 0) {
            Runnable runnable = new Runnable() {
                public void run() {
                    refreshHostIpWhiteList();
                }
            };
            scheduledService.scheduleAtFixedRate(runnable, whitelistRefreshInterval, whitelistRefreshInterval,
                    TimeUnit.SECONDS);
        }
    }

    @Override
    protected void doStop() {
        logger.info("Stopping AuthService");
    }

    @Override
    protected void doClose() {
        logger.info("Closing AuthService.");
    }
}
