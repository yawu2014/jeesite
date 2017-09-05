/**
 * 
 */
package com.thinkgem.jeesite.netty;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * <p>
 * 
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年8月31日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class TimeServer {
	
	public static void main(String[] args) throws Exception {
		int port = 9090;
		if(args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
		}
		ServerSocket server = null;
		try {
//			server = new ServerSocket(port);
			System.out.println("TimeServer start at port:"+port);
//			Socket socket = null;
			/*//每次连接单独创建线程
			while(true) {
				socket = server.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}*/
			//使用线程池
			/*TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50,1000);
			while(true) {
				socket = server.accept();
				singleExecutor.execute(new TimeServerHandler(socket));
			}*/
			//使用NIO
//			MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
//			new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();
			//使用NIO2.0 AIO
//			AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
//			new Thread(timeServer,"AIO-AsyncTimeServerHandler-001").start();
			//使用Netty
			new TimeServer().bind(port);
		}finally {
			if(server != null) {
				System.out.println("TimeServer close");
				server.close();
				server = null;
			}
		}
	}
	
	public void bind(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG,1024).childHandler(new ChildChannelHandler());
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		}finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
			arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
			arg0.pipeline().addLast(new StringDecoder());
			arg0.pipeline().addLast(new TimeServerHandlerNetty());
		}
	}
}
