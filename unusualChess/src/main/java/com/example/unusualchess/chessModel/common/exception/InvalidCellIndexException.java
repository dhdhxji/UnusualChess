package com.example.unusualchess.chessModel.common.exception;

import com.example.unusualchess.chessModel.board.CellIndex;

public class InvalidCellIndexException extends Exception {
    public InvalidCellIndexException(CellIndex index) {
        super("Invalid cell index: file: " + index.toString());
    }
}
