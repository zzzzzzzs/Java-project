package com.me.JUC.c_021_02_AQS;

import java.util.concurrent.locks.Lock;

public class Main {
    public static int m = 0;
    public static Lock lock = new MLock();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(() -> {
                    lock.lock();
                    try {
                        for (int k = 0; k < 100; k++) m++;
                    } finally {
                        lock.unlock();
                    }
                });
        }

        for (Thread thread : threads) thread.start();

        for (Thread thread : threads) thread.join();

        System.out.println(m);
    }
}
