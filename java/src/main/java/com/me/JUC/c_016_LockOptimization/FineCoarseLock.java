package com.me.JUC.c_016_LockOptimization;

import java.util.concurrent.TimeUnit;

/**
 * synchronized优化
 * 同步代码块的语句越少越好
 * 比较m1和m2
 *
 * 如果方法里面有很多锁，还不如加在方法上，这样锁的争用频率降低，反而提高了效率（锁的细化）
 */
public class FineCoarseLock {
    int count = 0;

    synchronized void m1() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 业务逻辑中只有下面这句话需要sync，这时不应该给整个方法上锁
        count++;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 业务逻辑中只有下面这句话需要sync，这时不应该给整个方法上锁
        // 采用细粒度的锁，可以使线程争用时间变短，从而提高效率
        synchronized (this) {
            count++;
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
