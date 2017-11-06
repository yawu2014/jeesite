package com.thinkgem.jeesite.thread;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.google.common.collect.Lists;
public class PrimeGenerator implements Runnable {
	private final List<BigInteger> primes = Lists.newArrayList();
	private volatile boolean canceled;
	private final BlockingQueue<BigInteger> queue ;//= new BlockingQueue<BigInteger>();
	@Override
	public void run() {
		BigInteger p = BigInteger.ONE;
		while(!canceled){
			p = p.nextProbablePrime();
			synchronized(this){
				primes.add(p);
			}
		}
	}
	public PrimeGenerator(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}
	public void cancel(){
		canceled = true;
	}
	public synchronized List<BigInteger> get(){
		return new ArrayList<BigInteger>(primes);
	}
	
	public void aSecondOfPrimes() throws InterruptedException{
		BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<BigInteger>(5);		
		PrimeGenerator generator = new PrimeGenerator(queue);
		new Thread(generator).start();
		try{
//			SECONDS.sleep(1);
			
		}finally{
			generator.cancel();
		}
		generator.get();
	}
}
