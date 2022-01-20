package com.me.JUC.c_026_00_interview_A1B2C3;

public class T03_00_cas {
    enum ReadyToRun {T1, T2}

    static volatile ReadyToRun r = ReadyToRun.T2; //思考为什么必须volatile

    public static void main(String[] args) {
        char[] aI = "0123456789".toCharArray();
        char[] aC = "ABCDEFGHIJ".toCharArray();

        new Thread(() -> {
            for (char c : aI) {
                while (r != ReadyToRun.T1) {

                }
                System.out.print(c);
                r = ReadyToRun.T2;
            }
        }, "t1").start();
        new Thread(() -> {
            for (char c : aC) {
                while (r != ReadyToRun.T2) {}
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        }, "t2").start();
    }
}
