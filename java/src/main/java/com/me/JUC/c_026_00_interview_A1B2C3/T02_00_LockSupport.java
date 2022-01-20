package com.me.JUC.c_026_00_interview_A1B2C3;

import java.util.concurrent.locks.LockSupport;

public class T02_00_LockSupport {
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] aI = "0123456789".toCharArray();
        char[] aC = "ABCDEFGHIJ".toCharArray();

        t1 = new Thread(() -> {
            for (char c : aC) {
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c : aI) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
