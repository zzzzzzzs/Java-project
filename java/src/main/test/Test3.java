import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Map;

/**
 * @author zs
 * @date 2021/9/15.
 */
public class Test3 {
  public static void main(String[] args) {
    Multimap<String, Integer> multimap = ArrayListMultimap.create();
    multimap.put("day",1);
    multimap.put("day",2);
    multimap.put("day",8);
    multimap.put("month",3);
    Collection<Integer> day = multimap.get("day");
    System.out.println(day);
  }
}
