package com.me.JUC.c_028_FalseSharing;

import com.google.common.base.Stopwatch;

/**
 * @author zs
 * @date 2022/1/11
 * 缓存行
 */
public class T01_CacheLinePadding {
    // 在内存中有一个 T 对象，在 T 对象中有一个 x ，x 是 long 类型，8bytes。一个缓存行是64bytes。
    private static class T {
        public volatile long x = 0L;
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
