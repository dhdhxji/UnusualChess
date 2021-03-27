package com.example.unusualchess.util;

import com.example.unusualchess.board.CellIndex;

public class ChessInvalidMoveException extends Exception {
    public ChessInvalidMoveException(CellIndex src, CellIndex dst) {
        super("Invalid move from: " + src + " to: " + dst);
    }
}
