package com.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    private static class Soldider implements Runnable{
        private String soldirerName;
        //循环栅栏
        private final CyclicBarrier cyclicBarrier;

        public Soldider(String soldirerName, CyclicBarrier cyclicBarrier) {
            this.soldirerName = soldirerName;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                //等待所有士兵到齐
                //可能会抛出两个异常
                cyclicBarrier.await();
                //System.out.println(cyclicBarrier.getNumberWaiting());

                //模拟士兵做任务
                Thread.sleep(Math.abs(new Random(10).nextInt()%1000));
                System.out.println(soldirerName + " complete the task");

                //等待所有士兵完成任务
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 循环栅栏每次倒数完成后执行的任务
     */
    private static class CyclicBarrierRun implements Runnable{

        private int n;
        private boolean hasDone;

        public CyclicBarrierRun(int n, boolean hasDone) {
            this.n = n;
            this.hasDone = hasDone;
        }

        @Override
        public void run() {
            if (hasDone){
                System.out.println("sir: " + n +" soldiers complete the work...");
            } else {
                System.out.println("sir: " + n +" soldiers assembled...");
                hasDone = true;
            }
        }
    }

    public static void main(String[] args) {
        final int n = 10;
        Thread[] allSoldiers = new Thread[10];
        CyclicBarrier barrier = new CyclicBarrier(n,new CyclicBarrierRun(n,false));

        System.out.println("all soldier gather");
        for(int i = 0; i < n; i++){
            System.out.println("soldier " + i + "check");
            allSoldiers[i] = new Thread(new Soldider("solider " + i, barrier));
            allSoldiers[i].start();

            if(i == 5){
                allSoldiers[i].interrupt();
            }
        }
    }
}
