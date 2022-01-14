package com.me.JUC.c_028_FalseSharing;

import com.google.common.base.Stopwatch;

/**
 * @author zs
 * @date 2022/1/11
 * 缓存行
 */
public class T02_CacheLinePadding {
    // 在内存中有一个 T 对象，在 T 对象中有一个 x ，x 是 long 类型，8bytes。一个缓存行是64bytes。
    /**
     * 缓存系统中是以缓存行（cache line）为单位存储的。缓存行通常是 64 字节，并且它有效地引用主内存中的一块地址。
     * 一个 Java 的 long 类型是 8 字节，因此在一个缓存行中可以存 8 个 long 类型的变量
     * 故在x之前设置7个空的long类型，占用7个字节，加上x，正好是一个cache line
     */
    private static class T {
        public long p1, p2, p3, p4, p5, p6, p7;
        public long x = 0L;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });

        Stopwatch stopwatch = Stopwatch.createStarted();

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(stopwatch.toString());
    }
}
