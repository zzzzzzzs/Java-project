package com.me.producerconsumer;

import java.util.concurrent.*;

// TODO 生产者消费者模型
public class MultiProducerSingleConsumerWithTimeout {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(1024 * 1024); // 创建一个有界的队列

        ExecutorService executor = Executors.newFixedThreadPool(5); // 创建线程池

        // 创建消费者线程
        Consumer consumer = new Consumer(queue);
        Thread consumerThread = new Thread(consumer, "Consumer");
        consumerThread.start();

        // 创建多个生产者线程
        for (int i = 0; i < 4; i++) {
            Producer producer = new Producer(queue, "Producer-" + i);
            executor.execute(producer);
        }

        // 关闭线程池
        executor.shutdown();
    }

    static class Producer implements Runnable {
        private final BlockingQueue<String> queue;

        public Producer(BlockingQueue<String> queue, String name) {
            this.queue = queue;
            System.out.println(name + " started.");
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    String item = generateItem();
                    queue.put(item); // 将数据放入队列
//                    System.out.println("Produced: " + item);
                    Thread.sleep((long) (Math.random() * 100)); // 随机休眠
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private String generateItem() {
            return "Item-" + System.currentTimeMillis();
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue<String> queue;

        public Consumer(BlockingQueue<String> queue) {
            this.queue = queue;
            System.out.println("Consumer started.");
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String item = queue.poll(1, TimeUnit.SECONDS); // 尝试获取数据，超时1秒
                    if (item != null) {
                        System.out.println("Consumed: " + item);
                    } else {
                        System.out.println("No data received in 1 second.");
                        Thread.sleep(1000); // 随机休眠
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
