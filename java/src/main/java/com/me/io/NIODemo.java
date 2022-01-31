package com.me.io;


import static com.me.io.IOUtils.*;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


/**
 * 面试官问：什么是NIO？ NEW IO ?
 * 面试官问：用过NIO吗？核心类有几个？Channel、Buffer、Selector
 * NIO在Java中是对于之前的IO模型进行了抽象，将数据传输介质抽象为通道组件，将传递的信息抽象为Buffer，引入通道选择器
 * @date 2021/5/17 21:30
 */
public class NIODemo {
  public static void main(String[] args) throws Exception {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.bind(new InetSocketAddress(DEFAULT_PORT), BACK_LOG);
    serverSocketChannel.configureBlocking(false);

    System.out.println("启动服务器");
    for (; ; ) {
      SocketChannel socketChannel = serverSocketChannel.accept();
      if (socketChannel != null) {
        System.out.println(socketChannel.getRemoteAddress());
        String resp = buildHttpResp();
        doSomeWork();
        socketChannel.write(ByteBuffer.wrap(resp.getBytes()));
      }
      System.out.println(socketChannel);
    }
  }
}
