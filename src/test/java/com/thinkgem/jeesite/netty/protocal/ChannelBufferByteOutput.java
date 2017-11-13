/**
 * 
 */
package com.thinkgem.jeesite.netty.protocal;

import java.io.IOException;

import org.jboss.marshalling.ByteOutput;

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
public class ChannelBufferByteOutput implements ByteOutput {
	private final ByteBuf buffer;
	public ChannelBufferByteOutput(ByteBuf buffer) {
		this.buffer = buffer;
	}
	@Override
	public void close() throws IOException {

	}

	@Override
	public void flush() throws IOException {

	}

	@Override
	public void write(int b) throws IOException {
		buffer.writeByte(b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		buffer.writeBytes(b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		buffer.writeBytes(b,off,len);
	}
	ByteBuf getBuffer() {
		return buffer;
	}
}
