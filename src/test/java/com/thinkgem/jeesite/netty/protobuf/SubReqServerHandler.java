/**
 * 
 */
package com.thinkgem.jeesite.netty.protobuf;

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
public class SubReqServerHandler extends ChannelHandlerAdapter{
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq)msg;
		if("luck".equalsIgnoreCase(req.getUsername())) {
			System.out.println("service receive client subscribe:["+req.toString()+"]");
			ctx.writeAndFlush(resp(req.getSubReqId()));
		}
	}
	private SubscribeRespRroto.SubscribeResp resp(int subReqId){
		SubscribeRespRroto.SubscribeResp.Builder builder = SubscribeRespRroto.SubscribeResp.newBuilder();
		builder.setSubReqID(subReqId);
		builder.setRespCode(0);
		builder.setDesc("Netty Boot ordered succeed");
		return builder.build();
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
