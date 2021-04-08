package com.example.unusualchess.util;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;


public class ChessMoveEvent {
    public ChessMoveEvent(CellIndex src, CellIndex dst, int seqNumber, Piece piece) {
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

    public Piece getPiece() {
        return _piece;
    }

    private Piece _piece;
    private CellIndex _src;
    private CellIndex _dst;
    private int _seqNumber;
}
