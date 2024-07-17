package com.me.other;

import org.duckdb.DuckDBAppender;
import org.duckdb.DuckDBConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Test3 {
    public static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (FileReader reader = new FileReader(filePath)) {
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String s = readFile("C:\\workspace\\zs\\duckdb\\large_file.json");

        try {
            DuckDBConnection conn = (DuckDBConnection) DriverManager.getConnection("jdbc:duckdb:C:\\workspace\\zs\\duckdb\\duckdb_demo.db");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE tbl (data json)");

            // using try-with-resources to automatically close the appender at the end of the scope
            try (DuckDBAppender appender = conn.createAppender(DuckDBConnection.DEFAULT_SCHEMA, "tbl")) {
                for (int i = 0; i < 100000; i++) {
                    System.out.println(i);
                    appender.beginRow();
                    appender.append(s);
                    appender.endRow();

                    // 每插入100行数据执行一次异步flush
                    if (i % 1000 == 0 && i > 0) {
                        appender.flush();
                    }
                }

                // 最后一次flush确保所有数据都写入数据库
                appender.flush();
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
