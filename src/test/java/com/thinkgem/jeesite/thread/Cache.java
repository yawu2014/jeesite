package com.thinkgem.jeesite.thread;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2017/10/31.
 */
public class Cache {
    static Map<String,Object> map;
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();
    public static final Object get(String key){
        r.lock();
        try{
            return map.get(key);
        }finally {
            r.unlock();
        }
    }
    public static final Object pub(String key,Object value){
        w.lock();
        try{
            return map.put(key,value);
        }finally{
            w.unlock();
        }
    }
    public static final void clean(){
        w.lock();
        try{
            map.clear();
        }finally {
            w.unlock();
        }
    }
}
