package com.me.leetcode;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 找最大数
public class lc_1791 {
    public static void main(String[] args) {
        StringBuilder sss = new StringBuilder("0");
        sss.append("0");
        sss.append("0");
        sss.append("0");
        sss.append("0");

        int j = 0;
        for (int i = 0; i < sss.length(); ++i) {
             j += sss.charAt(i) - '0';
        }

        System.out.println(j);
    }
}
