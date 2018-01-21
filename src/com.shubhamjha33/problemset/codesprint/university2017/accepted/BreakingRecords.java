package com.shubhamjha33.problemset.codesprint.university2017.accepted;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BreakingRecords {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Long> scores = Arrays.stream(br.readLine().split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        int minCount = 0, maxCount = 0;
        long minValue = scores.get(0);
        long maxValue = scores.get(0);
        for (int i = 1; i < n; i++) {
            if (minValue > scores.get(i)) {
                minCount++;
                minValue = scores.get(i);
            }
            if (maxValue < scores.get(i)) {
                maxCount++;
                maxValue = scores.get(i);
            }
        }
        System.out.println(maxCount + " " + minCount);
    }
}
