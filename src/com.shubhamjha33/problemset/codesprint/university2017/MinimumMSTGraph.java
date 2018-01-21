package com.shubhamjha33.problemset.codesprint.university2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MinimumMSTGraph {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = getAnInt(br);
        while (g-- > 0) {
            List<Long> list = Arrays.stream(br.readLine().split(" "))
                    .map(Long::parseLong).collect(Collectors.toList());
            long n = list.get(0);
            long m = list.get(1);
            long s = list.get(2);
            long val1 = 1;
            long sum = Long.MAX_VALUE;
            long left = Math.min(m - 1, (n - 1) * (n - 2) / 2);
            while (val1 <= s) {
                long tempSum = left * val1;
                if ((n - 2) * val1 >= s)
                    break;
                tempSum = tempSum + (s - (n - 2) * val1) * (m - left);
                sum = Math.min(sum, tempSum);
                val1++;
            }
            System.out.println(sum);
        }
    }

    private static int getAnInt(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }
}
