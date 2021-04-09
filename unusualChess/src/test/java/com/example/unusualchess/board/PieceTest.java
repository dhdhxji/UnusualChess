package com.example.unusualchess.board;

import com.example.unusualchess.board.pieces.Pawn;
import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.ChessMoveEvent;

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
}