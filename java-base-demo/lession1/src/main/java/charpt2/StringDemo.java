package charpt2;

import java.lang.reflect.Field;

/**
 * Created by zwz on 2019/8/19.
 */
public class StringDemo {

    public static void main(String[] args) throws Exception {

        //原生类型支持常量化
        int a = 1;

        //利用反射修改字符串的值，从java1.5开始对象属性（非类属性）可以通过反射修改
        String str1 = "Hello";//语法特性：对象类型常量化
        //两种情况
        //情况一，利用反射修改后值只改变了str2，此处改变的是堆中对常量的引用
        //String str2 = new String("Hello");
        //情况二，利用反射修改后str1,str2都改变了，此处改变了常量池中的值
        String str2 = "Hello";
        System.out.println("str1:"+str1);
        System.out.println("str2:"+str2);
        char[] chars = "World".toCharArray();
        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);
        field.set(str2,chars);
        System.out.println("str1:"+str1);
        System.out.println("str2:"+str2);
    }

    public static interface A{}
}

interface Count{
    String s = "";
}