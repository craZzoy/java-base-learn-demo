package com.base;

public class PriorityDemo {
    public static void main(String[] args) {
        Thread lowPriorityThread = new Thread(new PriorotyRunnable(),"lowThread");
        Thread highPriorityThread = new Thread(new PriorotyRunnable(),"hignThread");
        lowPriorityThread.setPriority(Thread.MIN_PRIORITY);
        highPriorityThread.setPriority(Thread.MAX_PRIORITY);
        lowPriorityThread.start();
        highPriorityThread.start();
    }

    public static class PriorotyRunnable implements Runnable{
        static int count = 0;
        @Override
        public void run() {
            while (true){
                synchronized (PriorityDemo.class){
                    count ++;
                    if(count > 10000000){
                        System.out.println(Thread.currentThread().getName() + " has completed");
                        break;
                    }
                }
            }
        }
    }
}
