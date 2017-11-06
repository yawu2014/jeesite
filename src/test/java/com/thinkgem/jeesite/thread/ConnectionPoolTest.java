package com.thinkgem.jeesite.thread;


import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/10/26.
 */
public class ConnectionPoolTest {
    static ConnectPool pool = new ConnectPool(10);
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        int threadCount = 20;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGet = new AtomicInteger();
        for(int i=0;i<threadCount;i++){
            Thread thread = new Thread(new ConnectionRunner(count,got,notGet),"ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke:"+(threadCount*count));
        System.out.println("got connection:"+got);
        System.out.println("not got connection"+notGet);
    }
    static class ConnectionRunner implements Runnable{
        private int count;
        private AtomicInteger got;
        private AtomicInteger notGet;
        public ConnectionRunner(int count,AtomicInteger got,AtomicInteger notGet){
            this.count = count;
            this.got = got;
            this.notGet = notGet;
        }

        @Override
        public void run() {
            try{
                start.await();
            }catch(Exception e){

            }
            while(count > 0){
                try{
                    Connection connection = pool.fetchConnection(1000);
                    if(connection != null){
                        try{
                            connection.createStatement();connection.commit();
                        }finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else{
                        notGet.incrementAndGet();
                    }
                }catch (Exception e){

                }finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
