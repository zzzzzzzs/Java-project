package com.me.DesignPatterns.Singleton;

// TODO 饿汉式---单例   在启动程序的时候加构造好单例对象了---线程安全，不需要加锁，内存占用大（但是无所谓，占用的内存可以忽略不记）
public class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();
    private HungrySingleton() {
    }
    public static HungrySingleton getInstance() {
        return instance;
    }
}