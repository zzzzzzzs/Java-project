import cn.hutool.core.io.file.FileReader;
import com.me.DataStructure.NFA.NFADemoMap;
import com.me.DataStructure.tuple.Tuple2;
import com.me.utils.simudata.SimuNum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author zs
 * @date 2021/9/15.
 */
public class Test6 {
  public static class Event {
    public String userId;
    public String eventType;
    public Long timestamp;

    public Event(String userId, String eventType, Long timestamp) {
      this.userId = userId;
      this.eventType = eventType;
      this.timestamp = timestamp;
    }

    public Event() {}

    @Override
    public String toString() {
      return "Event{"
          + "userId='"
          + userId
          + '\''
          + ", eventType='"
          + eventType
          + '\''
          + ", timestamp="
          + timestamp
          + '}';
    }
  }

  private static HashMap<Tuple2<String, String>, Supplier<String>> statemachine = new HashMap<>();

  public static void main(String[] args) throws InterruptedException {
    // TODO 初始化状态机
    statemachine.put(Tuple2.of("INIT", "real"), () -> "RES");
    statemachine.put(Tuple2.of("INIT", "real"), () -> "REAL");
    statemachine.put(Tuple2.of("REAL", "as"), () -> "RES");
    statemachine.put(Tuple2.of("INIT", "join"), () -> "JOIN");
    statemachine.put(Tuple2.of("JOIN", "real"), () -> "RES");
    statemachine.put(Tuple2.of("JOIN", "real"), () -> "REAL");
    statemachine.put(Tuple2.of("REAL", "as"), () -> "RES");

    // 初始状态
    String state = "initial";

    NFADemoMap.Event[] evenStream = {
      new NFADemoMap.Event("user-1", "fail", 1000L),
      new NFADemoMap.Event("user-1", "fail", 2000L),
      new NFADemoMap.Event("user-1", "fail", 5000L),
      new NFADemoMap.Event("user-2", "success", 10000L)
    };

    while (true) {
      NFADemoMap.Event event = evenStream[SimuNum.getRandInt(0, 3)];
      System.out.println(event);
      String nextState = statemachine.get(Tuple2.of(state, event.eventType)).get();
      if (nextState.equals("SUCCESS")) {
        System.out.println("用户" + event.userId + "登录成功");
        state = "initial";
      } else if (nextState.equals("FAIL")) {
        System.out.println("用户" + event.userId + "登录失败");
        state = "initial";
      } else {
        state = nextState;
      }
      Thread.sleep(1000);
    }
  }
}
