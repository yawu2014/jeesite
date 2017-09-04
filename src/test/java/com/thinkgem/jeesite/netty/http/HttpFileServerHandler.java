/**
 * 
 */
package com.thinkgem.jeesite.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

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
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		if(!request.getDecoderResult().isSuccess()) {
//			sendError(ctx,BAD_REQUEST);
			return;
		}
	}
	public HttpFileServerHandler(String url) {
		
	}
}
