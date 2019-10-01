package charpt4.functional;

import java.util.function.Function;

/**
 * Created by zwz on 2019/8/26.
 */
public class FunctionDesignDemo {
    public static void main(String[] args) {
        //Function用于数据转换
        Function function = a -> a;
        Function<Integer,Integer> function1 = a -> a/2;
        System.out.println(function1.apply(90));
    }
}
