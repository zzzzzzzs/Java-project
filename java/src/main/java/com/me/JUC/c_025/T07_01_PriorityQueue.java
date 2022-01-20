package com.me.JUC.c_025;

import java.util.PriorityQueue;

public class T07_01_PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();

        q.add("g");
        q.add("d");
        q.add("c");
        q.add("f");
        q.add("e");
        int size = q.size();
        for (int i = 0; i < size; i++) {
            System.out.println(q.poll());
        }
    }
}
