package com.me.leetcode;


import java.math.BigDecimal;
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
            }
        });

        for (int i = resultList.size() - 1; i >= 0; i--) {
            builder.append(resultList.get(i));
        }

        String sss = builder.toString();
        // 使用正则判断
//        System.out.println();
//        if (sss.matches("^[0]*$")){
//            return sss = "0";
//        }

        // 使用数字求和计算
        int sum = 0;
        for (int i = 0; i < sss.length(); ++i) {
            sum += sss.charAt(i) - '0';
        }
        if(sum == 0){
            return "0";
        }

        return sss;
    }

    public static void main(String[] args) {
        int[] arrs = {8308,8308,830};
        String number = largestNumber(arrs);
        System.out.println(number);
    }
}
