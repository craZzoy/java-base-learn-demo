package charpt4.functional;

/**
 * Created by zwz on 2019/8/26.
 */
public class ActionDemo {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("内部类实现");
            }
        };
        runnable.run();

        Runnable runnable1 = ()->{
            System.out.println("invokedynamic指令");
        };
        runnable1.run();
    }
}
