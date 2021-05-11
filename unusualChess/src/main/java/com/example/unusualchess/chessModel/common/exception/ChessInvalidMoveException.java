package com.example.unusualchess.chessModel.common.exception;

import com.example.unusualchess.chessModel.board.CellIndex;

public class ChessInvalidMoveException extends Exception {
    public ChessInvalidMoveException(CellIndex src, CellIndex dst) {
        super("Invalid move from: " + src + " to: " + dst);
    }
}
