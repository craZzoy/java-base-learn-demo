package charpt2;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zwz on 2019/8/20.
 */
public class EnumDemo {
    public static void main(String[] args) {
        //思考问题：
        //q1:成员变量定义的顺序
        prinEnumMeta(Season.SPRING);

        //q3:为什么枚举会输出成员变量，Enum的toString()
        System.out.println(Season.SPRING);

        //q2:能否输出全部成员
        //通过values打印
        printSeasonMerbers();
        //自己实现的枚举类
        printSeasonClassMerbers();
    }

    public static void prinEnumMeta(Enum enumeraion){
        System.out.println("enumeraion type:"+enumeraion.getClass());
        //名字
        System.out.println("member:"+enumeraion.name());
        //顺序
        System.out.println("ordinal:"+enumeraion.ordinal());
    }

    public static void printSeasonMerbers(){
        Stream.of(Season.values()).forEach(System.out::println);
    }

    public static void printSeasonClassMerbers(){
        Stream.of(SeasonClass.values()).forEach(System.out::println);
    }
}

enum Season{
    SPRING {
        @Override
        public String valueAsString() {
            return null;
        }
    },
    SUMMER {
        @Override
        public String valueAsString() {
            return null;
        }
    },
    AUTUMN {
        @Override
        public String valueAsString() {
            return null;
        }
    },
    WINTER {
        @Override
        public String valueAsString() {
            return null;
        }
    };

    public abstract String valueAsString();
}

final class SeasonClass{
    public static final SeasonClass SPRING = new SeasonClass("SPRING");
    public static final SeasonClass SUMMER = new SeasonClass("SUMMER");
    public static final SeasonClass AUTUMN = new SeasonClass("AUTUMN");
    public static final SeasonClass WINTER = new SeasonClass("WINTER");
    private String value;

    public SeasonClass(String s) {
        this.value = s;
    }

    @Override
    public String toString() {
        return "SeasonClass{" +
                "value='" + value + '\'' +
                '}';
    }

    public static SeasonClass[] values(){
        Field[] fields = SeasonClass.class.getDeclaredFields();
        return Stream.of(fields)
                .filter(field -> {
                    //获取修饰符
                    int modifiers = field.getModifiers();
                    return Modifier.isPublic(modifiers) && Modifier.isFinal(modifiers) && Modifier.isStatic(modifiers);
                }).map(field -> {
            try {
                return field.get(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList()).toArray(new SeasonClass[0]);

    }
}