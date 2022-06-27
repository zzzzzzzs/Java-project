package com.me.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 顺序读写
 */
public class SeqReadWrite {

  /**
   * 顺序写
   *
   * @param filePath
   * @param content
   * @param index
   * @return
   */
  public static long fileWrite(String filePath, String content, int index) {
    File file = new File(filePath);
    RandomAccessFile randomAccessTargetFile;
    //  操作系统提供的一个内存映射的机制的类
    MappedByteBuffer map;
    try {
      randomAccessTargetFile = new RandomAccessFile(file, "rw");
      FileChannel targetFileChannel = randomAccessTargetFile.getChannel();
      map = targetFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, (long) 1024 * 1024 * 1024);
      map.position(index);
      map.put(content.getBytes());
      return map.position();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
    }
    return 0L;
  }

  /**
   * 顺序读
   *
   * @param filePath
   * @param index
   * @return
   */
  public static String fileRead(String filePath, long index) {
    File file = new File(filePath);
    RandomAccessFile randomAccessTargetFile;
    //  操作系统提供的一个内存映射的机制的类
    MappedByteBuffer map;
    try {
      randomAccessTargetFile = new RandomAccessFile(file, "rw");
      FileChannel targetFileChannel = randomAccessTargetFile.getChannel();
      map = targetFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, index);
      byte[] byteArr = new byte[10 * 1024];
      map.get(byteArr, 0, (int) index);
      return new String(byteArr);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
    }
    return "";
  }

  public static void main(String[] args) {
    long hello = fileWrite("D:\\test.txt", "hello", 0);
    System.out.println(hello);
  }

}
