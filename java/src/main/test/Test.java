import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author zs
 *@date 2021/9/7.

 */
public class Test {
    public static void main(String[] args) {
        DateTime date = DateUtil.date();
        String s = DateUtil.offsetDay(date, -1).toString("yyyy-MM-dd");
        System.out.println(s);
    }
}
