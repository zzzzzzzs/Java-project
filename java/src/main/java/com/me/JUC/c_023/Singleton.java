package com.me.JUC.c_023;

import java.util.Arrays;

/**
 * 线程安全的单例模式
 * <p>
 * 阅读文章： http://www.cnblogs.com/xudong-bupt/p/3433643.html
 * <p>
 * 更好的是采用下面的方式，既不用加锁，也能实现懒加载
 *
 * @author Pang
 */
public class Singleton {

    private Singleton() {
        System.out.println("singleton init");
    }

    private static class Inner {
        private static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return Inner.instance;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                System.out.println(Singleton.getInstance());
            });
        }
        Arrays.asList(threads).forEach(o -> o.start());
    }

}
