package com.me.DataStructure.PriorityQueueManager;


import java.util.*;

class PriorityEntry {
    private int level;
    private String data;

    public PriorityEntry(int level, String data) {
        this.level = level;
        this.data = data;
    }

    public int getLevel() {
        return level;
    }

    public String getData() {
        return data;
    }

    // 实现 Comparable 接口以便在优先队列中按 level 排序
    public static class PriorityEntryComparator implements Comparator<PriorityEntry> {
        @Override
        public int compare(PriorityEntry o1, PriorityEntry o2) {
            // 优先队列默认是小顶堆，因此这里用 o2 减 o1 以实现大顶堆
            return o2.getLevel() - o1.getLevel();
        }
    }

    @Override
    public String toString() {
        return "(" + level + ", " + data + ")";
    }
}
// TODO 这是一个根据 key 获取最大 level 的数据结构
public class PriorityQueueManager {
    private Map<String, PriorityQueue<PriorityEntry>> data;

    public PriorityQueueManager() {
        data = new HashMap<>();
    }

    public void addQuery(String key, int level, String data) {
        if (!this.data.containsKey(key)) {
            this.data.put(key, new PriorityQueue<>(new PriorityEntry.PriorityEntryComparator()));
        }
        this.data.get(key).add(new PriorityEntry(level, data));
    }

    public String getTopQueryById(String key) {
        PriorityQueue<PriorityEntry> queue = this.data.get(key);
        if (queue != null && !queue.isEmpty()) {
            return queue.peek().getData();
        }
        return null;
    }

    public void removeQueryById(String key) {
        this.data.remove(key);
    }

    // 获取 key 对应的所有 PriorityEntry 的 data
    public List<String> getQueriesDataByKey(String key) {
        List<String> dataList = new ArrayList<>();
        PriorityQueue<PriorityEntry> queue = data.get(key);
        if (queue != null) {
            for (PriorityEntry entry : queue) {
                dataList.add(entry.getData());
            }
        }
        return dataList;
    }
}
