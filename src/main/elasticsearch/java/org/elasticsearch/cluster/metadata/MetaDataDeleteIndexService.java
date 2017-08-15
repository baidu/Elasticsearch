/*
 * Modifications copyright (C) 2017, Baidu.com, Inc.
 * 
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.cluster.metadata;

import org.elasticsearch.action.support.master.MasterNodeRequest;
import org.elasticsearch.authentication.LoginUserContext;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.action.index.NodeIndexDeletedAction;
import org.elasticsearch.cluster.block.ClusterBlocks;
import org.elasticsearch.cluster.routing.RoutingTable;
import org.elasticsearch.cluster.routing.allocation.AllocationService;
import org.elasticsearch.cluster.routing.allocation.RoutingAllocation;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.collect.Tuple;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.util.concurrent.FutureUtils;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.translog.dl.DistributedTranslog;
import org.elasticsearch.threadpool.ThreadPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MetaDataDeleteIndexService extends AbstractComponent {

    private final ThreadPool threadPool;

    private final ClusterService clusterService;

    private final AllocationService allocationService;

    private final NodeIndexDeletedAction nodeIndexDeletedAction;

    @Inject
    public MetaDataDeleteIndexService(Settings settings, ThreadPool threadPool, ClusterService clusterService, AllocationService allocationService,
                                      NodeIndexDeletedAction nodeIndexDeletedAction) {
        super(settings);
        this.threadPool = threadPool;
        this.clusterService = clusterService;
        this.allocationService = allocationService;
        this.nodeIndexDeletedAction = nodeIndexDeletedAction;
    }

    public void deleteIndices(final Request request, final Listener userListener) {
        final Collection<String> indices = Arrays.asList(request.indices);       
        final DeleteIndexListener listener = new DeleteIndexListener(userListener);
        final ArrayList<Tuple<Tuple<String, Settings>, Integer>> indicesToBeDeleted = new ArrayList<Tuple<Tuple<String, Settings>,Integer>>(); 
        
        clusterService.submitStateUpdateTask("delete-index " + indices, new ClusterStateUpdateTask(Priority.IMMEDIATE) {
            @Override
            public TimeValue timeout() {
                return request.masterTimeout;
            }

            @Override
            public void onFailure(String source, Throwable t) {
                listener.onFailure(t);
            }

            @Override
            public ClusterState execute(final ClusterState currentState) throws Exception {
                RoutingTable.Builder routingTableBuilder = RoutingTable.builder(currentState.routingTable());
                MetaData.Builder metaDataBuilder = MetaData.builder(currentState.metaData());
                ClusterBlocks.Builder clusterBlocksBuilder = ClusterBlocks.builder().blocks(currentState.blocks());
                long currentTenantId = request.userContext.tenantId();
                for (final String index: indices) {
                    if (!currentState.metaData().hasConcreteIndex(index)) {
                        throw new IndexNotFoundException(index);
                    }
                    logger.debug("[{}] deleting index", index);
                    // if current user is not root user, should check whether current tenant is the owner tenant
                    if (!UserProperty.ROOT_NAME.equalsIgnoreCase(UserProperty.getUsernameWithoutTenantFromFullUsername(request.userContext.fullUsername()))) {
                        long ownerTenantId = currentState.getMetaData().indices().get(index).indexOwnerTenantId();
                        if (ownerTenantId != currentTenantId) {
                            throw new Exception("current operation tenant [" + currentTenantId + "] is not the owner tenant for index [" + index + "]");
                        }
                    }
                    Settings indexSettings = currentState.getMetaData().indices().get(index).getSettings();
                    if (IndexMetaData.isIndexUsingDLEngine(indexSettings)) {
                        int shardNum = currentState.getMetaData().indices().get(index).getNumberOfShards();
                        Tuple<String, Settings> v1 = new Tuple<String, Settings>(index, indexSettings);
                        Tuple<Tuple<String, Settings>, Integer> v2 = new Tuple<Tuple<String,Settings>, Integer>(v1, shardNum);
                        indicesToBeDeleted.add(v2);
                    }
                    routingTableBuilder.remove(index);
                    clusterBlocksBuilder.removeIndexBlocks(index);
                    metaDataBuilder.remove(index);
                }
                // wait for events from all nodes that it has been removed from their respective metadata...
                int count = currentState.nodes().size();
                // add the notifications that the store was deleted from *data* nodes
                count += currentState.nodes().dataNodes().size();
                final AtomicInteger counter = new AtomicInteger(count * indices.size());

                // this listener will be notified once we get back a notification based on the cluster state change below.
                final NodeIndexDeletedAction.Listener nodeIndexDeleteListener = new NodeIndexDeletedAction.Listener() {
                    @Override
                    public void onNodeIndexDeleted(String deleted, String nodeId) {
                        if (indices.contains(deleted)) {
                            if (counter.decrementAndGet() == 0) {
                                listener.onResponse(new Response(true));
                                nodeIndexDeletedAction.remove(this);
                            }
                        }
                    }

                    @Override
                    public void onNodeIndexStoreDeleted(String deleted, String nodeId) {
                        if (indices.contains(deleted)) {
                            if (counter.decrementAndGet() == 0) {
                                listener.onResponse(new Response(true));
                                nodeIndexDeletedAction.remove(this);
                            }
                        }
                    }
                };
                nodeIndexDeletedAction.add(nodeIndexDeleteListener);
                listener.future = threadPool.schedule(request.timeout, ThreadPool.Names.SAME, new Runnable() {
                    @Override
                    public void run() {
                        listener.onResponse(new Response(false));
                        nodeIndexDeletedAction.remove(nodeIndexDeleteListener);
                    }
                });

                MetaData newMetaData = metaDataBuilder.build();
                ClusterBlocks blocks = clusterBlocksBuilder.build();
                RoutingAllocation.Result routingResult = allocationService.reroute(
                        ClusterState.builder(currentState).routingTable(routingTableBuilder.build()).metaData(newMetaData).build(),
                        "deleted indices [" + indices + "]");
                return ClusterState.builder(currentState).routingResult(routingResult).metaData(newMetaData).blocks(blocks).build();
            }

            @Override
            public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
            }
            
            @Override
            public void clusterStatePublished(ClusterState newClusterState) {
                for (int i = 0; i < indicesToBeDeleted.size(); i++) {
                    int shardNum = indicesToBeDeleted.get(i).v2();
                    String indexName = indicesToBeDeleted.get(i).v1().v1();
                    Settings indexSettings = indicesToBeDeleted.get(i).v1().v2();
                    for (int shardIndex = 0; shardIndex < shardNum; ++shardIndex) {
                        try {
                            String indexUUID = indexSettings.get(IndexMetaData.SETTING_INDEX_UUID, IndexMetaData.INDEX_UUID_NA_VALUE);
                            DistributedTranslog.deleteLog(settings, indexUUID, indexName, shardIndex, newClusterState.nodes().localNodeId());
                            logger.info("delete index[{}], shardId[{}] from distributed log service successfully", indexName, shardIndex);
                        } catch (Throwable t) {
                            logger.error("errors while delete log from distributed log service, index[{}], shardId[{}]", t, indexName, shardIndex);
                        }
                    }
                }
            }
        });
    }

    class DeleteIndexListener implements Listener {

        private final AtomicBoolean notified = new AtomicBoolean();
        private final Listener listener;
        volatile ScheduledFuture<?> future;

        private DeleteIndexListener(Listener listener) {
            this.listener = listener;
        }

        @Override
        public void onResponse(final Response response) {
            if (notified.compareAndSet(false, true)) {
                FutureUtils.cancel(future);
                listener.onResponse(response);
            }
        }

        @Override
        public void onFailure(Throwable t) {
            if (notified.compareAndSet(false, true)) {
                FutureUtils.cancel(future);
                listener.onFailure(t);
            }
        }
    }

    public interface Listener {

        void onResponse(Response response);

        void onFailure(Throwable t);
    }

    public static class Request {

        final String[] indices;

        TimeValue timeout = TimeValue.timeValueSeconds(10);
        TimeValue masterTimeout = MasterNodeRequest.DEFAULT_MASTER_NODE_TIMEOUT;
        LoginUserContext userContext;

        public Request(String[] indices, LoginUserContext userContext) {
            this.indices = indices;
            this.userContext = userContext;
        }

        public Request timeout(TimeValue timeout) {
            this.timeout = timeout;
            return this;
        }

        public Request masterTimeout(TimeValue masterTimeout) {
            this.masterTimeout = masterTimeout;
            return this;
        }
    }

    public static class Response {
        private final boolean acknowledged;

        public Response(boolean acknowledged) {
            this.acknowledged = acknowledged;
        }

        public boolean acknowledged() {
            return acknowledged;
        }
    }
}
