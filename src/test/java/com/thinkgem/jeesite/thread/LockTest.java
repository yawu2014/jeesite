/**
 * 
 */
package com.thinkgem.jeesite.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * <p>
 * 
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年10月13日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class LockTest {
	private static CountDownLatch latch = new CountDownLatch(1);
	/**
	 * <p>
	 * 
	 * </p>
	 * 
	 * @author liuyujian
	 * @date 2017年10月13日 上午11:18:54
	 * @param args
	 */
	public static void main(String[] args) {
		synchronized(lock){
			System.out.println("Main.........start");
			new Thread(new A()).start();//此处不会等待,会运行下一条语句
//			ScheduledThreadPoolExecutor.
			System.out.println("Main.........end");
			latch.countDown();
		}
	}
	private static Object lock = new Object();
	static class A implements Runnable{

		@Override
		public void run() {
			try {
				latch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("AAAAAAAAAAAAA");
		}
		
	}
}
