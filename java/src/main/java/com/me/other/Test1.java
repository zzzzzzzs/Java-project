package com.me.other;

import com.alibaba.fastjson.JSON;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test1 {
    public static void main(String[] args) {
        try {
            generateLargeJson("large_file.json", 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateLargeJson(String filePath, int targetSizeMB) throws IOException {
        Map<String, String> data = new HashMap<>();
        Random random = new Random();
        int sizeInBytes = targetSizeMB * 1024 * 1024;
        int currentSize = 0;

        while (currentSize < sizeInBytes) {
            String key = generateRandomString(random, 10);
            String value = generateRandomString(random, 50000);
            data.put(key, value);
            currentSize = JSON.toJSONString(data).getBytes().length;
            System.out.println("Current size: " + currentSize + " bytes");
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(JSON.toJSONString(data));
        }

        System.out.println("JSON file created at " + filePath + " with size approximately " + targetSizeMB + "MB");
    }

    public static String generateRandomString(Random random, int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
