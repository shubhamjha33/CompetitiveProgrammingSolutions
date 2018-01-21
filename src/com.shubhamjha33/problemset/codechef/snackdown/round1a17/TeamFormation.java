package com.shubhamjha33.problemset.codechef.snackdown.round1a17;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TeamFormation {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] data = br.readLine().split(" ");
            int n = Integer.parseInt(data[0]);
            int m = Integer.parseInt(data[1]);
            if ((n - 2 * m) % 2 == 0)
                System.out.println("yes");
            else
                System.out.println("no");
            while (m-- > 0) {
                br.readLine();
            }
        }
    }
}
