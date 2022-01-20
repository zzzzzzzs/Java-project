package com.me.JUC.c_022_RefTypeAndThreadLocal;

import java.io.IOException;

// 强引用
public class T01_NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc(); //DisableExplicitGC

        System.in.read();
    }
}
