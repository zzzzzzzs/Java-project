package com.me.DesignPatterns.Singleton;


import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * TODO 懒汉式--单例  好处是节省内存（但是无所谓，区别不大），使用的时候才构造，可以在初始化的时候做一些事情，线程不安全，需要加锁
 * lazy loading 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 * 可以通过 synchronized 解决，但也带来了效率下降
 *
 * DCL Double Check
 **/
public class LazySingleton1 {
    // 需要加 volatile
    private static volatile LazySingleton1 INSTANCE; // JIT

    private LazySingleton1(){

    }

    ConcurrentHashMap chm = null;

    public static LazySingleton1 getInstance() {
        if (INSTANCE == null) { // 业务逻辑代码省略
            synchronized (LazySingleton1.class) {
                if (INSTANCE == null) { // 双重检查
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new LazySingleton1();
                }
            }
        }
        return INSTANCE;
    }
}
