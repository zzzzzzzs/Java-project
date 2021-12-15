package com.me.crawler;

import cn.hutool.core.util.ReUtil;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author zs
 * @date 2021/12/8
 */
class CrawlerDemo {
    public static void main(String[] args) {
//      String content = "<p>下载地址：<a title=\"持续更新补丁下载地址\" target=\"_blank\" href=\"http://pan.baidu.com/s/xxxx\">持续更新补丁下载地址</a></p><p>";
//      List<String> all = ReUtil.findAll("<a[^>]*href=(\\\"([^\\\"]*)\\\"|\\'([^\\']*)\\'|([^\\\\s>]*))[^>]*>(.*?)</a>", content, 2);
//      System.out.println(all);
//      Pattern pattern_a = compile("<a[^>]*href=(\\\"([^\\\"]*)\\\"|\\'([^\\']*)\\'|([^\\\\s>]*))[^>]*>(.*?)</a>");
//      Matcher matcher_a = pattern_a.matcher(content);
//      System.out.println("网站连接");
//      while (matcher_a.find()) {
//          for (int i = 0; i < matcher_a.groupCount(); i++) {
//              System.out.println(matcher_a.group(i));
//          }
//      }
        String content = "href = \"http://www.baidu.com/link?url=XApKEbRUAy-YOybxmJkp7yK1O062_gbCOjt1cQYYUeq7UaS8eNOxJhYO87cyDAxGJxL0XftxXZO5r_-KgFUsZXvXH1jSQ0akSnmHdjTQrnm\"";
      List<String> all = ReUtil.findAll("(?<=href = \")(.+?)(?=\")", content, 0);
      System.out.println(all);
    }
}
