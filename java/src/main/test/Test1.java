import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.net.URLDecoder;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;

/**
 * @author zs
 * @date 2021/9/14.
 */
public class Test1 {

  public static void main(String[] args) throws UnsupportedEncodingException {
    String sql = "CREATE TABLE products (\n" +
        "    name STRING\n" +
        ") WITH (\n" +
        "  'connector' = 'postgres-cdc',\n" +
        "  'hostname' = 'localhost',\n" +
        "  'port' = '5432',\n" +
        "  'username' = 'postgres',\n" +
        "  'password' = '111',\n" +
        "  'database-name' = 'postgres',\n" +
        "  'schema-name' = 'public',\n" +
        "  'table-name' = 'aaa'\n" +
        ");";
    System.out.println(sql);
  }
}

