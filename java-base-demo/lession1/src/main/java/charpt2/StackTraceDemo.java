package charpt2;

public class StackTraceDemo {
    public static void main(String[] args) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for(StackTraceElement element:elements){
            System.out.println(element);
        }
    }
}
