package com.base;

public class ThreadGroupDemo implements Runnable{
    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("PrintGroup");
        Thread t1 = new Thread(tg,new ThreadGroupDemo(),"T1");
        Thread t2 = new Thread(tg,new ThreadGroupDemo(),"T2");
        t1.start();
        t2.start();
        //获得活动线程总数，由于线程是动态的，只是一个估计值，无法精确
        System.out.println(tg.activeCount());
        //打印线程组所有信息
        tg.list();

        //不建议使用
        //tg.stop();
    }

    @Override
    public void run() {
        String groupName = Thread.currentThread().getThreadGroup().getName()
                + "-" + Thread.currentThread().getName();
        while (true){
            System.out.println("i am "+groupName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
