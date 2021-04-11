package com.example.unusualchess.board;

import com.example.unusualchess.util.ChessMoveEvent;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PieceTestCommonUtils {
    public static final int BOARD_WIDTH = 8;

    public PieceTestCommonUtils(Piece p, CellIndex src) {
        testPiece = p;
        srcPos = src;

        board.set(srcPos, testPiece);
    }

    public void test() {
        Set<CellIndex> availableMoves =
                testPiece.getAvailableMoves(srcPos, board, movesHistory);

        assertEquals(allowedMoves, availableMoves);
    }

    public BoardHolder<Piece> board = new BoardHolder<>(BOARD_WIDTH);
    public List<ChessMoveEvent<Piece>> movesHistory = new LinkedList<>();

    public CellIndex srcPos;
    public Piece testPiece;

    public Set<CellIndex> allowedMoves = new HashSet<>();
}
