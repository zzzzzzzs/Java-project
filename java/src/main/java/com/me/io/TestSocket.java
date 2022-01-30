package com.me.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zs
 * @date 2022/1/30
 */
public class TestSocket {

  public static void main(String[] args) throws Exception {
    ServerSocket server = new ServerSocket(8090);
    System.out.println("step1 : new ServerSocket(8090)");
    while (true) {
      Socket client = server.accept();
      System.out.println("step2:cleint\t" + client.getPort());

      new Thread(new Runnable() {
        Socket ss;

        public Runnable setSs(Socket ss) {
          this.ss = ss;
          return this;
        }

        @Override
        public void run() {
          try {
            InputStream in = ss.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while (true) {
              System.out.println(reader.readLine());
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }.setSs(client));
    }
  }
}
