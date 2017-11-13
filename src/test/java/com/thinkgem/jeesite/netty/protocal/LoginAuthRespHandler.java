/**
 * 
 */
package com.thinkgem.jeesite.netty.protocal;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

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
public class LoginAuthRespHandler extends ChannelHandlerAdapter {
	private Map<String,Boolean> nodeCheck = new ConcurrentHashMap<String,Boolean>();
	private String[] whiteList = {"127.0.0.1","10.1.2.218"};
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage)msg;
		if(message.getHeader() != null&&message.getHeader().getType()==MessageType.LOGIN_REQ.value()) {
			String nodeIndex = ctx.channel().remoteAddress().toString();
			NettyMessage loginResp = null;
			if(nodeCheck.containsKey(nodeIndex)) {
				loginResp = buildResponse((byte)-1);
			}else {
				InetSocketAddress address = (InetSocketAddress)ctx.channel().remoteAddress();
				String ip = address.getAddress().getHostAddress();
				boolean isOK = false;
				for(String WIP:whiteList) {
					if(WIP.equals(ip)) {
						isOK = true;
						break;
					}
				}
				loginResp = isOK?buildResponse((byte)0):buildResponse((byte)-1);
				if(isOK) {
					nodeCheck.put(nodeIndex, true);
				}
			}
			System.out.println("The login response is:["+loginResp+"]");
			ctx.writeAndFlush(loginResp);
		}else {
			ctx.fireChannelRead(msg);
		}
	}
	private NettyMessage buildResponse(byte result) {
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setType(MessageType.LOGIN_RESP.value());
		message.setHeader(header);
		message.setBody(result);
		return message;
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		nodeCheck.remove(ctx.channel().remoteAddress().toString());
		ctx.close();
		ctx.fireExceptionCaught(cause);
	}
}
