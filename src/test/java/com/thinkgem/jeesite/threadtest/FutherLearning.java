/**
 * 
 */
package com.thinkgem.jeesite.threadtest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.http.annotation.GuardedBy;
import org.junit.Test;

/**
 * <p>
 * 
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年8月19日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class FutherLearning {
	
	@Test
	public void futherexe() {
		FutureTask<Integer> ft = new FutureTask<>(() -> {
			System.out.println(Thread.currentThread().getName());
			return 100;
		}) ;
		new Thread(ft,"first").start();
		try {
			System.out.println(Thread.currentThread().getName()+":"+ft.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	@GuardedBy("this")
	private int value;
}
