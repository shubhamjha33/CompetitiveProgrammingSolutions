package com.shubhamjha33.problemset.codejam.normal2017.round1c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ParentingPartnering {

    private static long[][][][] dp;
    private static boolean[][][] isComputed;
    private static List<Slot> slots;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            List<String> data = Arrays.asList(br.readLine().split(" "));
            int n = Integer.parseInt(data.get(0));
            int m = Integer.parseInt(data.get(1));
            List<Slot> inputSlots = new ArrayList<>();
            List<Integer> dataVal;
            for (int j = 0; j < n; j++) {
                dataVal = Arrays.stream(br.readLine().split(" "))
                        .map(val -> Integer.parseInt(val)).collect(Collectors.toList());
                inputSlots.add(new Slot(dataVal.get(0), dataVal.get(1), Type.ONE));
            }
            for (int j = 0; j < m; j++) {
                dataVal = Arrays.stream(br.readLine().split(" "))
                        .map(val -> Integer.parseInt(val)).collect(Collectors.toList());
                inputSlots.add(new Slot(dataVal.get(0), dataVal.get(1), Type.TWO));
            }
            Collections.sort(inputSlots);
            slots = new ArrayList<>();
            if (inputSlots.get(0).getStart() > 0) {
                slots.add(new Slot(0, inputSlots.get(0).getStart(), Type.ALL));
            }
            slots.add(inputSlots.get(0));
            for (int j = 1; j < inputSlots.size(); j++) {
                Slot one = inputSlots.get(j - 1);
                Slot two = inputSlots.get(j);
                if (one.getEnd() < two.getStart()) {
                    slots.add(new Slot(one.getEnd(), two.getStart(), Type.ALL));
                }
                slots.add(two);
            }
            int totalSlots = slots.size();
            dp = new long[totalSlots][721][721][2];
            for (int p = 0; p < totalSlots; p++) {
                for (int q = 0; q < 721; q++) {
                    for (int r = 0; r < 721; r++) {
                        for (int z = 0; z < 2; z++) {
                            dp[p][q][r][z] = -1;
                        }
                    }
                }
            }
            Slot slot = slots.get(0);
            long ans = Integer.MAX_VALUE;
            int diff = slot.getEnd() - slot.getStart();
            if (slot.getType().equals(Type.ALL)) {
                ans = Math.min(computeDp(1, 720 - diff, 720, 0),
                        computeDp(1, 720, 720 - diff, 1));
            } else if (slot.getType().equals(Type.TWO)) {
                ans = computeDp(1, 720 - diff, 720, 0);
            } else if (slot.getType().equals(Type.ONE)) {
                ans = computeDp(1, 720, 720 - diff, 1);
            }
            System.out.println(String.format("Case #%d: %d", i, ans));
        }
    }

    private static long computeDp(int index, int oneLeft, int twoLeft, int prev) {
        System.out.println(index + " " + oneLeft + " " + twoLeft + " " + prev);
        if (index >= slots.size() && oneLeft == 0 && twoLeft == 0)
            return 0;
        if (index >= slots.size())
            return Integer.MAX_VALUE;
        if (dp[index][oneLeft][twoLeft][prev] != -1)
            return dp[index][oneLeft][twoLeft][prev];
        dp[index][oneLeft][twoLeft][prev] = Integer.MAX_VALUE;
        Slot slot = slots.get(index);
        if (Type.ALL.equals(slot.getType()) || Type.TWO.equals(slot.getType())) {
            int newOneLeft = oneLeft - slot.getEnd() + slot.getStart();
            if (newOneLeft >= 0)
                dp[index][oneLeft][twoLeft][prev] = Math.min(prev + computeDp(index + 1, newOneLeft, twoLeft, 0),
                        dp[index][oneLeft][twoLeft][prev]);
        }
        if (Type.ALL.equals(slot.getType()) || Type.ONE.equals(slot.getType())) {
            int newTwoLeft = twoLeft - slot.getEnd() + slot.getStart();
            if (newTwoLeft >= 0)
                dp[index][oneLeft][twoLeft][prev] = Math.min(1 - prev + computeDp(index + 1, oneLeft, newTwoLeft, 1),
                        dp[index][oneLeft][twoLeft][prev]);
        }
        return dp[index][oneLeft][twoLeft][prev];
    }

    private static enum Type {
        ALL,
        ONE,
        TWO
    }

    private static class Slot implements Comparable<Slot> {
        private int start, end;
        private Type type;

        public Slot(int start, int end, Type type) {
            this.start = start;
            this.end = end;
            this.type = type;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public Type getType() {
            return type;
        }


        @Override
        public int compareTo(Slot o) {
            if (start < o.start)
                return -1;
            else if (start > o.start)
                return 1;
            if (end < o.end)
                return -1;
            else if (end > o.end)
                return 1;
            return 0;
        }
    }
}
