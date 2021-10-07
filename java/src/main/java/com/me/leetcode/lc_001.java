package com.me.leetcode;

import java.util.*;

/**
 * @author zs
 * @date 2021/10/7.
 * 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 */
public class lc_001 {
    public static int[] twoSum(Integer[] nums, int target) {
        Map<Integer, Integer> intMap= new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(intMap.containsKey(nums[i])){
                return new int[]{intMap.get(nums[i]),i};
            }
            // TODO 将差值缓存起来
            intMap.put(target-nums[i],i);
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        Integer[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] indexs = twoSum(nums, target);
    }
}
