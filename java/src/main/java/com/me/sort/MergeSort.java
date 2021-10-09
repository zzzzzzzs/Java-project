package com.me.sort;

import java.util.Arrays;

/**
 * @author zs
 * @date 2021/10/7.
 * TODO 分治法：每层递归有三个步骤
 *      1、分解， 将大问题分解成小问题
 *      2、解决， 子问题规模足够小，直接求解
 *      3、合并， 子问题的解成原问题的解
 */
public class MergeSort {
    /**
     * TODO 归井排序算法完全遵循分治模式。直观上其操作如下：
     *  1、分解：分解待排序的 n 个元素的序列成各具 n/2 个元素的两个子序列。
     *  2、解决：使用归并排序递归地排序两个子序列。
     *  3、合井：合并两个巳排序的子序列以产生已排序的答案。
     **/
    private static void mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) return;
//        int[] tmp = new int[nums.length];
        divide(nums, 0, nums.length);
    }

    private static void divide(int[] array, int first, int last) {
        if (first >= last){
            System.out.println(first +"," +last);
            return;
        }
        int mid = (first + last) / 2;
        divide(array, first, mid);
        divide(array, mid + 1, last);
//        Arrays.sort();
    }


    public static void main(String[] args) {
        int[] nums = {19, 15, 37, 12, 25};
        mergeSort(nums);
    }
}
