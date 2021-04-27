package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.PieceTestCommonUtils;
import com.example.unusualchess.common.MoveIntent;
import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.ChessMoveEvent;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class KingTest {
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
     *  5 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   | * | P | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testSimpleMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new King(Role.WHITE),
                new CellIndex('d', 3)
        );

        util.allowedMoves.add(new CellIndex('c', 2));
        util.allowedMoves.add(new CellIndex('c', 3));
        util.allowedMoves.add(new CellIndex('c', 4));
        util.allowedMoves.add(new CellIndex('d', 2));
        util.allowedMoves.add(new CellIndex('d', 4));
        util.allowedMoves.add(new CellIndex('e', 2));
        util.allowedMoves.add(new CellIndex('e', 3));
        util.allowedMoves.add(new CellIndex('e', 4));

        util.test();
    }

    /**
     *  Barrier move
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
     *  4 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   | * | P | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testBarrierMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new King(Role.WHITE),
                new CellIndex('d', 3)
        );

        util.allowedMoves.add(new CellIndex('c', 2));
        util.allowedMoves.add(new CellIndex('c', 3));
        util.allowedMoves.add(new CellIndex('c', 4));
        util.allowedMoves.add(new CellIndex('d', 2));
        util.allowedMoves.add(new CellIndex('d', 4));
        util.allowedMoves.add(new CellIndex('e', 2));
        util.allowedMoves.add(new CellIndex('e', 4));

        util.board.set(new CellIndex('e', 3), new King(Role.WHITE));

        util.test();
    }

    /**
     *  Beat move
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
     *  4 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   | * | P | + |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testBeatMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new King(Role.WHITE),
                new CellIndex('d', 3)
        );

        util.allowedMoves.add(new CellIndex('c', 2));
        util.allowedMoves.add(new CellIndex('c', 3));
        util.allowedMoves.add(new CellIndex('c', 4));
        util.allowedMoves.add(new CellIndex('d', 2));
        util.allowedMoves.add(new CellIndex('d', 4));
        util.allowedMoves.add(new CellIndex('e', 2));
        util.allowedMoves.add(new CellIndex('e', 3));
        util.allowedMoves.add(new CellIndex('e', 4));

        util.board.set(new CellIndex('e', 3), new King(Role.BLACK));

        util.test();
    }

    /**
     *  Environment move
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   | B | B | B | B | B |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   | B | * | * | * | B |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   | B | * | P | * | B |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   | B | * | * | * | B |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   | B | B | B | B | B |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testEnvironmentMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new King(Role.WHITE),
                new CellIndex('d', 3)
        );

        util.allowedMoves.add(new CellIndex('c', 2));
        util.allowedMoves.add(new CellIndex('c', 3));
        util.allowedMoves.add(new CellIndex('c', 4));
        util.allowedMoves.add(new CellIndex('d', 2));
        util.allowedMoves.add(new CellIndex('d', 4));
        util.allowedMoves.add(new CellIndex('e', 2));
        util.allowedMoves.add(new CellIndex('e', 3));
        util.allowedMoves.add(new CellIndex('e', 4));

        util.board.set(new CellIndex('b', 1), new King(Role.WHITE));
        util.board.set(new CellIndex('c', 1), new King(Role.WHITE));
        util.board.set(new CellIndex('d', 1), new King(Role.WHITE));
        util.board.set(new CellIndex('e', 1), new King(Role.WHITE));
        util.board.set(new CellIndex('f', 1), new King(Role.WHITE));

        util.board.set(new CellIndex('b', 5), new King(Role.WHITE));
        util.board.set(new CellIndex('c', 5), new King(Role.WHITE));
        util.board.set(new CellIndex('d', 5), new King(Role.WHITE));
        util.board.set(new CellIndex('e', 5), new King(Role.WHITE));
        util.board.set(new CellIndex('f', 5), new King(Role.WHITE));

        util.board.set(new CellIndex('b', 2), new King(Role.WHITE));
        util.board.set(new CellIndex('b', 3), new King(Role.WHITE));
        util.board.set(new CellIndex('b', 4), new King(Role.WHITE));

        util.board.set(new CellIndex('f', 2), new King(Role.WHITE));
        util.board.set(new CellIndex('f', 3), new King(Role.WHITE));
        util.board.set(new CellIndex('f', 4), new King(Role.WHITE));

        util.test();
    }

    /**
     *  Out of bound move
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 | * | * |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 | P | * |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testOutOfBoundsMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new King(Role.WHITE),
                new CellIndex('a', 1)
        );

        util.allowedMoves.add(new CellIndex('b', 1));
        util.allowedMoves.add(new CellIndex('a', 2));
        util.allowedMoves.add(new CellIndex('b', 2));

        util.test();
    }

    /**
     *  Blocked move
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
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
    public void testBlockMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new King(Role.WHITE),
                new CellIndex('d', 3)
        );

        util.board.set(new CellIndex('c', 2), new King(Role.WHITE));
        util.board.set(new CellIndex('c', 3), new King(Role.WHITE));
        util.board.set(new CellIndex('c', 4), new King(Role.WHITE));
        util.board.set(new CellIndex('d', 2), new King(Role.WHITE));
        util.board.set(new CellIndex('d', 4), new King(Role.WHITE));
        util.board.set(new CellIndex('e', 2), new King(Role.WHITE));
        util.board.set(new CellIndex('e', 3), new King(Role.WHITE));
        util.board.set(new CellIndex('e', 4), new King(Role.WHITE));

        util.test();
    }

    /**
     *  Castling (King & rook not moved)
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+     = - addition pieces
     *      -   -   -   -   -   -   -   -
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 | = | * | * | P | * | * |   | = |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      \      /\           /\      /
     *      \------/             \-----/
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testCastlingMoveWithKRUnmoved() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new King(Role.WHITE),
                new CellIndex('d', 1)
        );

        util.board.set(new CellIndex('a', 1), new Rook(Role.WHITE));
        util.board.set(new CellIndex('h', 1), new Rook(Role.WHITE));

        util.allowedMoves.add(new CellIndex('b', 1));
        util.allowedMoves.add(new CellIndex('c', 1));
        util.allowedMoves.add(new CellIndex('e', 1));
        util.allowedMoves.add(new CellIndex('f', 1));
        util.allowedMoves.add(new CellIndex('c', 2));
        util.allowedMoves.add(new CellIndex('d', 2));
        util.allowedMoves.add(new CellIndex('e', 2));

        util.test();


        //Test right castling
        MoveIntent rKMove = new MoveIntent(Role.WHITE,
                new CellIndex('d', 1),
                new CellIndex('f', 1));

        List<MoveIntent> reqMovesComp = util.testPiece.getRequiredMoves(rKMove,
                util.board, util.movesHistory);
        List<MoveIntent> rMoveReqExpected = new ArrayList<>();
        rMoveReqExpected.add(new MoveIntent(Role.WHITE,
                new CellIndex('h', 1),
                new CellIndex('e', 1)) );

        assertEquals(rMoveReqExpected, reqMovesComp);


        //Test left castling
        MoveIntent lKMove = new MoveIntent(Role.WHITE,
                new CellIndex('d', 1),
                new CellIndex('b', 1));

        reqMovesComp = util.testPiece.getRequiredMoves(lKMove, util.board, util.movesHistory);
        List<MoveIntent> lMoveReqExpected = new ArrayList<>();
        lMoveReqExpected.add(new MoveIntent(Role.WHITE,
                new CellIndex('a', 1),
                new CellIndex('c', 1)) );

        assertEquals(lMoveReqExpected, reqMovesComp);
    }

    /**
     *  Castling (rook moved, king not moved)
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+     = - addition pieces
     *      -   -   -   -   -   -   -   -
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 | = | * | * | P | * |   |   | = |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      \      /\
     *      \------/
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testCastlingMoveWithKUnmovedRMoved() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new King(Role.WHITE),
                new CellIndex('d', 1)
        );

        util.board.set(new CellIndex('a', 1), new Rook(Role.WHITE));
        util.board.set(new CellIndex('h', 1), new Rook(Role.WHITE));
        util.movesHistory.add(new ChessMoveEvent<>(
                new CellIndex('h', 2), new CellIndex('h', 1),
                0, util.board.get(new CellIndex('h', 1))
        ));

        util.allowedMoves.add(new CellIndex('b', 1));
        util.allowedMoves.add(new CellIndex('c', 1));
        util.allowedMoves.add(new CellIndex('e', 1));
        util.allowedMoves.add(new CellIndex('c', 2));
        util.allowedMoves.add(new CellIndex('d', 2));
        util.allowedMoves.add(new CellIndex('e', 2));

        util.test();


        //Test right castling
        MoveIntent rKMove = new MoveIntent(Role.WHITE,
                new CellIndex('d', 1),
                new CellIndex('f', 1));

        List<MoveIntent> reqMovesComp = util.testPiece.getRequiredMoves(rKMove,
                util.board, util.movesHistory);
        List<MoveIntent> rMoveReqExpected = new ArrayList<>();

        assertEquals(rMoveReqExpected, reqMovesComp);


        //Test left castling
        MoveIntent lKMove = new MoveIntent(Role.WHITE,
                new CellIndex('d', 1),
                new CellIndex('b', 1));

        reqMovesComp = util.testPiece.getRequiredMoves(lKMove, util.board, util.movesHistory);
        List<MoveIntent> lMoveReqExpected = new ArrayList<>();
        lMoveReqExpected.add(new MoveIntent(Role.WHITE,
                new CellIndex('a', 1),
                new CellIndex('c', 1)) );

        assertEquals(lMoveReqExpected, reqMovesComp);
    }

    /**
     *  Castling (rook moved, king not moved)
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+     = - addition pieces
     *      -   -   -   -   -   -   -   -
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 | = |   | * | P | * |   |   | = |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testCastlingMoveWithKUnmovedR2Moved() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new King(Role.WHITE),
                new CellIndex('d', 1)
        );

        util.board.set(new CellIndex('a', 1), new Rook(Role.WHITE));
        util.board.set(new CellIndex('h', 1), new Rook(Role.WHITE));
        util.movesHistory.add(new ChessMoveEvent<>(
                new CellIndex('h', 2), new CellIndex('h', 1),
                0, util.board.get(new CellIndex('h', 1))
        ));

        util.movesHistory.add(new ChessMoveEvent<>(
                new CellIndex('a', 2), new CellIndex('a', 1),
                0, util.board.get(new CellIndex('a', 1))
        ));

        util.allowedMoves.add(new CellIndex('c', 1));
        util.allowedMoves.add(new CellIndex('e', 1));
        util.allowedMoves.add(new CellIndex('c', 2));
        util.allowedMoves.add(new CellIndex('d', 2));
        util.allowedMoves.add(new CellIndex('e', 2));

        util.test();


        //Test right castling
        MoveIntent rKMove = new MoveIntent(Role.WHITE,
                new CellIndex('d', 1),
                new CellIndex('f', 1));

        List<MoveIntent> reqMovesComp = util.testPiece.getRequiredMoves(rKMove,
                util.board, util.movesHistory);
        List<MoveIntent> rMoveReqExpected = new ArrayList<>();

        assertEquals(rMoveReqExpected, reqMovesComp);


        //Test left castling
        MoveIntent lKMove = new MoveIntent(Role.WHITE,
                new CellIndex('d', 1),
                new CellIndex('b', 1));

        reqMovesComp = util.testPiece.getRequiredMoves(lKMove, util.board, util.movesHistory);
        List<MoveIntent> lMoveReqExpected = new ArrayList<>();

        assertEquals(lMoveReqExpected, reqMovesComp);
    }

    /**
     *  Castling (rook moved, king not moved)
     *
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+     = - addition pieces
     *      -   -   -   -   -   -   -   -
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | * | * | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 | = |   | * | P | * |   |   | = |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testCastlingMoveWithKMovedRUnmoved() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new King(Role.WHITE),
                new CellIndex('d', 1)
        );

        util.board.set(new CellIndex('a', 1), new Rook(Role.WHITE));
        util.board.set(new CellIndex('h', 1), new Rook(Role.WHITE));
        util.movesHistory.add(new ChessMoveEvent<>(
                new CellIndex('d', 2), new CellIndex('d', 1),
                0, util.board.get(new CellIndex('d', 1))
        ));


        util.allowedMoves.add(new CellIndex('c', 1));
        util.allowedMoves.add(new CellIndex('e', 1));
        util.allowedMoves.add(new CellIndex('c', 2));
        util.allowedMoves.add(new CellIndex('d', 2));
        util.allowedMoves.add(new CellIndex('e', 2));

        util.test();


        //Test right castling
        MoveIntent rKMove = new MoveIntent(Role.WHITE,
                new CellIndex('d', 1),
                new CellIndex('f', 1));

        List<MoveIntent> reqMovesComp = util.testPiece.getRequiredMoves(rKMove,
                util.board, util.movesHistory);
        List<MoveIntent> rMoveReqExpected = new ArrayList<>();

        assertEquals(rMoveReqExpected, reqMovesComp);


        //Test left castling
        MoveIntent lKMove = new MoveIntent(Role.WHITE,
                new CellIndex('d', 1),
                new CellIndex('b', 1));

        reqMovesComp = util.testPiece.getRequiredMoves(lKMove, util.board, util.movesHistory);
        List<MoveIntent> lMoveReqExpected = new ArrayList<>();

        assertEquals(lMoveReqExpected, reqMovesComp);
    }
}