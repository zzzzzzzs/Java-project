package com.me.leetcode;

/**
 * @author zs
 * @date 2021/10/7.
 * 最大子序和
 * 算法导论 第四章 分治策略
 */
public class lc_053 {
    public static int maxSubArray1(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int sumOld = 0;
        int sumLast = 0;
        for (int i = 0; i < nums.length; i++) {
//            if()
        }
        return 0;
    }
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int i = maxSubArray1(nums);
        System.out.println(i);
    }
}
