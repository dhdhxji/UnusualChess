package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.PieceTestCommonUtils;
import com.example.unusualchess.common.Role;

import org.junit.Test;

public class KnightTest {

    /**
     *  Simple move
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   |   | * |   | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   | * |   |   |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   | * |   |   |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   | * |   | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testSimpleMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Knight(Role.WHITE),
                new CellIndex('d', 3)
        );

        util.allowedMoves.add(new CellIndex('c', 5));
        util.allowedMoves.add(new CellIndex('e', 5));
        util.allowedMoves.add(new CellIndex('b', 4));
        util.allowedMoves.add(new CellIndex('f', 4));
        util.allowedMoves.add(new CellIndex('b', 2));
        util.allowedMoves.add(new CellIndex('f', 2));
        util.allowedMoves.add(new CellIndex('c', 1));
        util.allowedMoves.add(new CellIndex('e', 1));

        util.test();
    }

    /**
     *  Out of bounds move
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 | * |   | * |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   | P |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 | * |   | * |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testOutOfBoundsMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Knight(Role.WHITE),
                new CellIndex('b', 3)
        );

        util.allowedMoves.add(new CellIndex('a', 5));
        util.allowedMoves.add(new CellIndex('c', 5));
        util.allowedMoves.add(new CellIndex('d', 4));
        util.allowedMoves.add(new CellIndex('d', 2));
        util.allowedMoves.add(new CellIndex('a', 1));
        util.allowedMoves.add(new CellIndex('c', 1));

        util.test();
    }

    /**
     *  Move with interfering environment
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+     B - barrier pieces
     *  6 |   | B | B | B | B | B |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 | B | B | * | B | * | B | B |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 | B | * | B | B | B | * | B |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 | B | B | B | P | B | B | B |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 | B | * | B | B | B | * | B |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 | B | B | * | B | * | B | B |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testMoveWithEnvironment() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Knight(Role.WHITE),
                new CellIndex('d', 3)
        );

        util.allowedMoves.add(new CellIndex('c', 5));
        util.allowedMoves.add(new CellIndex('e', 5));
        util.allowedMoves.add(new CellIndex('b', 4));
        util.allowedMoves.add(new CellIndex('f', 4));
        util.allowedMoves.add(new CellIndex('b', 2));
        util.allowedMoves.add(new CellIndex('f', 2));
        util.allowedMoves.add(new CellIndex('c', 1));
        util.allowedMoves.add(new CellIndex('e', 1));


        //set barrier pieces
        util.board.set(new CellIndex('b', 6), new Knight(Role.WHITE));
        util.board.set(new CellIndex('c', 6), new Knight(Role.WHITE));
        util.board.set(new CellIndex('d', 6), new Knight(Role.WHITE));
        util.board.set(new CellIndex('e', 6), new Knight(Role.WHITE));
        util.board.set(new CellIndex('f', 6), new Knight(Role.WHITE));

        util.board.set(new CellIndex('a', 5), new Knight(Role.WHITE));
        util.board.set(new CellIndex('b', 5), new Knight(Role.WHITE));
        util.board.set(new CellIndex('d', 5), new Knight(Role.WHITE));
        util.board.set(new CellIndex('f', 5), new Knight(Role.WHITE));
        util.board.set(new CellIndex('g', 5), new Knight(Role.WHITE));

        util.board.set(new CellIndex('a', 4), new Knight(Role.WHITE));
        util.board.set(new CellIndex('c', 4), new Knight(Role.WHITE));
        util.board.set(new CellIndex('d', 4), new Knight(Role.WHITE));
        util.board.set(new CellIndex('e', 4), new Knight(Role.WHITE));
        util.board.set(new CellIndex('g', 4), new Knight(Role.WHITE));

        util.board.set(new CellIndex('a', 3), new Knight(Role.WHITE));
        util.board.set(new CellIndex('b', 3), new Knight(Role.WHITE));
        util.board.set(new CellIndex('c', 3), new Knight(Role.WHITE));
        util.board.set(new CellIndex('e', 3), new Knight(Role.WHITE));
        util.board.set(new CellIndex('f', 3), new Knight(Role.WHITE));
        util.board.set(new CellIndex('g', 3), new Knight(Role.WHITE));

        util.board.set(new CellIndex('a', 2), new Knight(Role.WHITE));
        util.board.set(new CellIndex('c', 2), new Knight(Role.WHITE));
        util.board.set(new CellIndex('d', 2), new Knight(Role.WHITE));
        util.board.set(new CellIndex('e', 2), new Knight(Role.WHITE));
        util.board.set(new CellIndex('g', 2), new Knight(Role.WHITE));

        util.board.set(new CellIndex('a', 1), new Knight(Role.WHITE));
        util.board.set(new CellIndex('b', 1), new Knight(Role.WHITE));
        util.board.set(new CellIndex('d', 1), new Knight(Role.WHITE));
        util.board.set(new CellIndex('f', 1), new Knight(Role.WHITE));
        util.board.set(new CellIndex('g', 1), new Knight(Role.WHITE));

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
     *  6 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   |   | B |   | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   | * |   |   |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   | * |   |   |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   | * |   | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testBarrierMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Knight(Role.WHITE),
                new CellIndex('d', 3)
        );

        util.board.set(new CellIndex('c', 5), new Knight(Role.WHITE));
        util.board.set(new CellIndex('e', 5), new Knight(Role.WHITE));

        util.allowedMoves.add(new CellIndex('b', 4));
        util.allowedMoves.add(new CellIndex('f', 4));
        util.allowedMoves.add(new CellIndex('b', 2));
        util.allowedMoves.add(new CellIndex('f', 2));
        util.allowedMoves.add(new CellIndex('c', 1));
        util.allowedMoves.add(new CellIndex('e', 1));

        util.test();
    }

    /**
     *  Move with beats
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+     + - enemy pieces, that can be beaten
     *  6 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   |   | + |   | + |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   | * |   |   |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   | * |   |   |   | * |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   | * |   | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testMoveWithBeats() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Knight(Role.WHITE),
                new CellIndex('d', 3)
        );

        util.board.set(new CellIndex('c', 5), new Knight(Role.BLACK));
        util.board.set(new CellIndex('e', 5), new Knight(Role.BLACK));

        util.allowedMoves.add(new CellIndex('c', 5));
        util.allowedMoves.add(new CellIndex('e', 5));
        util.allowedMoves.add(new CellIndex('b', 4));
        util.allowedMoves.add(new CellIndex('f', 4));
        util.allowedMoves.add(new CellIndex('b', 2));
        util.allowedMoves.add(new CellIndex('f', 2));
        util.allowedMoves.add(new CellIndex('c', 1));
        util.allowedMoves.add(new CellIndex('e', 1));

        util.test();
    }

    /**
     *  Move with full block
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   |   | B |   | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   | B |   |   |   | B |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   | B |   |   |   | B |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   | B |   | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testBlockedMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Knight(Role.WHITE),
                new CellIndex('d', 3)
        );

        util.board.set(new CellIndex('c', 5), new Knight(Role.WHITE));
        util.board.set(new CellIndex('e', 5), new Knight(Role.WHITE));
        util.board.set(new CellIndex('b', 4), new Knight(Role.WHITE));
        util.board.set(new CellIndex('f', 4), new Knight(Role.WHITE));
        util.board.set(new CellIndex('b', 2), new Knight(Role.WHITE));
        util.board.set(new CellIndex('f', 2), new Knight(Role.WHITE));
        util.board.set(new CellIndex('c', 1), new Knight(Role.WHITE));
        util.board.set(new CellIndex('e', 1), new Knight(Role.WHITE));

        util.test();
    }
}