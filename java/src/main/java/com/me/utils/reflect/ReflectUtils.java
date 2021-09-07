package com.me.utils.reflect;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;


@Data
class Student {
    private String name;
    private Integer age;
}

/**
 * @author zs
 *@date 2021/8/18.
 * 反射 动态创建，增加系统的可扩展性，比如通过配置文件指定那个类运行，实现插件等等
 */
public class ReflectUtils {

    // 反射调用方法
    public static <T> void test3(Class<T> clz) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        T obj = clz.newInstance();
        Method getName = clz.getMethod("setName", String.class); // String.class 是参数的类型
        getName.invoke(obj, "张三");
        System.out.println(obj.toString());
    }

    // 使用反射创建对象
    public static<T>void test2(Class<T> clz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T obj = clz.newInstance(); // 只能无参构造
//        T nm1 = clz.getConstructor().newInstance(); // 会检查这个编译异常
        System.out.println(obj);
    }

    // 对象的属性值
    public static <T> void test1(T oo) throws NoSuchFieldException, IllegalAccessException {
        Field getName = oo.getClass().getDeclaredField("age");
        getName.setAccessible(true); // 绕过private
        System.out.println(getName.get(oo));
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        List<Student> results = new LinkedList<>();
//        new Student("张三", 10)
//        test1();
        test2(Student.class);
        test3(Student.class);
    }
}
