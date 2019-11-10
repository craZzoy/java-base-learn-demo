package com.threadpool;

import java.util.concurrent.*;

public class RejectThreadPoolDemo {
    public static class MyTask implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        //自定义线程池
        ExecutorService service = new ThreadPoolExecutor(
                5, //核心线程数量，即不管是否闲置，线程池中都有的线程数量
                5, //最大线程数
                0L, //闲置时间不限
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString() + " is discard");
                    }
                } //自定义拒绝策略
                );
        for(int i = 0; i < 100; i++){
            service.submit(task);
            Thread.sleep(10);
        }
    }
}
