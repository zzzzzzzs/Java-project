package com.me.jvm;


/**
 * @author zs
 * @date 2021/10/4.

 */
public class HeapTuningDemo1 {
    public static void main(String[] args) {

        // TODO : VM参数： -Xms1024m -Xmx1024m -XX:+PrintGCDetails
        // 返回 Java 虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        // 返回 Java 虚拟机中的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("MAX_MEMORY = " + maxMemory + "（字节）、" + (maxMemory / (double) 1024 / 1024) + "MB");
        System.out.println("TOTAL_MEMORY = " + totalMemory + "（字节）、" + (totalMemory / (double) 1024 / 1024) + "MB");
    }
}
