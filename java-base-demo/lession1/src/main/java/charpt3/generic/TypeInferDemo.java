package charpt3.generic;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by zwz on 2019/8/22.
 */
public class TypeInferDemo {
    public static void main(String[] args) {
        Map<String,List<String>> map = Collections.map();
        List<String> list = Collections.list();
        Set<Integer> set = Collections.set();
        //...

        f(Collections.map());

        //添加显示的类型说明
        f2(Collections.<String,List<String>>map());


        Stream.of(1, 2, 3).forEachOrdered(System.out::println);
    }

    static void f(Map<String,List<? extends CharSequence>> map){

    }

    static void f2(Map<String,List<String>> map){

    }
}

class Collections{
    public static <K,V> Map<K,V> map(){
        return new HashMap();
    }

    public static <E> List<E> list(){
        return new ArrayList<E>();
    }

    public static <E> Set<E> set(){
        return new HashSet<E>();
    }

    public static <E> Queue<E> queue(){
        return new LinkedList<E>();
    }

}

