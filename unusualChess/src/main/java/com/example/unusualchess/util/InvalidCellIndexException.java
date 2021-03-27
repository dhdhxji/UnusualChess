package com.example.unusualchess.util;

import com.example.unusualchess.board.CellIndex;

public class InvalidCellIndexException extends Exception {
    public InvalidCellIndexException(CellIndex index) {
        super("Invalid cell index: file: " + index.toString());
    }
}
