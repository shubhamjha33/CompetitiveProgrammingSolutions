package com.shubhamjha33.problemset.codejam.normal2017.round1c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class CoreTraining {

    private static List<AmpleSyrup.Cake> cakes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            List<String> data = Arrays.asList(br.readLine().split(" "));
            int n = Integer.parseInt(data.get(0));
            int k = Integer.parseInt(data.get(1));
            double u = Double.parseDouble(br.readLine());
            Map<Double, Integer> coreMap = new TreeMap<>();
            Arrays.stream(br.readLine().split(" "))
                    .map(val -> Double.parseDouble(val))
                    .forEach(prob -> {
                        if (coreMap.containsKey(prob))
                            coreMap.put(prob, coreMap.get(prob) + 1);
                        else
                            coreMap.put(prob, 1);
                    });
            double ans = 1.0;
            PriorityQueue<Core> priorityQueue = new PriorityQueue<>();
            coreMap.forEach((key, value) -> priorityQueue.add(new Core(key, value)));
            while (priorityQueue.size() != 1 && u > 0.0) {
                Core first = priorityQueue.poll();
                Core second = priorityQueue.poll();
                if ((second.getP() - first.getP()) * first.getCount() <= u) {
                    priorityQueue.add(new Core(second.getP(), first.getCount() + second.getCount()));
                    u -= (second.getP() - first.getP()) * first.getCount();
                } else {
                    priorityQueue.add(new Core(first.getP() + u / ((double) first.getCount()), first.getCount()));
                    priorityQueue.add(second);
                    u = 0.0;
                }
            }
            if (u > 0.0 && priorityQueue.size() == 1) {
                Core first = priorityQueue.poll();
                priorityQueue.add(new Core(first.getP() + u / ((double) first.getCount()), first.getCount()));
            }
            while (priorityQueue.size() != 0) {
                Core core = priorityQueue.poll();
                int count = core.getCount();
                while (count-- > 0) {
                    ans *= core.getP();
                }
            }
            System.out.println(String.format("Case #%d: %.6f", i, ans));
        }
    }

    public static class Core implements Comparable<Core> {
        private double p;
        private int count;

        public Core(double p, int count) {
            this.p = p;
            this.count = count;
        }

        @Override
        public int compareTo(Core o) {
            if (p < o.p)
                return -1;
            else if (p > o.p) {
                return 1;
            }
            return 0;
        }

        public double getP() {
            return p;
        }

        public int getCount() {
            return count;
        }
    }
}