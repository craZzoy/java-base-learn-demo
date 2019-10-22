package com.base;

public class ThreadStopDemo {

    public static User u = new User();

    public static void main(String[] args) throws InterruptedException {
        //stopThreadUnsafely();

        stopThreadsafely();
    }


    /**
     * 错误示范的终止线程
     */
    public static void stopThreadUnsafely() throws InterruptedException {
        Runnable readObjectRunnable = ()->{
            while (true){
                synchronized (u){
                    if(u.getId() != Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }
                    //System.out.println(u.toString());
                }
                Thread.yield();
            }
        };
        Runnable changeObjectRunnable = ()->{
            while (true){
                synchronized (u){
                    int v = (int)System.currentTimeMillis()/1000;
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        };

        Thread readThread = new Thread(readObjectRunnable);
        readThread.start();
        while (true){
            Thread changeThread = new Thread(changeObjectRunnable);
            changeThread.start();
            Thread.sleep(150);
            changeThread.stop();
        }
    }



    /**
     * 自行实现终止线程
     */
    public static void stopThreadsafely() throws InterruptedException {
        Runnable readObjectRunnable = ()->{
            while (true){
                synchronized (u){
                    if(u.getId() != Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }
                    //System.out.println(u.toString());
                }
                Thread.yield();
            }
        };


        Thread readThread = new Thread(readObjectRunnable);
        readThread.start();
        while (true){
            WriteObjectSafelyThread changeThread = new WriteObjectSafelyThread();
            changeThread.start();
            Thread.sleep(150);
            changeThread.stopMe();
        }
    }

    public static class WriteObjectSafelyThread extends Thread{
        volatile boolean stopme = false;
        public void stopMe(){
            this.stopme = true;
        }
        @Override
        public void run() {
            while (true){
                if(stopme){
                    System.out.println("i am stop");
                    break;
                }
                synchronized (u){
                    int v = (int)System.currentTimeMillis()/1000;
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class User{
        private int id;
        private String name;

        public User() {
            this.id = 0;
            this.name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
