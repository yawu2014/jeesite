/**
 * 
 */
package com.thinkgem.jeesite.netty.protobuf;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

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
public class SubReqClientHandler extends ChannelHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for(int i=0;i<10;i++) {
			ctx.write(subReq(i));
		}
		ctx.flush();
	}
	private SubscribeReqProto.SubscribeReq subReq(int i){
		SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		builder.setSubReqId(i);
		builder.setUsername("luck");
		builder.setProductName("Netty Boot for Protobuf");
		ArrayList<String> address = Lists.newArrayList();
		address.add("Liaoning");
		builder.addAllAddress(address);
		return builder.build();
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("Receive server response:["+msg+"]");
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
