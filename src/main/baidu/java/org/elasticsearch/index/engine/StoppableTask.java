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

package org.elasticsearch.index.engine;

import org.elasticsearch.common.logging.ESLogger;

import org.apache.distributedlog.exceptions.DLInterruptedException;

public abstract class StoppableTask implements Runnable {
    // remember error counts just for debug
    protected boolean desireRunning = false;
    protected boolean actualRunning = false;
    private String threadName;
    private ESLogger logger;
    private Thread threadHandle;
    protected boolean isInterruptable = true;
    
    public StoppableTask(String threadName, ESLogger logger) {
        this.threadName = threadName;
        this.logger = logger;
        this.threadHandle = null;
    }
    
    public synchronized boolean start(String reason) {
        logger.info("try to start task, reason is: {}", reason);
        if (desireRunning || actualRunning) {
            return false;
        }
        this.actualRunning = true;
        this.desireRunning = true;
        this.threadHandle = new Thread(this, threadName);
        this.threadHandle.start();
        return true;
    }
    
    public boolean isDesireRunning() {
        return desireRunning;
    }
    
    public boolean isActualRunning() {
        return actualRunning;
    }
    
    public long getTid() {
        if (threadHandle == null || !threadHandle.isAlive()) {
            return -1;
        }
        return this.threadHandle.getId();
    }
    
    public void stop(boolean waitForStop) {
        this.desireRunning = false;
        // current thread call stop itself, just return
        if (Thread.currentThread().getId() == getTid()) {
            return;
        }
        if (this.threadHandle != null && isInterruptable) {
            this.threadHandle.interrupt();
        }
        int waitTimes = 0;
        if (waitForStop) {
            while (actualRunning) {
                if (this.threadHandle != null && isInterruptable) {
                    this.threadHandle.interrupt();
                    logger.trace("send interrupt message to thread [{}]", this.threadHandle.getId());
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // ignore the request and continue to wait
                }
                ++ waitTimes;
                if (waitTimes % 10 == 0) {
                    logger.info("wait too long, wait times [{}], target thread id is [{}]", waitTimes, this.threadHandle.getId());
                }
            }
        }
    }
    
    public void run() {
        try {
            actualTask();
        } catch (Throwable t) {
            if (t instanceof DLInterruptedException || t instanceof InterruptedException) {
                logger.trace("errors while running recover from task", t);
            } else {
                logger.error("errors while running recover from task", t);
            }
        }
        actualRunning = false;
    }
    
    public abstract void actualTask();
}
