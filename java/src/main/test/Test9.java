import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 * @author zs
 *@date 2021/9/26.

 */
public class Test9 {

    public void add(Byte b) {
        b = b++;
    }
    public void test() {
        Byte a = 127;
        Byte b = 127;
        add(++a);
        System.out.println(a + "");
        add(b);
        System.out.println(b + "");
    }

    public static void main(String[] args) {
        new Test9().test();
    }
}
