package com.me.script;

import cn.hutool.core.util.RuntimeUtil;

public class ExecTest {
  public static void main(String[] args) {
    String str = RuntimeUtil.execForStr("java -version");
    System.out.println(str);
  }
}
