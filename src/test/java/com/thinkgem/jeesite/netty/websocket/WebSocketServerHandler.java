/**
 * 
 */
package com.thinkgem.jeesite.netty.websocket;

import java.time.LocalDate;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.CharsetUtil;

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
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
//	private WebSocketServerhandshaker handshaker;
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof FullHttpRequest) {
			handleHttpRequest(ctx,(FullHttpRequest)msg);
		}else if(msg instanceof WebSocketFrame) {
			handleWebSocketFrame(ctx,(WebSocketFrame)msg);
		}
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	private void handleHttpRequest(ChannelHandlerContext ctx,FullHttpRequest req) throws Exception{
		if(!req.getDecoderResult().isSuccess()||(!"websocket".equals(req.headers().get("Upgrade")))) {
//			sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HTTP_1_1,BAD_REQUEST));
			return;
		}
//		WebSocketServerhandshakerFactory wsFactory = new WebSocketServerhandsharkerFactory("",null,false);
		
	}
	private void handleWebSocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame) {
		if(frame instanceof CloseWebSocketFrame) {
			return;
		}
		if(frame instanceof PingWebSocketFrame) {
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		if(!(frame instanceof TextWebSocketFrame)) {
			throw new UnsupportedOperationException(String.format("%s frame is not supported", frame.getClass().getName()));
		}
		String request = ((TextWebSocketFrame) frame).text();
		ctx.channel().write(new TextWebSocketFrame(request+"欢迎使用websocket服务,现在时刻:"+LocalDate.now().getDayOfYear()));
	}
	private static void sendHttpResponse(ChannelHandlerContext ctx,FullHttpRequest req,FullHttpResponse res) {
		if(res.getStatus().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(),CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
//			setContentLength(res,res.content().readableBytes());
		}
		ChannelFuture f = ctx.channel().writeAndFlush(res);
		/*if(!isKeepAlive(req) || res.getStatus().code() != 200) {
			f.addListener(ChannelFutureListener.CLOSE);
		}*/
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
