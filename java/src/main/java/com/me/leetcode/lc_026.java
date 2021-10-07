package com.me.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zs
 * @date 2021/10/7.
 * 删除有序数组中的重复项
 */
public class lc_026 {

    // 使用双指针
    // https://github.com/azl397985856/leetcode/blob/master/problems/26.remove-duplicates-from-sorted-array.md
    public static int removeDuplicates2(int[] nums) {
        int slow = 0, quick = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[slow] ==  nums[quick]){
                quick++;
            }else {
                slow++;
                nums[slow] = nums[quick];
                quick++;
            }
        }
        return ++slow;
    }

    // 使用set集合
    public static int removeDuplicates1(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        int i = 0;
        for (int num:nums){
            if (set.add(num)) {
                nums[i++] = num;
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int i = removeDuplicates2(nums);
        System.out.println(i);
    }
}
