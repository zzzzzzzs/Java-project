import java.util.ArrayList;
import java.util.List;

public class Test1 {

  // 归并排序
  public static void mergeSort(int[] arr) {
    int len = arr.length;
    if (len < 2) {
      return;
    }
    int mid = len / 2;
    int[] left = new int[mid];
    int[] right = new int[len - mid];
    for (int i = 0; i < mid; i++) {
      left[i] = arr[i];
    }
    for (int i = mid; i < len; i++) {
      right[i - mid] = arr[i];
    }
    mergeSort(left);
    mergeSort(right);
    merge(arr, left, right);
  }

  public static void merge(int[] arr, int[] left, int[] right) {
    int i = 0, j = 0, k = 0;
    while (i < left.length && j < right.length) {
      if (left[i] < right[j]) {
        arr[k++] = left[i++];
      } else {
        arr[k++] = right[j++];
      }
    }
    while (i < left.length) {
      arr[k++] = left[i++];
    }
    while (j < right.length) {
      arr[k++] = right[j++];
    }
  }

  ArrayList<Integer>  mergeSortFun(int[][] arr, int min, int max) {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        list.add(arr[i][j]);
      }
    }
    int[] arr1 = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
      arr1[i] = list.get(i);
    }
    mergeSort(arr1);
    ArrayList<Integer> list1 = new ArrayList<>();
    for (int i = 0; i < arr1.length; i++) {
     if(arr1[i]>=min && arr1[i]<max){
       list1.add(arr1[i]);
     }
    }
    return list1;
  }

  public static void main(String[] args) {
    Test1 test = new Test1();
    int[][] arr = {{1, 3, 10}, {2, 5, 8}, {4, 6}, {12, 13}, {11, 14}};

    ArrayList<Integer> integers = test.mergeSortFun(arr, 5, 10);
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < integers.size(); i++) {
        sb.append(integers.get(i));
        if (i != integers.size() - 1) {
            sb.append(",");
        }
    }
    sb.append("]");
    System.out.println(sb.toString());
  }
}
