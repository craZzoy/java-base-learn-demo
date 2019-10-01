package charpt4.functional;

import java.io.File;
import java.util.function.Predicate;

/**
 * Created by zwz on 2019/8/26.
 */
public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<File> filePredicate = File::isFile;
        System.out.println(filePredicate.test(new File("")));
    }
}
