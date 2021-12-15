import cn.hutool.core.lang.Console;

import java.io.PrintStream;

/**
 * @author zs
 *@date 2021/9/26.

 */
public class Test9 {
  enum Aaaa {
    HELLO,
    HHH
  }

  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static void main(String[] args) throws InterruptedException {
    PrintStream outStream = System.out;
    outStream.println(ANSI_GREEN + "Passed the test!" + ANSI_RESET);
    outStream.println(ANSI_RED + "Passed the test!" + ANSI_RESET);
    Console.error("asd");
    Console.log();
  }
}
