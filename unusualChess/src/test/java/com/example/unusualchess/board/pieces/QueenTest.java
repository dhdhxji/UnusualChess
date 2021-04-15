package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.PieceTestCommonUtils;
import com.example.unusualchess.common.Role;

import org.junit.Test;

public class QueenTest {
    /**
     *  Simple move
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   | * |   |   |   | * |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *  6 | * |   |   | * |   |   | * |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   | * |   | * |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 | * | * | * | P | * | * | * | * |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   | * |   | * |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testSimpleMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Queen(Role.WHITE),
                new CellIndex('d', 3)
        );

        //Horizontal line
        for(int file = 0; file < util.board.getWidth(); ++file) {
            util.allowedMoves.add(new CellIndex((char)('a' + file), 3));
        }

        //Vertical line
        for(int rank = 0; rank < util.board.getWidth(); ++rank) {
            util.allowedMoves.add(new CellIndex('d', rank+1));
        }

        //1st diagonal
        for(int file = 0; file < 6; ++file) {
            util.allowedMoves.add(new CellIndex((char)('a' + file), 6-file));
        }

        //2nd diagonal
        for(int file = 1; file < 8; ++file) {
            util.allowedMoves.add(new CellIndex((char)('a' + file), file));
        }

        util.allowedMoves.remove(util.srcPos);

        util.test();
    }

    /**
     *  Move near to the edge
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 | * |   |   |   |   |   |   | * |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 | * |   |   |   |   |   | * |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *  6 | * |   |   |   |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 | * |   |   |   | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 | * |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 | * |   | * |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 | * | * |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 | P | * | * | * | * | * | * | * |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testMoveNearToEdge() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Queen(Role.WHITE),
                new CellIndex('a', 1)
        );

        //Horizontal line
        for(int file = 0; file < 8; ++file) {
            util.allowedMoves.add(new CellIndex(file, 0));
        }

        //Vertical line
        for(int rank = 0; rank < 8; ++rank) {
            util.allowedMoves.add(new CellIndex(0, rank));
        }

        //Diagonale
        //Horizontal line
        for(int i = 0; i < 8; ++i) {
            util.allowedMoves.add(new CellIndex(i, i));
        }

        util.allowedMoves.remove(new CellIndex(0, 0));

        util.test();
    }

    /**
     *  Move with barrier
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+     B - barrier pieces
     *  6 |   |   |   |   |   |   | B |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   | B |   | B |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   | B | * | P | * | B |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * | B | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   | * |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testBarrierMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Queen(Role.WHITE),
                new CellIndex('d', 3)
        );

        //set up env
        util.board.set(new CellIndex('g', 6), new Queen(Role.WHITE));
        util.board.set(new CellIndex('b', 5), new Queen(Role.WHITE));
        util.board.set(new CellIndex('d', 5), new Queen(Role.WHITE));
        util.board.set(new CellIndex('b', 3), new Queen(Role.WHITE));
        util.board.set(new CellIndex('f', 3), new Queen(Role.WHITE));
        util.board.set(new CellIndex('d', 2), new Queen(Role.WHITE));
        util.board.set(new CellIndex('e', 2), new Queen(Role.WHITE));

        //set expected moves
        util.allowedMoves.add(new CellIndex('f', 5));

        util.allowedMoves.add(new CellIndex('c', 4));
        util.allowedMoves.add(new CellIndex('d', 4));
        util.allowedMoves.add(new CellIndex('e', 4));

        util.allowedMoves.add(new CellIndex('c', 3));
        util.allowedMoves.add(new CellIndex('e', 3));

        util.allowedMoves.add(new CellIndex('c', 2));

        util.allowedMoves.add(new CellIndex('b', 1));

        util.test();
    }

    /**
     *  Move with beat
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+     + - beat moves
     *  6 |   |   |   |   |   |   | + |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   | + |   | + |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 | * | * | * | P | * | * | + |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * | * | + |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   | * |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testBeatMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Queen(Role.WHITE),
                new CellIndex('d', 3)
        );

        //set up env
        util.board.set(new CellIndex('g', 6), new Queen(Role.BLACK));

        util.board.set(new CellIndex('b', 5), new Queen(Role.BLACK));
        util.board.set(new CellIndex('d', 5), new Queen(Role.BLACK));

        util.board.set(new CellIndex('g', 3), new Queen(Role.BLACK));

        util.board.set(new CellIndex('e', 2), new Queen(Role.BLACK));

        //set expected moves
        util.allowedMoves.add(new CellIndex('f', 5));

        util.allowedMoves.add(new CellIndex('c', 4));
        util.allowedMoves.add(new CellIndex('d', 4));
        util.allowedMoves.add(new CellIndex('e', 4));

        util.allowedMoves.add(new CellIndex('a', 3));
        util.allowedMoves.add(new CellIndex('b', 3));
        util.allowedMoves.add(new CellIndex('c', 3));
        util.allowedMoves.add(new CellIndex('e', 3));
        util.allowedMoves.add(new CellIndex('f', 3));

        util.allowedMoves.add(new CellIndex('c', 2));
        util.allowedMoves.add(new CellIndex('d', 2));

        util.allowedMoves.add(new CellIndex('b', 1));
        util.allowedMoves.add(new CellIndex('d', 1));


        //beaten pieces
        util.allowedMoves.add(new CellIndex('g', 6));

        util.allowedMoves.add(new CellIndex('b', 5));
        util.allowedMoves.add(new CellIndex('d', 5));

        util.allowedMoves.add(new CellIndex('g', 3));

        util.allowedMoves.add(new CellIndex('e', 2));

        util.test();
    }

    /**
     *  Move with environment
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   | * |   |   |   | * |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+     B - barrier pieces
     *  6 | * |   |   | * |   |   | * |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   | * | B | * | B | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 | * | * | * | P | * | * | * | * |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   | * | B | * | B | * |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testEnvironmentMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Queen(Role.WHITE),
                new CellIndex('d', 3)
        );

        //Horizontal line
        for(int file = 0; file < util.board.getWidth(); ++file) {
            util.allowedMoves.add(new CellIndex((char)('a' + file), 3));
        }

        //Vertical line
        for(int rank = 0; rank < util.board.getWidth(); ++rank) {
            util.allowedMoves.add(new CellIndex('d', rank+1));
        }

        //1st diagonal
        for(int file = 0; file < 6; ++file) {
            util.allowedMoves.add(new CellIndex((char)('a' + file), 6-file));
        }

        //2nd diagonal
        for(int file = 1; file < 8; ++file) {
            util.allowedMoves.add(new CellIndex((char)('a' + file), file));
        }

        util.allowedMoves.remove(util.srcPos);

        util.board.set(new CellIndex('c', 1), new Queen(Role.WHITE));
        util.board.set(new CellIndex('e', 1), new Queen(Role.WHITE));
        util.board.set(new CellIndex('c', 5), new Queen(Role.WHITE));
        util.board.set(new CellIndex('e', 5), new Queen(Role.WHITE));

        util.test();
    }

    /**
     *  Fully blocked move
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+     B - barrier pieces
     *  6 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   | B | B | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   | B | P | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | B | B | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testMoveBlocked() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Queen(Role.WHITE),
                new CellIndex('d', 3)
        );

        //Set up environment
        util.board.set(new CellIndex('c', 4), new Queen(Role.WHITE));
        util.board.set(new CellIndex('e', 4), new Queen(Role.WHITE));
        util.board.set(new CellIndex('c', 2), new Queen(Role.WHITE));
        util.board.set(new CellIndex('e', 2), new Queen(Role.WHITE));

        util.board.set(new CellIndex('d', 4), new Queen(Role.WHITE));
        util.board.set(new CellIndex('d', 2), new Queen(Role.WHITE));
        util.board.set(new CellIndex('c', 3), new Queen(Role.WHITE));
        util.board.set(new CellIndex('e', 3), new Queen(Role.WHITE));

        util.test();
    }
}