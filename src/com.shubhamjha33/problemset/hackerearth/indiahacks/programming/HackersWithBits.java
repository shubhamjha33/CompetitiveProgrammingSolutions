package com.shubhamjha33.problemset.hackerearth.indiahacks.programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class HackersWithBits {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] arr = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList()).toArray(new Integer[0]);
        int[] count = new int[2];
        count[0] = count[1] = 0;
        int i;
        for (i = 0; i < n; i++) {
            count[arr[i]]++;
        }
    }
}
