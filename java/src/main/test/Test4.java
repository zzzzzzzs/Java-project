/**
 * @author zs
 *@date 2021/9/15.

 */
public class Test4 {
    public static void main(String[] args) {
        String string="javajava_eclipse_class_jajavavajavajdjdj";
        String str="java";

        int i=string.length()-string.replace(str, "").length();
        System.out.println(i/str.length());
    }
}
