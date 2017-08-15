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

import java.nio.ByteBuffer;
import java.util.Arrays;

//helper for construct MySQL send row
//Now only support text protocol
public class MysqlRowBuffer {
	private byte[] buf = new byte[4096];
	private int posIndex;
	private int bufIndex;
	private int bufSize;
	
	public ByteBuffer getRow() {
		return ByteBuffer.wrap(buf);
	}

	// the first byte:
	// <= 250: length
	// = 251: NULL
	// = 252: the next two byte is length
	// = 253: the next three byte is length
	// = 254: the next eighth byte is length
	private int packVlen(int packetIndex, long length) {
	    if (length < 251) {
	        int1store(packetIndex, length);
	        return packetIndex + 1;
	    }

	    /* 251 is reserved for NULL */
	    if (length < 65536) {
	    	buf[packetIndex] = (byte)(252);
	    	packetIndex ++;
	        int2store(packetIndex, length);
	        return packetIndex + 2;
	    }

	    if (length < 16777216) {
	        buf[packetIndex] = (byte)(253);
	        packetIndex ++;
	        int3store(packetIndex, length);
	        return packetIndex + 3;
	    }

        buf[packetIndex] = (byte)(254);
        packetIndex ++;
	    int8store(packetIndex, length);
	    return packetIndex + 8;
	}
	
	public void pushString(String data) {
	    // 9 for length pack max, 1 for sign, other for digits
		if (data == null) {
			throw new RuntimeException("input string is NULL.");
		}

	    reserve(9 + data.length());

	    posIndex =  packVlen(posIndex, (long)data.length());
	    System.arraycopy(data.getBytes(), 0, buf, posIndex, data.length());
	    posIndex += data.length();
	}
	
	public void pushInt(int data) throws RuntimeException {
	    // 1 for string trail, 1 for length, 1 for sign, other for digits
	    reserve(3 + MysqlGlobal.MAX_INT_WIDTH);
	    
	    byte[] srcBytes = String.valueOf(data).getBytes();
	    int copyLenght = MysqlGlobal.MAX_INT_WIDTH + 2;
	    // srcBytes.length must be less then copyLengh
	    if (srcBytes.length > copyLenght) {
	    	throw new RuntimeException("src length is larger max");
	    }
	    
	    System.arraycopy(srcBytes, 0, buf, posIndex + 1, srcBytes.length);
	    
	    // TODO(dhc): do we need to fill zeror to (copyLenght - srcBytes.length)
	    int1store(posIndex, srcBytes.length);
	    posIndex += copyLenght + 1;
	}
	
	void int1store(int index, long value) {
		byte b = (byte)value;
		buf[index] = b;
	}
	
	void int2store(int index, long value) {
		byte b1 = (byte)(value & 0xff);
		byte b2 = (byte)((value >> 8) & 0xff);
		buf[index] = b1;
		buf[index + 1] = b2;
	}
	
	void int3store(int index, long value) {
		byte b1 = (byte)(value & 0xff);
		byte b2 = (byte)((value >> 8) & 0xff);
		byte b3 = (byte)((value >> 16) & 0xff);
		buf[index] = b1;
		buf[index + 1] = b2;
		buf[index + 2] = b3;
	}
	
	void int8store(int index, long value) {
		buf[index] = (byte)(value & 0xff);
		buf[index + 1] = (byte)((value >> 8) & 0xff);
		buf[index + 2] = (byte)((value >> 16) & 0xff);
		buf[index + 3] = (byte)((value >> 24) & 0xff);
		buf[index + 4] = (byte)((value >> 16) & 0xff);
		buf[index + 5] = (byte)((value >> 24) & 0xff);
		buf[index + 6] = (byte)((value >> 16) & 0xff);
		buf[index + 7] = (byte)((value >> 24) & 0xff);
	}
	
	// need more size
	public void reserve(int size) throws RuntimeException {
	    if (size < 0) {
	        throw new RuntimeException("alloc memory failed. size = "  + size);
	    }

	    int needSize = size + posIndex;
	    if (needSize <= bufSize) {
	        return;
	    }

	    int allocSize = Math.max(needSize, bufSize * 2);
	    byte[] newBuf = new byte[allocSize];

	    System.arraycopy(buf, 0, newBuf, 0, posIndex);
	    
	    buf = newBuf;
	    bufSize = allocSize;
	}
}
