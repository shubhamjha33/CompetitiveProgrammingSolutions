package com.shubhamjha33.problemset.codejam.normal2017.round1c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class AmpleSyrup {

    private static double PI = Math.acos(-1);
    private static double[][] dp;
    private static boolean[][] isComputed;
    private static List<Cake> cakes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            List<String> data = Arrays.asList(br.readLine().split(" "));
            int n = Integer.parseInt(data.get(0));
            int k = Integer.parseInt(data.get(1));
            cakes = new ArrayList<>();
            dp = new double[n][k + 1];
            isComputed = new boolean[n][k + 1];
            for (int j = 0; j < n; j++) {
                data = Arrays.asList(br.readLine().split(" "));
                cakes.add(new Cake(Double.parseDouble(data.get(0)), Double.parseDouble(data.get(1))));
                for (int p = 0; p < k + 1; p++) {
                    isComputed[j][p] = false;
                    dp[j][p] = 0.0;
                }
            }
            Collections.sort(cakes);
            double ans = 0.0;
            for (int j = 0; j < n; j++) {
                Cake cake = cakes.get(j);
//                System.out.println(cake.getP()+" "+cake.getH());
                ans = Math.max(ans, PI * cake.getR() * cake.getR() + computeDp(j, n, k));
            }
            System.out.println(String.format("Case #%d: %.9f", i, ans));
        }
    }

    private static double computeDp(int index, int n, int k) {
//        System.out.println(index + " " + n + " " + k);
        if (isComputed[index][k])
            return dp[index][k];
        if (k == 0) {
            isComputed[index][k] = true;
            return dp[index][k] = 0.0;
        }
        if (k == 1) {
            isComputed[index][k] = true;
            Cake cake = cakes.get(index);
            return dp[index][k] = 2 * PI * cake.getR() * cake.getH();
        }
        isComputed[index][k] = true;
        dp[index][k] = 0.0;
        for (int i = index + 1; i + k - 2 < n; i++) {
            dp[index][k] = Math.max(dp[index][k], computeDp(i, n, k - 1));
        }
        Cake cake = cakes.get(index);
        dp[index][k] += 2 * PI * cake.getR() * cake.getH();
        return dp[index][k];
    }

    public static class Cake implements Comparable<Cake> {
        private double r, h;

        public Cake(double r, double h) {
            this.r = r;
            this.h = h;
        }

        @Override
        public int compareTo(Cake o) {
            if (r < o.r)
                return 1;
            else if (r > o.r) {
                return -1;
            }
            if (h < o.h)
                return 1;
            else if (h > o.h) {
                return -1;
            }
            return 0;
        }

        public double getR() {
            return r;
        }

        public double getH() {
            return h;
        }
    }
}
