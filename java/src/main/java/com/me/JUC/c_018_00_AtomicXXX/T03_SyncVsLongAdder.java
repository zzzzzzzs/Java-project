package com.me.JUC.c_018_00_AtomicXXX;

import java.util.concurrent.atomic.LongAdder;

public class T03_SyncVsLongAdder {
    static long count2 = 0L;
    static LongAdder count = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[500];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 100000; k++) {
                    count.increment();
                }
            });
        }

        long start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("LongAdder: " + count.longValue() + " time : " + (end - start));

        //-----------------------------------------------------------
        Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 100000; k++) {
                    synchronized (lock) {
                        count2++;
                    }
                }
            });
        }

        start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        end = System.currentTimeMillis();
        System.out.println("Sync: " + count.longValue() + " time : " + (end - start));
    }
}
