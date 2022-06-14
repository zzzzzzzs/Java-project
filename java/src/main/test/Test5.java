import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author zs
 * @date 2021/9/15.
 */
public class Test5 {
  public static void main(String[] args) {
    HashMap<Integer, Supplier<String>> map = new HashMap<>();
    map.put(1, () -> "1");
    String apply = map.get(1).get();
    System.out.println(apply);
  }
}
