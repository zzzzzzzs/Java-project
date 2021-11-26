import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zs
 *@date 2021/9/26.

 */
public class Test8 {

  public static int func(int[] nums) {
    int a = nums[0]; // 6
    int b = nums[1]; // 9
    int f = nums[2]; // 2
    int k = nums[3]; // 4


    int count = 0;

    for (int i = 0; i < k; i++) {
      // 去
      if (i % 2 == 0) {
        b = b - f;
        if (b <= (a - f) * 2) {
          count++;
          b = 9;
        }
      } else { // 回
        b = b - a + f;
        if (b <= f * 2) {
          count++;
          b = 9;
        }
      }
    }
    return count;
  }

  // 6 9 2 4 -> 4
  public static void main(String[] args) {
    int[] nums = {6, 9, 2, 4};
    int func = func(nums);
    System.out.println(func);
  }
}
