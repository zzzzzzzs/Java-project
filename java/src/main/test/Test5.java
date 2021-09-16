import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zs
 *@date 2021/9/15.

 */
public class Test5 {
    public static void main(String[] args) {
        int n = 5;
        int[] arr = {1, 2, 3, 4, 5, 6, 6, 7, 8, 9};
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            ints.add(arr[i]);
        }

        for (int i = 0; i < arr.length - n + 1; i++) {
            List<Integer> subInts = ints.subList(i, n + i);
            float sum = 0;
            for (int j = 0; j < subInts.size(); j++) {
                sum += (float) 1 / subInts.get(j);
                if(sum == 1){
                    System.out.println(subInts.toString());
                }
            }
            if(i == arr.length - n + 1){
                System.out.println("No solution!");
            }
        }
    }
}
