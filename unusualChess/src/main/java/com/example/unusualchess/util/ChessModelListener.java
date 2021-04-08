package com.example.unusualchess.util;


public interface ChessModelListener<T> {
    void onMove(ChessMoveEvent<T> ev);
}
