package com.me.JUC.c_026_01_ThreadPool;

import java.util.concurrent.*;

/**
 * 认识Callable，对runnable进行了扩展
 * 对callable的调用，可以有返回值
 */
public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> c = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello Callable";
        };

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(c); // 异步
        //阻塞
        System.out.println(future.get());

        service.shutdown();
    }
}
