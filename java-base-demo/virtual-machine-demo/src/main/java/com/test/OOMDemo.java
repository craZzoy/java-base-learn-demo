package com.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OOMDemo {

    public static void main(String[] args) {
        //testHeapOOM();
        //System.gc();

        //testStackOOM();

        //testPermSize();
        testDirectSpaceOOM();
    }

    static class OOMObject{

    }

    /**
     * 测试java堆溢出
     * VM args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     */
    public static void testHeapOOM() {
        List<OOMObject> oomObjects = new ArrayList<>();
        while (true){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            oomObjects.add(new OOMObject());
        }
    }


    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    /**
     * 测试栈溢出
     * VM args：-Xss128k（设置栈容量）
     */
    public static void testStackOOM(){
        OOMDemo oomDemo = new OOMDemo();
        try{
            oomDemo.stackLeak();
        } catch (Throwable t){
            System.out.println("stack length:"+oomDemo.stackLength);
            throw t;
        }
    }

    /**
     * 方法区溢出
     * VM args：-XX:PermSize=10M -XX:MaxPermSize=10M（jdk7以及之前可用）
     */
    public static void testPermSize(){
        List<String> list = new ArrayList<>();
        int i = 0;
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }

    /**java8
     * 方法区溢出（Metaspace是方法区实现），通过cglib动态创建类信息
     * -XX:MetaspaceSize=10M(初始) -XX:MaxMetaspaceSize=10M（最大）
     */
    public static void testMetaspaceOOM(){
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invoke(o,objects);
                }
            });
            enhancer.create();
        }
    }


    /**
     * 直接内存溢出
     * VM args：-XX:MaxDirectMemorySize=10M
     * @throws IllegalAccessException
     */
    public static void testDirectSpaceOOM() {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = null;
        try {
            unsafe = (Unsafe)field.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        while (true){
            //申请内存
            unsafe.allocateMemory(1024*1024);
        }
    }

}
