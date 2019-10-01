package charpt4.functional;

import java.util.function.Supplier;

public class SupplierDemo {

    public static void main(String[] args) {
        Supplier<Long> supplier = getLong();
        System.out.println(supplier.get());
    }


    /**
     * System#currentTimeMillis方法刚好是只有输出没有输入的模式
     * @return
     */
    public static Supplier<Long> getLong() {
        return System::currentTimeMillis;
    }
}
