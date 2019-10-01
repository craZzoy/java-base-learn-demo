package charpt2;

/**
 * Created by zwz on 2019/8/20.
 */
public class InnerClassDemo {
    private int data;

    private C c;

    public class C {

        private int data;

    }

    public void setData() {
        c.data = data;
    }

    public static class A {
        public String to(){
            return null;
        }

    }

    public /*static*/ interface B {

    }

    public static void main(String[] args) {
        A a = null;
        B b = null;
        C c = null;

        A a1 = new A();
        a1.to();
    }
}
