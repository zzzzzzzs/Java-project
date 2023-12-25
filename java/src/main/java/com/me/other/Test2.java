package com.me.other;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;

public class Test2 {
    public static int findMostFrequentNumber(int[] nums) {
        // Step 1: Count the frequency of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Build a max heap based on frequency
        PriorityQueue<Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );
        maxHeap.addAll(frequencyMap.entrySet());

        // Step 3: Return the number with the highest frequency
        return maxHeap.peek().getKey();
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2,2,2,2, 1, 4, 1};
        System.out.println("The most frequent number is: " + findMostFrequentNumber(nums));
    }
}
