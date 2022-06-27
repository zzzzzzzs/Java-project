import cn.hutool.core.date.DateUtil;

/**
 * @author zs
 * @date 2021/9/15.
 */
public class Test5 {
  public static void main(String[] args) {
    boolean after = DateUtil.date().after(DateUtil.parse("2022-06-23 23:59:59"));
    System.out.println(after);
  }
}
