package com.me.leetcode;

/**
 * @author zs
 *@date 2021/9/10.
编写3个排序函数,并写出空间复杂度和时间复杂度。
 */
public class noNum_2 {
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //实现静态方法冒泡排序 时间 O(n²) 空间 O（1）
    public static void bubbling(int[] arr, boolean isDesc) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(isDesc == true){
                    if (arr[j] - arr[j +1] > 0) {
                        swap(arr, j, j + 1);
                    }
                }else {
                    if (arr[j] - arr[j +1] <= 0) {
                        swap(arr, j, j + 1);
                    }
                }

            }
        }
    }
    //选择排序 时间 O(n²) 空间 O（1）
    public static void select(int[] arr, boolean isDesc) {
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = 0; j < arr.length; j++) {
                if(isDesc == true){
                    if (arr[j] - arr[minIndex] > 0) {
                        minIndex = j;
                    }
                }else {
                    if (arr[j] - arr[minIndex] <= 0) {
                        minIndex = j;
                    }
                }

            }
            swap(arr, i, minIndex);
        }
    }
    //快速排序 时间 O(nlog2n) 空间 O(nlog2n)
    public static void quick(int[] arr, int begin, int end, boolean isdesc) {
        if (begin >= end) {
            return;
        }
        int key = arr[begin];
        int keyIndex = begin;
        for (int i = begin + 1; i < end; i++) {
            if(isdesc == true){
                if (arr[i] - key < 0) {
                    keyIndex++;
                    swap(arr, i, keyIndex);
                }
            }else {
                if (arr[i] - key >= 0) {
                    keyIndex++;
                    swap(arr, i, keyIndex);
                }
            }

        }
        arr[begin] = arr[keyIndex];
        arr[keyIndex] = key;
        quick(arr, begin, keyIndex, isdesc);
        quick(arr, keyIndex + 1, end, isdesc);
    }


    public static int[] sort(int[] arr, boolean isDesc, int w){
        if (w == 1){
            bubbling(arr, isDesc);
        }else if(w == 2){
            select(arr, isDesc);
        }else {
            quick(arr, 1, arr.length, isDesc);
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 1, 3, 9, 3, 1};
        sort(arr, true, 1);
    }

}
