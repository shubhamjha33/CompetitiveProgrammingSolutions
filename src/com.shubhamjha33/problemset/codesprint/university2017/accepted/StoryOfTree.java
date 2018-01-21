package com.shubhamjha33.problemset.codesprint.university2017.accepted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class StoryOfTree {

    private static Map<Integer, Map<Integer, Integer>> graph;
    private static Map<Integer, Set<Integer>> guess;
    private static int[] dp;

    private static int gcd(int a, int b) {
        if (a == 0)
            return b;
        else
            return gcd(b % a, a);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = getAnInt(br);
        while (q-- > 0) {
            int n = getAnInt(br);
            graph = new TreeMap<>();
            guess = new TreeMap<>();
            int counter = 0;
            for (int i = 1; i < n; i++) {
                List<Integer> pair = getIntegers(br);
                if (!graph.containsKey(pair.get(0)))
                    graph.put(pair.get(0), new TreeMap<>());
                if (!graph.containsKey(pair.get(1)))
                    graph.put(pair.get(1), new TreeMap<>());
                graph.get(pair.get(0)).put(pair.get(1), counter);
                counter++;
                graph.get(pair.get(1)).put(pair.get(0), counter);
                counter++;
            }
            List<Integer> pair = getIntegers(br);
            int g = pair.get(0);
            int k = pair.get(1);
            for (int i = 0; i < g; i++) {
                pair = getIntegers(br);
                if (!guess.containsKey(pair.get(0)))
                    guess.put(pair.get(0), new TreeSet<>());
                guess.get(pair.get(0)).add(pair.get(1));
            }
            dp = new int[2 * n];
            for (int i = 0; i < 2 * n; i++) {
                dp[i] = -1;
            }
            int num = graph.entrySet()
                    .stream()
                    .map(keyValuePair -> {
                        int correctGuess = keyValuePair.getValue()
                                .entrySet()
                                .stream()
                                .map(secondPair -> computeDp(keyValuePair.getKey(),
                                        secondPair.getKey()))
                                .reduce(0, (x, y) -> x + y);
                        if (correctGuess >= k)
                            return 1;
                        else
                            return 0;
                    }).reduce(0, (x, y) -> x + y);
            int calculatedGcd = 1;
            if (num == 0) {
                calculatedGcd = n;
            } else {
                calculatedGcd = gcd(num, n);
            }
            num /= calculatedGcd;
            n /= calculatedGcd;
            System.out.printf("%s/%s\n", num, n);
        }
    }

    private static int computeDp(int u, int v) {
        int index = graph.get(u).get(v);
        if (dp[index] != -1)
            return dp[index];
        if (guess.containsKey(u) && guess.get(u).contains(v))
            dp[index] = 1;
        else
            dp[index] = 0;
        return dp[index] += graph.get(v).entrySet()
                .stream()
                .map(keyValuePair -> {
                    if (keyValuePair.getKey() == u)
                        return 0;
                    return computeDp(v, keyValuePair.getKey());
                }).reduce(0, (x, y) -> (x + y));
    }

    private static List<Integer> getIntegers(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static int getAnInt(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }
}
