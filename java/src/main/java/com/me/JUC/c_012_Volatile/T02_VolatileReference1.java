package com.me.JUC.c_012_Volatile;

import java.util.concurrent.TimeUnit;

/**
 * volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 *
 * @author Pang
 */
public class T02_VolatileReference1 {
    /*volatile*/ boolean running = true;

    volatile static T02_VolatileReference1 T = new T02_VolatileReference1();

    void m() {
        System.out.println("m() start");
        while (running) {

        }
        System.out.println("m() end");
    }

    public static void main(String[] args) {
        new Thread(T::m).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        T.running = false;
    }
}
