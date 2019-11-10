package com.concurrent;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    private static Object obj = new Object();

    public static class BadRunnable implements Runnable{

        @Override
        public void run() {
            synchronized (obj){
                System.out.println(Thread.currentThread().getName() + " is running");
                LockSupport.park(this);
                System.out.println(Thread.currentThread().getName() + " end");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new BadRunnable();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        Thread.sleep(100);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
