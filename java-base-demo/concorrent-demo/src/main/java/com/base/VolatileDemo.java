package com.base;

public class VolatileDemo {
    private volatile static int i = 0;

    private static long t =0;

    private static final Object obj = new Object();

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while (!ready){};
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {


        /*Thread t1 = new Thread(new AddRunnable(),"t0");
        Thread t2 = new Thread(new AddRunnable(),"t1");
        t1.start();
        Thread.sleep(1000);
        t2.start();*/

        /*Thread[] threads = new Thread[10];
        for(int i = 0; i < 10; i++){
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
        }

        for(int i = 0; i < 10; i++){
            threads[i].join();
        }

        System.out.println(i);*/

        /*new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(333L)).start();
        new Thread(new ChangeT(-444L)).start();
        new Thread(new ReadT()).start();*/


        /*new ReaderThread().start();
        Thread.sleep(1000);
        ready = true;
        number = 42;
        Thread.sleep(1000);*/

    }

    public class AddThread extends Thread{
        public int i = 0;
        @Override
        public void run() {
            for(int i = 0; i < 1000; i++){
                this.i++;
            }
        }
    }

    /**
     * 使用volitile并不能保证复合原子操作
     */
    public static class PlusTask implements Runnable{

        @Override
        public void run() {
            synchronized (obj){
                for (int k = 0; k < 10000; k++){
                    i++;
                }
            }
        }

    }

    public static class ChangeT implements Runnable{
        private long to;

        public ChangeT(long to) {
            this.to = to;
        }

        @Override
        public void run() {
            while (true){
                VolatileDemo.t = to;
                Thread.yield();
            }
        }
    }

    public static class ReadT implements Runnable{

        @Override
        public void run() {
            while (true){
                long tmp = VolatileDemo.t;
                if(tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444L){
                    System.out.println(tmp);
                }
                Thread.yield();
            }
        }
    }
}

class AddRunnable implements Runnable{
    private static int i = 0;
    private static final Object obj = new Object();
    @Override
    public void run() {
        synchronized (obj){
            System.out.println(this);
            if("t0".equals(Thread.currentThread().getName())){
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
    }
}

