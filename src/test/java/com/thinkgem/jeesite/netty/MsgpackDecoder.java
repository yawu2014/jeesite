/**
 * 
 */
package com.thinkgem.jeesite.netty;

import java.util.List;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

/**
 * <p>
 * 
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年9月4日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {

	@Override
	protected void decode(ChannelHandlerContext arg0, ByteBuf arg1, List<Object> arg2) throws Exception {
		final byte[] array;
		final int length = arg1.readableBytes();
		array = new byte[length];
		arg1.getBytes(arg1.readerIndex(),array,0,length);
		MessagePack msgpack = new MessagePack();
		arg2.add(msgpack.read(array));
	}

}
