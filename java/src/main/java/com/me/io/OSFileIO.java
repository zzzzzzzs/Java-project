package com.me.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author zs
 * @date 2022/1/27
 */
public class OSFileIO {

  private final static byte[] data = "123456789\n".getBytes();
  private final static String path = String
      .format("%sroot%sworkspace%stestfileio%sout.txt", File.separator, File.separator,
          File.separator, File.separator);

  public static void main(String[] args) throws Exception {
    switch (args[0]) {
      case "0":
        testBasicFileIO();
        break;
      case "1":
        testBufferFileIO();
        break;
      case "2":
        testRandomAccessFileWrite();
        break;
      case "3":
        whatByteBuffer();
      case "4":
        testAllocate();
      default:
    }
  }

  /**
   * 最基本的 file 写
   *
   * @throws Exception 此时数据只是存到了 pagecache 中，被标记为 Direct，等到触发内核 flush 机制，才会被刷到磁盘中
   */
  public static void testBasicFileIO() throws Exception {
    File file = new File(path);
    FileOutputStream out = new FileOutputStream(file);
    while (true) {
//            Thread.sleep(10);
      out.write(data);
    }
  }

  /**
   * 测试 buffer 文件 IO 先在 jvm 内存中创建一个 8kb 的字节数组 8kb 满了以后调用 syscall  write(8KBbyte[]) 将数组写出去 结论： 比
   * FileOutputStream 写入更快，因为先将数据缓存到 jvm 内存中，到 8kb 时才会调用一次写操作，减少了用户态转换内核态的次数 应用缓冲解决系统调用的损耗
   */
  public static void testBufferFileIO() throws Exception {
    File file = new File(path);
    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
    while (true) {
//            Thread.sleep(100); // 减少系统调用
      out.write(data);
    }
  }

  // 测试 NIO
  public static void testRandomAccessFileWrite() throws Exception {
    RandomAccessFile raf = new RandomAccessFile(path, "rw");

    raf.write("hello world".getBytes());
    raf.write("hello java".getBytes());

    //设置文件指针偏移位置，从该文件的头开始。发生下一次读活写
    raf.seek(4);
    raf.write("ooxx".getBytes());

    //返回该文件唯一关联的 FileChannel 对象
    FileChannel channel = raf.getChannel();

    //mmap 堆外  和 mmap 系统调用文件映射的空间
    //mode - 一个常量READ_ONLY，根据该文件是否是要被映射的只读，读/写，或私人（写入时复制），分别
    //position - 映射区域要启动的文件中的位置; 必须是非负的
    //size - 要映射的区域的大小; 必须是非负数，不得大于Integer.MAX_VALUE
    MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 4096);

    //不是系统调用，和文件映射了，数据会到达内核的 pagecache
    map.put("@@@@".getBytes());
    // 曾经我们需要out.write() 这样的系统调用，才能让程序的data 进入内核的 pagecache
    // 曾经必须有用户态内核态切换
    // mmap的内存映射，依然是内核的pagecache体系所约束的！！！
    // 换言之，丢数据
    // 目前来说 jdk 没有能力让你逃离内核的pagecache
    // 你可以去GitHub上找一个C写的jni扩展库，使用Linux内核的Dircet IO（直接IO），可以忽略Linux的 pagecache
    // 是把 pagecache 交给了程序员自己开辟字节数组，动用代码量逻辑来维护一致性/dirty。。一系列复杂问题。
    // 只不过不用改全局系统
    map.force(); // flush
    raf.seek(0); // 回到文件开始的位置

//        ByteBuffer buffer = ByteBuffer.allocate(1024);
    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
    buffer.put("123".getBytes());

    //读写交替
    buffer.flip();
    //读取该缓冲区当前位置的字节，然后增加位置
    buffer.get();

    //压缩此缓冲区
    buffer.compact();

    //清除此缓冲区。 位置设置为零，限制设置为容量，标记被丢弃。
    buffer.clear();
  }

  public static void whatByteBuffer() {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
//        ByteBuffer buffer = ByteBuffer.allocateDirect(1024); // 堆外分配

    System.out.println("postition: " + buffer.position());
    System.out.println("limit: " + buffer.limit());
    System.out.println("capacity: " + buffer.capacity());
    System.out.println("mark: " + buffer);

    buffer.put("123".getBytes());

    System.out.println("-------------put:123......");
    System.out.println("mark: " + buffer);

    buffer.flip();

    System.out.println("-------------flip......");
    System.out.println("mark: " + buffer);

    buffer.get();

    System.out.println("-------------get......");
    System.out.println("mark: " + buffer);

    buffer.compact();

    System.out.println("-------------compact......");
    System.out.println("mark: " + buffer);

    buffer.clear();

    System.out.println("-------------clear......");
    System.out.println("mark: " + buffer);
  }

  // 测试 allocate，看看是不是 on heap -> off heap
  public static void testAllocate() throws IOException {
    RandomAccessFile raf = new RandomAccessFile(path, "rw");
    while (true) {
      raf.write("12345678".getBytes());
    }
  }
}
