import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author zs
 *@date 2021/9/14.

 */
public class Test2 {

    public static String solution(int[] arr) {

        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                nums.add(arr[i]);
            }
        }

        List resultList = new ArrayList<>(nums.size());

        StringBuilder builder = new StringBuilder();

        for (int s : nums) {
            resultList.add(String.valueOf(s));
        }

        Collections.sort(resultList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });

        for (int i = resultList.size() - 1; i >= 0; i--) {
            builder.append(resultList.get(i));
        }

        String sss = builder.toString();

        // 使用数字求和计算
        int sum = 0;
        for (int i = 0; i < sss.length(); ++i) {
            sum += (sss.charAt(i) - '0') * (i+1);
        }
        if (sum == 0) {
            return "0";
        }

        return sss;
    }

    public static void main(String[] args) {
        int[] arrs = {3, 3, 6, 7};
        String number = solution(arrs);
        System.out.println(number);
    }
}

