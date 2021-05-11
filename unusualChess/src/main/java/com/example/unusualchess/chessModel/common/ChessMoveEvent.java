package com.example.unusualchess.chessModel.common;

import androidx.annotation.NonNull;

import com.example.unusualchess.chessModel.board.CellIndex;

import java.util.Objects;


public class ChessMoveEvent<T> {
    public ChessMoveEvent(CellIndex src, CellIndex dst, int seqNumber, T piece) {
        this(src, dst, seqNumber, piece, piece,
                false, false, null, null);
    }

    public ChessMoveEvent(CellIndex src,
                          CellIndex dst,
                          int seqNumber,
                          T piece,
                          T transformFrom,
                          boolean isCancel,
                          boolean prevMoveBound) {
        this(src, dst, seqNumber, piece, transformFrom,
                isCancel, prevMoveBound, null, null);
    }

    public ChessMoveEvent(CellIndex src,
                          CellIndex dst,
                          int seqNumber,
                          T piece,
                          T transformFrom,
                          boolean isCancel,
                          boolean prevMoveBound,
                          CellIndex beatKingPos,
                          Role winner) {
        _src = src;
        _dst = dst;
        _seqNumber = seqNumber;
        _piece = piece;
        _transformFrom = transformFrom;
        _transformTo = piece;
        _cancelMove = isCancel;
        _prevMoveBound = prevMoveBound;
        _beatKingPos = beatKingPos;
        _winner = winner;
    }

    public ChessMoveEvent(ChessMoveEvent<T> src, int seqNumber) {
        this(
                src.getSrc(),
                src.getDst(),
                seqNumber,
                src.getPiece(),
                src.getTransformFrom(),
                src.isCancelMove(),
                src.isPreviousMoveBound(),
                src.getCheckKingPos(),
                src.getWinner()
        );
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

    public boolean isCheck() {
        return _beatKingPos != null;
    }

    public CellIndex getCheckKingPos() {
        return _beatKingPos;
    }

    public Role getWinner() {
        return _winner;
    }

    @NonNull
    @Override
    public String toString() {
        return "ChessMoveEvent{" +
                "_piece=" + _piece +
                ", _src=" + _src +
                ", _dst=" + _dst +
                ", _transformFrom=" + _transformFrom +
                ", _transformTo=" + _transformTo +
                ", _cancelMove=" + _cancelMove +
                ", _seqNumber=" + _seqNumber +
                ", _prevMoveBound=" + _prevMoveBound +
                ", _beatKingPos=" + _beatKingPos +
                ", _winner=" + _winner +
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
                Objects.equals(_piece, that._piece) &&
                Objects.equals(_src, that._src) &&
                Objects.equals(_dst, that._dst) &&
                Objects.equals(_transformFrom, that._transformFrom) &&
                Objects.equals(_transformTo, that._transformTo) &&
                Objects.equals(_beatKingPos, that._beatKingPos) &&
                _winner == that._winner;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_piece, _src, _dst, _transformFrom, _transformTo, _cancelMove, _seqNumber, _prevMoveBound, _beatKingPos, _winner);
    }

    private final T _piece;
    private final CellIndex _src;
    private final CellIndex _dst;
    private final T _transformFrom;
    private final T _transformTo;
    private final boolean _cancelMove;
    private final int _seqNumber;
    private final boolean _prevMoveBound;
    private final CellIndex _beatKingPos;
    private final Role _winner;
}
