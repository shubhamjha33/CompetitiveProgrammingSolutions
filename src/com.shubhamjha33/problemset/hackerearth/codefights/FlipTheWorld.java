package com.shubhamjha33.problemset.hackerearth.codefights;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FlipTheWorld {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String[] data;
        int[][] matrix;
        while(t-->0){
            data = br.readLine().split(" ");
            int n = Integer.parseInt(data[0]);
            int m = Integer.parseInt(data[1]);
            matrix = new int[n][m];
            for(int i=0;i<n;i++){
                String row = br.readLine();
                for(int j=0;j<m;j++) {
                    matrix[i][j] = row.charAt(j)-'0';
                }
            }
            int res = 0;
            for(int i=n-1;i>=0;i--){
                for (int j=m-1;j>=0;j--){
                    if(matrix[i][j]==1)
                        continue;
                    res++;
                    for(int p=0;p<=i;p++){
                        for (int q=0;q<=j;q++){
                            matrix[p][q]=1-matrix[p][q];
                        }
                    }
                }
            }
            System.out.println(res);
        }
    }
}
