package com.shubhamjha33.problemset.uva.cp3.gettingstarted.supereasy.solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Shubham on 01/21/2018.
 */
public class SearchingForNessy {

    public static void main(String[] args) throws Exception {
        InputReaders.LongInputReader longInputReader = new InputReaders().newLongInputReader();
        List<List<Long>> input = longInputReader.readInput(" ");
        input.forEach(line -> {
            long n = line.get(0) - 2;
            long m = line.get(1) - 2;
            System.out.println(getSonarCount(n) * getSonarCount(m));
        });
    }

    private static long getSonarCount(long x) {
        long rem = x % 3;
        if (rem > 0)
            rem = 1;
        return x / 3 + rem;
    }

    public static class InputReaders {

        public static LongInputReader newLongInputReader() {
            return new LongInputReader();
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

        public static class LongInputReader extends InputReader {

            @Override
            protected Long convert(String input) {
                return Long.parseLong(input);
            }
        }

    }
}
