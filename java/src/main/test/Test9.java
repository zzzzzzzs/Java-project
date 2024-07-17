import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Test9 {
  private static final Logger LOG =  LogManager.getLogger(Test9.class);
  public static void main(String[] args) {
    Integer ints = 3_000_000_000_000 % Integer.MAX_VALUE;
    System.out.println(ints);
  }
}
