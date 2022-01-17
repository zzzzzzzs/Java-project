package com.me.JUC.c_000;

import java.util.concurrent.TimeUnit;

public class T01_WhatIsThread {
    private static class T1 extends Thread {
        @Override
        public void run() {
           for(int i=0; i<10; i++) {
               try {
                   TimeUnit.MICROSECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("T1");
           }
        }
    }

    public static void main(String[] args) {
        // TODO 有2中方式调用
//        new T1().run(); // 这种方式是先输出 T1 ，在输出 main。这种就是方法调用。main() 开始，然后执行 run()，然后再次回到main()，这种在程序中只有一条路径
        new T1().start(); // 这种方式是交替输出。调用 start，main() 继续运行，然后 run() 同时运行。
        for(int i=0; i<10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }

    }
}
