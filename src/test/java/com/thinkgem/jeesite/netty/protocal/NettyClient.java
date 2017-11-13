/**
 * 
 */
package com.thinkgem.jeesite.netty.protocal;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

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
public class NettyClient {
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	EventLoopGroup group = new NioEventLoopGroup();
	public void connect(int port,String host) throws Exception{
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new NettyMessageDecoder(1024*1024,4,4));
					ch.pipeline().addLast("MessageEncoder",new NettyMessageEncoder());
					ch.pipeline().addLast("readTimeoutHandler",new ReadTimeoutHandler(NettyConstant.READTIMEOUT));
					ch.pipeline().addLast("LoginAuthHandler",new LoginAuthReqHandler());
					ch.pipeline().addLast("HeartBeatHandler",new HeartBeatReqHandler());
				}
				
			});
			ChannelFuture future = b.connect(new InetSocketAddress(host,port),new InetSocketAddress(NettyConstant.LOCALIP,NettyConstant.LOCAL_PORT)).sync();
			future.channel().closeFuture().sync();
		}finally {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(5);
						try {
							connect(NettyConstant.PORT,NettyConstant.REMOTEIP);
						}catch(Exception e) {
							e.printStackTrace();
						}
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			});
		}
	}
	/**
	 * <p>
	 * 
	 * </p>
	 * 
	 * @author liuyujian
	 * @date 2017年9月15日 下午2:52:25
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		new NettyClient().connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
	}

}
