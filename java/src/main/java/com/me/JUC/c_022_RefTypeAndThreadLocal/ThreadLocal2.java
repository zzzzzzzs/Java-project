package com.me.JUC.c_022_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 线程局部变量
 *
 * ThreadLocal 是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在与ThreadLocal中，避免synchronized的使用
 * 例如 Spring 中的 @Transactional , 就是我们把业务方法m()标记为事务，要么全局完成，要么回滚。
 *  假如m()中调用了m1()和m2()，这个两个方法都有可能访问数据库，由于他们位于同一个Transactional中，这两个方法必须保证拿到数据库连接是同一个，在本质上就是用 ThreadLocal 实现的。
 *  因为 ThreadLocal 是和当前线程有关系的，只要从当前线程拿，拿到都永远都是同一个。
 *
 *
 *
 * 运行下面的程序， 理解ThreadLocal
 */
public class ThreadLocal2 {
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Person p = new Person();
            p.name = "lisi";
            tl.set(new Person());
        }).start();

    }
}
