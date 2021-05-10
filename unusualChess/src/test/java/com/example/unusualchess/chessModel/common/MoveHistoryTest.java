package com.example.unusualchess.chessModel.common;

import com.example.unusualchess.chessModel.board.CellIndex;
import com.example.unusualchess.chessModel.board.Piece;
import com.example.unusualchess.chessModel.board.pieces.Pawn;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MoveHistoryTest {
    @Test
    public void testAddMove() {
        Piece testPiece = new Pawn(Role.WHITE);
        MoveHistory hist = new MoveHistory();

        ChessMoveEvent<Piece> ev = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                -1,
                testPiece
        );

        hist.addMove(ev);

        assertEquals(new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                0, testPiece),
                hist.getHistory(-1).get(0));

        assertEquals(0, hist.getLastMoveSeqNumber());
    }

    @Test
    public void testCancelMove() {
        Piece testPiece = new Pawn(Role.WHITE);
        MoveHistory hist = new MoveHistory();

        ChessMoveEvent<Piece> ev = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                -1,
                testPiece
        );

        hist.addMove(ev);

        List<ChessMoveEvent<Piece>> evCancel = hist.cancelLastMove();
        ChessMoveEvent<Piece> expectedCancelMove = new ChessMoveEvent<>(
                new CellIndex('a', 2),
                new CellIndex('a', 1),
                1,
                testPiece,
                testPiece,
                true,
                false
        );

        assertEquals(expectedCancelMove, hist.getHistory(-1).get(1));
        assertEquals(expectedCancelMove, evCancel.get(0));
        assertEquals(1, hist.getLastMoveSeqNumber());

        assertEquals(0, hist.getShortHistory(-1).size());
    }

    @Test
    public void testCancelTwoMoves() {
        Piece testPiece = new Pawn(Role.WHITE);
        MoveHistory hist = new MoveHistory();

        ChessMoveEvent<Piece> ev1 = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                -1,
                testPiece
        );

        ChessMoveEvent<Piece> ev2 = new ChessMoveEvent<>(
                new CellIndex('a', 2),
                new CellIndex('a', 3),
                -1,
                testPiece
        );

        hist.addMove(ev1);
        hist.addMove(ev2);

        List<ChessMoveEvent<Piece>> evCancel2 = hist.cancelLastMove();
        ChessMoveEvent<Piece> expectedCancelMove2 = new ChessMoveEvent<>(
                new CellIndex('a', 3),
                new CellIndex('a', 2),
                2,
                testPiece,
                testPiece,
                true,
                false
        );

        List<ChessMoveEvent<Piece>> evCancel1 = hist.cancelLastMove();
        ChessMoveEvent<Piece> expectedCancelMove1 = new ChessMoveEvent<>(
                new CellIndex('a', 2),
                new CellIndex('a', 1),
                3,
                testPiece,
                testPiece,
                true,
                false
        );

        assertEquals(expectedCancelMove2, hist.getHistory(-1).get(2));
        assertEquals(expectedCancelMove1, hist.getHistory(-1).get(3));

        assertEquals(expectedCancelMove2, evCancel2.get(0));
        assertEquals(expectedCancelMove1, evCancel1.get(0));

        assertEquals(3, hist.getLastMoveSeqNumber());

        assertEquals(0, hist.getShortHistory(-1).size());
    }

    @Test
    public void testCancelWithoutMoves() {
        MoveHistory hist = new MoveHistory();

        hist.cancelLastMove();

        assertEquals(0, hist.getHistory(-1).size());
    }

    @Test
    public void testCancelBoundMoves() {
        Piece testPiece = new Pawn(Role.WHITE);
        MoveHistory hist = new MoveHistory();

        ChessMoveEvent<Piece> ev1 = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                -1,
                testPiece
        );

        ChessMoveEvent<Piece> ev2 = new ChessMoveEvent<>(
                new CellIndex('a', 2),
                new CellIndex('a', 3),
                -1,
                testPiece,
                testPiece,
                false,
                true
        );

        hist.addMove(ev1);
        hist.addMove(ev2);

        hist.cancelLastMove();

        assertEquals(0, hist.getShortHistory(-1).size());
        assertEquals(4, hist.getHistory(-1).size());

    }

    @Test
    public void testCancelTransformMove() {
        Piece testPiece1 = new Pawn(Role.WHITE);
        Piece testPiece2 = new Pawn(Role.WHITE);
        MoveHistory hist = new MoveHistory();

        ChessMoveEvent<Piece> ev = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                -1,
                testPiece2,
                testPiece1,
                false,
                false
        );

        hist.addMove(ev);

        ChessMoveEvent<Piece> cancelExpected = new ChessMoveEvent<>(
                new CellIndex('a', 2),
                new CellIndex('a', 1),
                1,
                testPiece1,
                testPiece2,
                true,
                false
        );

        ChessMoveEvent<Piece> cancelMove = hist.cancelLastMove().get(0);

        assertEquals(cancelExpected, cancelMove);
    }

    @Test
    public void testPieceMoved() {
        Piece testPiece = new Pawn(Role.WHITE);
        MoveHistory hist = new MoveHistory();

        ChessMoveEvent<Piece> ev = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                -1,
                testPiece,
                testPiece,
                false,
                false
        );

        hist.addMove(ev);

        assertTrue(hist.isPieceMoved(testPiece));
    }

    @Test
    public void testTransformedPieceMoved() {
        Piece testPiece1 = new Pawn(Role.WHITE);
        Piece testPiece2 = new Pawn(Role.WHITE);
        MoveHistory hist = new MoveHistory();

        ChessMoveEvent<Piece> ev1 = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                -1,
                testPiece1,
                testPiece2,
                false,
                false
        );

        hist.addMove(ev1);

        assertTrue(hist.isPieceMoved(testPiece1));
        assertTrue(hist.isPieceMoved(testPiece2));
    }

    @Test
    public void testCancelledMovePieceMoved() {
        Piece testPiece = new Pawn(Role.WHITE);
        MoveHistory hist = new MoveHistory();

        ChessMoveEvent<Piece> ev = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                -1,
                testPiece,
                testPiece,
                false,
                false
        );

        hist.addMove(ev);
        hist.cancelLastMove();

        assertFalse(hist.isPieceMoved(testPiece));
    }

    @Test
    public void testPieceMovedCancelBoundMoves() {
        Piece testPiece1 = new Pawn(Role.WHITE);
        Piece testPiece2 = new Pawn(Role.WHITE);
        MoveHistory hist = new MoveHistory();

        ChessMoveEvent<Piece> ev1 = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                -1,
                testPiece1,
                testPiece2,
                false,
                false
        );

        ChessMoveEvent<Piece> ev2 = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                -1,
                testPiece1,
                testPiece2,
                false,
                true
        );

        hist.addMove(ev1);
        hist.addMove(ev2);

        hist.cancelLastMove();

        assertFalse(hist.isPieceMoved(testPiece1));
        assertFalse(hist.isPieceMoved(testPiece2));
    }

    @Test
    public void testCopyConstructor() {
        Piece testPiece = new Pawn(Role.WHITE);
        MoveHistory hist = new MoveHistory();

        ChessMoveEvent<Piece> ev = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                -1,
                testPiece,
                testPiece,
                false,
                false
        );
        hist.addMove(ev);

        MoveHistory histClone = new MoveHistory(hist);
        assertEquals(hist.getHistory(-1), histClone.getHistory(-1));

        ev = new ChessMoveEvent<>(
                new CellIndex('a', 2),
                new CellIndex('a', 3),
                -1,
                testPiece,
                testPiece,
                false,
                false
        );
        histClone.addMove(ev);

        assertNotEquals(hist.getHistory(-1), histClone.getHistory(-1));
    }

    @Test
    public void testCloneEquals() {
        Piece testPiece = new Pawn(Role.WHITE);
        MoveHistory hist = new MoveHistory();

        ChessMoveEvent<Piece> ev = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                -1,
                testPiece,
                testPiece,
                false,
                false
        );
        hist.addMove(ev);

        MoveHistory histClone = new MoveHistory(hist);

        assertEquals(hist, histClone);
    }
}