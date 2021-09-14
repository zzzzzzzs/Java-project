import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.LinkedList;

/**
 * @author zs
 *@date 2021/9/7.

 */
public class Test {

    /*
     判断反转序列是否相同
     */
    public static void main(String[] args) {

        int[] a = new int[]{1, 2, 3, 4, 3, 2, 1, 1};
        System.out.println(compare(a));
//        int flag = swap(a);
//        if (flag == 1) {
//            System.out.println("a是回文数");
//        } else {
//            System.out.println("a不是回文数");
//
//        }

    }
    // 1 是回文数 ， 0不是
    public static int compare(int[] arr){
        for (int i = 0, m =  arr.length /2; i < m; ++i) {
            if(arr[i] != arr[arr.length - i -1]){
                return 0;
            }
        }
        return 1;
    }

    /*
    参数：数组
    返回值：标志位
     */
    public static int swap(int[] arr) {
        int n = arr.length;
        int flag = 1;
        //判断是偶数个还是奇数个
        if (n / 2 == 0) {
            for (int i = 0; i < n / 2; i++) {
                //如果不是回文数的话就跳出循环,标志位置零。
                if (arr[i] != arr[n - i - 1]) {
                    flag = 0;
                    break;
                }
            }
        } else {
            for (int i = 0; i < n - 1 / 2; i++) {
                //如果不是回文数的话就跳出循环,标志位置零。
                if (arr[i] != arr[n - i - 1]) {
                    flag = 0;
                    break;
                }
            }
        }

        return flag;
    }


}
