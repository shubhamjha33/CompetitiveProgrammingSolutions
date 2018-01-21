package com.shubhamjha33.problemset.codesprint.university2017.accepted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GameOfTwoStacks {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(bufferedReader.readLine());
        int i = 0;
        int j = 0;
        Long n = 0l;
        Long m = 0l;
        long x = 0;
        long sum = 0;
        Long[] list1, list2, data;
        while (g-- > 0) {
            data = Arrays.stream(bufferedReader.readLine().split(" "))
            .map(Long::parseLong).collect(Collectors.toList()).toArray(new Long[0]);
            n = data[0];
            m = data[1];
            x = data[2];
            list1 = getArrayList(bufferedReader);
            list2 = getArrayList(bufferedReader);
            int result = Math.max(binarySearch(list1, x)+1, binarySearch(list2, x)+1);
            for (i = 0; i < n; i++) {
                if (list1[i] <= x) {
                    long val = x - list1[i];
                    int tempResult = i + 1;
                    if (list2[0] <= val) {
                        tempResult = tempResult + binarySearch(list2, val) + 1;
                    }
                    result = Math.max(result, tempResult);
                }
            }
            System.out.println(result);
        }
    }

    public static int binarySearch(Long[] arr, long val) {
        int start = 0;
        int end = arr.length - 1;
        int mid;
        while (start < end) {
            mid = (start + end + 1) / 2;
            if (arr[mid] < val) {
                start = mid + 1;
            } else if (arr[mid] > val) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }
        if (start == end) {
            if(arr[start]<=val)
                return start;
            else
                return start-1;
        }
        else
            return Math.min(start, end);
    }

    private static Long[] getArrayList(BufferedReader bufferedReader) throws IOException {
        Long[] sum = Arrays.stream(bufferedReader.readLine().split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList()).toArray(new Long[0]);
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i] + sum[i - 1];
        }
        return sum;
    }
}
