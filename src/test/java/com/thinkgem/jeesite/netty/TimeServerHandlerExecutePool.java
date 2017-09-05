/**
 * 
 */
package com.thinkgem.jeesite.netty;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
public class TimeServerHandlerExecutePool {
	private ExecutorService executor;
	public TimeServerHandlerExecutePool(int maxPoolSize,int queueSize) {
		executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSize,120L,TimeUnit.SECONDS,new ArrayBlockingQueue<java.lang.Runnable>(queueSize));
	}
	public void execute(java.lang.Runnable task) {
		executor.execute(task);
	}
}
