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

       _transformFrom = piece;
       _transformTo = piece;
       _cancelMove = false;
       _prevMoveBound = false;
    }

    public ChessMoveEvent(CellIndex src,
                          CellIndex dst,
                          int seqNumber,
                          T piece,
                          T transformFrom,
                          boolean isCancel,
                          boolean prevMoveBound) {
        _src = src;
        _dst = dst;
        _seqNumber = seqNumber;
        _piece = piece;
        _transformFrom = transformFrom;
        _transformTo = piece;
        _cancelMove = isCancel;
        _prevMoveBound = prevMoveBound;
    }

    public ChessMoveEvent(ChessMoveEvent<T> src, int seqNumber) {
        _src = src.getSrc();
        _dst = src.getDst();
        _piece = src.getPiece();
        _transformFrom = src.getTransformFrom();
        _transformTo = src.getTransformTo();
        _cancelMove = src.isCancelMove();
        _seqNumber = seqNumber;
        _prevMoveBound = src.isPreviousMoveBound();
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

    public T getTransformFrom() {
        return _transformFrom;
    }

    public T getTransformTo() {
        return _transformTo;
    }

    public boolean isCancelMove() {
        return _cancelMove;
    }

    public boolean isPreviousMoveBound() {
        return _prevMoveBound;
    }

    public ChessMoveEvent<T> getCancelMove() {
        return new ChessMoveEvent<>(
                getDst(),
                getSrc(),
                -1,
                getTransformFrom(),
                getTransformTo(),
                true,
                isPreviousMoveBound()
        );
    }

    @NonNull
    @Override
    public String toString() {
        return "ChessMoveEvent{" +
                "_piece=" + _piece + "@" + System.identityHashCode(_transformFrom) +
                ", _src=" + _src +
                ", _dst=" + _dst +
                ", _transformFrom=" + _transformFrom + "@" + System.identityHashCode(_transformFrom) +
                ", _transformTo=" + _transformTo + "@" + System.identityHashCode(_transformFrom) +
                ", _cancelMove=" + _cancelMove +
                ", _seqNumber=" + _seqNumber +
                ", _prevMoveBound=" + _prevMoveBound +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessMoveEvent<?> that = (ChessMoveEvent<?>) o;
        return _cancelMove == that._cancelMove &&
                _seqNumber == that._seqNumber &&
                _prevMoveBound == that._prevMoveBound &&
                _piece == that._piece &&
                _src.equals(that._src) &&
                _dst.equals(that._dst) &&
                Objects.equals(_transformFrom, that._transformFrom) &&
                Objects.equals(_transformTo, that._transformTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_piece, _src, _dst, _transformFrom, _transformTo, _cancelMove, _seqNumber, _prevMoveBound);
    }

    private final T _piece;
    private final CellIndex _src;
    private final CellIndex _dst;
    private final T _transformFrom;
    private final T _transformTo;
    private final boolean _cancelMove;
    private final int _seqNumber;
    private final boolean _prevMoveBound;
}
