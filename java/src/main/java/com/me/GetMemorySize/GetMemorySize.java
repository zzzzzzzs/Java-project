package com.me.GetMemorySize;

import org.apache.lucene.util.RamUsageEstimator;

public class GetMemorySize {
    public static void main(String[] args) {
        String s = "";

        //下面三个方法参数都是 Object类型

        //计算指定对象及其引用树上的所有对象的综合大小，单位字节
        long a =  RamUsageEstimator.sizeOf(s);
        System.out.println(a);

        //计算指定对象本身在堆空间的大小，单位字节
        long b = RamUsageEstimator.shallowSizeOf(s);
        System.out.println(b);

        //计算指定对象及其引用树上的所有对象的综合大小，返回可读的结果，如：2KB
        String c = RamUsageEstimator.humanSizeOf(s);
        System.out.println(c);

    }
}



