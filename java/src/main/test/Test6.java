import cn.hutool.core.io.file.FileReader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zs
 * @date 2021/9/15.
 */
public class Test6 {
    public static void main(String[] args) {
        FileReader fileReader1 = new FileReader("C:\\Users\\simeitol\\Desktop\\所有数据.txt");
        FileReader fileReader2 = new FileReader("C:\\Users\\simeitol\\Desktop\\结果数据.txt");
        List<String> list1 = fileReader1.readLines();
        List<String> list2 = fileReader2.readLines();
        list1.removeAll(list2);
        System.out.println(list1);
    }
}
