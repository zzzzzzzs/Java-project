package com.me.other;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public enum ContextUtil {
    INSTANCE;
    private final static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final String OPERATOR = "operator";
    private final String OWNER = "owner";

    public void clear() {
        Map<String, Object> store = loadStore();
        store.clear();
    }

    public void setOperator(Long operator) {
        Map<String, Object> store = loadStore();
        store.put(OPERATOR, operator);
    }

    public Long getOperator() {
        Map<String, Object> store = loadStore();
        return (Long) store.get(OPERATOR);
    }

    public void setOwer(String owner) {
        Map<String, Object> store = loadStore();
        store.put(OWNER, owner);
    }

    public String getOwner() {
        Map<String, Object> store = loadStore();
        return (String) store.get(OWNER);
    }

    //读写锁二次验证
    private Map<String, Object> loadStore() {
        lock.readLock().lock();
        Map<String, Object> store = threadLocal.get();
        if (null == store) {
            // Must release read lock before acquiring write lock
            lock.readLock().unlock();
            lock.writeLock().lock();
            // Recheck state because another thread might have
            // acquired write lock and changed state before we did.
            store = threadLocal.get();
            if (null == store) {
                store = new HashMap<>();
                threadLocal.set(store);
            }
            // Downgrade by acquiring read lock before releasing write lock
            lock.readLock().lock();
            // Unlock write, still hold read
            lock.writeLock().unlock();
        }
        lock.readLock().unlock();
        return store;
    }

    public static void main(String[] args) {
        // 设置操作员信息
        ContextUtil.INSTANCE.setOperator(12345L);

// 获取操作员信息
        Long operator = ContextUtil.INSTANCE.getOperator();
        System.out.println("Operator: " + operator);

// 设置所有者信息
        ContextUtil.INSTANCE.setOwer("Alice");

// 获取所有者信息
        String owner = ContextUtil.INSTANCE.getOwner();
        System.out.println("Owner: " + owner);

    }
}

