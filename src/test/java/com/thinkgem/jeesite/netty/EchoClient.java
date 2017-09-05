/**
 * 
 */
package com.thinkgem.jeesite.netty;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

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
public class EchoClient {

	/**
	 * <p>
	 * 
	 * </p>
	 * 
	 * @author liuyujian
	 * @date 2017年9月1日 下午4:55:03
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		int port = 9090;
		if(args != null && args.length > 0) {
			port = Integer.valueOf(args[0]);
		}
		new EchoClient().connect(port,"127.0.0.1");
	}
	public void connect(int port, String host) throws Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true).handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
					// 使用特殊分隔符分割消息
//					ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
//					ch.pipeline().addLast(new FixedLengthFrameDecoder(20));
//					ch.pipeline().addLast(new StringDecoder());
					ch.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
					ch.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
					ch.pipeline().addLast(new EchoClientHandler());
				}
				
			});
			ChannelFuture f = b.connect(host, port).sync();
			f.channel().closeFuture().sync();
		}finally {
			group.shutdownGracefully();
		}
	}
	
}
