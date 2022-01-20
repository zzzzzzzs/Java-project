package com.me.JUC.c_021_01_interview;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题： 写一个固定容量同步容器，拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程以及10消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 *
 * 使用Lock 和 Condition 来实现
 * 对比两种方式， condition的方式可以更加精确的指定哪些线程被唤醒
 * @author Pang
 */
public class MyContainer2<T> {

    private final LinkedList<T> lists = new LinkedList<>();
    // 最多10个元素
    private final int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            // 想想为什么用while而不用if？
            while (lists.size() == MAX) {
                producer.await();
            }
            lists.add(t);
            count++;
            consumer.signalAll(); // 通知消费者线程进行消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (lists.size() == 0) {
                consumer.await();
            }
            t = lists.removeFirst();
            count--;
            producer.signalAll(); // 通知生产者线程进行生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            return t;
        }
    }

    public static void main(String[] args) {
        MyContainer2<String> c = new MyContainer2();

        // 启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int k = 0; k < 5; k++) {
                    System.out.println(Thread.currentThread().getName() + " 消费了 " + c.get());
                }
            }, "consumer-" + i).start();
        }

        // 启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int k = 0; k < 25; k++) {
                    c.put(Thread.currentThread().getName() + " - " + k + "  号产品");
                }
            }, "producer-" + i).start();
        }
    }
}
