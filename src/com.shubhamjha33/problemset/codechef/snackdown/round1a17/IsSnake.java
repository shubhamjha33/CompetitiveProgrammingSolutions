package com.shubhamjha33.problemset.codechef.snackdown.round1a17;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IsSnake {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] matrix = new String[2];
            for (int i = 0; i < 2; i++) {
                matrix[i] = br.readLine();
            }
            if (isSnakePresent(matrix, n))
                System.out.println("yes");
            else
                System.out.println("no");
        }
    }

    private static boolean isSnakePresent(String[] matrix, int n) {
        String prevState = "any";
        boolean isActive = false;
        int blockCount = 0;
        boolean snakeFound = false;
        for (int i = 0; i < n; i++) {
            char up = matrix[0].charAt(i);
            char down = matrix[1].charAt(i);
            if (up == down && up == '.') {
                if (!isActive)
                    continue;
                isActive = false;
                blockCount = 0;
            } else {
                if (snakeFound && !isActive)
                    return false;
                snakeFound = true;
                isActive = true;
                if (up == down && up == '#') {
                    blockCount++;
                } else if (up == '#') {
                    if (!validConf(blockCount, prevState, 0))
                        return false;
                    prevState = "up";
                    blockCount = 0;
                } else {
                    if (!validConf(blockCount, prevState, 1))
                        return false;
                    prevState = "down";
                    blockCount = 0;
                }
            }
        }
        return true;
    }

    private static boolean validConf(int blockCount, String prevState, int row) {
        if (prevState.equals("any")) {
            return true;
        } else if (prevState.equals("up")) {
            return blockCount % 2 == row;
        } else {
            return blockCount % 2 != row;
        }
    }

}
