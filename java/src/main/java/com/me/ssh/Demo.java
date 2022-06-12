package com.me.ssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.io.IOUtils;

import java.util.Properties;

public class Demo {
  public static void main(String[] args) throws JSchException {
    // 远程服务用户名
    String username = "root";
    // 远程服务ip
    String host = "106.14.150.229";
    // 远程服务密码
    String password = "Hminde1314";
    // 连接超时 10秒
    int timeout = 10000;
    int port = 22;
    JSch jSch = new JSch();
    Session session = jSch.getSession(username, host, port);
    // 设置密码
    session.setPassword(password);

    Properties config = new Properties();
    // 设置第一次登录的时候提示，可选值：(ask | yes | no)
    config.put("StrictHostKeyChecking", "no");
    // 为Session对象设置properties
    session.setConfig(config);
    // 设置连接超时
    session.setTimeout(timeout);
    // 通过Session建立连接
    session.connect();
    // 打开shell通道
    Channel shell = session.openChannel("shell");
    String ss = "ls\npwd\ncd /root/test\n";
    // 控制台接收输入
    shell.setInputStream(IOUtils.toInputStream(ss));
    // 控制台接收输出
    shell.setOutputStream(System.out);
    ss = "ls\n";
    // 超时时间，0 表示无限制
    shell.connect(0);
  }
}
