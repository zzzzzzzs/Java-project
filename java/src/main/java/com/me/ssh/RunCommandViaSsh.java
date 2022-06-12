package com.me.ssh;

import com.google.common.io.CharStreams;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static java.util.Arrays.asList;

public class RunCommandViaSsh {

  private static final String SSH_HOST = "106.14.150.229";
  private static final String SSH_LOGIN = "root";
  private static final String SSH_PASSWORD = "Hminde1314";

  //    session.connect();
  //
  //  ChannelExec channel = (ChannelExec) session.openChannel("exec");

  public static void main(String[] args) throws JSchException {
    Session session = new JSch().getSession(SSH_LOGIN, SSH_HOST, 22);
    session.setPassword(SSH_PASSWORD);
    session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
    session.setConfig("StrictHostKeyChecking", "no"); // disable check for RSA key
    session.connect();
    ChannelExec channel = (ChannelExec) session.openChannel("exec");

    System.out.println(runCommand(channel, "cd /root/test"));
    System.out.println(runCommand(channel, "pwd"));
    System.out.println(runCommand(channel, "ls"));
  }

  private static List<String> runCommand(ChannelExec channel, String command) throws JSchException {

    try {
      channel.setCommand(command);
      channel.setInputStream(null);
      InputStream output = channel.getInputStream();
      channel.connect();

      String result = CharStreams.toString(new InputStreamReader(output));
      return asList(result.split("\n"));

    } catch (JSchException | IOException e) {
//      closeConnection(channel, session);
      throw new RuntimeException(e);

    } finally {
//      closeConnection(channel, session);
    }
  }

  private static Session setupSshSession() throws JSchException {
    Session session = new JSch().getSession(SSH_LOGIN, SSH_HOST, 22);
    session.setPassword(SSH_PASSWORD);
    session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
    session.setConfig("StrictHostKeyChecking", "no"); // disable check for RSA key
    return session;
  }

  private static void closeConnection(ChannelExec channel, Session session) {
    try {
      channel.disconnect();
    } catch (Exception ignored) {
    }
    session.disconnect();
  }
}
