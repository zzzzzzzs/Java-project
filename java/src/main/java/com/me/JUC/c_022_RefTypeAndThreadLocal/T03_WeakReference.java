package com.me.JUC.c_022_RefTypeAndThreadLocal;

import java.lang.ref.WeakReference;

/**
 * 弱引用遇到gc就会回收
 */
public class T03_WeakReference {
    public static void main(String[] args) {
        // m -> WeakReference 强引用，WeakReference 指向 M 是弱引用
        WeakReference<M> m = new WeakReference<>(new M());

        // 在有引用存在的情况下直接将 m 回收了，也就是说弱引用遇到 gc 就被回收了。一次性的效果。
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
   }
}
