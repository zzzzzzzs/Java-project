package com.me.DataStructure.NFA;

import com.me.DataStructure.tuple.Tuple2;
import com.me.utils.simudata.SimuNum;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author zs
 *@date 2021/9/9.
有限状态机

//                                  reset
//                         +------------------------+
//                         |                        |
//                         |                        |
//                         |                        |
//                         v                        |
//                     +---------+  success   +-----------+
//         +---------->+INITIAL  +----------->+SUCCESS    |
//         |           +---------+            +-----------+
//         |                |                       ^   ^
//         |           fail |                       |   |
//         |                |              success  |   |
//         |                v                       |   |
//         |           +---------+                  |   |
//         |           |   S1    +------------------+   |
//         |           +---------+                      |
//         |                |                           |
//   reset |           fail |                           |
//         |                |                    success|
//         |                v                           |
//         |           +---------+                      |
//         |           |   S2    +----------------------+
//         |           +---------+
//         |                |
//         |                |
//         |           fail |
//         |                |
//         |                v
//         |           +---------+
//         +-----------+  FAIL   |
//                     +---------+

// TODO 使用状态机实现连续三次登录失败
// TODO 如果在工作中可以将状态机画出来，可以减少大量的if else

 */
public class NFADemo {

    public static class Event {
        public String userId;
        public String eventType;
        public Long timestamp;

        public Event(String userId, String eventType, Long timestamp) {
            this.userId = userId;
            this.eventType = eventType;
            this.timestamp = timestamp;
        }

        public Event() {
        }

        @Override
        public String toString() {
            return "Event{" +
                    "userId='" + userId + '\'' +
                    ", eventType='" + eventType + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    private static HashMap<Tuple2<String, String>, String> statemachine = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        // TODO 初始化状态机
        statemachine.put(Tuple2.of("initial", "fail"), "S1");
        statemachine.put(Tuple2.of("initial", "success"), "SUCCESS");
        statemachine.put(Tuple2.of("S1", "fail"), "S2");
        statemachine.put(Tuple2.of("S2", "fail"), "FAIL");
        statemachine.put(Tuple2.of("S1", "success"), "SUCCESS");
        statemachine.put(Tuple2.of("S2", "success"), "SUCCESS");

        // 初始状态
        String state = "initial";

//        ArrayList<Event>{
//            add(new Event("user-1", "fail", 1000L)),
//            ad
//        }

        Event[] evenStream = {
                new Event("user-1", "fail", 1000L),
                new Event("user-1", "fail", 2000L),
                new Event("user-1", "fail", 5000L),
                new Event("user-2", "success", 10000L)
        };


        while (true) {
            Event event = evenStream[SimuNum.getRandInt(0, 3)];
            System.out.println(event);
            String nextState = statemachine.get(Tuple2.of(state, event.eventType));
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
