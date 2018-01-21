package com.shubhamjha33.problemset.topcoder.tco17.round1a.solved;

import java.util.ArrayDeque;
import java.util.Queue;

public class PingPongQueue {

    public static int[] whoPlaysNext(int[] skills, int n, int k) {
        ArrayDeque<Item> q = new ArrayDeque<Item>();
        int l, w, winCount = 0;
        for (int i = 0; i < skills.length; i++) {
            q.addLast(new Item(i, skills[i], 0));
        }
        l = 0;
        w = 0;
        for (int i = 0; i < k; i++) {
            Item player1 = q.poll();
            Item player2 = q.poll();
            Item winner, loser;
            if (player1.getVal() > player2.getVal()) {
                winner = player1;
                loser = player2;
            } else {
                winner = player2;
                loser = player1;
            }
            l = loser.getVal();
            w = winner.getVal();
            winner.setCount(winner.getCount() + 1);
            loser.setCount(0);
            q.addLast(loser);
            if (winner.getCount() == n) {
                winner.setCount(0);
                q.addLast(winner);
            } else {
                q.addFirst(winner);
            }
        }
        return new int[]{l, w};
    }

    public static class Item {
        int index;
        int val;
        int count;

        public Item(int index, int val, int count) {
            this.index = index;
            this.val = val;
            this.count = count;
        }

        public int getIndex() {
            return index;
        }

        public int getVal() {
            return val;
        }

        public int getCount() {
            return count;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
