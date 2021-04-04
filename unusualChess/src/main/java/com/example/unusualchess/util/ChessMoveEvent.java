package com.example.unusualchess.util;

import com.example.unusualchess.board.CellIndex;


public class ChessMoveEvent {
    public ChessMoveEvent(CellIndex src, CellIndex dst, int seqNumber) {
       _src = src;
       _dst = dst;
       _seqNumber = seqNumber;
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

    private CellIndex _src;
    private CellIndex _dst;
    private int _seqNumber;
}
