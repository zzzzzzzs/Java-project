package com.me.jol;

import org.openjdk.jol.info.ClassLayout;

//https://github.com/openjdk/jol
public class HelloJOL {
    public static void main(String[] args) throws Exception{
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}