package com.me.Singleton;


// TODO 懒汉式--单例  好处是节省内存（但是无所谓，区别不大），使用的时候才构造，可以在初始化的时候做一些事情，线程不安全，需要加锁
public class LazySingleton {
    private static volatile LazySingleton instance = null;    //保证 instance 在所有线程中同步

    private LazySingleton() {
    }    //private 避免类在外部被实例化

    public static LazySingleton getInstance() {
        //getInstance 方法前加同步
        if (instance == null) {
            synchronized (LazySingleton.class){
                if(instance == null){
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
