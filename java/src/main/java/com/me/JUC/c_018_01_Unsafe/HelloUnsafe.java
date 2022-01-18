package com.me.JUC.c_018_01_Unsafe;

import sun.misc.Unsafe;

public class HelloUnsafe {
    static class M {
        public M() {
        }

        int i = 0;
    }

    public static void main(String[] args) throws InstantiationException {
        Unsafe unsafe = Unsafe.getUnsafe();
        M m = (M) unsafe.allocateInstance(M.class);
        m.i = 9;
        System.out.println(m.i);
    }
}
