/**
 * 
 */
package com.thinkgem.jeesite.netty;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * <p>
 * 共Netty使用的HandlerAdapter
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年9月1日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class TimeServerHandlerNetty extends ChannelHandlerAdapter {
	private int counter;
	public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception {
		/*ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req,"UTF-8").substring(0,req.length-System.getProperty("line.separator").length());*/
		String body = (String)msg;
		System.out.println("Time Server receive order:"+body+" this counter is:"+ ++counter);
		String currentTime = "QUERY TIME ORDER".equals(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
		currentTime += System.getProperty("line.separator");
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.writeAndFlush(resp);
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
