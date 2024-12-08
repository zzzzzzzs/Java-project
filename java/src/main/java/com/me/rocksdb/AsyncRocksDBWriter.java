package com.me.rocksdb;

import org.rocksdb.RocksDB;
import org.rocksdb.WriteBatch;
import org.rocksdb.WriteOptions;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncRocksDBWriter {
    private final RocksDB db;
    private final ExecutorService executor;

    public AsyncRocksDBWriter(RocksDB db, int threadCount) {
        this.db = db;
        this.executor = Executors.newFixedThreadPool(threadCount); // 自定义线程池
    }

    public CompletableFuture<Void> asyncWrite(String key, String value) {
        return CompletableFuture.runAsync(() -> {
            try {
                db.put(key.getBytes(), value.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, executor);
    }

    public CompletableFuture<Void> asyncBatchWrite(Map<String, String> keyValues) {
        return CompletableFuture.runAsync(() -> {
            try (WriteBatch batch = new WriteBatch(); WriteOptions writeOptions = new WriteOptions()) {
                for (Map.Entry<String, String> entry : keyValues.entrySet()) {
                    batch.put(entry.getKey().getBytes(), entry.getValue().getBytes());
                }
                db.write(writeOptions, batch);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, executor);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
