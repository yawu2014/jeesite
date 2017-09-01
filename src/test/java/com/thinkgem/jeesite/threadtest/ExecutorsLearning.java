/**
 * 
 */
package com.thinkgem.jeesite.threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerArray;

import org.junit.Test;

/**
 * <p>
 * 
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年8月21日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class ExecutorsLearning {
	@Test
	public void testExecutors() {
		ExecutorService service = Executors.newFixedThreadPool(4);
		service.submit(()->{System.out.println("xxx");});
	}
	private AtomicIntegerArray aia = new AtomicIntegerArray(3);
	@Test
	public void testAtomic() {
		aia.addAndGet(1, 2);
	}
}
