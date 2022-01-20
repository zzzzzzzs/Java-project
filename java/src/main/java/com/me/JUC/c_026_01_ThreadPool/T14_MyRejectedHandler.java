package com.me.JUC.c_026_01_ThreadPool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class T14_MyRejectedHandler {
    public static void main(String[] args) {

    }

    static class MyRejectedHandler implements RejectedExecutionHandler {
        // log("r rejected")
        // save r kafka mysql redis
        // try 3 times
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (executor.getQueue().size() < 10000) {
                // try put again();
            }
        }
    }

}
