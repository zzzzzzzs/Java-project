package com.me.other;


public class Test7 {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1000, 3, 2, 7, 5, 10};
        bubbleSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
