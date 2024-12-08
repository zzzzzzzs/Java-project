package com.me.rocksdb;

import org.rocksdb.RocksDB;

import java.nio.charset.StandardCharsets;

public class RocksDBReader {
    private final RocksDB db;

    public RocksDBReader(RocksDB db) {
        this.db = db;
    }

    public String read(String key) {
        try {
            byte[] value = db.get(key.getBytes(StandardCharsets.UTF_8)); // 获取字节数组
            if (value != null) {
                return new String(value, StandardCharsets.UTF_8); // 转换为字符串
            } else {
                return null; // 未找到键
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
