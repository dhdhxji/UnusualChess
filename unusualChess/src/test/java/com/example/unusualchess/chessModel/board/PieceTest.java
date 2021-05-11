package com.example.unusualchess.chessModel.board;

import com.example.unusualchess.chessModel.board.pieces.Pawn;
import com.example.unusualchess.chessModel.common.Role;
import com.example.unusualchess.chessModel.common.ChessMoveEvent;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class PieceTest {

    @Test
    public void isPieceMoved() {
        //Use child class because Piece is abstract
        Pawn testPiece = new Pawn(Role.WHITE);

        List<ChessMoveEvent<Piece>> moveHistory = new LinkedList<>();

        assertFalse("Piece was not moved", testPiece.isPieceMoved(moveHistory));

        moveHistory.add(new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                0,
                testPiece
        ));

        assertTrue("Piece was moved", testPiece.isPieceMoved(moveHistory));
    }

    @Test
    public void getType() {
        //Use child class because Piece is abstract
        Pawn testPiece = new Pawn(Role.WHITE);

        assertEquals("Piece has another type", Piece.Type.PAWN, testPiece.getType());
    }

    @Test
    public void getRole() {
        //Use child class because Piece is abstract
        Pawn testPiece = new Pawn(Role.WHITE);

        assertEquals("Piece has another role", Role.WHITE, testPiece.getRole());
    }

    @Test
    public void isMOvePossibleOutOfBounds() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
            new Pawn(Role.WHITE),
            new CellIndex('D', 2)
        );

        assertFalse("Impossible move (out of board bounds)",
                util.testPiece.isMovePossible(
                        new CellIndex(9, 9),
                        util.board
                ));

        assertFalse("Impossible move (out of board bounds)",
                util.testPiece.isMovePossible(
                        new CellIndex(-1, -1),
                        util.board
                ));
    }

    @Test
    public void isMovePossiblePieceOverlap() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('D', 2)
        );

        //set up environment
        util.board.set(
                new CellIndex('a', 1),
                new Pawn(Role.WHITE)
        );

        assertFalse("Impossible move (piece overlap)",
                util.testPiece.isMovePossible(
                        new CellIndex('a', 1),
                        util.board
                ));
    }

    @Test
    public void isMovePossiblePieceNoOverlap() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('D', 2)
        );

        assertTrue("Possible move",
                util.testPiece.isMovePossible(
                        new CellIndex('a', 1),
                        util.board
                ));
    }


    @Test
    public void isBeatPossibleOutOfBounds() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('D', 2)
        );

        assertFalse("Invalid beat (out of board bounds)",
                util.testPiece.isBeatPossible(
                        new CellIndex(8, 8),
                        util.board
                ));

        assertFalse("Invalid beat (out of board bounds)",
                util.testPiece.isBeatPossible(
                        new CellIndex(0, 0),
                        util.board
                ));
    }

    @Test
    public void isBeatPossiblePieceNoOverlap() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('D', 2)
        );

        assertFalse("Invalid beat (no overlap wit enemy)",
                util.testPiece.isBeatPossible(
                        new CellIndex('a', 1),
                        util.board
                ));
    }

    @Test
    public void isBeatPossiblePieceOverlap() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('D', 2)
        );

        //set up environment
        util.board.set(
                new CellIndex('a', 1),
                new Pawn(Role.BLACK)
        );

        assertTrue("Possible beat",
                util.testPiece.isBeatPossible(
                        new CellIndex('a', 1),
                        util.board
                ));
    }
}