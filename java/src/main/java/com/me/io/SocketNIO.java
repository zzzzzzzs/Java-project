package com.me.io;

import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * @author zs
 * @date 2022/1/30
 * 整个逻辑代码是在一个线程中进行的
 */
public class SocketNIO {

  public static void main(String[] args) throws Exception {
    LinkedList<SocketChannel> clients = new LinkedList<>();
    ServerSocketChannel ss = ServerSocketChannel.open();
    ss.bind(new InetSocketAddress(9090));
    //设置为非阻塞
    ss.configureBlocking(false); // 重要 OS SOCK_NONBLOCK ！！！
    ss.setOption(StandardSocketOptions.TCP_NODELAY, false);
    while (true) {
      // 接收客户端的连接
      Thread.sleep(1000);
      //不会阻塞 -1 null，一调用就会有返回
      SocketChannel client = ss.accept();
      /* accept 调用内核了：
       *  1、没有客户端连接进来，返回值？ 在BIO的时候一直卡着，但是在NIO，不卡着，返回 -1 , Java就是 null
       *    如果来了客户端连接，accept 返回的就是这个客户端的 fd，比如说 5u，Java中就是一个对象 object
       *    NONBLOCKING 就是代码能往下走
       *
       *
       **/
      if (client == null) {
        System.out.println("null....");
      } else {
        // socket(服务端的listen socket<连接请求三次握手后，往我这里扔，我去通过accept得到连接的socket>，
        // 连接socket<连接后的数据读写使用>）
        client.configureBlocking(false);// 重要 OS SOCK_NONBLOCK ！！！
        int port = client.socket().getPort();
        System.out.println("client...port" + port);
        clients.add(client);
      }
      //可以在堆里 也可以在堆外
      ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
      // 遍历已经连接进来的客户端不能读写数据
      for (SocketChannel c : clients) {
        //不会阻塞
        int num = c.read(buffer);
        if (num > 0) {
          buffer.flip();
          byte[] bytes = new byte[buffer.limit()];
          buffer.get(bytes);

          String b = new String(bytes);
          System.out.println(c.socket().getPort() + ":" + b);
          buffer.clear();
        }
      }
    }
  }
}
