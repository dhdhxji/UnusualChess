package com.example.unusualchess.chessModel.common.exception;

import com.example.unusualchess.chessModel.board.CellIndex;

public class NoPieceException extends Exception {
    public NoPieceException(CellIndex pos) {
        super("The " + pos + " position does not contain a piece");
    }
}
