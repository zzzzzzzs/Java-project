package com.me.other;


import java.util.HashMap;
import java.util.function.BiConsumer;

public class Test5 {
    // 二分查找



    public static int find(int[] nums, int tartget){
        int i = 0;
        int j = nums.length -1;
        while (i <= j) {
            int m = (j + i) / 2;
            if (nums[m] < tartget) {
                i = m + 1;
            } else if(tartget <  nums[m]) {
                j = m - 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3, 4, 4, 5, 6, 6};
        System.out.println(find(nums, 10));


    }
}
