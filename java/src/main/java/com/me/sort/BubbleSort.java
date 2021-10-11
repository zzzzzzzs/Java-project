package com.me.sort;

/**
 * @author zs
 * @date 2021/10/11.
 * 冒泡排序
 */
public class BubbleSort {

    /**
     * 交换arr的i和j位置上的值
     * int a = 甲
     * int b = 乙
     * a = a ^ b;   a = 甲 ^ 乙                   b = 乙
     * b = a ^ b;   a = 甲 ^ 乙                   b = 甲 ^ 乙 ^ 乙 = 甲 ^ 0 = 甲
     * a = a ^ b;   a = 甲 ^ 乙 ^ 甲 = 0 ^ 乙 = 乙  b = 甲
     *
     * 这样交换不需要额外的空间，这样做的前提是 a 和 b 在内存中是2个独立的区域。可以a的值等于b的值。但是内存地址不一样。
     * 也就是说 i 位置不能等于 j 位置。如果 i 位置等于 j 位置，这个数就变成了 0
     * 不要这样做。
     **/
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) { // 0 ~ i
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, 6};
        bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
