package com.me.JUC.c_012_Volatile;

/**
 * volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 *
 * @author Pang
 */
public class T03_VolatileReference2 {
    private static class Data {
        int a;
        int b;

        public Data(int x, int y) {
            this.a = x;
            this.b = y;
        }
    }

    volatile static Data data;

    public static void main(String[] args) {
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                data = new Data(i, i);
            }
        }, "t1");
        Thread reader = new Thread(() -> {
            while (data == null) {

            }
            int x = data.a;
            int y = data.b;
            if (x != y) {
                System.out.printf("a = %s, b = %s%n", x, y);
            }
        }, "t2");
        writer.start();
        reader.start();

        try {
            writer.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
