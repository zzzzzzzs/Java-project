package com.me.JUC.c_027_future_to_loom;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用future进行异步操作
 * 缺点：
 * 不知道何时结束
 * 阻塞获取结果
 */
public class T01_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> future = service.submit(() -> {
            return 8;
        });

        int i = future.get();
        System.out.println(i);
        service.shutdown();
    }
}
