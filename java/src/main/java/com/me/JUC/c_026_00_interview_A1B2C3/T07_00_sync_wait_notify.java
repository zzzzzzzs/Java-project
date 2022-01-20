package com.me.JUC.c_026_00_interview_A1B2C3;

public class T07_00_sync_wait_notify {
    private static volatile boolean t2started = false;

    public static void main(String[] args) {
        char[] aI = "0123456789".toCharArray();
        char[] aC = "ABCDEFGHIJ".toCharArray();

        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                while (!t2started) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char c : aI) {
                    System.out.print(c);
                    try {
                        o.notifyAll();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notifyAll();
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (o) {
                for (char c : aC) {
                    System.out.print(c);
                    t2started = true;
                    try {
                        o.notifyAll();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notifyAll();

            }
        }, "t2").start();
    }
}
