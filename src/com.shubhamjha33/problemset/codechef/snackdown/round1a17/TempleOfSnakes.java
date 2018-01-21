package com.shubhamjha33.problemset.codechef.snackdown.round1a17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TempleOfSnakes {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            long n = Long.parseLong(br.readLine());
            List<Long> arr = Arrays.stream(br.readLine().split(" "))
                    .map(val -> Long.parseLong(val))
                    .collect(Collectors.toList());
            System.out.println(getTemple(arr, (int) n));
        }
    }

    private static long getTemple(List<Long> data, int n) {
        long current = 1;
        long[] leftList = new long[n];
        long[] rightList = new long[n];
        int i;
        long sum = 0;
        for (i = 0; i < n; i++) {
            leftList[i] = (Math.min(data.get(i), current));
            sum += data.get(i);
            current = leftList[i] + 1;
        }
        current = 1;
        for (i = n - 1; i >= 0; i--) {
            rightList[i] = (Math.min(data.get(i), current));
            current = rightList[i] + 1;
        }
        long maxTempleSize = 0;
        for (i = 0; i < n; i++) {
            maxTempleSize = Math.max(maxTempleSize, Math.min(leftList[i], rightList[i]));
        }
        return (sum - maxTempleSize - (maxTempleSize - 1) * (maxTempleSize));
    }
}
