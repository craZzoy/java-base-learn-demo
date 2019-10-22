package com.base;

public class InterruptedDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () ->
        {while(true){
            //线程中断处理逻辑
            if (Thread.currentThread().isInterrupted()){
                System.out.println("thread is interrupted");
                break;
            }
            Thread.yield();
        }};

        Runnable runnable1 = ()->{
            while (true){
                //中断处理逻辑
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("thread is interrupted");
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    //sleep中断处理逻辑
                    System.out.println("interrupted when sleep");
                    //使下一次循环能走中断逻辑
                    Thread.currentThread().interrupt();
                }
                Thread.yield();
            }
        };


        /*Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(2000);
        //标志位置为true
        thread.interrupt();*/

        Thread thread = new Thread(runnable1);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
