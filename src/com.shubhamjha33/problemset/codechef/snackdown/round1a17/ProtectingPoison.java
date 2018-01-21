package com.shubhamjha33.problemset.codechef.snackdown.round1a17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ProtectingPoison {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            List<Long> data = getInputData(br);
            long n = data.get(0);
            long k = data.get(1);
            long m = data.get(2);
            int i;
            List<Snake> horList = new ArrayList<>();
            List<Snake> verList = new ArrayList<>();
            boolean[][] isUseful = new boolean[(int) m][2];
            for (i = 0; i < m; i++) {
                data = getInputData(br);
                isUseful[i][0] = isUseful[i][1] = true;
                Pair p1 = new Pair(data.get(0), data.get(1));
                Pair p2 = new Pair(data.get(2), data.get(3));
                Pair head, tail;
                if (p1.compareTo(p2) < 0) {
                    head = p1;
                    tail = p2;
                } else {
                    head = p2;
                    tail = p1;
                }
                horList.add(new Snake(head, tail, i));
                verList.add(new Snake(head, tail, i));
            }
            Collections.sort(horList, new HorComparator());
            Collections.sort(verList, new VerComparator());
            Snake prevSnake = horList.get(0);
            Snake currentSnake;
            for (i = 1; i < m; i++) {
                currentSnake = horList.get(i);
                if (prevSnake.getHead().getY() == currentSnake.getHead().getY()) {
                    isUseful[currentSnake.getIndex()][0] = false;
                } else if (prevSnake.getTail().getY() >= currentSnake.getTail().getY()) {
                    isUseful[currentSnake.getIndex()][0] = false;
                } else {
                    prevSnake = currentSnake;
                }
            }
            final long START_POISON = (n - k) / 2 + 1;
            final long END_POISON = START_POISON + k - 1;
            long currentPos = START_POISON;
            boolean isValid = true;
            for (i = 0; i < m; i++) {
                currentSnake = horList.get(i);
                if (currentPos > END_POISON) {
                    isUseful[currentSnake.getIndex()][0] = false;
                } else if (isUseful[currentSnake.getIndex()][0]) {
                    if (currentSnake.getHead().getY() > currentPos) {
                        isValid = false;
                        break;
                    } else if (currentSnake.getHead().getY() + currentSnake.getHorizontalLength() <= currentPos) {
                        isUseful[currentSnake.getIndex()][0] = false;
                    } else {
                        currentPos = currentSnake.getHead().getY() + currentSnake.getHorizontalLength();
                    }
                }
            }
            if (currentPos <= END_POISON)
                isValid = false;
            prevSnake = verList.get(0);
            for (i = 1; i < m; i++) {
                currentSnake = verList.get(i);
                if (prevSnake.getHead().getX() == currentSnake.getHead().getX()) {
                    isUseful[currentSnake.getIndex()][1] = false;
                } else if (prevSnake.getTail().getX() >= currentSnake.getTail().getX()) {
                    isUseful[currentSnake.getIndex()][1] = false;
                } else {
                    prevSnake = currentSnake;
                }
            }
            currentPos = START_POISON;
            for (i = 0; i < m; i++) {
                currentSnake = verList.get(i);
                if (currentPos > END_POISON) {
                    isUseful[currentSnake.getIndex()][1] = false;
                } else if (isUseful[currentSnake.getIndex()][1]) {
                    if (currentSnake.getHead().getX() > currentPos) {
                        isValid = false;
                        break;
                    } else if (currentSnake.getHead().getX() + currentSnake.getVerticalLength() <= currentPos) {
                        isUseful[currentSnake.getIndex()][1] = false;
                    } else {
                        currentPos = currentSnake.getHead().getX() + currentSnake.getVerticalLength();
                    }
                }
            }
            if (currentPos <= END_POISON)
                isValid = false;
            if (!isValid)
                System.out.println("-1");
            else {
                long ans = 0;
                for (i = 0; i < m; i++) {
                    if (isUseful[i][0] || isUseful[i][1])
                        ans++;
                }
                System.out.println(ans);
            }
        }
    }

    private static List<Long> getInputData(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .map(Long::parseLong).collect(Collectors.toList());
    }

    private static class Pair implements Comparable<Pair> {
        long x, y;

        public Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long getX() {
            return x;
        }

        public long getY() {
            return y;
        }

        @Override
        public int compareTo(Pair o) {
            if (x < o.x)
                return -1;
            else if (x == o.x) {
                if (y < o.y)
                    return -1;
                else if (y == o.y)
                    return 0;
                else
                    return 1;
            }
            return 1;
        }
    }

    private static class Snake {
        private Pair head, tail;
        private int index;

        public Snake(Pair head, Pair tail, int index) {
            this.head = head;
            this.tail = tail;
            this.index = index;
        }

        public Pair getHead() {
            return head;
        }

        public Pair getTail() {
            return tail;
        }

        public int getIndex() {
            return index;
        }

        public long getVerticalLength() {
            return tail.getX() - head.getX() + 1;
        }

        public long getHorizontalLength() {
            return tail.getY() - head.getY() + 1;
        }
    }

    private static class HorComparator implements Comparator<Snake> {

        @Override
        public int compare(Snake o1, Snake o2) {
            Pair o1Head = o1.getHead();
            Pair o2Head = o2.getHead();
            if (o1Head.getY() < o2Head.getY()) {
                return -1;
            } else if (o1Head.getY() == o2Head.getY()) {
                long o1Length = o1.getHorizontalLength();
                long o2Length = o2.getHorizontalLength();
                if (o1Length > o2Length)
                    return -1;
                else if (o1Length == o2Length)
                    return 0;
                else
                    return 1;
            } else
                return 1;
        }
    }

    private static class VerComparator implements Comparator<Snake> {

        @Override
        public int compare(Snake o1, Snake o2) {
            Pair o1Head = o1.getHead();
            Pair o2Head = o2.getHead();
            if (o1Head.getX() < o2Head.getX()) {
                return -1;
            } else if (o1Head.getX() == o2Head.getX()) {
                long o1Length = o1.getVerticalLength();
                long o2Length = o2.getVerticalLength();
                if (o1Length > o2Length)
                    return -1;
                else if (o1Length == o2Length)
                    return 0;
                else
                    return 1;
            } else
                return 1;
        }
    }
}
