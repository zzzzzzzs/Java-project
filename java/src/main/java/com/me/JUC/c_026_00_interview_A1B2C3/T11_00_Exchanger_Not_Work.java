package com.me.JUC.c_026_00_interview_A1B2C3;

import java.util.concurrent.Exchanger;

public class T11_00_Exchanger_Not_Work {
    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        char[] aI = "0123456789".toCharArray();
        char[] aC = "ABCDEFGHIJ".toCharArray();

        new Thread(() -> {
            for (int i = 0; i < aI.length; i++) {
                System.out.print(aI[i]);
                try {
                    exchanger.exchange("T1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
        new Thread(() -> {
            for (int i = 0; i < aC.length; i++) {
                try {
                    exchanger.exchange("T2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(aC[i]);
            }
        }, "t2").start();
    }
}
