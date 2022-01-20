package com.me.JUC.c_020_01_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 曾经的面试题（淘宝）
 * 实现一个容器，提供两个方法，add， size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数达到5个时，线程2给出提示并结束
 * <p>
 * 给list添加volatile之后，t2额能够接到通知，但是，t2线程的死循环很浪费cpu， 如果不用死循环，该怎么做呢
 * <p>
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是， 运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 * <p>
 * 阅读下面程序，并分析输出结果
 * 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才会接收到通知而退出
 * 想想这是为什么？
 * <p>
 * notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行
 * 整个通信过程比较繁琐
 * <p>
 * 使用Latch（门闩） 替代wait notify来进行通信
 * 好处是通信方式简单，同时也可以指定等待时间
 * 使用await和countdown 方法替代wait和notify
 * CountDownLatch不涉及锁定，当count的值为零时当前线程继续运行
 * 当不涉及同步，只是设计线程通信的时候，用synchronized + wait/notify就显得太重了
 * 这是应该考虑countdownlatch/cyclicBarrier/semaphore
 *
 * @author Pang
 */
public class T07_LockSupport_WithoutSleep {

    volatile List lists = new ArrayList();

    void add(Object o) {
        this.lists.add(o);
    }

    int size() {
        return this.lists.size();
    }

    static void secSleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static Thread t1 = null, t2 = null;
    public static void main(String[] args) {
        T07_LockSupport_WithoutSleep c = new T07_LockSupport_WithoutSleep();

        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);
                if (c.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
                secSleep(1);
            }
        }, "t1");

        t2 = new Thread(() -> {
            System.out.println("t2 start");
            LockSupport.park();
            System.out.println("t2 end");
            LockSupport.unpark(t1);
        }, "t2");
        t1.start();
        t2.start();
    }
}
