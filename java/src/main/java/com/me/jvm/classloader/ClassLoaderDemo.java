package com.me.jvm.classloader;

/**
 * @author zs
 * @date 2021/10/3.
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        Object obj = new Object();
        ClassLoaderDemo d01 = new ClassLoaderDemo();
        // TODO 沙箱安全机制，包含 jdk 原生的代码
        String str = new String("abc");
        // TODO 这种一层一层的就是双亲委派机制
        System.out.println(obj.getClass().getClassLoader()); // null 是启动类加载器，是由C++编写的，获取不到索引
        System.out.println(d01.getClass().getClassLoader().getParent().getParent()); //null
        System.out.println(d01.getClass().getClassLoader().getParent()); // sun.misc.Launcher$ExtClassLoader@5b6f7412
        System.out.println(d01.getClass().getClassLoader()); // sun.misc.Launcher$AppClassLoader@18b4aac2

        /* TODO Java代码运行到这一行，线程起来没有？
                不确定起没起，线程不是Java程序起的。线程和进程是操作系统决定的。
         */

        new Thread().start();
    }
}
