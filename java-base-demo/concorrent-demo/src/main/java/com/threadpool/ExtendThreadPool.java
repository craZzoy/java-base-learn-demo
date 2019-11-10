package com.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExtendThreadPool {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService service = new ThreadPoolExecutor(
                5,
                5,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>()
        ){
            @Override
            public void execute(Runnable command) {
                super.execute(command);
            }

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("before " + t.getName() + " execute");
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("after " + Thread.currentThread().getName() + " execute");
            }

            @Override
            protected void terminated() {
                System.out.println("thread pool is terminated");
            }
        };

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " is executing");

        };

        for(int i = 0; i < 5; i++){
            //service.submit(task);
            service.execute(task);
            Thread.sleep(50);
        }
        //设置线程池关闭信号，非阻塞关闭线程池
        service.shutdown();
    }
}
