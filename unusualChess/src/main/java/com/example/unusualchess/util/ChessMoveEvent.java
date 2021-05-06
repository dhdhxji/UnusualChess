package com.example.unusualchess.util;

import androidx.annotation.NonNull;

import com.example.unusualchess.board.CellIndex;

import java.util.Objects;


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

    @NonNull
    @Override
    public String toString() {
        return "ChessMoveEvent{" +
                "_piece=" + _piece +
                ", _src=" + _src +
                ", _dst=" + _dst +
                ", _seqNumber=" + _seqNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessMoveEvent<?> that = (ChessMoveEvent<?>) o;
        return _seqNumber == that._seqNumber &&
                Objects.equals(_piece, that._piece) &&
                Objects.equals(_src, that._src) &&
                Objects.equals(_dst, that._dst);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_piece, _src, _dst, _seqNumber);
    }

    private final T _piece;
    private final CellIndex _src;
    private final CellIndex _dst;
    private final int _seqNumber;
}
