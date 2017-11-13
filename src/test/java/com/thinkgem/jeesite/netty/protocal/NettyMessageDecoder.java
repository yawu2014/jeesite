/**
 * 
 */
package com.thinkgem.jeesite.netty.protocal;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.HashMap;

import com.google.common.collect.Maps;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

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
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {
	NettyMessageMarshallingDecoder marshallingDecoder;
	/**
	 * <p>
	 * 
	 * </p>
	 * 
	 * @author Lennon.Wang
	 * @date 2017年9月15日 上午11:28:35
	 * @param maxFrameLength
	 * @param lengthFieldOffset
	 * @param lengthFieldLength
	 * @param failFast
	 */
	public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) throws IOException {
		super( maxFrameLength, lengthFieldOffset, lengthFieldLength);
		marshallingDecoder = new NettyMessageMarshallingDecoder();
	}
	
	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		ByteBuf frame = (ByteBuf) super.decode(ctx, in);
		if(frame == null) {
			return null;
		}
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setCrcCode(frame.readInt());
		header.setLength(frame.readInt());
		header.setSessionID(frame.readLong());
		header.setType(frame.readByte());
		header.setPriority(frame.readByte());
		int size = frame.readInt();
		if(size > 0) {
			HashMap<String,Object> attch = Maps.newHashMap();
			int keySize = 0;
			byte[] keyArray = null;
			String key = null;
			for(int i=0;i<size;i++) {
				keySize = frame.readInt();
				keyArray = new byte[keySize];
				frame.readBytes(keyArray);
				key = new String(keyArray,"UTF-8");
				attch.put(key, marshallingDecoder.decode(frame));
			}
			keyArray = null;
			key = null;
			header.setAttachment(attch);
		}
		if(frame.readableBytes() > 4) {
			message.setBody(marshallingDecoder.decode(frame));
		}
		message.setHeader(header);
		return message;
	}

}
