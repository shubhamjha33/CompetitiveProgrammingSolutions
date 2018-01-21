package com.shubhamjha33.problemset.codejam.normal2017.qual;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TidyNumbers {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            char[] arr = br.readLine().toCharArray();
            long[] numberArr = new long[arr.length];
            for(int j=0;j<arr.length;j++){
                numberArr[j]=arr[j]-'0';
            }
            System.out.println(String.format("Case #%d: %d", i, getTidyNumber(numberArr)));
        }
    }

    public static long getTidyNumber(long[] number) {
        if (number.length == 1)
            return number[0];
        long recTidyNumber = getTidyNumber(Arrays.copyOfRange(number, 1, number.length));
        long power = 1;
        for (int i = 0; i < number.length - 1; i++) {
            power *= 10;
        }
        long currentNumber = recTidyNumber + power * number[0];
        if (isValidTidyNumber(currentNumber))
            return currentNumber;
        else {
            long[] newNumber = new long[number.length];
            newNumber[0] = number[0] - 1;
            Arrays.fill(newNumber, 1, newNumber.length, 9);
            long tidyNumber = 0;
            power = 1;
            for (int i = newNumber.length - 1; i >= 0; i--) {
                tidyNumber += newNumber[i] * power;
                power *= 10;
            }
            return tidyNumber;
        }
    }

    public static boolean isValidTidyNumber(long number) {
        long prev = 9;
        while (number != 0) {
            if (number % 10 > prev) {
                return false;
            }
            prev = number % 10;
            number /= 10;
        }
        return true;
    }
}
