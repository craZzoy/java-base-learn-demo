package com.concurrent;

import com.sun.org.apache.bcel.internal.generic.ObjectType;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockDemo implements Runnable{
    public static Lock reenterLock = new ReentrantLock();
    public static int i = 0;
    public static void main(String[] args) throws InterruptedException {
        //可重入锁
        /*ReenterLockDemo runnable = new ReenterLockDemo();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);*/

        //可中断ReentrantLock
        /*InterruptLock interruptLock1 = new InterruptLock(1);
        InterruptLock interruptLock2 = new InterruptLock(2);
        Thread t1 = new Thread(interruptLock1);
        Thread t2 = new Thread(interruptLock2);
        t1.start();
        t2.start();
        Thread.sleep(5000);
        t2.interrupt();*/

        //死锁
        /*DeadLock deadLock1 = new DeadLock();
        DeadLock deadLock2 = new DeadLock();
        Thread t1 = new Thread(deadLock1,"t1");
        Thread t2 = new Thread(deadLock2,"t2");
        t1.start();
        t2.start();
        t2.interrupt();*/

        //限时获得锁
        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        for(int j = 0; j < 100000; j++){
            reenterLock.lock();
            //可重入，获得一次，关闭一次
            //reenterLock.lock();
            try{
                i++;
            }finally {
                reenterLock.unlock();
                //reenterLock.unlock();
            }
        }
    }


    static class InterruptLock implements Runnable{
        public static ReentrantLock lock1 = new ReentrantLock();
        public static ReentrantLock lock2 = new ReentrantLock();
        //控制加锁顺序
        int lockOrder;

        public InterruptLock(int lockOrder) {
            this.lockOrder = lockOrder;
        }

        @Override
        public void run() {
            try{
                if(lockOrder == 1){
                    //可中断
                    lock1.lockInterruptibly();
                    Thread.sleep(2000);
                    lock2.lockInterruptibly();
                }else{
                    lock2.lockInterruptibly();
                    Thread.sleep(2000);
                    lock1.lockInterruptibly();
                }
                System.out.println(Thread.currentThread().getName() + " end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //当前线程持有这个锁
                if(lock1.isHeldByCurrentThread()){
                    lock1.unlock();
                }
                if(lock2.isHeldByCurrentThread()){
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getName() + " exit");
            }
        }
    }


    static class DeadLock implements Runnable{
        public static Object obj1 = new Object();
        public static Object obj2 = new Object();

        @Override
        public void run() {
            if("t1".equals(Thread.currentThread().getName())){
                synchronized (obj1){
                    System.out.println(Thread.currentThread().getName() + " run");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " is interrupted");
                        e.printStackTrace();
                    }
                    synchronized (obj2){

                    }
                }
            }else{
                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName() + " run");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " is interrupted");
                        e.printStackTrace();
                    }
                    synchronized (obj1){

                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }
    }


    static class TimeLock implements Runnable{
        public static ReentrantLock lock = new ReentrantLock();
        @Override
        public void run() {
            try{
                if(lock.tryLock(5, TimeUnit.SECONDS)){
                    System.out.println(Thread.currentThread().getName() + " get lock successful");
                    Thread.sleep(6000);
                    System.out.println(Thread.currentThread().getName() + " end");
                }else {
                    System.out.println(Thread.currentThread().getName() + " get lock failed");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if(lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }
    }

    static class TryLock implements Runnable{

        @Override
        public void run() {

        }
    }
}
