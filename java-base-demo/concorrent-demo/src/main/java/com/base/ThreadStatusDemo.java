package com.base;

public class ThreadStatusDemo implements Runnable{
    private static Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        //Thread.sleep(500000);
        /*synchronized (obj){
            //obj.wait();
            obj.wait(3000000);
        }*/

        new Thread(new ThreadStatusDemo()).start();

        new ThreadClass().start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is running");
    }
}

class ThreadClass extends Thread{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is running");
    }
}
