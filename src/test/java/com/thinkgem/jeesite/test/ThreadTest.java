package com.thinkgem.jeesite.test;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;


public class ThreadTest {
	private int temp = 0;
	/**
	 * 在执行run方法时,此时的temp变量的值的位置决定了run方法中temp的值,run执行的时间不是按照代码顺序执行的
	 */
	@Test
	public void testThread(){
		Runnable run = new Runnable() {
			private int mTemp;
			@Override
			public void run() {
				mTemp = temp;
				System.out.println("result:"+mTemp);
			}
		};
		new Thread(run).start();
		//此处记过都会是2,上一句的执行可能会在这句赋值之后执行
//		temp = 2;
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//此处结果会是0,2,经过3秒的休眠,上一个子进程已经结束
//		temp = 2;
		new Thread(run).start();
	}
	
	@Test
	public void futherexe() {
		Callable ctt = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				int i = (int) (System.currentTimeMillis()%2);
				System.out.println(Thread.currentThread().getName()+i);
				return i;
			}
		};
		Objects.compare("a", "b",null);
		FutureTask<Integer> ft = new FutureTask<>(ctt);
		System.out.println("xxx");
	}
}
