package com.base;

public class SynchronizedDemo implements Runnable{

    static int i = 0;
    public synchronized void increase(){
        i++;
    }


    @Override
    public void run() {
        for(int j = 0; j < 100000; j++){
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //SynchronizedDemo instance = new SynchronizedDemo();
        Thread t1 = new Thread(new SynchronizedDemo());
        Thread t2 = new Thread(new SynchronizedDemo());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}

class A{


    void test(){}

    void test(int a,int b){}

    int test(int a){return 1;}
}

class B extends A{
    @Override
    void test(){}
}

