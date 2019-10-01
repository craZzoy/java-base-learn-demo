package charpt4.functional;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zwz on 2019/8/26.
 */
public class PredicateDesignDemo {

    public static void main(String[] args) {
        //过滤数据
        List<Integer> numbers = Arrays.asList(1,2,2,3,4,5);
        Collection<Integer> even = filter(numbers,num -> num % 2 == 0);
        Collection<Integer> odd = filter(numbers,num -> num % 2 != 0);
        System.out.println(even);
        System.out.println(odd);

        //用Stream操作
        Stream.of(1,2,3,4,5,6).filter(num -> num % 2 == 0).forEachOrdered(System.out::println);
    }

    public static <E> Collection<E> filter(Collection<E> source, Predicate<E> predicate){
        //集合操作，最后别直接操作原有数据
        //这里是浅拷贝
        List<E> copy = new ArrayList<E>(source);
        Iterator<E> iterator = copy.iterator();
        while(iterator.hasNext()){
            E element = iterator.next();
            if(!predicate.test(element)){
                iterator.remove();
            }
        }
        return Collections.unmodifiableCollection(copy);
    }
}
