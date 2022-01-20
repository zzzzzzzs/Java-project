package com.me.JUC.c_020_juclocks;

import java.util.concurrent.Semaphore;

public class T11_TestSemphore {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(2, true);

        new Thread(() -> {
            try {
                s.acquire();

                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
