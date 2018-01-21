package com.shubhamjha33.problemset.hackerearth.battleofbots;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Taunt {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        List<List<Piece>> inputMatrix = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            inputMatrix.add(Arrays.stream(bufferedReader.readLine().split(" "))
                    .map(val -> new Piece(val.toCharArray()))
                    .collect(Collectors.toList()));
        }
        int numberOfMoves = Integer.parseInt(bufferedReader.readLine());
        char player = bufferedReader.readLine().charAt(0);
        Board startingBoard = new Board(inputMatrix, player, numberOfMoves);
//        BestMoveGenerator bestMoveGenerator = new RandomBestMoveGenerator(new SimpleNextMovesGenerator());
        BestMoveGenerator bestMoveGenerator = new MCTSBestMoveGenerator(new SimpleNextMovesGenerator(), new SimpleNextBoardGenerator(), new SimpleScoreGenerator());
        Move move = bestMoveGenerator.generateBestMove(startingBoard);
        System.out.println(move.getStartPos());
        System.out.println(move.getEndPos());
    }

    public static class Board {
        private List<List<Piece>> matrix;
        private char player;
        private int noOfMoves;

        public Board(List<List<Piece>> matrix, char player, int noOfMoves) {
            this.matrix = matrix;
            this.player = player;
            this.noOfMoves = noOfMoves;
        }

        public Piece getPieceAtPos(Pair pair) {
            return matrix.get(pair.getX()).get(pair.getY());
        }

        public void setPieceAtPos(Pair pair, Piece piece) {
            matrix.get(pair.getX()).set(pair.getY(), piece);
        }

        public List<List<Piece>> getMatrix() {
            return matrix;
        }

        public char getPlayer() {
            return player;
        }

        public int getNoOfMoves() {
            return noOfMoves;
        }

        public char getNextPlayer() {
            return (char) (2 * '0' + 3 - player);
        }

        public boolean isEmpty(Pair pos) {
            return getPieceAtPos(pos).getPlayer() == '0';
        }

        public boolean isOpponent(Pair pos) {
            return getPieceAtPos(pos).getPlayer() == getNextPlayer();
        }

        public char isOver() {
            int[] count = new int[3];
            for (int i = 0; i < 3; i++) {
                count[i] = 0;
            }
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 4; j++) {
                    Piece piece = matrix.get(i).get(j);
                    int player = piece.getPlayer() - '0';
                    count[player]++;
                }
            }
            if (noOfMoves == 100) {
                if (count[1] > count[2])
                    return '1';
                else if (count[1] < count[2])
                    return '2';
                else
                    return '3';
            } else {
                if (count[1] == 0)
                    return '2';
                else if (count[2] == 0)
                    return '1';
                return '0';
            }
        }
    }

    public static class Piece {
        private char[] arr;

        public Piece(char[] arr) {
            this.arr = arr;
        }

        public char getPlayer() {
            return arr[0];
        }

        public char getPieceType() {
            return arr[1];
        }

        public int getDirection() {
            return arr[2] - '0';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Piece piece = (Piece) o;

            return Arrays.equals(arr, piece.arr);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(arr);
        }
    }

    public static class Move {
        private Pair startPos, endPos;
        private boolean isDirectionChanged = false;

        public Move(Pair startPos, Pair endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }

        public Move(Pair startPos, Pair endPos, boolean isDirectionChanged) {
            this.startPos = startPos;
            this.endPos = endPos;
            this.isDirectionChanged = isDirectionChanged;
        }

        public Pair getStartPos() {
            return startPos;
        }

        public Pair getEndPos() {
            return endPos;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Move move = (Move) o;

            if (isDirectionChanged != move.isDirectionChanged) return false;
            if (!startPos.equals(move.startPos)) return false;
            return endPos.equals(move.endPos);
        }

        @Override
        public int hashCode() {
            int result = startPos.hashCode();
            result = 31 * result + endPos.hashCode();
            result = 31 * result + (isDirectionChanged ? 1 : 0);
            return result;
        }
    }

    public static class Pair {
        private int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (x != pair.x) return false;
            return y == pair.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }

        public boolean isValid() {
            return x >= 0 && x < 10 && y >= 0 && y < 4;
        }
    }

    public interface BestMoveGenerator {
        Move generateBestMove(Board board);
    }

    public static class RandomBestMoveGenerator implements BestMoveGenerator {

        private NextMovesGenerator nextMovesGenerator;

        public RandomBestMoveGenerator(NextMovesGenerator nextMovesGenerator) {
            this.nextMovesGenerator = nextMovesGenerator;
        }

        @Override
        public Move generateBestMove(Board board) {
            List<Move> moves = nextMovesGenerator.generateNextMoves(board);
            return moves.get(generateRandomIndex(moves.size()));
        }

        private int generateRandomIndex(int size) {
            SecureRandom secureRandom = new SecureRandom();
            byte[] arr = new byte[16];
            secureRandom.nextBytes(arr);
            int randomIndex = 0;
            for (int i = 0; i < 16; i++) {
                randomIndex += (((int) (arr[i]) + 256) % 256) % size;
            }
            return randomIndex % size;
        }
    }

    public interface NextBoardGenerator {
        Board generateNextBoard(Board board, Move move);
    }

    public static class SimpleNextBoardGenerator implements NextBoardGenerator {

        @Override
        public Board generateNextBoard(Board board, Move move) {
            List<List<Piece>> newMatrix = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                newMatrix.add(new ArrayList<>());
                for (int j = 0; j < 4; j++) {
                    newMatrix.get(i).add(board.getPieceAtPos(new Pair(i, j)));
                }
            }
            Pair startPos = move.getStartPos();
            Pair endPos = move.getEndPos();
            if (startPos.equals(endPos)) {
                return new Board(newMatrix, board.getNextPlayer(), board.getNoOfMoves() + 1);
            }
            newMatrix.get(startPos.getX()).set(startPos.getY(), board.getPieceAtPos(endPos));
            newMatrix.get(endPos.getX()).set(endPos.getY(), board.getPieceAtPos(startPos));
            return new Board(newMatrix, board.getNextPlayer(), board.getNoOfMoves() + 1);
        }
    }

    public interface NextMovesGenerator {
        List<Move> generateNextMoves(Board board);
    }

    public static class SimpleNextMovesGenerator implements NextMovesGenerator {

        @Override
        public List<Move> generateNextMoves(Board board) {
            List<Move> moves = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 4; j++) {
                    Pair startPos = new Pair(i, j);
                    Piece piece = board.getPieceAtPos(startPos);
                    if (board.isOpponent(startPos) || board.isEmpty(startPos)) {
                        continue;
                    }
                    moves.addAll(getNextPossibleMoves(piece, startPos)
                            .stream()
                            .filter(move -> move.getStartPos().isValid() &&
                                    move.getEndPos().isValid() && (board.isEmpty(move.getEndPos()) || board.isOpponent(move.getEndPos()))).collect(Collectors.toList()));
                }
            }
            return moves;
        }

        public List<Move> getNextPossibleMoves(Piece piece, Pair pos) {
            List<Move> moves = new ArrayList<>();
            int direction = piece.getDirection();
            int xDiff;
            Pair endPos;
            switch (piece.getPieceType()) {
                case '1':
                    endPos = new Pair(pos.getX(), pos.getY() - 1);
                    moves.add(new Move(pos, endPos));
                    endPos = new Pair(pos.getX(), pos.getY() + 1);
                    moves.add(new Move(pos, endPos));
                    endPos = new Pair(pos.getX() - 1, pos.getY());
                    moves.add(new Move(pos, endPos));
                    endPos = new Pair(pos.getX() + 1, pos.getY());
                    moves.add(new Move(pos, endPos));
                    moves = moves.stream().filter(move -> move.getStartPos().isValid() ||
                            move.getEndPos().isValid()).collect(Collectors.toList());
                    break;
                case '2':
                    endPos = new Pair(pos.getX(), pos.getY() - 1);
                    moves.add(new Move(pos, endPos));
                    endPos = new Pair(pos.getX(), pos.getY() + 1);
                    moves.add(new Move(pos, endPos));
                    if (direction == 0) {
                        xDiff = -2;
                    } else
                        xDiff = 2;
                    endPos = new Pair(pos.getX() + xDiff, pos.getY());
                    if (endPos.isValid())
                        moves.add(new Move(pos, endPos));
                    else
                        moves.add(new Move(pos, pos, true));
                    moves = moves.stream().filter(move -> move.getStartPos().isValid() ||
                            move.getEndPos().isValid()).collect(Collectors.toList());
                    break;
                case '3':
                    if (direction == 0) {
                        xDiff = -2;
                    } else
                        xDiff = 2;
                    if (pos.getY() != 0) {
                        endPos = new Pair(pos.getX() + xDiff, pos.getY() - 2);
                        if (endPos.isValid())
                            moves.add(new Move(pos, endPos));
                        else {
                            endPos = new Pair(pos.getX() + xDiff, pos.getY());
                            if (endPos.isValid())
                                moves.add(new Move(pos, endPos));
                            else {
                                endPos = new Pair(pos.getX(), pos.getY() - 2);
                                if (endPos.isValid())
                                    moves.add(new Move(pos, endPos, true));
                                else
                                    moves.add(new Move(pos, pos, true));
                            }
                        }
                    }
                    if (pos.getY() != 3) {
                        endPos = new Pair(pos.getX() + xDiff, pos.getY() + 2);
                        if (endPos.isValid())
                            moves.add(new Move(pos, endPos));
                        else {
                            endPos = new Pair(pos.getX() + xDiff, pos.getY());
                            if (endPos.isValid())
                                moves.add(new Move(pos, endPos));
                            else {
                                endPos = new Pair(pos.getX(), pos.getY() + 2);
                                if (endPos.isValid())
                                    moves.add(new Move(pos, endPos, true));
                                else
                                    moves.add(new Move(pos, pos, true));
                            }
                        }
                    }
                    break;
            }
            return moves;
        }
    }

    static class Score {
        private int playerOneCount;
        private int playerTwoCount;

        public Score(int playerOneCount, int playerTwoCount) {
            this.playerOneCount = playerOneCount;
            this.playerTwoCount = playerTwoCount;
        }

        public int getPlayerOneCount() {
            return playerOneCount;
        }

        public int getPlayerTwoCount() {
            return playerTwoCount;
        }

        public void increasePlayerOneCount() {
            playerOneCount++;
        }

        public void increasePlayerTwoCount() {
            playerTwoCount++;
        }
    }

    interface ScoreGenerator {
        Score getScore(Board board);
    }

    static class SimpleScoreGenerator implements ScoreGenerator {

        @Override
        public Score getScore(Board board) {
            int playerOneCount = 0;
            int playerTwoCount = 0;
            List<List<Piece>> pieces = board.getMatrix();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 4; j++) {
                    char player = pieces.get(i).get(j).getPlayer();
                    if (player == '1') {
                        playerOneCount++;
                    } else if (player == '2') {
                        playerTwoCount++;
                    }
                }
            }
            return new Score(playerOneCount, playerTwoCount);
        }
    }

    static class State {
        private Board board;
        private Move move;
        private Score score;

        public State(Board board, Move move, Score score) {
            this.board = board;
            this.score = score;
            this.move = move;
        }

        public Board getBoard() {
            return board;
        }

        public Score getScore() {
            return score;
        }

        public Move getMove() {
            return move;
        }
    }

    static class StateComparator implements Comparator<State> {

        private char player;

        public StateComparator(char player) {
            this.player = player;
        }

        @Override
        public int compare(State o1, State o2) {
            if (player == '1') {
                return -(o1.getScore().getPlayerOneCount() - o2.getScore().getPlayerOneCount());
            } else {
                return -(o1.getScore().getPlayerTwoCount() - o2.getScore().getPlayerTwoCount());
            }
        }
    }

    static class MCTSBestMoveGenerator implements BestMoveGenerator {

        private NextMovesGenerator nextMovesGenerator;
        private NextBoardGenerator nextBoardGenerator;
        private ScoreGenerator scoreGenerator;
        private RandomBestMoveGenerator randomBestMoveGenerator;
        private static final long WINDOW_TIME = 800;

        public MCTSBestMoveGenerator(NextMovesGenerator nextMovesGenerator, NextBoardGenerator nextBoardGenerator,
                                     ScoreGenerator scoreGenerator) {
            this.nextMovesGenerator = nextMovesGenerator;
            this.nextBoardGenerator = nextBoardGenerator;
            this.scoreGenerator = scoreGenerator;
            this.randomBestMoveGenerator = new RandomBestMoveGenerator(nextMovesGenerator);
        }

        @Override
        public Move generateBestMove(Board board) {
            long startTime = Instant.now().toEpochMilli();
            List<Move> moves = nextMovesGenerator.generateNextMoves(board);
            List<State> nextStates = moves.stream().map(move -> new State(nextBoardGenerator.generateNextBoard(board, move), move, new Score(0, 0)))
                    .collect(Collectors.toList());
            PriorityQueue<State> priorityQueue = new PriorityQueue<>(nextStates.size(), new StateComparator(board.getPlayer()));
            for (int i = 0; i < nextStates.size() && Instant.now().toEpochMilli() - startTime <= WINDOW_TIME; i++) {
                for (int j = 0; j < 10; j++) {
                    performPlayout(nextStates.get(i));
                }
            }
            priorityQueue.addAll(nextStates);
            while (Instant.now().toEpochMilli() - startTime <= WINDOW_TIME) {
                // selection
                State state = priorityQueue.poll();
                //start playout
                performPlayout(state);
                //reenter in pq
                priorityQueue.add(state);
            }
            return priorityQueue.poll().getMove();
        }

        public void performPlayout(State state) {
            Board board = state.getBoard();
            Board statingBoard = new Board(board.getMatrix(), board.getPlayer(), board.getNoOfMoves());
            int counter = 0;
            do {
                char isOver = statingBoard.isOver();
                counter++;
                switch (isOver) {
                    case '1':
                        state.getScore().increasePlayerOneCount();
                        break;
                    case '2':
                        state.getScore().increasePlayerTwoCount();
                        break;
                    case '3':
                        state.getScore().increasePlayerOneCount();
                        state.getScore().increasePlayerTwoCount();
                        break;
                    default:
                        statingBoard = nextBoardGenerator.generateNextBoard(statingBoard, randomBestMoveGenerator.generateBestMove(statingBoard));
                }
                if (isOver != '0')
                    break;
            } while (true);
        }

    }
}