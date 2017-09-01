package com.thinkgem.jeesite.concurrent;

import java.util.concurrent.TimeUnit;

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
	public void testStopThread(){
		
	}
}
