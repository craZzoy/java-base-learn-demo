package com.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutor =
                Executors.newScheduledThreadPool(10);
        /*ScheduledExecutorService scheduledExecutor =
                Executors.newSingleThreadScheduledExecutor();*/
        scheduledExecutor.scheduleWithFixedDelay(
                () -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()
                            +" "+System.currentTimeMillis()/1000);
                },
                0, //第一个线程执行延时时间
                3, //延时多久执行下一次任务
                TimeUnit.SECONDS
        );

        /*scheduledExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()
                +" at " + System.currentTimeMillis()/1000 + " running");
            }
        },
                2,//延时时间
                TimeUnit.SECONDS);*/
    }
}
