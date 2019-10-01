package charpt4.stream;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Created by zwz on 2019/8/26.
 */
public class StreamDemo {
    public static void main(String[] args) {

        //排序
        sort(4,6,3,7,4,8,8,9,0);
        line();

        //排序，自定义比较器
        sort((x, y) -> (x < y) ? 1 : ((x.equals(y)) ? 0 : -1),
                4,6,3,7,4,8,8,9,0);
        line();

        //并行排序
        parallelSort(4,6,3,7,4,8,8,9,0,90,76,54,200);
        line();

        count(1,2,3,4,5,6,7,8,9,10);
    }



    private static void sort(Integer... numbers) {
        Stream.of(numbers)
                .sorted()
                .forEach(System.out::println);
    }

    private static void sort(Comparator<Integer> comparator, Integer... numbers){
        Stream.of(numbers)
                .sorted(comparator)
                .forEach(System.out::println);
    }

    private static void parallelSort(Integer... numbers) {
        Stream.of(numbers)
                .sorted()
                .parallel()
                .forEach(StreamDemo::println);
    }

    private static void println(Object o){
        System.out.printf("[%s]:%s\n",Thread.currentThread(),o);
    }

    private static void line() {
        System.out.println();
    }

    public static void count(Integer... numbers){
        Stream.of(numbers)
                .reduce(Integer::sum)   //合并操作
                //.map(String::valueOf)
                .ifPresent(System.out::println); //阻塞等待结果
    }
}
