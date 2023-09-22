package com.me.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i1 = sc.nextInt();
        int i2 = sc.nextInt();
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = i1; i <= i2; i++) {
            for (int j = i + 1; j <= i2; j++) {
                if (checkFlag(i, j, i2)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    list.add((int) Math.sqrt(i * i + j * j));
                    Collections.sort(list);
                    lists.add(list);
                }
            }
        }
        int idx = 0;
        while (idx < lists.size()) {
            List<Integer> list = lists.get(idx);
            if (checkYue(list.get(0), list.get(1), list.get(2))) {
                lists.remove(idx);
                idx = 0;
            }
            idx++;
        }
        if(lists.size()==0){
            System.out.println("NA");
        }
        for (List<Integer> list : lists) {
            System.out.println(list.get(0) + " " + list.get(1) + " " + list.get(2));
        }
    }

    private static boolean checkFlag(int a1, int a2, int max) {
        int mul = a1 * a1 + a2 * a2;
        double res = Math.sqrt(mul);
        int sub = (int) res;
        return res * res == sub * sub && sub <= max && sub > a2;
    }

        private static boolean checkYue(int a1, int a2, int a3) {
        for (int i = 2; i < a3; i++) {
            if (a1 % i == 0 && a2 % i == 0) {
                return true;
            } else if (a1 % i == 0 && a3 % i == 0) {
                return true;
            } else if (a2 % i == 0 && a3 % i == 0) {
                return true;
            }
        }
        return false;
    }
//    public static boolean checkYue(int a, int b, int c) {
//        int min = Math.min(Math.min(a, b), c);
//        for (int i = 2; i <= min; i++) {
//            if (a % i == 0 && b % i == 0 && c % i == 0) {
//                return true;
//            }
//        }
//        return false;
//    }
}