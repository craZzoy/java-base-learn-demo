package charpt2;


/**
 * Created by zwz on 2019/8/20.
 */
public class TestDemo {

    public static void main(String[] args) {
        InitDemo i1 = new InitDemo();
        i1 = new InitDemo();
        InitDemo i2 = new InitDemo();
        String s1 = "Hello";
        System.out.println(s1);
    }

}

class InitDemo{
    {
        System.out.println("instance block");
    }
}
