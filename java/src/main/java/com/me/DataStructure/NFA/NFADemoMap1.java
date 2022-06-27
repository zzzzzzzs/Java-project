package com.me.DataStructure.NFA;

import com.me.DataStructure.tuple.Tuple2;
import com.me.utils.simudata.SimuNum;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * 有限状态机
 *
 * <p>TODO 在map中传入了函数，更灵活
 *
 * <p>TODO 使用状态机实现连续三次登录失败
 *
 * <p>TODO 如果在工作中可以将状态机画出来，可以减少大量的if else
 *
 * <p>TODO 有限状态图 @see NFA.md
 */
public class NFADemoMap1 {

  // TODO 如果 java.util.function 中的接口满足不了的话，可以自己创建接口
  //      https://stackoverflow.com/questions/395816/function-pointers-delegates-in-java
  private static HashMap<Tuple2<String, String>, Handler<String>> stateMachine = new HashMap<>();

  public static void main(String[] args) throws InterruptedException {
    Tuple2<String, String> of1 = Tuple2.of("INIT", "table");
      Tuple2<String, String> of2 = Tuple2.of("INIT", "table");
      Tuple2<String, String> of3 = Tuple2.of("INIT", "table");
    // TODO 初始化状态机
    stateMachine.put(
        of1,
        (var1) -> {
          System.out.println(var1);
          return var1;
        });
      stateMachine.put(
              of2,
              (var1) -> {
                  System.out.println(var1);
                  return var1;
              });
      stateMachine.put(
              of3,
              (var1) -> {
                  System.out.println(var1);
                  return var1;
              });
    // 初始状态
    String state = "initial";
    // TODO 循环执行状态机
    Tuple2<String, String> of = Tuple2.of("INIT", "table");
    String func = stateMachine.get(of).func("asdasd");
    System.out.println(func);
  }
}
