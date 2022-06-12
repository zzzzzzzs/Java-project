package com.me.file;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;

public class HutoolFile {

  public static void main(String[] args) {
    // 默认UTF-8编码，可以在构造中传入第二个参数做为编码
    FileReader fileReader = new FileReader("C:\\Users\\simeitol\\Desktop\\1.txt");
    List<String> list = fileReader.readLines();
    for (String s : list) {
      if (s.length() >= 10) {
        System.out.println(s);
      }
    }
  }
}
