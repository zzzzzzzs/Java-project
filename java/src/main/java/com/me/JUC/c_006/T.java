package com.me.JUC.c_006;

/**
 * 相比上一个程序 分析一下这个程序的输出
 */
public class T implements Runnable {
    private int count = 10;
    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + "count is " + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 5; i++) {
            new Thread(t, "THREAD-" + i).start();
        }
    }
}
