package charpt4.functional;

import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer consumer = System.out::println;
        consumer.accept(new Object());
        consumer.accept("Hello World");

        Consumer<String> consumer1 = ConsumerDemo::echo;
        Consumer<String> consumer2 = ConsumerDemo::echoHasException;
        Consumer<String> consumer3 = ConsumerDemo::echoAddMsg;

        /**
         * andThen用法，相当一个调用链对同一个参数处理，并不是基于前面方法的结果处理
         * 如果某个处理方法中有异常返回，返回异常当前节点的后续将不能执行
         */
        //无异常
        //consumer1.andThen(consumer).andThen(consumer3).accept("你好啊！");
        //加入异常
        consumer1.andThen(consumer).andThen(consumer2).andThen(consumer3).accept("你好啊！");
    }

    private static void echoAddMsg(String s) {
        System.out.println(s+"不好");
    }

    private static void echoHasException(String s) {
        throw new RuntimeException("haha");
    }

    private static void echo(String msg) {
        System.out.println("echo: " + msg);
    }
}
