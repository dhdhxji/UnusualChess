package com.example.unusualchess.chessModel.board.pieces;

import com.example.unusualchess.chessModel.board.CellIndex;
import com.example.unusualchess.chessModel.board.PieceTestCommonUtils;
import com.example.unusualchess.chessModel.common.Role;

import org.junit.Test;

public class BishopTest {

    /**
     *  Simple move
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   | * |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *  6 | * |   |   |   |   |   | * |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   | * |   |   |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   | * |   | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * |   | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   | * |   |   |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testSimpleMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Bishop(Role.WHITE),
                new CellIndex('d', 3)
        );

        util.allowedMoves.add(new CellIndex('h', 7));

        util.allowedMoves.add(new CellIndex('a', 6));
        util.allowedMoves.add(new CellIndex('g', 6));

        util.allowedMoves.add(new CellIndex('b', 5));
        util.allowedMoves.add(new CellIndex('f', 5));

        util.allowedMoves.add(new CellIndex('c', 4));
        util.allowedMoves.add(new CellIndex('e', 4));

        util.allowedMoves.add(new CellIndex('c', 2));
        util.allowedMoves.add(new CellIndex('e', 2));

        util.allowedMoves.add(new CellIndex('b', 1));
        util.allowedMoves.add(new CellIndex('f', 1));

        util.test();
    }

    /**
     *  Move near to the edge
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   | * |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   | * |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   |   |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   |   |   |   | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   | * |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   | * |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 | P |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testMoveNearToEdge() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Bishop(Role.WHITE),
                new CellIndex('a', 1)
        );

        for(int i = 1; i < util.board.getWidth(); ++i) {
            util.allowedMoves.add(new CellIndex(i, i));
        }

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
     *  5 |   | B |   |   |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   | * |   | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * |   | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   | * |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testBarrierMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Bishop(Role.WHITE),
                new CellIndex('d', 3)
        );

        //set up env
        util.board.set(new CellIndex('g', 6), new Bishop(Role.WHITE));
        util.board.set(new CellIndex('b', 5), new Bishop(Role.WHITE));
        util.board.set(new CellIndex('e', 2), new Bishop(Role.WHITE));

        //set expected moves
        util.allowedMoves.add(new CellIndex('f', 5));

        util.allowedMoves.add(new CellIndex('c', 4));
        util.allowedMoves.add(new CellIndex('e', 4));

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
     *    +---+---+---+---+---+---+---+---+     B - barrier pieces
     *  6 |   |   |   |   |   |   | + |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   | + |   |   |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   | * |   | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * |   | + |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   | * |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testBeatMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Bishop(Role.WHITE),
                new CellIndex('d', 3)
        );

        //set up env
        util.board.set(new CellIndex('g', 6), new Bishop(Role.BLACK));
        util.board.set(new CellIndex('b', 5), new Bishop(Role.BLACK));
        util.board.set(new CellIndex('e', 2), new Bishop(Role.BLACK));

        //set expected moves
        util.allowedMoves.add(new CellIndex('f', 5));
        util.allowedMoves.add(new CellIndex('c', 4));
        util.allowedMoves.add(new CellIndex('e', 4));
        util.allowedMoves.add(new CellIndex('c', 2));
        util.allowedMoves.add(new CellIndex('b', 1));

        //beaten pieces
        util.allowedMoves.add(new CellIndex('g', 6));
        util.allowedMoves.add(new CellIndex('b', 5));
        util.allowedMoves.add(new CellIndex('e', 2));

        util.test();
    }

    /**
     *  Move with environment
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   | * |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+     B - barrier pieces
     *  6 | * |   |   |   |   |   | * |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   | * |   |   |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   | * | B | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   | B | P | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * | B | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   | * |   |   |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testMoveWithEnv() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Bishop(Role.WHITE),
                new CellIndex('d', 3)
        );

        //Set up environment
        util.board.set(new CellIndex('d', 4), new Bishop(Role.WHITE));
        util.board.set(new CellIndex('d', 2), new Bishop(Role.WHITE));
        util.board.set(new CellIndex('c', 3), new Bishop(Role.WHITE));
        util.board.set(new CellIndex('e', 3), new Bishop(Role.WHITE));

        //Set up allowed moves
        util.allowedMoves.add(new CellIndex('h', 7));

        util.allowedMoves.add(new CellIndex('a', 6));
        util.allowedMoves.add(new CellIndex('g', 6));

        util.allowedMoves.add(new CellIndex('b', 5));
        util.allowedMoves.add(new CellIndex('f', 5));

        util.allowedMoves.add(new CellIndex('c', 4));
        util.allowedMoves.add(new CellIndex('e', 4));

        util.allowedMoves.add(new CellIndex('c', 2));
        util.allowedMoves.add(new CellIndex('e', 2));

        util.allowedMoves.add(new CellIndex('b', 1));
        util.allowedMoves.add(new CellIndex('f', 1));

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
     *  4 |   |   | B |   | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | B |   | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testMoveBlocked() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Bishop(Role.WHITE),
                new CellIndex('d', 3)
        );

        //Set up environment
        util.board.set(new CellIndex('c', 4), new Bishop(Role.WHITE));
        util.board.set(new CellIndex('e', 4), new Bishop(Role.WHITE));
        util.board.set(new CellIndex('c', 2), new Bishop(Role.WHITE));
        util.board.set(new CellIndex('e', 2), new Bishop(Role.WHITE));


        util.test();
    }
}