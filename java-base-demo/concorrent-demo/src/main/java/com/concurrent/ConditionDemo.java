package com.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        lock.lock();
        try {
            condition.signal();
            condition.await();
            Thread.sleep(2000);
            condition.signal();
            System.out.println(Thread.currentThread().getName() + " go on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo conditionDemo = new ConditionDemo();
        Thread t1 = new Thread(conditionDemo);
        Thread t2 = new Thread(conditionDemo);
        Thread t3 = new Thread(conditionDemo);
        t1.start();
        t2.start();
        t3.start();
        //通知t1线程继续执行
        /*Thread.sleep(2000);
        lock.lock();
        condition.signal();
        lock.unlock();*/
    }
}
