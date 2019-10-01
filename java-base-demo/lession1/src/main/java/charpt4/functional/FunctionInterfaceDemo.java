package charpt4.functional;

import java.io.ObjectInputStream;

/**

 * Created by zwz on 2019/8/24.
 */
public class FunctionInterfaceDemo {

    public static void main(String[] args) {
        //匿名内置类实现
        Functional f = new Functional() {
            @Override
            public void excute() {

            }
        };
        //Lambda表达式
        Functional f1 = () -> {
            System.out.println("666");
        };
        f1.excute();
    }
}

@FunctionalInterface
interface Functional{

    /**
     * 覆盖的Object方法，不会算入抽象方法计数
     * @param obj
     * @return
     */
    //@Override
    boolean equals(Object obj);

    void excute();
}