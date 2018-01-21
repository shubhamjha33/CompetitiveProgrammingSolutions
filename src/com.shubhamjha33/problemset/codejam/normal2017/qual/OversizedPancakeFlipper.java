package com.shubhamjha33.problemset.codejam.normal2017.qual;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OversizedPancakeFlipper {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int counter = 1;
        for (counter = 1; counter <= t; counter++) {
            String[] input = br.readLine().split(" ");
            char[] arr = input[0].toCharArray();
            int k = Integer.parseInt(input[1]);
            int count = 0;
            int nonHappyCount = 0;
            for (int i = 0; i <= arr.length - k; i++) {
                if (arr[i] == '-') {
                    count++;
                    for (int j = i; j < i + k; j++) {
                        if (arr[j] == '-')
                            arr[j] = '+';
                        else
                            arr[j] = '-';
                    }
                }
            }
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == '-')
                    nonHappyCount++;
            }
            if (nonHappyCount > 0) {
                System.out.println(String.format("Case #%d: IMPOSSIBLE", counter));
            } else
                System.out.println(String.format("Case #%d: %d", counter, count));
        }
    }
}
