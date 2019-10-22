package com.test;

public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOME = null;


    /**
     * 通过finalize()方法拯救自己
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method running");
        FinalizeEscapeGC.SAVE_HOME = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOME = new FinalizeEscapeGC();

        //第一次自救
        SAVE_HOME = null;
        //触发第一次标记
        System.gc();
        //finalize()方法优先级很低，暂停0.5s等待
        Thread.sleep(500);
        if(SAVE_HOME != null){
            System.out.println("SAVE_HOME is alive");
        }else{
            System.out.println("SAVE_HOME is dead");
        }

        //第二次自救
        SAVE_HOME = null;
        System.gc();
        //finalize()方法优先级很低，暂停0.5s等待
        Thread.sleep(500);
        if(SAVE_HOME != null){
            System.out.println("SAVE_HOME is alive");
        }else{
            System.out.println("SAVE_HOME is dead");
        }
    }
}
