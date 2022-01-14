package com.me.Order;

import java.util.concurrent.CountDownLatch;

/**
 * @author zs
 * @date 2022/1/14
 * 证明程序是乱序执行的
 * https://preshing.com/20120515/memory-reordering-caught-in-the-act/
 */
public class T01_Disorder {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch latch = new CountDownLatch(2);

            Thread one =
                    new Thread(
                            () -> {
                                a = 1;
                                x = b;

                                latch.countDown();
                            });

            Thread other =
                    new Thread(
                            () -> {
                                b = 1;
                                y = a;
                                latch.countDown();
                            });

            one.start();
            other.start();
            latch.await();
            String result = "第" + i + "次 (" + x + "," + y + ")";
            if (i % 100000 == 0) {
                System.out.println(result);
            }

            if (x == 0 && y == 0) {
                System.err.println(result);
                // break;
            }
        }
    }
}
