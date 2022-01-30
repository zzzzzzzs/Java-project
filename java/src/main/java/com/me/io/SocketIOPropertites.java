package com.me.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zs
 * @date 2022/1/30
 * BIO 多线程的方式
 *
 *
 */
public class SocketIOPropertites {

  //设置接收缓冲区大小的大小。 该值必须大于0
  private static final int RECEIVE_BUFFER = 10;
  //是否启用或禁用套接字选项
  private static final boolean REUSE_ADDR = false;
  //指定的超时，以毫秒为单位
  private static final int SO_TIMEOUT = 0;
  //请求进入连接队列的最大长度
  private static final int BACK_LOG = 2;
  //是否开启心跳
  private static final boolean CLI_KEEPALIVE = false;
  //接收TCP紧急数据
  private static final boolean CLI_OOB = false;
  //设置接收缓冲区大小的大小。 该值必须大于0
  private static final int CLI_REC_BUF = 20;

  private static final boolean CLI_REUSE_ADDR = false;
  private static final int CLI_SEND_BUF = 20;
  private static final boolean CLI_LINGER = true;
  private static final int CLI_LINGER_N = 0;
  private static final int CLI_TIMEOUT = 0;
  private static final boolean CLI_NO_DELAY = false;

  public static void main(String[] args) {
    ServerSocket server = null;
    try {
      server = new ServerSocket();
      server.bind(new InetSocketAddress(9090), BACK_LOG);
      server.setReceiveBufferSize(RECEIVE_BUFFER);
      server.setReuseAddress(REUSE_ADDR);
      server.setSoTimeout(SO_TIMEOUT);
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("server up use 9090!");
    try {
      while (true) {
        // System.in.read();  //分水岭：
        Socket client = server.accept();  //阻塞的，没有 -1  一直卡着不动  accept(4,
        System.out.println("client port: " + client.getPort());

        client.setKeepAlive(CLI_KEEPALIVE);
        client.setOOBInline(CLI_OOB);
        client.setReceiveBufferSize(CLI_REC_BUF);
        client.setReuseAddress(CLI_REUSE_ADDR);
        client.setSendBufferSize(CLI_SEND_BUF);
        client.setSoLinger(CLI_LINGER, CLI_LINGER_N);
        client.setSoTimeout(CLI_TIMEOUT);
        client.setTcpNoDelay(CLI_NO_DELAY);

        //client.read   //阻塞   没有  -1 0
        // 因为阻塞，所以抛线程
        new Thread(
            () -> {
              try {
                InputStream in = client.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                char[] data = new char[1024];
                while (true) {
                  int num = reader.read(data);

                  if (num > 0) {
                    System.out.println(
                        "client read some data is :" + num + " val :" + new String(data, 0, num));
                  } else if (num == 0) {
                    System.out.println("client readed nothing!");
                    continue;
                  } else {
                    System.out.println("client readed -1...");
                    System.in.read();
                    client.close();
                    break;
                  }
                }
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
        ).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        server.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
