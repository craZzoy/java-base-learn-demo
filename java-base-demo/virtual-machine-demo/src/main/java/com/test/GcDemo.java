package com.test;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class GcDemo {

    private static final int _1MB = 8*1024*1024;

    //用于占用内存，以便查看是否gc
    private byte[] bigSize = new byte[2*_1MB];

    public static void main(String[] args) {
//        char ch = '你';
//        System.out.println(Character.valueOf(ch));
        //testIsRefrenceCountingGc();
        testRefrence();
    }

    /**
     * 测试是否引用计数法
     * VM args：-XX:+PrintGCDetails -XX:+PrintGCTimeStamps
     */
    public static void testIsRefrenceCountingGc(){
        RefrenceCountingGc objA = new RefrenceCountingGc();
        RefrenceCountingGc objB = new RefrenceCountingGc();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }

    static class RefrenceCountingGc{
        public Object instance = null;
    }


    /**
     * 测试引用类型
     */
    public static void testRefrence(){
        String s = new String("Hello");
        Reference reference = new SoftReference(s);
        System.out.println(reference.get());

        ReferenceQueue referenceQueue = new ReferenceQueue();
        Reference phantomReference = new PhantomReference(s,referenceQueue);
        System.out.println(phantomReference.get());

    }



}
