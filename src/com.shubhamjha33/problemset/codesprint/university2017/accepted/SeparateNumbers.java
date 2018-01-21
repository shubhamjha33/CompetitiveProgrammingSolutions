package com.shubhamjha33.problemset.codesprint.university2017.accepted;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class SeparateNumbers {

    private static BigInteger one = new BigInteger("1");

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            String str = br.readLine();
            String outputStr = isBeautiful(str);
            if (outputStr.length() >= 1) {
                System.out.println("YES " + outputStr);
            } else
                System.out.println("NO");
        }
    }

    public static String isBeautiful(String str) {
        if (str.charAt(0) == '0') {
            return "";
        }
        for (int i = 0; i < str.length(); i++) {
            BigInteger start = new BigInteger(str.substring(0, i + 1));
            int j = i + 1;
            int count = 0;
            while (j != str.length()) {
                start = start.add(one);
                String next = start.toString();
                if (j + next.length() <= str.length() && next.equals(str.substring(j, j + next.length()))) {
                    count++;
                    j = j + next.length();
                } else
                    break;
            }
            if (count != 0 && j == str.length())
                return str.substring(0, i + 1);
        }
        return "";
    }

}
