package com.shubhamjha33.problemset.codesprint.university2017.accepted;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SherlockArrayMergingAlgorithm {

    private static long[][] dp;
    private static long[] fact, denFact;
    private static long MOD = 1000000007;
    private static int MAXN = 1500;
    private static List<Integer> arr;

    private static long modExp(long val, long power) {
        long res = 1;
        long exp = val;
        while (power != 0) {
            if ((power & 1) == 1) {
                res = (res * exp) % MOD;
            }
            power >>= 1;
            exp = (exp * exp) % MOD;
        }
        return res;
    }

    private static void initFact() {
        fact = new long[MAXN];
        denFact = new long[MAXN];
        fact[0] = 1;
        denFact[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            fact[i] = ((long) i * fact[i - 1]) % MOD;
            denFact[i] = modExp(fact[i], MOD - 2);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        initFact();
        int n = Integer.parseInt(bufferedReader.readLine());
        arr = Arrays.stream(bufferedReader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        dp = new long[n + 10][n + 10];
        for (int i = 0; i < n + 10; i++) {
            for (int j = 0; j < n + 10; j++) {
                dp[i][j] = -1;
            }
        }
        long result = 0;
        for (int i = 1; i <= n; i++) {
            result = (result + computeDp(i, i)) % MOD;
            if (i != n && arr.get(i) < arr.get(i - 1))
                break;
        }
//        for (int i = 0; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                System.out.println(i + "," + j + "=" + dp[i][j]);
//            }
//        }
        System.out.println(result);
    }

    private static long computeDp(int startPos, int length) {
        if (dp[startPos][length] != -1)
            return dp[startPos][length];
        int j = 1;
        long sum = 0;
        if (startPos == arr.size() - 1)
            return dp[startPos][length] = length;
        if (startPos == arr.size()){
            return dp[startPos][length] = 1;
        }
        for (int i = startPos; i < arr.size() && j <= length; i++, j++) {
            if (i != startPos && arr.get(i) < arr.get(i - 1))
                break;
            sum = (sum + (((fact[length] * denFact[length - j]) % MOD) * computeDp(startPos + j, j)) % MOD) % MOD;
        }
        return dp[startPos][length] = sum;
    }
}
