/**
 * 
 */
package com.thinkgem.jeesite.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

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
public class AsyncTimeServerHandler implements Runnable {
	private int port;
	CountDownLatch latch;
	AsynchronousServerSocketChannel asynchronousServerChannel;
	public AsyncTimeServerHandler(int port) {
		this.port = port;
		try {
			asynchronousServerChannel = AsynchronousServerSocketChannel.open();
			asynchronousServerChannel.bind(new InetSocketAddress(port));
			System.out.println("Time Server start in port :"+port);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		latch = new CountDownLatch(1);
		doAccept();
		try {
			latch.await();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void doAccept() {
		asynchronousServerChannel.accept(this,new AcceptCompletionHandler());
	}
}
class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,AsyncTimeServerHandler> {

	@Override
	public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
		attachment.asynchronousServerChannel.accept(attachment,this);
	}

	@Override
	public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
		exc.printStackTrace();
		attachment.latch.countDown();
	}

}
