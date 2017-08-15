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

package org.elasticsearch.rest;

import org.apache.http.Consts;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;

public class LocalRestChannel extends RestChannel {

    private String content;
    private int status;
    private CountDownLatch count;

    public LocalRestChannel(RestRequest request, boolean detailedErrorsEnabled) {
        super(request, detailedErrorsEnabled);
        this.count = new CountDownLatch(1);
    }

    public int getStatus() throws Exception{
        count.await();
        return status;
    }

    public String getContent() throws Exception{
        count.await();
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void doSendResponse(RestResponse response) {
        status = response.status().getStatus();

        byte[] bytes = response.content().toBytes();
        long length = bytes.length;
        Args.check(length <= Integer.MAX_VALUE, "HTTP entity too large to be buffered in memory");
        if(length < 0) {
            length = 4096;
        }

        InputStream instream =  new ByteArrayInputStream(bytes);
        InputStreamReader reader = new InputStreamReader(instream, Consts.UTF_8);
        CharArrayBuffer buffer = new CharArrayBuffer((int)length);
        char[] tmp = new char[1024];

        int l;
        try {
            while ((l = reader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
            content = buffer.toString();
        } catch (IOException e) {
            status = RestStatus.INTERNAL_SERVER_ERROR.getStatus();
            content = "IOException: " + e.getMessage();
        } finally {
            try {
                reader.close();
                instream.close();
            } catch (IOException e1) {
                content = "IOException: " + e1.getMessage();
            } finally {
                count.countDown();
            }
        }
    }
}
