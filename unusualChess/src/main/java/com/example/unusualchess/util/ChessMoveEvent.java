package com.example.unusualchess.util;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;


public class ChessMoveEvent<T> {
    public ChessMoveEvent(CellIndex src, CellIndex dst, int seqNumber, T piece) {
       _src = src;
       _dst = dst;
       _seqNumber = seqNumber;
       _piece = piece;
    }

    public CellIndex getSrc() {
        return _src;
    }

    public CellIndex getDst() {
        return _dst;
    }

    public int getSeqNumber() {
        return _seqNumber;
    }

    public T getPiece() {
        return _piece;
    }

    private T _piece;
    private CellIndex _src;
    private CellIndex _dst;
    private int _seqNumber;
}
