package com.me.ssh;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import org.apache.commons.vfs2.provider.sftp.TrustEveryoneUserInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @Description: ssh util
 */
public class SSHUtil {

  private Session session;
  private ChannelShell shell;
  private String username;
  private String host;
  private String password;
  ChannelShell channel = null;
  InputStream in = null;
  OutputStream os = null;

  public SSHUtil(String username, String host, String password) {
    this.username = username;
    this.host = host;
    this.password = password;
  }

  public Session getSession() {
    return this.session;
  }

  public void connect() {
    JSch ssh = new JSch();
    try {
      this.session = ssh.getSession(this.username, this.host);
      this.session.setPassword(this.password);
      this.session.setServerAliveCountMax(0);
      Properties config = new Properties();
      config.put("StrictHostKeyChecking", "no");
      session.setConfig(config);
      this.session.connect(1000);
//      UserInfo user = new TrustEveryoneUserInfo();
//      this.session.setUserInfo(user);
      channel = (ChannelShell) session.openChannel("shell");
      in = channel.getInputStream();
      channel.setPty(true);
      channel.connect();
      os = channel.getOutputStream();
      System.out.println("连接成功");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public synchronized void shellCmd(String... cmds) {
    try {
      for (String cmd : cmds) {
        os.write((cmd + "\r\n").getBytes());
        os.flush();
        TimeUnit.MILLISECONDS.sleep(100);
      }
      channel.setOutputStream(System.out);
//      byte[] tmp = new byte[1024];
//      while (in.available() > 0) {
//        int i = in.read(tmp, 0, 1024);
//        if (i < 0) {
//          break;
//        }
//        System.out.println(new String(tmp, 0, i));
//      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  // 测试
  public static void main(String[] args) throws Exception {
    SSHUtil ssh = new SSHUtil("root", "106.14.150.229", "Hminde1314");
    ssh.connect();
    String[] cmds = new String[] {"cd /root/test", "netstat -nltp", "cat 1.txt"};
    ssh.shellCmd(cmds);
    ssh.shellCmd("ls", "pwd", "sudo su");
    for (int i = 0; i < 100; i++) {
      ssh.shellCmd("echo 'hello' >> 1.txt");
    }
    System.out.println("执行完毕");
  }
}
