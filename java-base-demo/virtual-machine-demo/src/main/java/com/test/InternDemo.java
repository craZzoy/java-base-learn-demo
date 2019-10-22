package com.test;

/**
 * jdk8中测试
 * intern:
 * 1、常量池中有，返回对象
 * 2、常量池中没有，在常量池中创建，返回创建的对象
 */
public class InternDemo {

    public static void main(String[] args) {
        //情况一：pool中有"HelloWorld"
        String a = "HelloWorld";
        String b = new String("HelloWorld");
        String b1 = new String("HelloWorld");
        System.out.println(b.intern() == b); //b.intern()取pool中的"HelloWorld"，false
        System.out.println(b.intern() == a); //b.intern()取pool中的"HelloWorld"，true
        System.out.println(b1.intern() == b.intern());//都是取pool中的"HelloWorld"，true
        System.out.println();

        //情况二：pool中未有常量
        String c = new String("Jack");
        String c1 = "Jack";
        System.out.println(c.intern() == c); //pool中"Jack"和堆中不等，false
        System.out.println(c.intern() == c1); //都取了pool中常量，true
        System.out.println();

        //情况三
        String d = new String("Ni") + new String("Hao"); //只在常量池中创建了"Ni"和"Hao"
        String d1 = "NiHao";
        System.out.println(d.intern() == d); //未执行String d1 = "NiHao",pool没有"NiHao"，d.intern返回了堆中数据？
        System.out.println(d.intern() == d1); //String d1 = "NiHao",pool有值，true


        String e = new String("Back");
        System.out.println(e.intern() == e);

        //验证new String("Ni") + new String("Hao")
        StringBuilder sb = new StringBuilder();
        String s = sb.append("Tom").append("Jerry").toString();
        System.out.println(s.intern() == s);


        String f = new StringBuilder("ja").append("va").toString();
        System.out.println(f.intern() == f);

    }
}
