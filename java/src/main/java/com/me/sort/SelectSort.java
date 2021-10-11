package com.me.sort;

/**
 * @author zs
 * @date 2021/10/11.
 * 选择排序
 */
public class SelectSort {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /*
     * 在这个过程中，开辟了空间i, j, minIndex，而且minIndex每次用完就释放了。
     * 一共就是有限的空间维持程序，额外空间复杂度就是O(1)
     **/
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) { // i ~ N-1
            int maxIndex = i;
            for (int j = i + 1; j < arr.length; j++) { // i ~ N-1上找最小值的下标
                maxIndex = arr[j] < arr[maxIndex] ? j : maxIndex;
            }
            swap(arr, i, maxIndex);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, 6};
        selectSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
