package com.me.JUC.c_028_FalseSharing;

import com.google.common.base.Stopwatch;

/**
 * @author zs
 * @date 2022/1/14
 */
public class T03_CacheLinePadding {

    /**
     * 前后都做了填充，这样确保了 x 在临近的缓存行中都可以获取到。
     */
    private static class T {
        public long p1, p2, p3, p4, p5, p6, p7;
        public long x = 0L;
        public long p9, p10, p11, p12, p13, p14, p15;
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