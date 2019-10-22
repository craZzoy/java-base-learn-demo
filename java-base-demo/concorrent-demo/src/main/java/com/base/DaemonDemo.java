package com.base;

public class DaemonDemo {

    public void test(){
        new DaemonDemo.B();
    }

    public static class DaemonThread extends Thread{
        @Override
        public void run() {
            while (true) {
                System.out.println("i am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new DaemonThread();
        thread.start();
        thread.setDaemon(true);
        Thread.sleep(2000);

        DaemonDemo.A.test();

        new DaemonDemo.A().test1();

        Integer i = new Integer(1);

    }

    static protected class A{
       static void test(){}

        void test1(){}
    }

    private class B{

    }
}
