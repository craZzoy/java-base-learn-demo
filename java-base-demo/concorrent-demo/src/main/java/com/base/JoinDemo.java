package com.base;

public class JoinDemo {
    private volatile static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        t1.start();
        //t1.join();
        t1.joinMe();
        System.out.println(i);
    }

    public static class MyThread extends Thread{
        @Override
        public void run() {
            /*try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            synchronized (this){
                for (i=0; i < 10000; i++);
            }

        }

        /**
         * 自己实现的简单版join()
         * @throws InterruptedException
         */
        public synchronized void joinMe() throws InterruptedException {
            System.out.println(this.getName());
            this.wait(0);
        }
    }
}

