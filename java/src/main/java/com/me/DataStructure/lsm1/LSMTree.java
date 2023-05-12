package com.me.DataStructure.lsm1;

import java.util.*;
import java.io.*;

public class LSMTree {
    private Map<String, String> memory;
    private Map<String, List<Pair>> disk;
    private String filename;

    public LSMTree(String filename) {
        memory = new HashMap<>();
        disk = new HashMap<>();
        this.filename = filename;
    }

    public void put(String key, String value) {
        memory.put(key, value);
        if (memory.size() >= 1000) {
            flush();
        }
    }

    public String get(String key) {
        if (memory.containsKey(key)) {
            return memory.get(key);
        }
        List<Pair> data = new ArrayList<>();
        for (List<Pair> values : disk.values()) {
            for (Pair pair : values) {
                if (pair.key.equals(key)) {
                    data.add(pair);
                }
            }
        }
        if (!data.isEmpty()) {
            return data.get(data.size() - 1).value;
        }
        return null;
    }

    public void flush() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            List<String> sortedKeys = new ArrayList<>(memory.keySet());
            Collections.sort(sortedKeys);
            for (String key : sortedKeys) {
                String value = memory.get(key);
                if (!disk.containsKey(key)) {
                    disk.put(key, new ArrayList<>());
                }
                disk.get(key).add(new Pair(key, value));
                writer.write(key + "," + value + "\n");
            }
        } catch (IOException e) {
            System.err.println("Failed to write data to file: " + e.getMessage());
        }
        memory.clear();
    }

    private static class Pair {
        String key;
        String value;

        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        // 创建 LSM-Tree 实例，并指定数据文件名
        LSMTree tree = new LSMTree("data.txt");

        // 写入数据
        tree.put("key1", "value1");
        tree.put("key2", "value2");
        tree.put("key3", "value3");

        // 读取数据
        String value1 = tree.get("key1");
        String value2 = tree.get("key2");
        String value3 = tree.get("key3");
        String value4 = tree.get("key4");
        System.out.println(value1); // 输出：value1
        System.out.println(value2); // 输出：value2
        System.out.println(value3); // 输出：value3
        System.out.println(value4); // 输出：null

        // 将所有数据写入磁盘
//        tree.flush();
    }


}
