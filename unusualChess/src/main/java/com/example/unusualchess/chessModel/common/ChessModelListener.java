package com.example.unusualchess.chessModel.common;


public interface ChessModelListener<T> {
    void onMove(ChessMoveEvent<T> ev);
}
