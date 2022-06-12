package com.me.ssh;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.extra.ssh.JschUtil;
import com.jcraft.jsch.*;

import java.io.BufferedInputStream;

public class JschTest {
  public static void main(String[] args) throws JSchException, SftpException {
    FileReader fileReader = new FileReader("C:\\Users\\simeitol\\Desktop\\1.txt");
    BufferedInputStream inputStream = fileReader.getInputStream();
    Session session = JschUtil.getSession("106.14.150.229", 22, "root", "Hminde1314");
    ChannelShell channelShell = JschUtil.openShell(session);
    channelShell.start();
//    ChannelSftp channelSftp = JschUtil.openSftp(session, 5000);

//    channelSftp.put(inputStream, "/root/test/1.txt");
    //    System.out.println(exec);
  }
}
