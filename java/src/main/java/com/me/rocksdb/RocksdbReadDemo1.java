package com.me.rocksdb;

import java.util.Map;

public class RocksdbReadDemo1 {
    public static void main(String[] args) throws Exception {
        RocksDBExample dbExample = new RocksDBExample("C:\\data\\rocksdb");
        RocksDBReader reader = new RocksDBReader(dbExample.getDb());

        // 假设先前已经插入了 "key1": "value1"
        String value = reader.read("key1");
        if (value != null) {
            System.out.println("读取到的值: " + value);
        } else {
            System.out.println("未找到该键");
        }

        dbExample.close();
    }
}
