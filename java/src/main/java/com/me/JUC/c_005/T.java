package com.me.JUC.c_005;

/**
 * 分析一下这个程序的输出
 */
public class T implements Runnable {

    private int count = 100;

    // 问题是：count 还没有修改，就被别的线程拿到了，然后输出的是上一次的值。
    // 加 synchronized 就可以解决问题，synchronized 既保证了原子性，也保证了可见性
    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count is " + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }
}
