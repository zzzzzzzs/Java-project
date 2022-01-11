package com.me.jvm.classloader;

/**
 * @author zs
 * @date 2022/1/10
 */
public class T005_LoadClassByHand {
    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 加载一个类，使用 loadClass 方法就能将类加载到内存，加载到内存会返回一个 Class 对象。
         *  就是说，loadClass 方法会在硬盘上这个类的源码，找到源码以后将它 load 到内存，于此同时生成一个 class 对象，把这个 class 对象返回。
         *  loadClass 是反射的基石，平时用的所谓的反射，就是访问二进制代码。
         *  什么时候需要自己去加载类：比如说动态代理，热部署
         *
         **/
        Class clazz = T005_LoadClassByHand.class.getClassLoader().loadClass("com.me.jvm.classloader.T002_ClassLoaderLevel");
        System.out.println(clazz.getName());
    }
}

