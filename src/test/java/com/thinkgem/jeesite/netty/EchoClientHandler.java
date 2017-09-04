/**
 * 
 */
package com.thinkgem.jeesite.netty;

import com.google.common.util.concurrent.UncaughtExceptionHandlers;

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
public class EchoClientHandler extends ChannelHandlerAdapter {
	private int counter;
	static final String ECHO_REQ = "Hi,welcome to Netty ";
	private volatile int sendNumber = 10;
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		/*for(int i=0;i<100;i++) {
			ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
		}*/
		UserInfo[] userInfos = userInfo();
		for(UserInfo info :userInfos) {
			ctx.write(info);
		}
		ctx.flush();
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("This is "+ ++counter+" times receive server:["+msg+"]");
		ctx.write(msg);
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
	private UserInfo[] userInfo() {
		UserInfo[] userInfos = new UserInfo[sendNumber];
		for(int i=0;i<sendNumber;i++) {
			UserInfo info = new UserInfo();
			info.setAge(i);
			info.setName("ABCDEFG--->"+i);
			userInfos[i] = info;
		}
		
		return userInfos;
	}
}
