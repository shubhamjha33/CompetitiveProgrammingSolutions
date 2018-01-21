package com.shubhamjha33.problemset.topcoder.tco17.round1a;

import java.util.PriorityQueue;

public class CheeseSlicing {

    private static int[][][] dp;

    public static int totalArea(int a, int b, int c, int s) {
        dp = new int[a + 10][b + 10][c + 10];
        for (int i = 0; i < a + 10; i++) {
            for (int j = 0; j < b + 10; j++) {
                for (int k = 0; k < c + 10; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return computeDP(a,b,c,s);
    }

    private static int computeDP(int a, int b, int c, int s) {
        if (dp[a][b][c] != -1)
            return dp[a][b][c];
        dp[a][b][c] = calcGoodSliceSurfaceArea(a,b,c,s);
        for(int i=1;i<a;i++){
            dp[a][b][c]=Math.max(dp[a][b][c], computeDP(i,b,c,s)+computeDP(a-i,b,c,s));
        }
        for(int i=1;i<b;i++){
            dp[a][b][c]=Math.max(dp[a][b][c], computeDP(a,i,c,s)+computeDP(a,b-i,c,s));
        }
        for(int i=1;i<c;i++){
            dp[a][b][c]=Math.max(dp[a][b][c], computeDP(a,b,i,s)+computeDP(a,b,c-i,s));
        }
        return dp[a][b][c];
    }

    private static int calcGoodSliceSurfaceArea(int a, int b, int c, int s) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(a);
        pq.add(b);
        pq.add(c);
        int minThickness = pq.poll();
        if (minThickness < s)
            return 0;
        int x = pq.poll();
        int y = pq.poll();
        return x * y;
    }

}
