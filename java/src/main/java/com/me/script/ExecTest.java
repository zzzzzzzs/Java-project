package com.me.script;

import cn.hutool.core.util.RuntimeUtil;

public class ExecTest {
  public static void main(String[] args) {
    // 到 /d/dfsw/mgr/system/controller/qa/QaQuestionController.java
    String str =
        RuntimeUtil.execForStr(
        "d:","cd workspace/code/dfsw-mgr-qa","mvn compile"
                );
    System.out.println(str);
  }
}
