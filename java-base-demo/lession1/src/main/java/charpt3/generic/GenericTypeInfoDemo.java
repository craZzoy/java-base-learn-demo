package charpt3.generic;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.springframework.core.ResolvableType;

import java.lang.reflect.TypeVariable;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Map;
import java.util.Collections;

/**
 * Created by zwz on 2019/8/24.
 */
public class GenericTypeInfoDemo<T extends Serializable,E extends CharSequence> {

    private List<Map<String,List<Object>>> values =  Collections.emptyList();

    public static void main(String[] args) throws Exception {
        resolveGenericType();
        System.out.println(resolveGenericClassType(Container.class));
    }

    /**
     * 获取泛型信息
     * 成员变量
     * 类
     * @throws Exception
     */
    public static void resolveGenericType() throws Exception {
        Field field = GenericTypeInfoDemo.class.getDeclaredField("values");
        field.setAccessible(true);

        //SPRING实现的ResolverStyle
        //获取成员变量泛型信息
        ResolvableType resolvableType = ResolvableType.forField(field);
        ResolvableType[] types = resolvableType.getGenerics();
        for (ResolvableType type : types){
            System.out.println(type.getType());
        }
        //System.out.println(resolvableType.getGenerics());

        //类泛型信息
        ResolvableType resolvableType1 = ResolvableType.forClass(GenericTypeInfoDemo.class);
        ResolvableType[] types1 = resolvableType1.getGenerics();
        for (ResolvableType type : types1){
            System.out.println(type.getType()+","+type.resolve().getName());
        }


        //java内部实现
        //获取类泛型类型信息
        TypeVariable[] parameters = GenericTypeInfoDemo.class.getTypeParameters();
        for(TypeVariable typeVariable : parameters){
            System.out.println(typeVariable);
        }

        //具体参数类型
        GenericTypeInfoDemo<String,String> demo = new GenericTypeInfoDemo<String,String>();
        parameters = demo.getClass().getTypeParameters();
        for(TypeVariable typeVariable : parameters){
            System.out.println(typeVariable);
        }



    }

    public static <T> Class<T> resolveGenericClassType(Class<?> declareClass){
        //ParameterizedType parameterizedType = (ParameterizedType) (declareClass.getGenericSuperclass());
        //Type[] types = parameterizedType.getActualTypeArguments();
        //return (Class<T>) types[0];
        Type type = declareClass.getGenericSuperclass();
        System.out.println(type.getClass());
        return null;
    }

}

class Container<T extends CharSequence>{
    private T element;
}