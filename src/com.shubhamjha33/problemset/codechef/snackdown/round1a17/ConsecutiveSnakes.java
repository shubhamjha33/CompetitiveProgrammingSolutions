package com.shubhamjha33.problemset.codechef.snackdown.round1a17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConsecutiveSnakes {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] inputData = br.readLine().split(" ");
            long n = Long.parseLong(inputData[0]);
            long l = Long.parseLong(inputData[1]);
            long a = Long.parseLong(inputData[2]);
            long b = Long.parseLong(inputData[3]);
            List<Long> snakeArr = Arrays.stream(br.readLine()
                    .split(" ")).map(Long::parseLong)
                    .sorted()
                    .collect(Collectors.toList());
            long start = getStart(n, snakeArr.get(0), snakeArr.get((int) n - 1) + l, l);
            if (start < a) {
                start = a;
            }
            if (start + n * l > b) {
                start = b - n * l;
            }
            long ans = 0;
            for (int i = 0; i < n; i++) {
                long difference = start - snakeArr.get(i);
                if (difference < 0)
                    difference = -difference;
                ans += difference;
                start += l;
            }
            System.out.println(ans);
        }
    }

    private static long getStart(long n, long currentStart, long currentEnd, long l) {
        long difference = currentEnd - currentStart;
        if (difference >= n * l)
            return currentStart;
        return currentStart - (n * l - difference) / 2;
    }

}
