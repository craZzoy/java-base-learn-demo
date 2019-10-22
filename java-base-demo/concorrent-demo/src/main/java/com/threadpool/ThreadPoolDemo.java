package com.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

    public static class EchoTask implements Runnable{

        @Override
        public void run() {
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println(System.currentTimeMillis()
                    +" " + Thread.currentThread().getName() + " running");
        }
    }

    public static void main(String[] args) {
        EchoTask task = new EchoTask();
        //创建一个容量固定为5的线程池
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService executorService = Executors.newCachedThreadPool();
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 20; i++){
            executorService.submit(task);
        }
    }
}
