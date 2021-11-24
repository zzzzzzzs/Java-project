import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 * @author zs
 *@date 2021/9/26.

 */
public class Test9 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis() - start);
    }
}
