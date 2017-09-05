/**
 * 
 */
package com.thinkgem.jeesite.netty;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * <p>
 * 
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年9月1日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class TimeClientHandlerNetty extends ChannelHandlerAdapter {
	private static final Logger logger = Logger.getLogger(TimeClientHandlerNetty.class.getName());
	private byte[] req;
	private int counter;
	public TimeClientHandlerNetty() {
		req = ("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf firstMessage = null;
		for(int i=0;i<100;i++) {
			firstMessage = Unpooled.buffer(req.length);
			firstMessage.writeBytes(req);
			ctx.writeAndFlush(firstMessage);
		}
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		/*ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req,"UTF-8");*/
		String body = (String)msg;
		System.out.println("Now is :"+body+" this counter is:"+ ++counter);
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.warn("Unexpected Exception :"+cause.getMessage());
		ctx.close();
	}
}
