import java.util.function.Supplier;

/**
 * @author zs
 *@date 2021/9/26.

 */
public class Test10 {

    public static void main(String[] args) throws InterruptedException {
        int a = 1;
        while (a==1){
            System.out.println(a);
            Thread.sleep(1000);
        }
    }
}
