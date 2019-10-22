package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReadWriteLockDemo {
    private static ReentrantLock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    /**
     * 模拟读操作，读耗时越高，读写锁的优势越明显
     * @param lock
     * @return
     */
    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 模拟写操作
     * @param lock
     * @param val
     * @throws InterruptedException
     */
    public void handleWrite(Lock lock,int val) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = val;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnable = () -> {
            try {
                //使用读写锁
                //demo.handleRead(readLock);
                //使用普通的重入锁
                demo.handleRead(lock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable writeRunnable = () ->{
            try {
                //使用读写锁
                //demo.handleWrite(writeLock,new Random().nextInt());
                //使用普通的重入锁
                demo.handleWrite(lock,new Random().nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        long start = System.currentTimeMillis();
        List<Thread> readThreads = new ArrayList<>();
        for(int i = 0; i < 18; i++){
            Thread thread = new Thread(readRunnable);
            readThreads.add(thread);
            thread.start();
        }

        List<Thread> writeThread = new ArrayList<>();
        for(int i = 18; i < 20; i++){
            Thread thread = new Thread(writeRunnable);
            writeThread.add(thread);
            thread.start();
        }

        for(Thread thread:readThreads){
            thread.join();
        }

        for(Thread thread:writeThread){
            thread.join();
        }


        long end = System.currentTimeMillis();

        System.out.println("cost " + (end - start)/1000 + "s");
    }
}
