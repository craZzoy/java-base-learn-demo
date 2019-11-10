package com.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class FailLockDemo implements Runnable{

    public static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        /*FailLockDemo f = new FailLockDemo();
        Thread t1 = new Thread(f);
        Thread t2 = new Thread(f);
        t1.start();
        t2.start();*/

        System.out.println(1 ^ 0);
        System.out.println(1 | 0);
        System.out.println(1 & 0);

        int SHUTDOWN   =  0 << (Integer.SIZE - 3);
        System.out.println(SHUTDOWN);
    }

    @Override
    public void run() {
        while (true){
            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName()+" get the lock");
            }finally {
                if(lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }
    }
}
