package com.me.leetcode;

import java.util.Arrays;

/**
 * @author zs
 * @date 2021/10/8.
 * 66. 加一
 */
public class lc_0066 {

    public static int[] plusOne(int[] digits) {
        int tmp = 1;
        for (int i = digits.length - 1; i >= 0; --i) {
            digits[i] += tmp;
            tmp = digits[i] / 10;
            digits[i] = digits[i] % 10;
            if (tmp == 0) {
                return digits;
            }
        }
        int[] nums = new int[digits.length + 1];
        System.arraycopy(nums, 0, nums, 1, digits.length);
        nums[0] = 1;
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {8, 9, 9, 9};
        int[] ints = plusOne(nums);
        for (int num : ints) {
            System.out.println(num);
        }
    }
}
