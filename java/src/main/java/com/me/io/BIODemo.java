package com.me.io;
import static com.me.io.IOUtils.BACK_LOG;
import static com.me.io.IOUtils.DEFAULT_PORT;
import static com.me.io.IOUtils.buildBufferedWriter;
import static com.me.io.IOUtils.buildHttpResp;
import static com.me.io.IOUtils.doSomeWork;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zs
 * @date 2022/1/30
 * 模拟一个http请求
 * Block IO
 */
public class BIODemo {
  public static void main(String[] args) throws IOException, InterruptedException {
    ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT, BACK_LOG, null);
    System.out.println("服务器启动");
    for (; ; ) {
      Socket socket = serverSocket.accept();
      System.out.println(socket.getRemoteSocketAddress());
      OutputStream outputStream = socket.getOutputStream();
      BufferedWriter bufferedWriter = buildBufferedWriter(outputStream);
      doSomeWork();
      bufferedWriter.write(buildHttpResp());
      bufferedWriter.flush();
    }
  }
}
