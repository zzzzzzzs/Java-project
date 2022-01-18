package com.me.JUC.c_017_MoreAboutSync;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某对象o,如果o的属性发生改变，不影响锁的使用
 * 但是如果o变成另外一个对象，则锁定的对象发生改变
 * 应该避免将锁定对象的引用变成另外的对象
 */
public class SyncSameObject {
    /*final*/ Object o = new Object();

    void m() {
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        SyncSameObject t = new SyncSameObject();
        // 执行第一个线程
        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 创建第二个线程
        Thread t2 = new Thread(t::m, "t2");
        // 锁定的对象发生修改，所以t2线程得以执行，如果注释掉这句话，那么t2线程永远得不到执行
        // 因为锁是对象头上的两位代表的，本来线程访问的是这两位，结果变成了别的，这样就没有关系了。因此使用 final 修饰变量
        t.o = new Object();

        t2.start();
    }
}
