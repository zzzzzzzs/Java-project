package com.me.JUC.c_026_00_interview_A1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T08_00_lock_condition {

    public static void main(String[] args) {
        char[] aI = "0123456789".toCharArray();
        char[] aC = "ABCDEFGHIJ".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();

                for (char c : aI) {
                    System.out.print(c);
                    condition.signalAll();
                    condition.await();
                }
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                lock.lock();
                for (char c : aC) {
                    System.out.print(c);
                    condition.signalAll();
                    condition.await();
                }
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
