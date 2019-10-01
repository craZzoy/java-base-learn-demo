package charpt3.method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by zwz on 2019/8/23.
 */
public class MethodReturnTypeDemo {

    public static void main(String[] args) {
        List<Integer> nums = getErrorInteger();
        nums.forEach(System.out::println);
        //运行报错
        nums.add(3);
        nums.set(2,8);
        nums.forEach(System.out::println);

        List<Integer> unmodifiedNumbers = getNumbers();
        //运行异常
        unmodifiedNumbers.set(1,10);

    }

    public static List<Integer> getErrorInteger(){
        List<Integer> numbers = Arrays.asList(1,2,3,4);
        return numbers;
    }

    // 数组尽管确保长度不变的，无法保证只读
    private static Integer[] getNumbersArray() {
        return new Integer[]{1, 2, 3, 4, 5};
    }

    //原则三
    public static List<Integer> getNumbers(){
        List<Integer> numbers = Arrays.asList(1,2,3,4);
        return Collections.unmodifiableList(numbers);
    }

    //原则四：如果需要非只读集合返回的话，那么确保返回快照
    //技巧：如果需要返回快照，尽可能地选择 ArrayList
    public static List<Integer> getNumberSnapShot(){
        List<Integer> numbers = Arrays.asList(1,2,3,4);
        return new ArrayList<>(numbers);
    }

}
