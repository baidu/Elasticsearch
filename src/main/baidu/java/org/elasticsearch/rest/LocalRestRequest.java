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

import org.apache.http.entity.ContentType;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;
import org.elasticsearch.common.bytes.BytesArray;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.rest.support.RestUtils;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashMap;
import java.util.Map;

public class LocalRestRequest extends RestRequest {

    private final Map<String, String> headers;
    private final String uri;
    private final String rawPath;
    private BytesReference content;
    private final Method method;
    private final Map<String, String> params;

    public LocalRestRequest(final String uri, final Method method) {
        this.headers = new HashMap<>();
        if (uri.startsWith("\"") && uri.endsWith("\"")) {
            this.uri = uri.substring(1, uri.length()-1);
        } else {
            this.uri = uri;
        }
        this.method = method;
        this.params = new HashMap<>();
        this.content = BytesArray.EMPTY;

        int pathEndPos = this.uri.indexOf('?');
        if (pathEndPos < 0) {
            this.rawPath = this.uri;
        } else {
            this.rawPath = this.uri.substring(0, pathEndPos);
            RestUtils.decodeQueryString(this.uri, pathEndPos + 1, params);
        }
    }

    public void addHeader(final String key, final String value) {
        this.headers.put(key, value);
    }

    public void setContent(final String source, final ContentType contentType) throws UnsupportedCharsetException {
        Args.notNull(source, "Source string");
        Charset charset = contentType != null?contentType.getCharset():null;
        if(charset == null) {
            charset = HTTP.DEF_CONTENT_CHARSET;
        }

        try {
            this.content = new BytesArray(source.getBytes(charset.name()));
        } catch (UnsupportedEncodingException var) {
            throw new UnsupportedCharsetException(charset.name());
        }

        if(contentType != null) {
            addHeader("Content-Type", contentType.toString());
        }
    }

    public LocalRestRequest addParameter(final String key, final String value) {
        this.params.put(key, value);
        return this;
    }

    @Override
    public RestRequest.Method method() {
        return method;
    }

    @Override
    public String uri() {
        return uri;
    }

    @Override
    public String rawPath() {
        return rawPath;
    }

    @Override
    public Map<String, String> params() {
        return params;
    }

    @Override
    public boolean hasContent() {
        return content.length() > 0;
    }

    @Override
    public BytesReference content() {
        return content;
    }

    @Override
    public SocketAddress getRemoteAddress() {
        InetSocketAddress socketAddress;
        try {
            socketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 4300);
        } catch (UnknownHostException e) {
            return null;
        }
        return socketAddress;
    }

    @Override
    public SocketAddress getLocalAddress() {
        InetSocketAddress socketAddress;
        try {
            socketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 4300);
        } catch (UnknownHostException e) {
            return null;
        }
        return socketAddress;
    }

    @Override
    public String header(String name) {
        return headers.get(name);
    }

    @Override
    public Iterable<Map.Entry<String, String>> headers() {
        return headers.entrySet();
    }

    @Override
    public boolean hasParam(String key) {
        return params.containsKey(key);
    }

    @Override
    public String param(String key) {
        return params.get(key);
    }

    @Override
    public String param(String key, String defaultValue) {
        String value = params.get(key);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }
}
