package com.example.unusualchess.util;

import com.example.unusualchess.board.CellIndex;

public interface ChessModelListener {
    void onMove(ChessMoveEvent ev);
}
