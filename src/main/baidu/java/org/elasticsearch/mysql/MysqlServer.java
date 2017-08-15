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

package org.elasticsearch.mysql;

import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

// MySQL protocol network service
public class MysqlServer {
    private static final ESLogger logger = Loggers.getLogger(MysqlServer.class);

    private int port;
    private ServerSocketChannel serverChannel = null;
    private ConnectScheduler scheduler = null;
    // used to accept connect request from client
    private Thread listener;
    private boolean running;

    public MysqlServer(int port, ConnectScheduler scheduler) {
        this.port = port;
        this.scheduler = scheduler;
    }

    // start MySQL protocol service
    // return true if success, otherwise false
    public boolean start() {
        if (scheduler == null) {
            logger.warn("scheduler is NULL.");
            return false;
        }

        // open server socket
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.socket().bind(new InetSocketAddress("0.0.0.0", port), 2048);
            serverChannel.configureBlocking(true);
        } catch (IOException e) {
            logger.warn("Open MySQL network service failed.", e);
            return false;
        }

        // start accept thread
        listener = new Thread(new Listener(), "MySQL Protocol Listener");
        running = true;
        listener.start();

        return true;
    }

    public void stop() {
        if (running) {
            running = false;
            // close server channel, make accept throw exception
            try {
                serverChannel.close();
            } catch (IOException e) {
                logger.warn("close server channel failed.", e);
            }
        }
    }

    public void join() {
        try {
            listener.join();
        } catch (InterruptedException e) {
            // just return
            logger.warn("Join MySQL server exception.", e);
        }
    }

    private class Listener implements Runnable {
        @Override
        public void run() {
            while (running && serverChannel.isOpen()) {
                SocketChannel clientChannel = null;
                try {
                    clientChannel = serverChannel.accept();
                    if (clientChannel == null) {
                        continue;
                    }
                    // submit this context to scheduler
                    ConnectContext context = new ConnectContext(clientChannel);
                    // Set catalog here.
                    // context.setCatalog(Catalog.getInstance());
                    if (!scheduler.submit(context)) {
                        logger.warn("Submit one connect request failed. Client=" + clientChannel.toString());
                        // clear up context
                        context.cleanup();
                    }
                } catch (IOException e) {
                    // some error when accept
                    logger.warn("Query server failed when calling accept.", e);
                    return;
                }
            }
        }
    }

    public ConnectScheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(ConnectScheduler scheduler) {
        this.scheduler = scheduler;
    }
    
}
