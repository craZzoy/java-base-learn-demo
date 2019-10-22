package com.base;

public class SuspendDemo {
    private static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        /*Thread t1 = new Thread(new BadRunnable());
        Thread t2 = new Thread(new BadRunnable());
        t1.start();
        Thread.sleep(2000);
        t2.start();
        t1.resume();
        //Thread.sleep(2000);
        t2.resume();
        t1.join();
        t2.join();*/

        GoodResume t1 = new GoodResume();
        GoodResume t2 = new GoodResume();
        t1.start();
        t2.start();
        Thread.sleep(3000);
        t1.suspendMe();
        System.out.println("suspend t1 for 2 second");
        Thread.sleep(2000);
        t2.suspendMe();
        Thread.sleep(1000);
        System.out.println("resume thread");
        t2.resumeMe();
        t1.resumeMe();
    }

    public static class BadRunnable implements Runnable{

        @Override
        public void run() {
            synchronized (obj){
                System.out.println(Thread.currentThread().getName() + " is running");
                Thread.currentThread().suspend();
                System.out.println(Thread.currentThread().getName() + " end");
            }
        }
    }

    /**
     * 正常挂起线程示范
     */
    public static class GoodResume extends Thread{

        volatile boolean isSuspend = false;

        public boolean suspendMe(){
            synchronized (this){
                this.isSuspend = true;
                return isSuspend;
            }
        }

        public void resumeMe(){
            synchronized (this){
                isSuspend = false;
                notify();
            }
        }

        @Override
        public void run() {
            while (true){
                synchronized (this){
                    System.out.println(Thread.currentThread().getName()+ " is running");
                    while (isSuspend){
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName()+ " end");
                    Thread.yield();
                }
            }
        }
    }
}
