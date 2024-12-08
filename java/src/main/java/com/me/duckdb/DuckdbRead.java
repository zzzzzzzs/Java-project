package com.me.duckdb;

import org.duckdb.DuckDBAppender;
import org.duckdb.DuckDBConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DuckdbRead {

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DriverManager.getConnection("jdbc:duckdb:C:\\workspace\\zs\\duckdb\\duckdb_demo.db");
             Statement stmt = conn.createStatement()) {

            // 查询数据
            String querySQL = "SELECT * FROM tbl limit 10";
            ResultSet rs = stmt.executeQuery(querySQL);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
