package com.me.script;

/**
 * @author zs
 * @date 2022/4/19
 */
public class Python {
  // 调用python脚本
  public static void python(String[] args) {
    try {
      Process process = Runtime.getRuntime().exec(args);
      process.waitFor();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    python(
        new String[] {
          "python",
          "D:\\Data\\KnowledgeWarehouse\\code\\Java-project\\java\\src\\main\\python\\test.py"
        });
  }
}
