package com.thinkgem.jeesite.concurrent;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class StopThread {
	private static boolean stopRequest;
	public static void main(String[] args) throws InterruptedException {
		Thread backgroundThread = new Thread(new Runnable(){

			@Override
			public void run() {
				int i=0;
				if(!stopRequest){
					i++;
				}
			}
			
		});
		backgroundThread.start();
		TimeUnit.SECONDS.sleep(1);
		stopRequest = true;
	}
	@Test
	public void testStopThread() throws InterruptedException, ExecutionException{
		ExecutorService executor = Executors.newCachedThreadPool();
		CompletionService service = new ExecutorCompletionService(executor);
		service.submit(null);
		Future<StopThread> future = service.take();
		future.get();
		AtomicInteger aInteger = new AtomicInteger();
		aInteger.compareAndSet(1, 1);
		aInteger.addAndGet(2);
	}
}
