package com.me.H2;

import org.h2.tools.DeleteDbFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloWorld {
  public static void main(String... args) throws Exception {
    // delete the database named 'test' in the user home directory
    DeleteDbFiles.execute("~", "test", true);

    Class.forName("org.h2.Driver");
    Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
    Statement stat = conn.createStatement();

    // this line would initialize the database
    // from the SQL script file 'init.sql'
    // stat.execute("runscript from 'init.sql'");

    stat.execute("create table test(id int primary key, name varchar(255))");
    stat.execute("insert into test values(1, 'Hello')");
    ResultSet rs;
    rs = stat.executeQuery("select * from test");
    while (rs.next()) {
      System.out.println(rs.getString("name"));
    }
    stat.close();
    conn.close();
  }
}
