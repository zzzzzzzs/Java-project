package com.me.leetcode;


import java.util.*;

// 找最大数
public class lc_179 {
    public static String largestNumber(int[] nums) {
        List resultList = new ArrayList<>(nums.length);

        StringBuilder builder = new StringBuilder();

        for (int s : nums) {
            resultList.add(String.valueOf(s));
        }

        Collections.sort(resultList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                return (o1 + o2).compareTo( o2 + o1);

//                char[] chars1 = o1.toCharArray();
//                char[] chars2 = o2.toCharArray();
//                int i = 0;
//                while (i < chars1.length && i < chars2.length) {
//                    if (chars1[i] > chars2[i]) {
//                        return 1;
//                    } else if (chars1[i] < chars2[i]) {
//                        return -1;
//                    } else {
//                        i++;
//                    }
//                }
//                if(chars1.length==i && chars2[i] != '0'){
//                    return -1;
//                }
//
//                if(chars2.length == i){
//                    return 1;
//                }
//                return 0;

//                if (i >= chars2.length && i < chars1.length && chars1[i] == '0') { // 判断0的
//
//                    return -1;
//                }else if(i >= chars2.length && i < chars1.length && chars1[i] != '0'){
//                    return 1;
//                }else {
//                    return 0;
//                }
//                if (i == chars1.length) {  //o1到头
//                    return -1;
//                }
//                if (i == chars2.length) { //o2到头
//                    return 1;
//                }
//                return 0;
            }
        });


//        for (int i = 0; i < resultList.size(); i++) {
//            System.out.println(resultList.get(i));
//        }
//        System.out.println("---------------");

        for (int i = resultList.size() - 1; i >= 0; i--) {
            builder.append(resultList.get(i));
        }

        String sss = builder.toString();
        System.out.println();
        if (sss.matches("^[0]*$")){
            return sss = "0";
        }

        return sss;
    }

    public static void main(String[] args) {
        int[] arrs = {0, 0};
        String number = largestNumber(arrs);
        System.out.println(number);
    }
}
