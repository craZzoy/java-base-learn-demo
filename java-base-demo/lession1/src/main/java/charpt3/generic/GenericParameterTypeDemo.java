package charpt3.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by zwz on 2019/8/22.
 */
public class GenericParameterTypeDemo {

    public static void main(String[] args) {
        Container<String> a = new Container("Hello");
        //compile error Integer不属于CharSequence类型
        //Container<Integer> b = new Container<Integer>(3);

        //error 编译器提示两边类型不一样
        //Container<StringBuffer> c = new Container<String>("Hi");
        //error 编译器提示不能推断类型
        //Container<StringBuffer> c = new Container<>("Hi");

        //编译不报错？
        //diamond语法不报错，即这里没有进行类型推断
        //StringBuffer和String都是CharSequence类型
        Container<StringBuffer> c = new Container("Hi");
        Container<StringBuffer> c1 = new Container("Hi");
        //运行时不报错？因为类型被擦写了，都变为Object传入
        System.out.println(c.getElement());
        System.out.println(c.getClass()+" "+c1.getClass());



        add(new ArrayList<>(),"Hello,World");
        add(new ArrayList<>(),256);
        add(new ArrayList<>(),3);

        forEach(Arrays.asList(1,2,2,3),System.out::println);
    }


    /**
     * extends 声明了上限类型为CharSequence
     * @param <E>
     */
    public static class Container<E extends CharSequence>{

        private E element;

        public Container(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        public boolean setElement(E element) {
            this.element = element;
            return true;
        }
    }

    //多界限类型参数
    public static class C{

    }

    public static interface I{

    }

    public interface I1{

    }

    /**
     * 第一个参数允许是具体类或是接口类型
     * 第二参数以后只能是接口类型，不允许具体类或是抽象类
     * @param <T>
     */
    public static class Temple<T extends C & I & I1>{

    }

    /**
     * 声明了两个泛型参数，且第一个参数依赖第二个参数
     * 往集合中添加元素
     * @param target
     * @param element
     * @param <C>
     * @param <E>
     */
    public static <C extends Collection<E>,E extends Serializable> void add(C target,E element){
        target.add(element);
    }

    public static <C extends Iterable<E>,E extends Serializable> void forEach(C source, Consumer<E> consumer){
        for(E e : source){
            consumer.accept(e);
        }
    }
}

