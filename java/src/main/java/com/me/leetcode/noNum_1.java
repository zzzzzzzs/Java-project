package com.me.leetcode;

import java.util.Arrays;

/**
 * @author zs
 * @date 2021/9/10.
    最长的连续序列的长度
    题目描述
    给定无序数组arr，返回其中最长的连续序列的长度(要求值连续，位置可以不连续,例如 3,4,5,6为连续的自然数）
 */
public class noNum_1 {
    /**
     * max increasing subsequence
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public static int MLS (int[] arr) {
        // 先排序
        Arrays.sort(arr);
        // 用来表示i结尾最长上升子序列
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int maxValue = Integer.MIN_VALUE;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] == arr[i-1]){
                dp[i] = dp[i-1];
            } else if(arr[i] == arr[i-1] + 1){
                dp[i] = dp[i-1] + 1;
            } else {
                dp[i] = 1;
            }
            maxValue = Math.max(maxValue, dp[i]);
        }
        return maxValue;
    }
    public static void main(String[] args) {
        int[] arr = {1,4,3,9,1,2,11};
        int mls = MLS(arr);
        System.out.println(mls);
    }
}
