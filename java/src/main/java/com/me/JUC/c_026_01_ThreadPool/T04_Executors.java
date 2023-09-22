package com.me.JUC.c_026_01_ThreadPool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class T04_Executors {
    public static void main(String[] args) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("aaa");
            }
        });
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("bbb");
            }
        });
        // 对于这个程序，主线程执行完了退出，异步任务还没执行完，因此需要加一个 join()
        future.join(); // 等待任务完成
    }
}
