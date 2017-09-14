package com.thinkgem.jeesite.thread;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
public class PrimeGenerator implements Runnable {
	private final List<BigInteger> primes = Lists.newArrayList();
	private volatile boolean canceled;
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
	public void cancel(){
		canceled = true;
	}
	public synchronized List<BigInteger> get(){
		return new ArrayList<BigInteger>(primes);
	}
	
	public void aSecondOfPrimes() throws InterruptedException{
		PrimeGenerator generator = new PrimeGenerator();
		new Thread(generator).start();
		try{
//			SECONDS.sleep(1);
			
		}finally{
			generator.cancel();
		}
		generator.get();
	}
}
