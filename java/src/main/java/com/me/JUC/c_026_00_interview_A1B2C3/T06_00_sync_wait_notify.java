package com.me.JUC.c_026_00_interview_A1B2C3;

public class T06_00_sync_wait_notify {
    public static void main(String[] args) {
        final Object o = new Object();

        char[] aI = "0123456789".toCharArray();
        char[] aC = "ABCDEFGHIJ".toCharArray();

        new Thread(() -> {
            synchronized (o) {
                for (char c : aI) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(c);
                    o.notifyAll();
                }
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (o) {
                for (char c : aC) {
                    System.out.print(c);
                    try {
                        o.notifyAll();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t2").start();
    }
}
