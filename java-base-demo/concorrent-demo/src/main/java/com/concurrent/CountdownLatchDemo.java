package com.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchDemo implements Runnable{
    private static final CountDownLatch countdownLatch = new CountDownLatch(5);
    @Override
    public void run() {
        try {
            //模拟检查任务
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("complete check");
            countdownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountdownLatchDemo demo = new CountdownLatchDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++){
            executorService.submit(demo);
        }
        //不中断的情况下，当前线程等待countdownLatch计数为0才继续执行
        countdownLatch.await();
        System.out.println("Fire!");
        executorService.shutdown();
    }
}
