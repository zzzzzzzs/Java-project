package com.me.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * @author zs
 * @date 2022/1/30
 * windows 的单进程会对服务器发起10w以上的连接
 * BIO，NIO，多路复用器 测试
 */
public class C10kClient {

  public static void main(String[] args) {
    LinkedList<SocketChannel> clients = new LinkedList<>();
    InetSocketAddress serverAddr = new InetSocketAddress("127.0.0.1", 9090);

    for (int i = 10000; i < 65000; i++) {
      try {
        SocketChannel socketChannel1 = SocketChannel.open();
        SocketChannel socketChannel2 = SocketChannel.open();

        // 虚拟网络
//        socketChannel1.bind(new InetSocketAddress("192.168.0.1", i));
//        socketChannel1.connect(serverAddr);
//        boolean c1 = socketChannel1.isOpen();
//        clients.add(socketChannel1);

        // 有线网卡
        socketChannel2.bind(new InetSocketAddress("172.18.0.2", i));
        socketChannel2.connect(serverAddr);
        boolean c2 = socketChannel2.isOpen();
        clients.add(socketChannel2);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.out.println("clients " + clients.size());
  }
}
