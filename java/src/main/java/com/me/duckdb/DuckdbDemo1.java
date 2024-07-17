package com.me.duckdb;

import org.duckdb.DuckDBAppender;
import org.duckdb.DuckDBConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DuckdbDemo1 {

    // 读取文件
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

    public static void main(String[] args) throws SQLException {
        String s = readFile("C:\\workspace\\zs\\duckdb\\large_file.json");

        DuckDBConnection conn = (DuckDBConnection) DriverManager.getConnection("jdbc:duckdb:C:\\workspace\\zs\\duckdb\\duckdb_demo.db");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE tbl (data json)");

        try (DuckDBAppender appender = conn.createAppender(DuckDBConnection.DEFAULT_SCHEMA, "tbl")) {
            for (int i = 0; i < 100000; i++) {
                System.out.println(i);
                appender.beginRow();
                appender.append(s);
                appender.endRow();
                if (i % 100 == 0) {
                    appender.flush();
                }
            }
            appender.flush();
        }
        stmt.close();
    }


}
