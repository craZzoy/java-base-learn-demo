package com.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 */
public class SemaphoreDemo implements Runnable{
    //5个许可
    public static final Semaphore semaphore = new Semaphore(5);
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Runnable semaphoreDemo = new SemaphoreDemo();
        for(int i = 0; i < 20; i++){
            executorService.submit(semaphoreDemo);
        }

    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            //模拟耗时
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " done");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
