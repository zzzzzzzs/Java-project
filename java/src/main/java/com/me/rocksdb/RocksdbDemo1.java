package com.me.rocksdb;

import java.util.Map;

public class RocksdbDemo1 {
    public static void main(String[] args) throws Exception {
        RocksDBExample dbExample = new RocksDBExample("C:\\data\\rocksdb");
        AsyncRocksDBWriter writer = new AsyncRocksDBWriter(dbExample.getDb(), 10);

        // 异步写入单个键值
        writer.asyncWrite("key1", "value1").thenRun(() -> System.out.println("写入完成: key1"));

        // 异步批量写入
        Map<String, String> keyValues = Map.of(
                "key2", "value2",
                "key3", "value3"
        );
        writer.asyncBatchWrite(keyValues).thenRun(() -> System.out.println("批量写入完成"));

        writer.shutdown();
        dbExample.close();
    }
}
