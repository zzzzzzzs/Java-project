package com.me.other;


import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test1 {
    static {
        RocksDB.loadLibrary(); // 加载 RocksDB 的库
    }

    public static void main(String[] args) {
        // 指定数据库存储路径
        String dbPath = "C:\\Temp\\testdb";

        try (final Options options = new Options().setCreateIfMissing(true);
             final RocksDB db = RocksDB.open(options, dbPath)) {

            // 插入数据
            db.put("key1".getBytes(), "value1".getBytes());

            // 获取数据
            byte[] value = db.get("key1".getBytes());
            System.out.println("Retrieved value: " + new String(value));

            // 删除数据
            db.delete("key1".getBytes());

        } catch (RocksDBException e) {
            System.err.println("Error accessing RocksDB: " + e.getMessage());
        }
    }
}
