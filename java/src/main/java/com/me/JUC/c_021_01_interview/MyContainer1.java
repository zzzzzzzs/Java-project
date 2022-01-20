package com.me.JUC.c_021_01_interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题： 写一个固定容量同步容器，拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程以及10消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 * @author Pang
 */
public class MyContainer1<T> {
    final private LinkedList<T> list = new LinkedList();
    final private int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        while (list.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        count ++;
        this.notifyAll();
    }

    public synchronized T get() {
        T result = null;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = list.removeFirst();
        count--;
        this.notifyAll();
        return result;
    }

    public synchronized int getCount() {
        return this.count;
    }

    public static void main(String[] args) {
        MyContainer1 c = new MyContainer1();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int k = 0; k < 5; k++) {
                    System.out.println(Thread.currentThread().getName() + " 消费了 " + c.get());
                }
            }, "consumer-" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int k = 0; k < 25; k++) {
                    c.put(Thread.currentThread().getName() + " - " + k + " 号产品");
                }
            }, "producer-" + i).start();
        }
    }
}
