package com.shubhamjha33.problemset.codechef.snackdown.round1b17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SnakeSocial {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            Integer[] input = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt).collect(Collectors.toList()).toArray(new Integer[0]);
            int n = input[0];
            int m = input[1];
            int[][] matrix = new int[n][m];
            int i, j;
            int[][] prevMatrix = new int[n][m];
            int maxValue = 0;
            for (i = 0; i < n; i++) {
                List<Integer> arr = Arrays.stream(br.readLine().split(" "))
                        .map(Integer::parseInt).collect(Collectors.toList());
                for (j = 0; j < m; j++) {
                    matrix[i][j] = arr.get(j);
                    prevMatrix[i][j] = arr.get(j);
                    maxValue = Math.max(maxValue, matrix[i][j]);
                }
            }
            int ans = 0;
            do {
                boolean isCompleted = true;
                for (i = 0; i < n; i++) {
                    for (j = 0; j < m; j++) {
                        if (prevMatrix[i][j] != maxValue) {
                            isCompleted = false;
                            break;
                        }
                    }
                    if (j != m)
                        break;
                }
                if (isCompleted)
                    break;
                ans++;
                for (i = 0; i < n; i++) {
                    for (j = 0; j < m; j++) {
                        matrix[i][j] = prevMatrix[i][j];
                        if (i > 0)
                            matrix[i][j] = Math.max(matrix[i][j], prevMatrix[i - 1][j]);
                        if (i < n - 1)
                            matrix[i][j] = Math.max(matrix[i][j], prevMatrix[i + 1][j]);
                        if (j > 0)
                            matrix[i][j] = Math.max(matrix[i][j], prevMatrix[i][j - 1]);
                        if (j < m - 1)
                            matrix[i][j] = Math.max(matrix[i][j], prevMatrix[i][j + 1]);
                        if (i > 0 && j > 0)
                            matrix[i][j] = Math.max(matrix[i][j], prevMatrix[i - 1][j - 1]);
                        if (i < n - 1 && j > 0)
                            matrix[i][j] = Math.max(matrix[i][j], prevMatrix[i + 1][j - 1]);
                        if (i > 0 && j < m - 1)
                            matrix[i][j] = Math.max(matrix[i][j], prevMatrix[i - 1][j + 1]);
                        if (i < n - 1 && j < m - 1)
                            matrix[i][j] = Math.max(matrix[i][j], prevMatrix[i + 1][j + 1]);
                    }
                }
                for (i = 0; i < n; i++) {
                    for (j = 0; j < m; j++) {
                        prevMatrix[i][j] = matrix[i][j];
                    }
                }
            } while (true);
            System.out.println(ans);
        }
    }
}
