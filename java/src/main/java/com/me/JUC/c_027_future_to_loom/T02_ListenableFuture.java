package com.me.JUC.c_027_future_to_loom;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Executors;
// JDK9
public class T02_ListenableFuture {
    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
        ListenableFuture<Integer> future = service.submit(() -> 8);

//        Futures.addCallback(future, new FutureCallback<>() {
//            public void onSuccess(@Nullable Integer integer) {
//                System.out.println(integer);
//            }
//
//            public void onFailure(Throwable throwable) {
//                throwable.printStackTrace();
//            }
//        }, service);
//
//        service.shutdown();

    }
}
