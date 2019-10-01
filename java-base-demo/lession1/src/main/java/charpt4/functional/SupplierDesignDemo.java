package charpt4.functional;

import java.util.function.Supplier;

public class SupplierDesignDemo {
    public static void main(String[] args) {
        //固定参数，固定实现
        echo("Hello,World");

        //可变化的实现
        echo(() ->{
            sleep(1000);
            return "Hello World";
        });

        //及时返回数据
        getMessage();

        //待执行的数据获取流程
        Supplier<String> getString = SupplierDesignDemo::getMessage;
        //实际执行
        getString.get();
    }

    private static void echo(String msg) {
        System.out.println(msg);
    }

    /**
     * Supplier作为方法参数
     * @param msg
     */
    private static void echo(Supplier<String> msg){
        System.out.println(msg.get());
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getMessage() {
        return "Hello,World";
    }
}
