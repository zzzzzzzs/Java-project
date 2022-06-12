package com.me.ssh;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
// https://rstyro.github.io/blog/2021/08/01/Jsch%E6%89%A7%E8%A1%8C%E8%BF%9C%E7%A8%8B%E6%9C%8D%E5%8A%A1%E5%99%A8%E5%91%BD%E4%BB%A4/
public class Demo1 {

  static InputStream input = null;

  public static void main(String[] args) throws JSchException, IOException {
    // 远程服务用户名
    String username = "root";
    // 远程服务ip
    String host = "106.14.150.229";
    // 远程服务密码
    String password = "Hminde1314";
    int timeout = 10000;
    int port = 22;
    JSch jSch = new JSch();
    Session session = jSch.getSession(username, host, port);
    session.setPassword(password);
    Properties config = new Properties();
    config.put("StrictHostKeyChecking", "no");
    session.setConfig(config);
    session.setTimeout(timeout);
    session.connect();
    ChannelExec channel = (ChannelExec) session.openChannel("exec");

    channel.setInputStream(null);
    channel.setErrStream(System.err);
    channel.connect(10000);
    input = channel.getInputStream();
    // 打印root目录下的信息
    String cmd = "pwd";
    List<String> result = exec(channel, cmd);
    result.forEach(System.out::println);
    exec(channel, "ls").forEach(System.out::println);
    exec(channel, "cd /root/test").forEach(System.out::println);
    exec(channel, "ls").forEach(System.out::println);
  }

  /**
   * 执行命令
   *
   * @param command 命令
   * @return list
   * @throws JSchException err
   */
  public static List<String> exec(ChannelExec channel, String command) throws JSchException {
    List<String> resultLines = new ArrayList<>();
    channel.setCommand(command);
    try {
      try {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
        String inputLine = null;
        while ((inputLine = inputReader.readLine()) != null) {
          resultLines.add(inputLine);
        }
      } finally {
        if (input != null) {
          try {
            input.close();
          } catch (Exception e) {
            // todo...
            e.printStackTrace();
          }
        }
      }
    } catch (IOException e) {
    } finally {
      if (channel != null) {
        try {
          channel.disconnect();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return resultLines;
  }
}
