import com.me.DataStructure.NFA.Handler;
import com.me.DataStructure.tuple.Tuple2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.function.Supplier;

/**
 * @author zs
 * @date 2021/9/26.
 */
public class Test7 {
  private static HashMap<Tuple2<String, String>, Handler<String>> stateMachine = new HashMap<>();

  static {
    // TODO 初始化状态机
    stateMachine.put(
        Tuple2.of("INIT", "table"),
        (var1) -> {
          System.out.println(var1);
          return var1;
        });
  }

  public static void main(String[] args) {
    LinkedList<String> col = new LinkedList<>();
    col.add("INIT");
    col.add("REAL");
    col.add("AS");
    String func = stateMachine.get(Tuple2.of("INIT", "table")).func("table");
    System.out.println(func);
  }
}
