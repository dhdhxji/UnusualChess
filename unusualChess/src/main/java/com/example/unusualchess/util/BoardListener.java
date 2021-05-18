package com.example.unusualchess.util;

import com.example.unusualchess.chessModel.board.CellIndex;

public interface BoardListener {
    void onBoardClick(CellIndex pos);
}
