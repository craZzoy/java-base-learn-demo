package com.base;

public class WaitDemo {

    private static final Object obj = new Object();

    static class WaitRunnable implements Runnable{

        @Override
        public void run() {
            synchronized (obj){
                System.out.println(Thread.currentThread().getName() +
                        " at "+System.currentTimeMillis() + " start running");
                try {
                    System.out.println(Thread.currentThread().getName()
                            +" at "+System.currentTimeMillis()+" waiting for obj");
                    //立即释放锁
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end");
            }
        }
    }

    static class NotifyRunnable implements Runnable{

        @Override
        public void run() {
            synchronized (obj){
                System.out.println(Thread.currentThread().getName() +
                        " at " + System.currentTimeMillis() + " start,notify one Thread");
                obj.notify();
                //obj.notifyAll();
                System.out.println(Thread.currentThread().getName()
                +" at "+ System.currentTimeMillis()+" end");
                int i = 5;
                while (i > 0){
                    System.out.println(Thread.currentThread().getName() + " is still running");
                    i --;
                }
                //并不立即释放锁
                obj.notify();
                i = 5;
                while (i > 0){
                    System.out.println(Thread.currentThread().getName() + " is still running");
                    i --;
                }
                /*try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new WaitRunnable());
        Thread t2 = new Thread(new NotifyRunnable());
        t1.start();
        Thread.sleep(2000);
        t2.start();
    }
}

