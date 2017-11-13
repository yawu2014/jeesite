/**
 * 
 */
package com.thinkgem.jeesite.test;

import java.lang.ref.*;
import java.util.*;

/**
 * <p>
 * 强引用/软引用/弱引用/虚引用 使用demo
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年10月9日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
class Grocery{
	private static final int SIZE=1000;
	private double[] d=new double[SIZE];
	private String id;
	public Grocery(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return this.id;
	}
	@Override
	protected void finalize() throws Throwable {
		System.out.println("Finalizing..."+id);
	}
}
public class TestReference {
	
	private static ReferenceQueue<Grocery> rq = new ReferenceQueue<Grocery>();
	public static void checkQueue() {
		Reference<? extends Grocery> inq = rq.poll();
		if(inq != null) {
			System.out.println("In queue"+inq.get());
		}
	}
	/**
	 * <p>
	 * 
	 * </p>
	 * 
	 * @author liuyujian
	 * @date 2017年10月9日 下午5:58:48
	 * @param args
	 */
	public static void main(String[] args) {
		final int size = 10;
		Set<SoftReference<Grocery>> sa = new HashSet<SoftReference<Grocery>>();
		for(int i=0;i<size;i++) {
			SoftReference<Grocery> ref = new SoftReference<Grocery>(new Grocery("soft:"+i),rq);
			System.out.println("Just created soft:"+ref.get());
			sa.add(ref);
		}
		System.gc();
		checkQueue();
		// 创建10个Grocery对象以及10个弱引用  
        Set<WeakReference<Grocery>> wa = new HashSet<WeakReference<Grocery>>();  
        for (int i = 0; i < size; i++) {  
            WeakReference<Grocery> ref = new WeakReference<Grocery>(  
                    new Grocery("Weak " + i), rq);  
            System.out.println("Just created weak: " + ref.get());  
            wa.add(ref);  
        }  
        System.gc();  
        checkQueue();  
        // 创建10个Grocery对象以及10个虚引用  
        Set<PhantomReference<Grocery>> pa = new HashSet<PhantomReference<Grocery>>();  
        for (int i = 0; i < size; i++) {  
            PhantomReference<Grocery> ref = new PhantomReference<Grocery>(  
                    new Grocery("Phantom " + i), rq);  
            System.out.println("Just created phantom: " + ref.get());  
            pa.add(ref);  
        }  
        System.gc();  
        checkQueue(); 
	}

}
