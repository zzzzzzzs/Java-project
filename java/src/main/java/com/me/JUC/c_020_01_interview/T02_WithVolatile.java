package com.me.JUC.c_020_01_interview;

import java.util.LinkedList;
import java.util.List;

/**
 * 曾经的面试题（淘宝）
 * 实现一个容器，提供两个方法，add， size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数达到5个时，线程2给出提示并结束
 *
 * 给list添加volatile之后，t2额能够接到通知，但是，t2线程的死循环很浪费cpu， 如果不用死循环，
 * 而且，如果在if 和 break 之间被别的线程打断，得到的结果也不精确
 * 该怎么做呢
 *
 * @author Pang
 */
public class T02_WithVolatile {
    volatile List lists = new LinkedList();

    void add(Object o) {
        this.lists.add(o);
    }

    int size() {
        return this.lists.size();
    }

    public static void main(String[] args) {
        T02_WithVolatile c = new T02_WithVolatile();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);
               /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }, "t2").start();

        new Thread(() -> {
            while (true) {
                if (c.size() == 5) {
                    break;
                }
            }
            System.out.println("t1 结束");
        }, "t1").start();
    }
}
