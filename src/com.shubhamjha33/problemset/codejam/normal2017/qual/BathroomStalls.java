package com.shubhamjha33.problemset.codejam.normal2017.qual;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BathroomStalls {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            long n, k;
            String[] input = br.readLine().split(" ");
            n = Long.parseLong(input[0]);
            k = Long.parseLong(input[1]);
            PriorityQueue<Stall> pq = new PriorityQueue<>();
            pq.add(new Stall(1, n + 2));
            Stall lastStall = null;
            while (k-- > 0) {
                lastStall = pq.poll();
                pq.addAll(getNextStalls(lastStall));
            }
            System.out.println(String.format("Case #%d: %d %d", i, Math.max(lastStall.getLs(), lastStall.getRs()), Math.min(lastStall.getLs(), lastStall.getRs())));
        }
    }

    public static List<Stall> getNextStalls(Stall stall) {
        long stallNumber = stall.getStallNumber();
        List<Stall> stalls = new ArrayList<>();
        if (stallNumber - 1 > stall.getL())
            stalls.add(new Stall(stall.getL(), stallNumber));
        if (stallNumber + 1 < stall.getR())
            stalls.add(new Stall(stallNumber, stall.getR()));
        return stalls;
    }

    static class Stall implements Comparable<Stall> {
        private long l, r;

        public Stall(long l, long r) {
            this.l = l;
            this.r = r;
        }

        public long getL() {
            return l;
        }

        public long getR() {
            return r;
        }

        public long getLs() {
            return (r - l) / 2 - 1;
        }

        public long getRs() {
            return r - ((r - l) / 2 + l) - 1;
        }

        public long getStallNumber() {
            return (r - l) / 2 + l;
        }

        @Override
        public int compareTo(Stall o) {
            long firstMin = Math.min(getLs(), getRs());
            long firstMax = Math.max(getLs(), getRs());
            long secondMin = Math.min(o.getLs(), o.getRs());
            long secondMax = Math.max(o.getLs(), o.getRs());
            if (firstMin > secondMin) {
                return -1;
            } else if (firstMin == secondMin) {
                if (firstMax > secondMax) {
                    return -1;
                } else if (firstMax == secondMax) {
                    if (getStallNumber() <= o.getStallNumber())
                        return -1;
                    else
                        return 1;
                } else return 1;
            } else return 1;
        }
    }
}
