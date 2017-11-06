package com.thinkgem.jeesite.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2017/11/6.
 */
public class SemaphoreTest {
    private static final int THREA_COUNT = 30;
    private static ExecutorService threadPool =Executors.newFixedThreadPool(THREA_COUNT);
    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for(int i=0;i<THREA_COUNT;i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        s.acquire();
                        System.out.println("save data"+Thread.currentThread().getName());
                        s.release();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPool.shutdown();
    }
}
