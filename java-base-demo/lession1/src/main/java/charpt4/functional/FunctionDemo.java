package charpt4.functional;

import java.util.function.Function;

/**
 * Created by zwz on 2019/8/26.
 */
public class FunctionDemo {
    public static void main(String[] args) {
        Function<String,Long> stringLongFunction = FunctionDemo::StringToLong;
        stringLongFunction.apply("67");

        Function<Long,String> longStringFunction = FunctionDemo::longToString;
        longStringFunction.apply(7L);

        //在执行stringLongFunction之前会执行longStringFunction
        Long value = stringLongFunction.compose(longStringFunction).apply(1L);
        //System.out.println(value);

        //apply参数对应integerToString方法入参
        Function<Integer,String> function = FunctionDemo::integerToString;
        Long value1 = stringLongFunction.compose(function).apply(456);


    }


    public static String longToString(Long l){
        System.out.println("longToString:"+l);
        return String.valueOf(l);
    }

    public static Long StringToLong(String s){
        System.out.println("StringToLong:"+s);
        return Long.valueOf(s);
    }

    public static String integerToString(Integer i){
        System.out.println("integerToString:"+i);
        return i.toString();
    }
}
