package com.algotithm;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RecursiveDemo {

    public static void main(String[] args) {
        System.out.println(recursiveSum(100));
        System.out.println(recursiveMul(5));

        String[] strings = {"Hello","Jack","Ma","Hello","World"};
        System.out.println(clearRepeat(Arrays.asList(strings)));

        Object[] objects = clearArrayRepeat(strings);
        Stream.of(objects).forEach(System.out::println);

    }

    /**
     * 递归计算
     * @param val
     * @return
     */
    public static int recursiveSum(int val){
        if(val < 0){
            throw new RuntimeException("not valid");
        }else if(val == 1 || val == 0){
            return val;
        }else{
            return val + recursiveSum(val - 1);
        }
    }

    /**
     * 计算阶乘
     * @param val
     * @return
     */
    public static int recursiveMul(int val){
        if(val == 0 ||val == 1){
            return 1;
        }else{
            return val * recursiveMul(val - 1);
        }
    }


    /**
     * 去重 
     * @param source
     * @return
     */
    public static List clearRepeat(List source){
        List result = new ArrayList();
        for (int i = 0; i < source.size(); i++){
            if(indexOf(result,source.get(i)) < 0){
                result.add(source.get(i));
            }
        }
        return result;
    }

    public static <T> int indexOf(List<T> source,T obj){
        if(obj == null){
            for(int i = 0; i < source.size(); i++){
                if(source.get(i) == null){
                    return i;
                }
            }
        }else{
            for(int i = 0; i < source.size(); i++){
                if(source.get(i).equals(obj)){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 数组去重
     * @param source
     * @param <E>>
     * @return
     */
    public static <E> E[] clearArrayRepeat(E[] source){
        E[] target = (E[])new Object[source.length];
        System.out.println(source.length);
        System.out.println(target.length);
        int t = 0;
        for(int i = 0; i < source.length; i++){
            if(indexOfArray(target,source[i]) < 0){
                target[t] = source[i];
                t++;
            }
        }
        E[] result = (E[])new Object[t];
        System.arraycopy(target,0,result,0,t);
        return result;
    }

    private static <E> int indexOfArray(E[] target, E e) {
        if(e == null){
            for(int t = 0; t < target.length; t++){
                if(target[t] == null){
                    return t;
                }
            }
        }else{
            for(int t = 0; t < target.length; t++){
                if(target[t] != null && target[t].equals(e)){
                    return t;
                }
            }
        }
        return -1;
    }


}
