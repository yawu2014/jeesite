package com.thinkgem.jeesite.thread;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by Administrator on 2017/10/26.
 */
public class ConnectPool {
    private LinkedList<Connection> pool = new LinkedList<Connection>();
    public ConnectPool(int initialSize){
        if(initialSize > 0){
            for(int i=0;i<initialSize;i++){
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }
    public void releaseConnection(Connection connection){
        if(connection != null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }
    public Connection fetchConnection(int mills) throws InterruptedException{
        synchronized (pool){
            if(mills <= 0){
                if(pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                long future = System.currentTimeMillis()+mills;
                long remains = mills;
                while(pool.isEmpty() && remains > 0){
                    pool.wait();
                    remains = future - System.currentTimeMillis();
                }
                Connection result = null;
                if(!pool.isEmpty()){
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
