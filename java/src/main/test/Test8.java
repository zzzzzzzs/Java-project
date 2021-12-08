import cn.hutool.core.date.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zs
 *@date 2021/9/26.

 */
public class Test8 {
  public static void main(String[] args) {
    String r = DateUtil.parse("2021-10-10 20:01:01").toString("yyyy-MM-dd");
    System.out.println(r);
  }
}
