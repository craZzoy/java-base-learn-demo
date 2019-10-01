package charpt3.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by zwz on 2019/8/22.
 */
public class GenericWildcardsTypeDemo {
    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();

        //上界
        upperBoundWildCards(numbers);

        //完全统配
        unboundedWildCards(numbers);

        //下限
        lowerBoundWildCards(numbers);


    }



    /**
     * 反向上限通配符示例
     * @param numbers
     */
    private static void upperBoundWildCards(List<Number> numbers){
        //Number -> Byte Short Integer Long Float Double
        numbers.add(Byte.valueOf((byte)1));
        numbers.add(Short.valueOf((short)2));
        numbers.add(Integer.valueOf(4));
        numbers.add(Float.valueOf(2F));

        List<Byte> bytes = Arrays.asList((byte) 5);
        List<Short> shorts = Arrays.asList((short) 10);
        List<Long> longs = Arrays.asList(5L);

        numbers.addAll(bytes);  // ? extends Number：List<Byte>
        numbers.addAll(shorts); // ? extends Number：List<Short>
        numbers.addAll(longs); // ? extends Number：List<Long>


        upperBoundWildCordsDemo(numbers,System.out::println);


    }



    private static void upperBoundWildCordsDemo(List<? extends Number> numbers, Consumer<Object> consumer) {
        for(Number n:numbers){
            consumer.accept(n);
        }
    }

    private static <E extends Number> void upperBoundWildCordsDemo1(List<E> numbers, Consumer<Object> consumer) {
        for(Number n:numbers){
            consumer.accept(n);
        }
    }

    private static void unboundedWildCards(List<Number> numbers) {

        unboundedWildCardsDemo(numbers);

    }

    /**
     * 完全统配类型，可以适配任意类型，反而具体类型的泛型会限制类型范围
     * 在运行时与非通配泛型会出现方法签名冲突
     * @param numbers
     */
    private static void unboundedWildCardsDemo(Iterable<?> numbers) {//泛型擦写原因，与下方法冲突
        for(Object e : numbers){
            System.out.println(e);
        }
    }


    /*private static void printUnboundedWildCards(Iterable<Object> numbers) {
        for(Object e : numbers){
            System.out.println(e);
        }
    }*/


    private static void lowerBoundWildCards(List<Number> numbers) {
        lowerBoundWildCardsDemo(numbers,numbers);
    }

    /**
     * 上限
     * PECS(effictive java)：producer-extends,consumer-super
     * @param producer
     * @param consumer
     */
    private static void lowerBoundWildCardsDemo(List<? extends Number> producer, List<? super Number> consumer) {
        //error：? extends Number 不确定是否为Integer，可能是Float等
        //producer.add(1);

        //读取数据，生产-extends
        for(Number n : producer){
            //...
        }

        //其实也容易理解，? super Number限定了上限是Number，
        //即传的类型只能是Number实现的接口或类，如Serializable，而Float、Integer等一定是Serializable的
        //操作数据，消费-super
        consumer.add(producer.get(0));
        consumer.add((short)3);
    }
}
