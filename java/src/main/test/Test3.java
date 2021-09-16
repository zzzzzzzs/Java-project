import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author zs
 *@date 2021/9/15.

 */
public class Test3 {
    public static void main(String[] args) {
        //从键盘输入商品零售价总和，并用逗号分隔
        Scanner scanner1 = new Scanner(System.in);
        String s = scanner1.next();

        //输入目标总金额
        Scanner scanner2 = new Scanner(System.in);
        int totalPrice = scanner2.nextInt();

        String[] priceStrs = s.split(",");
        ArrayList<Integer> prices = new ArrayList<Integer>();
        for (int i = 0; i < priceStrs.length; i++) {
            prices.add(Integer.valueOf(priceStrs[i]));
        }

        prices.sort((o1, o2) -> o2 - o1);

        int count = 0;
        int num = totalPrice;
        for (int i = 0, m = prices.size(); i < m; i++) {
            int div = num / prices.get(i);
            int mod = num % prices.get(i);
            num = mod;
            count += div;
        }
        System.out.println(count);
    }
}
