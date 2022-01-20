package com.me.JUC.c_022_RefTypeAndThreadLocal;

public class M {
    // 自己不要重写 finalize()，有可能产生OOM，这个之后在垃圾回收的时候会被调用
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
