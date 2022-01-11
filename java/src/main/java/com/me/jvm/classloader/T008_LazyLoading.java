package com.me.jvm.classloader;

/**
 * @author zs
 * @date 2022/1/11
 */
public class T008_LazyLoading { //严格讲应该叫lazy initialzing，因为java虚拟机规范并没有严格规定什么时候必须loading,但严格规定了什么时候initialzing
    public static void main(String[] args) throws Exception {
//        P p; // p 没有被用到，整个类没有被加载
//        X x = new X(); // P 被加载
//        System.out.println(P.i); // i 被加载了，p没有被加载。打印 final 的值是不需要加载整个类。
//        System.out.println(P.j); // j 是非 final，会加载整个类
        Class.forName("com.me.jvm.classloader.T008_LazyLoading$P"); // 加载类的时候会加载所有类
    }

    public static class P {
        final static int i = 8;
        static int j = 9;
        static {
            System.out.println("P");
        }
    }

    public static class X extends P {
        static {
            System.out.println("X");
        }
    }
}
