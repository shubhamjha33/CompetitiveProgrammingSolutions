package com.shubhamjha33.problemset.hackerrank.world.codesprint10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ZigZagArray {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> data = Arrays.stream(br.readLine().split(" "))
                .map(val -> Integer.parseInt(val))
                .collect(Collectors.toList());
        int incCount = 0;
        int decCount = 0;
        int ans = 0;
        int prevIndex = 0;
        for (int i = 1; i < n; i++) {
            if (data.get(i) > data.get(i - 1)) {
                incCount++;
                decCount = 0;
            } else if (data.get(i) < data.get(i - 1)) {
                incCount = 0;
                decCount++;
            }
            if (incCount == 2) {
                incCount--;
                ans++;
            }
            else if (decCount == 2) {
                decCount--;
                ans++;
            }
            else {
                prevIndex = i;
            }
        }
        System.out.println(ans);
    }
}
