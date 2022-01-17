package com.me.JUC.c_008;

import java.util.concurrent.TimeUnit;

/**
 * 面试题： 模拟银行账户
 * 对业务写方法加锁
 * 对业务都方法不加锁
 * 这样行不行？
 *
 * 容易产生脏读问题（dirtyRead）
 */
public class Account {
    String name;
    double blance;

    public synchronized void set(String name, double blance) {
        this.name = name;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.blance = blance;
    }

    public double get(String name) {
        return this.blance;
    }

    public static void main(String[] args) {
        Account account = new Account();

        new Thread(() -> account.set("zhangsan", 100)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.get("zhangsan"));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.get("zhangsan"));
    }
}
