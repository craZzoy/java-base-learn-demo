package com.threadpool;

import java.util.concurrent.*;

public class ThreadFactoryDemo {
    public static class MyTask implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        ExecutorService service = new ThreadPoolExecutor(
                5,
                5,
                0L,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                r -> {
                    Thread t = new Thread(r);
                    //设为守护线程
                    System.out.println("create thread:"+t.getName());
                    t.setDaemon(true);
                    return t;
                }
        );
        for (int i = 0; i < 5; i++){
            service.submit(task);
        }
        Thread.sleep(500);
    }
}
