package com.me.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;

public class RocksDBExample {
    static {
        RocksDB.loadLibrary(); // 加载RocksDB的本地库
    }

    private RocksDB db;

    public RocksDBExample(String dbPath) throws Exception {
        Options options = new Options();
        options.setCreateIfMissing(true); // 如果路径不存在则创建

        this.db = RocksDB.open(options, dbPath); // 打开数据库连接
    }

    public RocksDB getDb() {
        return db;
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }
}
