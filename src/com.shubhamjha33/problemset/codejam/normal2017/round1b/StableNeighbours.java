package com.shubhamjha33.problemset.codejam.normal2017.round1b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StableNeighbours {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            List<Integer> input = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt).collect(Collectors.toList());
            int n = input.get(0);

        }
    }

    public static class Vertex{
        char val;

    }
}
