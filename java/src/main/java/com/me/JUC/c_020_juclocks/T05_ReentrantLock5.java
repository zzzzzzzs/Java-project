package com.me.JUC.c_020_juclocks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantLock用于替代synchronized
 * 本例中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 这里是复习synchronized最原始的语义
 * <p>
 * 使用reentrantLock可以完成同样的功能
 * 需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 * 使用sync锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须要手动释放锁，因此经常在finally中进行锁的释放
 * <p>
 * 使用reentrantlock可以进行尝试锁定 trylock， 这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 * <p>
 * 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 在一个线程等待锁的过程中，可以被打断
 *
 * ReentrantLock还可以指定为公平锁
 * @author Pang
 */
public class T05_ReentrantLock5 extends Thread{
    // 参数为true表示为公平锁，请对比输出结果
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock5 rl = new T05_ReentrantLock5();
        Thread t1 = new Thread(rl);
        Thread t2 = new Thread(rl);
        t1.start();
        t2.start();
    }
}
