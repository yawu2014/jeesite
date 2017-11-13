/**
 * 
 */
package com.thinkgem.jeesite.netty.protocal;

import java.io.IOException;

import org.jboss.marshalling.ByteInput;

import io.netty.buffer.ByteBuf;

/**
 * <p>
 * 
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年9月15日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class ChannelBufferByteInput implements ByteInput {
	private final ByteBuf buffer;
	public ChannelBufferByteInput(ByteBuf buffer) {
		this.buffer = buffer;
	}
	@Override
	public void close() throws IOException {

	}

	@Override
	public int read() throws IOException {
		if(buffer.isReadable()) {
			return buffer.readByte()&0xff;
		}
		return -1;
	}

	@Override
	public int read(byte[] b) throws IOException {
		return read(b,0,b.length);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int available = available();
		if(available == 0) {
			return -1;
		}
		len = Math.min(available, len);
		buffer.readBytes(b,off,len);
		return len;
	}

	@Override
	public int available() throws IOException {
		return buffer.readableBytes();
	}

	@Override
	public long skip(long n) throws IOException {
		int readable = buffer.readableBytes();
		if(readable < n) {
			n = readable;
		}
		buffer.readerIndex((int)(buffer.readerIndex()+n));
		return 0;
	}

}
