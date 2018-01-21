package com.shubhamjha33.problemset.hackerearth.battleofbots;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TauntTests {

    @Test
    public void testPiece1CorrectMovement() throws Exception {
        Taunt.SimpleNextMovesGenerator simpleNextMovesGenerator = new Taunt.SimpleNextMovesGenerator();
        Taunt.Pair startPos = new Taunt.Pair(1, 1);
        String piece = "111";
        List<Taunt.Move> nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(2, 1)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(1, 2)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(0, 1)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(1, 0)));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
        piece = "110";
        nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(2, 1)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(1, 2)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(0, 1)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(1, 0)));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
    }

    @Test
    public void testPiece2CorrectMovement() throws Exception {
        Taunt.SimpleNextMovesGenerator simpleNextMovesGenerator = new Taunt.SimpleNextMovesGenerator();
        Taunt.Pair startPos = new Taunt.Pair(8, 1);
        String piece = "121";
        List<Taunt.Move> nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(8, 0)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(8, 2)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(8, 1), true));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
        piece = "120";
        nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(8, 0)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(8, 2)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(6, 1)));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
        piece = "121";
        startPos = new Taunt.Pair(1, 1);
        nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(1, 0)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(1, 2)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(3, 1)));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
        piece = "120";
        nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(1, 0)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(1, 2)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(1, 1), true));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
    }

    @Test
    public void testPiece3CorrectMovement() throws Exception {
        Taunt.SimpleNextMovesGenerator simpleNextMovesGenerator = new Taunt.SimpleNextMovesGenerator();
        Taunt.Pair startPos = new Taunt.Pair(1, 1);
        String piece = "131";
        List<Taunt.Move> nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(3, 1)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(3, 3)));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
        startPos = new Taunt.Pair(1, 2);
        nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(3, 0)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(3, 2)));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
        startPos = new Taunt.Pair(1, 0);
        nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(3, 2)));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
        startPos = new Taunt.Pair(1, 3);
        nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(3, 1)));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
        startPos = new Taunt.Pair(8, 1);
        nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(8, 1), true));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(8, 3), true));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
        startPos = new Taunt.Pair(8, 2);
        nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(8, 0), true));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(8, 2), true));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
        piece = "230";
        startPos = new Taunt.Pair(8, 1);
        nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(6, 1)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(6, 3)));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
        startPos = new Taunt.Pair(8, 2);
        nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(6, 0)));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(6, 2)));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
        startPos = new Taunt.Pair(1, 1);
        nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(1, 1), true));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(1, 3), true));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
        startPos = new Taunt.Pair(1, 2);
        nextMovesList = new ArrayList<>();
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(1, 0), true));
        nextMovesList.add(new Taunt.Move(startPos, new Taunt.Pair(1, 2), true));
        checkCorrectNextMovesGen(simpleNextMovesGenerator, startPos, piece, nextMovesList);
    }

    private void checkCorrectNextMovesGen(Taunt.SimpleNextMovesGenerator simpleNextMovesGenerator, Taunt.Pair startPos, String piece, List<Taunt.Move> nextMovesList) {
        List<Taunt.Move> moves = simpleNextMovesGenerator.getNextPossibleMoves(new Taunt.Piece(piece.toCharArray()), startPos);
        assertTrue(nextMovesList.stream()
                .map(nextMove -> moves.stream().map(move -> move.equals(nextMove))
                        .filter(val -> val)
                        .findFirst().orElse(false))
                .filter(val -> !val)
                .findFirst().orElse(true));
    }
}
