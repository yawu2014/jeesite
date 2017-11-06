package com.thinkgem.jeesite.thread;

/**
 * Created by Administrator on 2017/10/26.
 */
public interface ThreadPool<Job extends Runnable> {
    void execute(Job job);
    void shutdown();
    void addWorkers(int num);
    int getJobSize();
}
