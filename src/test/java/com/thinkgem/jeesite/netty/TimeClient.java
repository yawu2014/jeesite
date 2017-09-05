/**
 * 
 */
package com.thinkgem.jeesite.netty;

import java.io.IOException;
import java.net.Socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
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
public class TimeClient {
	public static void main(String[] args) {
		int port = 9090;
		if(args != null && args.length > 0) {
			try {
			port = Integer.valueOf(args[0]);
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
		}
		Socket socket = null;
//		PrintWriter out = null;
		try {
			//NIO方式
			//new Thread(new TimeClientHandler("127.0.0.1",port),"TimeClient-001").start();
			//NIO2.0
//			new Thread(new AsyncTimeClientHandler("127.0.0.1",port),"AIO-TimeClient-001").start();
			/*socket = new Socket("10.1.2.218",port);
			//读取服务器端数据    
            DataInputStream input = new DataInputStream(socket.getInputStream());    
            //向服务器端发送数据    
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
            String str = "QUERY TIME ORDER";//new BufferedReader(new InputStreamReader(System.in)).readLine();    
            out.writeUTF(str);
                
            String ret = input.readUTF();     
            System.out.println("服务器端返回过来的是: " + ret);    
            input.close();
            out.close();*/
			//使用Netty
			new TimeClient().connect(port, "127.0.0.1");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket = null;
			}
		}
	}
	public void connect(int port, String host) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel arg0) throws Exception {
					arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
					arg0.pipeline().addLast(new StringDecoder());
					arg0.pipeline().addLast(new TimeClientHandlerNetty());
				}
				
			});
			ChannelFuture f = b.connect(host,port).sync();
			f.channel().closeFuture().sync();
		}finally {
			group.shutdownGracefully();
		}
	}
}
