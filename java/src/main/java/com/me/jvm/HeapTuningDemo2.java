package com.me.jvm;


import java.util.Random;

/**
 * @author zs
 * @date 2021/10/4.

 */
public class HeapTuningDemo2 {
    public static void main(String[] args) {
        // 撑爆堆空间 java.lang.OutOfMemoryError: Java heap space
        // TODO vm参数： -Xms8m -Xmx8m -XX:+PrintGCDetails
        String str = "www.baidu.com";
        while (true) {
            str += str + new Random().nextInt(888888888) + new Random().nextInt(999999999);
        }
    }
}

