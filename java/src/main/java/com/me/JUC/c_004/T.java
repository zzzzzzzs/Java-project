package com.me.JUC.c_004;

/**
 * synchronized关键字
 * 对某个对象加锁
 */
public class T {
    private static int count = 10;
    public synchronized static void m() {
        // 这里等同于synchronized(T.class)
        count--;
        System.out.println(Thread.currentThread().getName() + " count is " + count);
    }

    // 静态方法没有 this 对象，每一个 class 文件 load 到内存会生成一个专门的对象，这里是 T.class
    public static void mm() {
        synchronized (T.class) {
            count--;
        }
    }
}
