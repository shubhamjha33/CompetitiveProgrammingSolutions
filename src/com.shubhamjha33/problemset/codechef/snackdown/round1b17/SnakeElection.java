package com.shubhamjha33.problemset.codechef.snackdown.round1b17;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SnakeElection {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            char[] arr = br.readLine().toCharArray();
            int i;
            int snake = 0;
            int mongoose = 0;
            if (arr[0] == 's')
                snake++;
            else if (arr[0] == 'm') {
                if (arr.length > 1 && arr[1] == 's') {
                    arr[1] = '.';
                }
                mongoose++;
            }
            for (i = 1; i < arr.length; i++) {
                if (arr[i] == 's')
                    snake++;
                else if (arr[i] == 'm') {
                    if (arr[i - 1] == 's') {
                        arr[i - 1] = '.';
                        snake--;
                    } else if (i + 1 < arr.length && arr[i + 1] == 's') {
                        arr[i + 1] = '.';
                    }
                    mongoose++;
                }
            }
            if (snake == mongoose) {
                System.out.println("tie");
            } else if (snake > mongoose) {
                System.out.println("snakes");
            } else {
                System.out.println("mongooses");
            }
        }
    }
}
