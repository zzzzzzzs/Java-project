package com.me.JUC.c_026_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 认识ExecutorService 阅读API文档
 * 认识submit方法，扩展了execute方法，具有一个返回值
 */
public class T02_ExecutorService {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
    }
}
