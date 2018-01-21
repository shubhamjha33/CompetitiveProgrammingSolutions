package com.shubhamjha33.problemset.uva.cp3.gettingstarted.supereasy.solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Shubham on 01/21/2018.
 */
public class RelationalOperator {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String line = br.readLine();
            String[] data = line.split(" ");
            long left = Long.parseLong(data[0]);
            long right = Long.parseLong(data[1]);
            if (left < right)
                System.out.println("<");
            else if (left == right)
                System.out.println("=");
            else
                System.out.println(">");
        }
    }
}
