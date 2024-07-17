package com.me.other;

import java.util.*;



public class ShortestSubsequenceFinder {
    public static List<Integer> findShortestContinuousSubsequence(List<Integer> events, List<Integer> traits) {
        Map<Integer, Integer> traitCount = new HashMap<>();
        for (int trait : traits) {
            traitCount.put(trait, traitCount.getOrDefault(trait, 0) + 1);
        }
        int requiredTraits = traits.size();
        Map<Integer, Integer> window = new HashMap<>();
        int foundTraits = 0;
        int minLength = Integer.MAX_VALUE;
        int start = 0;

        for (int end = 0; end < events.size(); end++) {
            int current = events.get(end);
            if (traitCount.containsKey(current)) {
                window.put(current, window.getOrDefault(current, 0) + 1);

                if (window.get(current).intValue() == traitCount.get(current).intValue()) {
                    foundTraits++;
                }
            }
            while (foundTraits == requiredTraits) {
                if (end - start + 1 < minLength) {
                    minLength = end - start + 1;
                    start = end - minLength + 1;
                }
                int leftVal = events.get(start);
                if (traitCount.containsKey(leftVal)) {
                    window.put(leftVal, window.get(leftVal) - 1);

                    if (window.get(leftVal) < traitCount.get(leftVal)) {
                        foundTraits--;
                    }
                }
                start++;
            }
        }
        return events.subList(start - 1, start + minLength - 1);
    }

    public static void main(String[] args) {
        List<Integer> events = Arrays.asList(4, 8, 4, 3, 6, 6, 8);
        List<Integer> traits = Arrays.asList(4, 6, 8);

        List<Integer> result = findShortestContinuousSubsequence(events, traits);
        System.out.println(result); // Output: [4, 3, 6, 6, 8]
    }
}


