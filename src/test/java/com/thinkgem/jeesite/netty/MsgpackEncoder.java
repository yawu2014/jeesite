/**
 * 
 */
package com.thinkgem.jeesite.netty;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * <p>
 * 使用MessagePack编码
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年9月4日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext arg0, Object arg1, ByteBuf arg2) throws Exception {
		MessagePack msgPack = new MessagePack();
		byte[] raw = msgPack.write(arg1);
		arg2.writeBytes(raw);
	}

}
