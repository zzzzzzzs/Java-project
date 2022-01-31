package com.me.io;
import static com.me.io.IOUtils.*;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
/**
 * @author zs
 * @date 2022/1/31
 * 测试QPS为： 之前是 2*60 QPM    32QPM 经测试结果，比BIO还要差？
 */
public class NIOWithSelectorDemo {
  public static Selector selector;
  public static void main(String[] args) throws Exception {
    System.out.println("启动服务器");
    createServerSocketChannel();
    for (; ; ) {
      // 阻塞方法
      selector.select(100);
      // 获取有哪些通道准备好了
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      Iterator<SelectionKey> iterator = selectionKeys.iterator();
      while (iterator.hasNext()) {
        SelectionKey next = iterator.next();

        if (next.isWritable()) {
          handleWrite((SocketChannel) next.channel());
        }

        if (next.isAcceptable()) {
          handleAccept((ServerSocketChannel) next.channel());
        }

        iterator.remove();
      }
      // 执行业务操作
    }
  }

  public static ServerSocketChannel createServerSocketChannel() throws Exception {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.configureBlocking(false); // 不阻塞
    serverSocketChannel.bind(new InetSocketAddress(DEFAULT_PORT), BACK_LOG);

    selector = Selector.open();
    // 通道挂到选择器上，通过SelectionKey告诉选择器，我对什么事件感兴趣
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    return serverSocketChannel;
  }

  public static void handleWrite(SocketChannel socketChannel) {
    String resp = buildHttpResp();
    try {
      doSomeWork();
      socketChannel.write(ByteBuffer.wrap(resp.getBytes()));
    } catch (Exception e) {
    }
  }

  public static void handleAccept(ServerSocketChannel serverSockChannel) throws Exception {
    SocketChannel socketChannel = serverSockChannel.accept();
    System.out.println(socketChannel.getRemoteAddress());
    socketChannel.configureBlocking(false);

    // 告诉选择器，当前这个acceptSocketChannel客户端通道对写事件感兴趣
    socketChannel.register(selector, SelectionKey.OP_WRITE);
  }
}
