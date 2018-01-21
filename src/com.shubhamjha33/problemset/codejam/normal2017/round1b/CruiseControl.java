package com.shubhamjha33.problemset.codejam.normal2017.round1b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class CruiseControl {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        List<Long> inputLine;
        List<Double> doubleInputLine;
        for (int i = 1; i <= t; i++) {
            inputLine = getData(br);
            double d = inputLine.get(0);
            long n = inputLine.get(1);
            PriorityQueue<Horse> priorityQueue = new PriorityQueue<>();
            for (long j = 0; j < n; j++) {
                doubleInputLine = getDoubleData(br);
                priorityQueue.add(new Horse(doubleInputLine.get(0), doubleInputLine.get(1)));
            }
            double currentTime = 0.0;
            while (!priorityQueue.isEmpty()) {
                if (priorityQueue.size() == 1) {
                    Horse h = priorityQueue.poll();
                    currentTime = currentTime + (d - h.getK()) / h.getS();
                } else {
                    Horse one = priorityQueue.poll();
                    Horse two = priorityQueue.poll();
                    if (one.getS() <= two.getS()) {
                        currentTime += (d - one.getK()) / one.getS();
                        break;
                    } else if (willHorseOneCatchUp(one, two, d)) {
                        double timeTaken = (two.getK() - one.getK()) / (one.getS() - two.getS());
                        currentTime += timeTaken;
                        priorityQueue.add(new Horse(two.getK() + two.getS() * timeTaken, two.getS()));
                    } else {
                        currentTime = Math.max(currentTime, (d - one.getK()) / one.getS());
                        break;
                    }
                }
            }
            System.out.println(String.format("Case #%d: %.6f", i, d / currentTime));
        }
    }

    private static boolean willHorseOneCatchUp(Horse one, Horse two, double d) {
        double time1 = (d - one.getK()) / one.getS();
        double time2 = (d - two.getK()) / two.getS();
        if (time1 < time2)
            return true;
        else
            return false;
    }

    public static class Horse implements Comparable<Horse> {
        private double k, s;

        public Horse(double k, double s) {
            this.k = k;
            this.s = s;
        }

        @Override
        public int compareTo(Horse o) {
            if (k < o.k)
                return -1;
            else if (k == o.k)
                return 0;
            else
                return 1;
        }

        public double getK() {
            return k;
        }

        public double getS() {
            return s;
        }
    }

    private static List<Long> getData(BufferedReader br) throws Exception {
        String[] arr = (br.readLine()).split(" ");
        return Arrays.stream(arr).map(Long::parseLong).collect(Collectors.toList());
    }

    private static List<Double> getDoubleData(BufferedReader br) throws Exception {
        String[] arr = (br.readLine()).split(" ");
        return Arrays.stream(arr).map(Double::parseDouble).collect(Collectors.toList());
    }
}
