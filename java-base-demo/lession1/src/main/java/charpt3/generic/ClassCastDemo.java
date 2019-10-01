package charpt3.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * D:\projects\learn_projects\gupaocodepersonal\github-syn\2019\java-base-demo\lession1\target\classes\charpt3\generic\ClassCastDemo.class
 * Created by zwz on 2019/8/22.
 */
public class ClassCastDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        //类型强转（编译器实现）
        String str1 = list.get(0);

        List list1 = new ArrayList();
        list1.add("World");
        //需手动强转
        String str2 = (String) list1.get(0);
    }
}
