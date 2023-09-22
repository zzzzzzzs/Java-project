package com.me.other;

import java.util.*;

public class Test4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numStrings = scanner.nextLine().split(",");
        int n = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> combinations = new ArrayList<>();
        Arrays.sort(numStrings);

        for (int i = n; i <= numStrings.length; i++) {
            generateCombinations(numStrings, i, 0, new ArrayList<>(), combinations);
        }


        if (!combinations.isEmpty()) {
            for (List<Integer> combination : combinations) {
                StringBuilder sb = new StringBuilder();
                for (int num : combination) {
                    sb.append(num).append(",");
                }
                String combinationString = sb.toString();
                System.out.println(combinationString.substring(0,
                        combinationString.length() - 1));
            }
        } else {
            System.out.println("None");
        }
    }
    private static void generateCombinations(String[] numStrings, int k, int start,
                                             List<Integer> current, List<List<Integer>> combinations) {
        if (k == 0) {
            combinations.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i <= numStrings.length - k; i++) {
            current.add(Integer.parseInt(numStrings[i]));
            generateCombinations(numStrings, k - 1, i + 1, current, combinations);
            current.remove(current.size() - 1);
        }
    }
}
