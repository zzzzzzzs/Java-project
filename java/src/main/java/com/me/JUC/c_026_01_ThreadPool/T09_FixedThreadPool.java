package com.me.JUC.c_026_01_ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class T09_FixedThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        final int cpuCoreNum = 4;

        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);

        MyTask task1 = new MyTask(1, 80000);
        MyTask task2 = new MyTask(80000, 130000);
        MyTask task3 = new MyTask(130000, 170000);
        MyTask task4 = new MyTask(170000, 200000);

        Future<List<Integer>> f1 = service.submit(task1);
        Future<List<Integer>> f2 = service.submit(task2);
        Future<List<Integer>> f3 = service.submit(task3);
        Future<List<Integer>> f4 = service.submit(task4);

        start = System.currentTimeMillis();

        f1.get();
        f2.get();
        f3.get();
        f4.get();

        end = System.currentTimeMillis();
        System.out.println(end - start);
        service.shutdown();
    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        public MyTask(int startPos, int endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> result = getPrime(startPos, endPos);
            return result;
        }
    }


    static boolean isPrime(int num) {
        int max = num / 2;
        for (int i = 2; i < max; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    static List<Integer> getPrime(int start, int end) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (isPrime(i)) result.add(i);
        }
        return result;
    }
}
