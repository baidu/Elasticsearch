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

import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.io.stream.BytesStreamOutput;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.discovery.BlockingClusterStatePublishResponseHandler;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.BytesTransportRequest;
import org.elasticsearch.transport.EmptyTransportResponseHandler;
import org.elasticsearch.transport.TransportChannel;
import org.elasticsearch.transport.TransportException;
import org.elasticsearch.transport.TransportRequestHandler;
import org.elasticsearch.transport.TransportRequestOptions;
import org.elasticsearch.transport.TransportResponse;
import org.elasticsearch.transport.TransportService;

public class PublishClusterStateVersionAction extends AbstractComponent {

    public static final String PUBLISH_VERSION_ACTION_NAME = "internal:discovery/dl/publish_version";
    public final static String SETTING_FULL_STATE_SYNC_THRESHOLD = "discovery.dl.full_state_sync_operations";
    public static final String PUBLISH_TIMEOUT = "discovery.dl.publish_timeout";
    private volatile TimeValue publishTimeout = TimeValue.timeValueSeconds(30);

    private TransportService transportService;
    private ClusterService clusterService;
    private ClusterStateOpLog clusterStateOpLog;
    
    private final PullFullClusterStateAction pullFullClusterStateAction;
    private final long fullStateSyncOps;
    
    public PublishClusterStateVersionAction(Settings settings, ClusterService clusterService, TransportService transportService, ClusterStateOpLog clusterStateOpLog) {
        super(settings);
        this.clusterService = clusterService;
        this.transportService = transportService;
        this.clusterStateOpLog = clusterStateOpLog;
        this.pullFullClusterStateAction = new PullFullClusterStateAction(settings, clusterService, transportService, clusterStateOpLog);
        this.fullStateSyncOps = settings.getAsLong(SETTING_FULL_STATE_SYNC_THRESHOLD, 30L);
        this.publishTimeout = settings.getAsTime(PUBLISH_TIMEOUT, publishTimeout);
        this.transportService.registerRequestHandler(PUBLISH_VERSION_ACTION_NAME, BytesTransportRequest.class, ThreadPool.Names.GENERIC, new PublishClusterStateVersionRequestHandler());
    }

    public void publishClusterStateVersionToNode(final long newVersion,
            final DiscoveryNode node, 
            final TimeValue publishTimeout,
            final BlockingClusterStatePublishResponseHandler publishResponseHandler) {
        try {
            BytesStreamOutput bStream = new BytesStreamOutput();
            bStream.writeLong(newVersion);
            clusterService.localNode().writeTo(bStream);
            bStream.writeString(node.getId());
            BytesReference nodeBytes = bStream.bytes();
            TransportRequestOptions options = TransportRequestOptions.builder().withType(TransportRequestOptions.Type.STATE).withCompress(false).withTimeout(publishTimeout).build();
            transportService.sendRequest(node, PUBLISH_VERSION_ACTION_NAME, new BytesTransportRequest(nodeBytes, node.version()), 
                    options, 
                    new EmptyTransportResponseHandler(ThreadPool.Names.SAME) {
                
                @Override
                public void handleResponse(TransportResponse.Empty response) {
                    publishResponseHandler.onResponse(node);
                }
                
                @Override
                public void handleException(TransportException exp) {
                    logger.debug("failed to send cluster state to {}, version {}", exp, node, newVersion);
                    publishResponseHandler.onFailure(node, exp);
                }
            });
        } catch (Throwable t) {
            logger.warn("error sending cluster state to {}", t, node);
            publishResponseHandler.onFailure(node, t);
        }
    }
    
    private class PublishClusterStateVersionRequestHandler extends TransportRequestHandler<BytesTransportRequest> {
        
        @Override
        public void messageReceived(BytesTransportRequest request, final TransportChannel channel) throws Exception {
            StreamInput in = request.bytes().streamInput();
            in.setVersion(request.version());
            long version = in.readLong();
            DiscoveryNode masterNode = DiscoveryNode.readNode(in);
            in.close();
            if (version - clusterService.state().getVersion() > fullStateSyncOps || clusterStateOpLog.shouldPullFullStateFromMaster()) {
                // when node restarted, it does not know who is master, it depends on the request msg received
                logger.info("local version is [{}], version received is [{}], threshold is [{}], need pull full state config is [{}]", 
                        clusterService.state().getVersion(), version, fullStateSyncOps, clusterStateOpLog.shouldPullFullStateFromMaster());
                pullFullClusterStateAction.pullFullClusterStateFromNode(masterNode, publishTimeout);
            } else {
                // it is a best effort try to pull state, if failed ignore it
                int sleepTimes = 0;
                while (clusterService.state().version() < version) {
                    Thread.sleep(500);
                    sleepTimes ++;
                    if (sleepTimes > 10) {
                        break;
                    }
                }
            }
            channel.sendResponse(TransportResponse.Empty.INSTANCE);
        }
    }
}
