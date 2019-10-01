package charpt4.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by zwz on 2019/8/26.
 */
public class StreamSeniorOperationDemo {
    public static void main(String[] args) {
        //collectOperation();

        //grouppingOperation();

        flatMapOperation();
    }

    /**
     * Collect操作
     */
    public static void collectOperation(){
        List<Integer> numbers = Stream.of(1,2,3,4,5)
                .collect(Collectors.toList());
        System.out.println(numbers);
        System.out.println(numbers.getClass());

        /**
         * supplier：创建一个结果容器
         * accumulator：用于合并结果的方法
         * combiner：合并两个结果的方法
         */
        numbers = Stream.of(1,2,3,4,5)
                .collect(LinkedList::new, List::add, List::addAll);

        BiConsumer consumer = new BiConsumer() {
            @Override
            public void accept(Object o, Object o2) {

            }
        };

        System.out.println(numbers);
        System.out.println(numbers.getClass());
    }

    public static void grouppingOperation(){
        List<People> peoples = new ArrayList<>();
        peoples.add(new People("doctor","jack",1000));
        peoples.add(new People("teacher","marry",2000));
        peoples.add(new People("doctor","tom",3000));
        peoples.add(new People("teacher","joy",5000));

        //根据professioin分组
        Map<String,List<People>> map = peoples.stream()
                .collect(Collectors.groupingBy(People::getProfession));
        System.out.println(map);

        //分组聚合
        Map<Object,Integer> map1 = peoples.stream()
                .collect(Collectors.groupingBy(p->p.getProfession(),
                        Collectors.summingInt(p -> p.getAvgOwn())));

        System.out.println(map1);


    }


    public static void flatMapOperation(){
        String[] values = new String[]{"Hello","World"};
        //数组中包含了数组
        List<String[]> list = Arrays.stream(values)
                .map(value -> value.split(""))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list);
        list.stream().forEach(strings ->Arrays.stream(strings).forEach(System.out::println));

        List<String> list1 = Arrays.stream(values)
                .map(value -> value.split(""))
                .flatMap(Arrays::stream)
                .distinct().collect(Collectors.toList());
        System.out.println(list1);


        String[][] val = new String[2][2];
        val[0][0] = "Hello";
        val[0][1] = "World";
        val[1][0] = "Hello";
        val[1][1] = "Jack";
        List<String> list2 = Arrays.stream(val).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(list2);

    }

}

class People {
    private String profession;
    private String name;
    private int avgOwn;

    public People(String profession, String name, int avgOwn) {
        this.profession = profession;
        this.name = name;
        this.avgOwn = avgOwn;
    }

    public People(String profession, String name) {
        this.profession = profession;
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "People{" +
                "profession='" + profession + '\'' +
                ", name='" + name + '\'' +
                ", avgOwn=" + avgOwn +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAvgOwn() {
        return avgOwn;
    }

    public void setAvgOwn(int avgOwn) {
        this.avgOwn = avgOwn;
    }

    public void setName(String name) {
        this.name = name;
    }

}
