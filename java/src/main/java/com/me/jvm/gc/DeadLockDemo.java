package com.me.jvm.gc;

/**
 * @author zs
 * @date 2022/1/6
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                synchronized (lockA) {
                    System.out.println(name + " got lockA,  want LockB");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    synchronized (lockB) {
                        System.out.println(name + " got lockB");
                        System.out.println(name + ": say Hello!");
                    }
                }
            }
        }, "thread-A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                String name = Thread.currentThread().getName();
                synchronized (lockB) {
                    System.out.println(name + " got lockB, want LockA");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    synchronized (lockA) {
                        System.out.println(name + " got lockA");
                        System.out.println(name + ": say Hello!");
                    }
                }

            }
        }, "thread-B").start();
    }
}
