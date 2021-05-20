package com.me.thread;


import java.util.concurrent.*;


public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = ThreadPoolUtil.getInstance();
        // 设置的线程数是10，因此一次打印10个
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    int j = 0;
                    while (true){
                        System.out.println("当前的线程号" + Thread.currentThread().getId() + " j = " + j++);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        };
    }
}
