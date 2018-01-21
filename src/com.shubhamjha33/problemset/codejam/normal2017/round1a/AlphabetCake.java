package com.shubhamjha33.problemset.codejam.normal2017.round1a;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AlphabetCake {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            int r, c;
            String[] data = br.readLine().split(" ");
            r = Integer.parseInt(data[0]);
            c = Integer.parseInt(data[1]);
            char[][] matrix = new char[r][c];
            for (int j = 0; j < r; j++) {
                matrix[j] = br.readLine().toCharArray();
            }
            matrix = convertUnknownToKnown(matrix, r, c);
            System.out.println(String.format("Case #%d: ", i));
            for (int j = 0; j < r; j++) {
                System.out.println(new String(matrix[j]));
            }
        }
    }

    public static char[][] convertUnknownToKnown(char[][] input, int r, int c) {
        char[][] output = new char[r][c];
        boolean[] charSet = new boolean[26];
        int i, j, k, l;
        Grid[] grids = new Grid[26];
        for (i = 0; i < 26; i++) {
            charSet[i] = false;
            grids[i] = new Grid();
        }
        for (i = 0; i < r; i++) {
            for (j = 0; j < c; j++) {
                if (input[i][j] != '?') {
                    grids[input[i][j] - 'A'].setBoundary(i, j);
                }
            }
        }
        for (i = 0; i < r; i++) {
            for (j = 0; j < c; j++) {
                if (input[i][j] != '?' && !charSet[input[i][j]-'A']) {
                    int[][] coord = grids[input[i][j] - 'A'].getCoord();
                    for (k = coord[0][0] - 1; k >= 0; k--) {
                        for (l = coord[0][1]; l <= coord[1][1]; l++) {
                            if (input[k][l] != '?') {
                                break;
                            }
                        }
                        if (l != coord[1][1] + 1) {
                            break;
                        } else {
                            coord[0][0] = k;
                        }
                    }
                    for (k = coord[0][0] + 1; k < r; k++) {
                        for (l = coord[0][1]; l <= coord[1][1]; l++) {
                            if (input[k][l] != '?') {
                                break;
                            }
                        }
                        if (l != coord[1][1] + 1) {
                            break;
                        } else {
                            coord[1][0] = k;
                        }
                    }
                    for (k = coord[0][1] - 1; k >= 0; k--) {
                        for (l = coord[0][0]; l <= coord[1][0]; l++) {
                            if (input[l][k] != '?') {
                                break;
                            }
                        }
                        if (l != coord[1][0] + 1) {
                            break;
                        } else {
                            coord[0][1] = k;
                        }
                    }
                    for (k = coord[0][1] + 1; k < c; k++) {
                        for (l = coord[0][0]; l <= coord[1][0]; l++) {
                            if (input[l][k] != '?') {
                                break;
                            }
                        }
                        if (l != coord[1][0] + 1) {
                            break;
                        } else {
                            coord[1][1] = k;
                        }
                    }
                    charSet[input[i][j] - 'A'] = true;
                }
            }
        }

        return output;
    }

    static class Grid {
        int[][] coord;

        public Grid() {
            this.coord = new int[2][2];
            coord[0][0] = 100;
            coord[0][1] = 100;
            coord[1][0] = -1;
            coord[1][1] = -1;
        }

        public void setBoundary(int x, int y) {
            coord[0][0] = Math.min(x, coord[0][0]);
            coord[0][1] = Math.min(y, coord[0][1]);
            coord[1][0] = Math.max(x, coord[1][0]);
            coord[1][1] = Math.max(y, coord[1][1]);
        }

        public int[][] getCoord() {
            return coord;
        }
    }
}
