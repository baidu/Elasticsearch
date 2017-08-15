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

package org.elasticsearch.discovery.dl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import org.elasticsearch.cluster.ClusterChangedEvent;
import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.ClusterStateUpdateTask;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.cluster.routing.RoutingService;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.discovery.AckClusterStatePublishResponseHandler;
import org.elasticsearch.discovery.BlockingClusterStatePublishResponseHandler;
import org.elasticsearch.discovery.Discovery;
import org.elasticsearch.discovery.DiscoverySettings;
import org.elasticsearch.discovery.InitialStateDiscoveryListener;
import org.elasticsearch.env.Environment;
import org.elasticsearch.node.service.NodeService;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

public class DLDiscovery extends AbstractLifecycleComponent<Discovery> implements Discovery {

    private ClusterName clusterName;
    private final ClusterService clusterService;
    private DiscoverySettings discoverySettings;
    private ClusterStateOpLog clusterStateOpLog;
    private PublishClusterStateVersionAction publishClusterStateVersionAction;
    private JoinClusterAction joinClusterAction;
    private NodesFailureDetectionService nodesFailureDetectionService;
    private RoutingService routingService;
    private ThreadPool threadPool;
    private Environment nodeEnvironment;
    private TransportService transportService;
    private final CopyOnWriteArrayList<InitialStateDiscoveryListener> initialStateListeners = new CopyOnWriteArrayList<>();
    
    @Inject
    public DLDiscovery(Settings settings, ClusterName clusterName, ThreadPool threadPool,
            TransportService transportService, final ClusterService clusterService, 
            DiscoverySettings discoverySettings, Environment nodeEnvironment) {
        super(settings);
        this.clusterService = clusterService;
        this.clusterName = clusterName;
        this.discoverySettings = discoverySettings;
        this.threadPool = threadPool;
        this.nodeEnvironment = nodeEnvironment;
        this.transportService = transportService;
        this.clusterStateOpLog = null;
        this.publishClusterStateVersionAction = null;
        this.joinClusterAction = null;
        this.nodesFailureDetectionService = null;
    }
    
    @Override
    public DiscoveryNode localNode() {
        return clusterService.localNode();
    }

    @Override
    public void addListener(InitialStateDiscoveryListener listener) {
        this.initialStateListeners.add(listener);
    }

    @Override
    public void removeListener(InitialStateDiscoveryListener listener) {
        this.initialStateListeners.remove(listener);
    }

    @Override
    public String nodeDescription() {
        return clusterName.value() + "/" + clusterService.localNode().id();
    }

    @Override
    public void setNodeService(NodeService nodeService) {
    }

    @Override
    public void setRoutingService(RoutingService routingService) {
        this.routingService = routingService;
    }

    @Override
    public void publish(ClusterChangedEvent clusterChangedEvent,
            AckListener ackListener) {
        final TimeValue publishTimeout = discoverySettings.getPublishTimeout();
        if (!clusterChangedEvent.state().getNodes().localNodeMaster()) {
            throw new IllegalStateException("Shouldn't publish state when not master");
        }
        final ClusterState curClusterState = clusterChangedEvent.state();
        final ClusterState previousState = clusterChangedEvent.previousState();
        try {
            clusterStateOpLog.writeDiff(curClusterState, previousState, publishTimeout.millis());
        } catch (IOException e) {
            logger.error("errors while persist diff cluster state to dl", e);
            throw new IllegalStateException("errors while persist diff cluster state to dl, maybe master lost or not master any more");
        }
        Set<DiscoveryNode> nodesToPublishTo = new HashSet<>(clusterChangedEvent.state().nodes().size());
        DiscoveryNode localNode = curClusterState.nodes().localNode();
        for (final DiscoveryNode node : clusterChangedEvent.state().nodes()) {
            if (node.equals(localNode)) {
                continue;
            }
            nodesToPublishTo.add(node);
        }

        final AtomicBoolean timedOutWaitingForNodes = new AtomicBoolean(false);
        BlockingClusterStatePublishResponseHandler publishResponseHandler = new AckClusterStatePublishResponseHandler(nodesToPublishTo, ackListener);
        for (final DiscoveryNode node : nodesToPublishTo) {
            publishClusterStateVersionAction.publishClusterStateVersionToNode(curClusterState.version(), node, publishTimeout, publishResponseHandler);
        }
        
        if (publishTimeout.millis() > 0) {
            // only wait if the publish timeout is configured...
            try {
                timedOutWaitingForNodes.set(!publishResponseHandler.awaitAllNodes(publishTimeout));
                if (timedOutWaitingForNodes.get()) {
                    DiscoveryNode[] pendingNodes = publishResponseHandler.pendingNodes();
                    // everyone may have just responded
                    if (pendingNodes.length > 0) {
                        logger.warn("timed out waiting for all nodes to process published state [{}] (timeout [{}], pending nodes: {})", curClusterState.version(), publishTimeout, pendingNodes);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    @Override
    protected void doStart() {
        if (clusterStateOpLog == null) {
            this.clusterStateOpLog = new ClusterStateOpLog(settings, discoverySettings, clusterService, routingService, nodeEnvironment, threadPool);
        }
        if (publishClusterStateVersionAction == null) {
            this.publishClusterStateVersionAction = new PublishClusterStateVersionAction(settings, clusterService, transportService, clusterStateOpLog);
        }
        if (joinClusterAction == null) {
            this.joinClusterAction = new JoinClusterAction(settings, transportService, clusterService, threadPool, routingService);
        }
        if (nodesFailureDetectionService == null) {
            this.nodesFailureDetectionService = new NodesFailureDetectionService(settings, threadPool, transportService, clusterName, clusterService, routingService, joinClusterAction, clusterStateOpLog);
        }
        if (clusterStateOpLog.getLatestClusterState().state().version() > clusterService.state().version()) {
            final CountDownLatch latch = new CountDownLatch(1);
            clusterService.submitStateUpdateTask("load cluster state from image", new ClusterStateUpdateTask(Priority.IMMEDIATE) {
                
                @Override
                public boolean runOnlyOnMaster() {
                    return false;
                }
                
                @Override
                public void onFailure(String source, Throwable t) {
                    clusterStateOpLog.exitProcess(t, "errors while apply initial state");
                }
                
                @Override
                public void clusterStateProcessed(String source, ClusterState oldState, ClusterState newState) {
                    latch.countDown();
                }
                
                @Override
                public ClusterState execute(ClusterState currentState) throws Exception {
                    return clusterStateOpLog.getLatestClusterState().state();
                }
            });
            logger.info("waiting for initializing state");
            try {
                latch.await();
            } catch (InterruptedException e) {
                logger.error("", e);
            }
        }
        for (InitialStateDiscoveryListener listener : initialStateListeners) {
            listener.initialStateProcessed();
        }
        if (localNode().isMasterNode()) {
            this.nodesFailureDetectionService.start();
        }
    }

    @Override
    protected void doStop() {
    }

    @Override
    protected void doClose() {
    }

    @Override
    public void startInitialJoin() {
        
    }
}
