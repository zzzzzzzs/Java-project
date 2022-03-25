import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author zs
 *@date 2021/9/14.

 */
public class Test2 {

    public static void main(String[] args) {
        DateTime time = DateUtil.parse("2021", "yyyy");
        System.out.println(time);
    }
}

