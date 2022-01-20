package com.me.JUC.c_020_01_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题（淘宝）
 * 实现一个容器，提供两个方法，add， size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数达到5个时，线程2给出提示并结束
 * <p>
 * 给list添加volatile之后，t2额能够接到通知，但是，t2线程的死循环很浪费cpu， 如果不用死循环，该怎么做呢
 * <p>
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是， 运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 * <p>
 * 阅读下面程序，并分析输出结果
 * 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才会接收到通知而退出
 * 想想这是为什么？
 *
 * @author Pang
 */
public class T03_NotifyHoldingLock {

    volatile List lists = new ArrayList();

    void add(Object o) {
        this.lists.add(o);
    }

    int size() {
        return this.lists.size();
    }

    public static void main(String[] args) {
        T03_NotifyHoldingLock c = new T03_NotifyHoldingLock();
        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);

                    if (c.size() == 5) {
                        lock.notify();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }
}
