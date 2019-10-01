package charpt2;

/**
 * Created by zwz on 2019/8/21.
 */
public class FinalDemo {
    public static void main(String[] args) {
        final int a = 1;
        final A aI= new A(1,"Hello");
        //aI = new A(2,"World");
        System.out.println("before:"+aI);
        aI.setValue("World");
        System.out.println("after"+aI);

        F f = new F();
    }
}

class B{
    private String to(){
        return "i am b";
    }
}

class C extends B{
    private String to(){
        //super.to();
        return "i am c";
    }
}

final class A{

    int d = 1;

    //静态变量
    final static int c;


    static{
        c = 3;
    }
    //实例变量
    final int b;
    {
        b = 2;
    }

    private int id;
    private String value;

    public A(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "A{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}

class D{
    public D() {
        System.out.println("D is init");
    }
}

class E{
    public E() {
        System.out.println("E is init");
    }
}
class F extends D{
    E e = new E();
}
