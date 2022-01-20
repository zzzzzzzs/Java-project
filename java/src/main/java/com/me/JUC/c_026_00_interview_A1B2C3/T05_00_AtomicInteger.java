package com.me.JUC.c_026_00_interview_A1B2C3;

import java.util.concurrent.atomic.AtomicInteger;

public class T05_00_AtomicInteger {
    static AtomicInteger threadNo = new AtomicInteger(2);

    public static void main(String[] args) {
        char[] aI = "0123456789".toCharArray();
        char[] aC = "ABCDEFGHIJ".toCharArray();

        new Thread(() -> {
            for (char c : aI) {
                while (threadNo.get() != 1) {

                }
                System.out.print(c);
                threadNo.set(2);
            }
        }, "t1").start();
        new Thread(() -> {
            for (char c : aC) {
                while (threadNo.get() != 2) {}
                System.out.print(c);
                threadNo.set(1);
            }
        }, "t2").start();

    }
}
