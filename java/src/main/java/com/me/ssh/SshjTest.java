package com.me.ssh;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;

import java.io.Console;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SshjTest {
  private static final Console con = System.console();

  public static void main(String... args) throws IOException {
    final SSHClient ssh = new SSHClient();
//    ssh.loadKnownHosts();
    ssh.connect("106.14.150.229", 22);
    ssh.authPassword("root", "Hminde1314");
    Session session = null;
    try {
//      ssh.authPublickey(System.getProperty("user.name"));
      session = ssh.startSession();
      final Command cmd = session.exec("ls -l");
      con.writer().print(IOUtils.readFully(cmd.getInputStream()).toString());
      cmd.join(5, TimeUnit.SECONDS);
      con.writer().print("\n** exit status: " + cmd.getExitStatus());
    } finally {
      try {
        if (session != null) {
          session.close();
        }
      } catch (IOException e) {
        // Do Nothing
      }

      ssh.disconnect();
    }
  }
}
