package com.thinkgem.jeesite.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/11/6.
 */
public class ExchangeTest {
    private static final Exchanger<String> exgr = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String A = "data flow A";
                try {
                    exgr.exchange(A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String B = "data flow B";
                try {
                    String A = exgr.exchange(B);
                    System.out.println(A.equals(B)+"::"+A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        threadPool.shutdown();
    }
}
