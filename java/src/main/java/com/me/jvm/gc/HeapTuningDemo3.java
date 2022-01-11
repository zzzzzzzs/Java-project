package com.me.jvm.gc;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zs
 * @date 2021/10/4.

 */
public class HeapTuningDemo3 {
    // TODO vm参数 -Xms8m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\
    byte[] byteArray = new byte[1 * 1024 * 1024]; //1MB
    public static void main(String[] args) {
        List<HeapTuningDemo3> list = new ArrayList<>();
        int count = 0;
        try {
            while (true){
                list.add(new HeapTuningDemo3());
                count = count + 1;
            }
            // TODO Exception 捕获不到GC异常。有问题找父类，这就是双亲委派。借助工具容易捕获异常。
        } catch (Throwable e) {
            System.out.println("******count=" + count);
            e.printStackTrace();
        }
    }
}

