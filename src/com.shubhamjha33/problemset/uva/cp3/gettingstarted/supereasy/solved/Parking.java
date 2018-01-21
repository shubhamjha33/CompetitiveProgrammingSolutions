package com.shubhamjha33.problemset.uva.cp3.gettingstarted.supereasy.solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Parking {

    public static void main(String[] args) throws Exception {
        InputReaderFactory.InputReader<Integer> inputReader = new ParkingInputReader();
        List<List<Integer>> inputData = inputReader.readInput(" ");
        inputData.forEach(Parking::processTestCase);
    }

    private static void processTestCase(List<Integer> testCase) {
        System.out.println(computeTotalDistanceTravelled(testCase));
    }

    private static int computeTotalDistanceTravelled(List<Integer> shops) {
        Collections.sort(shops);
        return (shops.get(shops.size() - 1) - shops.get(0)) * 2;
    }

    public static class ParkingInputReader extends InputReaderFactory.IntegerInputReader {
        @Override
        public List<List<Integer>> readInput(String delimiter) throws Exception {
            int t = Integer.parseInt(br.readLine());
            List<List<Integer>> inputSet = new ArrayList<>();
            while (t-- > 0) {
                int n = convert(br.readLine());
                inputSet.add(Arrays.stream(br.readLine().split(delimiter))
                        .map(this::convert)
                        .collect(Collectors.toList()));
            }
            return inputSet;
        }
    }

    public static class InputReaderFactory {

        public static InputReaderFactory.LongInputReader newLongInputReader() {
            return new InputReaderFactory.LongInputReader();
        }

        public static InputReaderFactory.IntegerInputReader newIntegerInputReader() {
            return new InputReaderFactory.IntegerInputReader();
        }

        public static abstract class InputReader<T> {

            protected final BufferedReader br;

            public InputReader() {
                br = new BufferedReader(new InputStreamReader(System.in));
            }

            public List<List<T>> readInput(String delimiter) throws Exception {
                int t = Integer.parseInt(br.readLine());
                List<List<T>> dataset = new ArrayList<>();
                while (t-- > 0) {
                    dataset.add(Arrays.stream(br.readLine().split(delimiter))
                            .map(this::convert)
                            .collect(Collectors.toList()));
                }
                return dataset;
            }

            protected abstract T convert(String input);
        }

        public static class LongInputReader extends InputReader<Long> {

            @Override
            protected Long convert(String input) {
                return Long.parseLong(input);
            }
        }

        public static class IntegerInputReader extends InputReader<Integer> {

            @Override
            protected Integer convert(String input) {
                return Integer.parseInt(input);
            }
        }

    }

}
