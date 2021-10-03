import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author zs
 *@date 2021/9/26.

 */
public class Test7 {
    public static void main(String[] args) {

        //从键盘输入一组整数
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();

        System.out.println(s);

        //根据匹配正则表达式来拆分该字符串
        String[] string = s.split(" ");
        //定义一个数组用于存放分割后的字符转化成的整数
        int[] arr = new int[string.length];
        for (int i = 0; i < string.length; i++) {
            arr[i] = Integer.parseInt(string[i]);
        }

        //创建list对象，存放任意三个整数的和
        List<Integer> sums = new ArrayList<Integer>();

        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    sums.add(arr[i] + arr[j] + arr[k]);
                }
            }
        }
        //遍历list中所有元素
        int max_sum = 0;
        for (int m = 0; m < sums.size(); m++) {
            int sum = sums.get(m);
            //找出最大的和
            if (sum > max_sum) {
                max_sum = sum;
            }
        }
        if (max_sum % 3 == 0) {
            System.out.println(max_sum);
        } else {
            System.out.println("false");
        }

    }
}
