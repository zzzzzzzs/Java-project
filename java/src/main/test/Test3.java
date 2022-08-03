/**
 * @author zs
 * @date 2021/9/15.
 */
public class Test3 {

  // replaceChars 中的字符代替 searchChars 中的字符
  public static String translate(String str, String searchChars, String replaceChars) {
    return org.apache.commons.lang3.StringUtils.replaceChars(str, searchChars, replaceChars);
  }

  public static void main(String[] args) {
    System.out.println(translate("AaBbCc", "abc", "123"));
    System.out.println(translate("AaBbCc", "abc", "1"));
    System.out.println(translate("AaBbCc", "abc", ""));
  }
}
