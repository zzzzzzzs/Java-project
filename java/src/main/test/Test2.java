import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.map.ListOrderedMap;

/**
 * @author zs
 * @date 2021/9/14.
 */
public class Test2 {
  public static void main(String[] args) {
    OrderedMap<String, String> orderedMap = new ListOrderedMap<>();
    orderedMap.put("b", "bb");
    orderedMap.put("c", "cc");
    orderedMap.put("a", "aa");
    orderedMap.put("d", "dd");
    System.out.println(orderedMap);
    String s = orderedMap.lastKey();
    System.out.println(s);
  }
}
