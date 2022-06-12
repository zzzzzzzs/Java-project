package com.me.WatchMonitor;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.io.watch.watchers.DelayWatcher;
import cn.hutool.core.lang.Console;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

public class WatchMonitorAllTest {
  public static void main(String[] args) {
    File file = FileUtil.file("C:\\Users\\simeitol\\Desktop\\11");
    // 这里只监听文件或目录的修改事件
    WatchMonitor.createAll(
            file,
            new DelayWatcher(
                new Watcher() {
                  @Override
                  public void onCreate(WatchEvent<?> event, Path currentPath) {
                    Object obj = event.context();
                    Console.log("创建：{}-> {}", currentPath, obj);
                  }

                  @Override
                  public void onModify(WatchEvent<?> event, Path currentPath) {
                    Object obj = event.context();
                    Console.log("修改：{}-> {}", currentPath, obj);
                  }

                  @Override
                  public void onDelete(WatchEvent<?> event, Path currentPath) {
                    Object obj = event.context();
                    Console.log("删除：{}-> {}", currentPath, obj);
                  }

                  @Override
                  public void onOverflow(WatchEvent<?> event, Path currentPath) {
                    Object obj = event.context();
                    Console.log("Overflow：{}-> {}", currentPath, obj);
                  }
                },
                500))
        .setMaxDepth(Integer.MAX_VALUE)
        .start();
  }
}
