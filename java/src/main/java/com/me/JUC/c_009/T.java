package com.me.JUC.c_009;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候任然会得到该对象的锁
 * 也就是说synchronized获得的锁是可重入的。
 *
 * m1 中是可以调用 m2 的。如果不可重入，那么一开始的时候线程得到一把锁运行 m1() ，
 * 然后在 m1() 中调用 m2() 如果不允许别的线程拿到这把锁的时候，这个时候就产生死锁的现象。
 * 这个时候调用 m2() 的时候发现是同一个线程的锁，是可重入的。
 */
public class T {

    synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println(Thread.currentThread().getName() + " m1 start...");
    }

    synchronized void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 ");
    }

    public static void main(String[] args) {
        new Thread(() -> new T().m1(), "t").start();
    }
}
