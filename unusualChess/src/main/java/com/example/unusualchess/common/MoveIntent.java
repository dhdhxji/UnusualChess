package com.example.unusualchess.common;

import androidx.annotation.NonNull;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;

import java.util.Objects;

public class MoveIntent {
    public MoveIntent(Role role, CellIndex src, CellIndex dst) {
        this(role, src, dst, null);
    }

    public MoveIntent(Role role, CellIndex src, CellIndex dst, Piece transform) {
        _role = role;
        _srcPos = src;
        _dstPos = dst;
        _transformTo = transform;
    }

    public Role getRole() {
        return _role;
    }

    public CellIndex getSrc() {
        return _srcPos;
    }

    public CellIndex getDst() {
        return _dstPos;
    }

    public Piece getTransformTo() {
        return _transformTo;
    }

    @NonNull
    @Override
    public String toString() {
        return "MoveIntent{" +
                "_role=" + _role +
                ", _srcPos=" + _srcPos +
                ", _dstPos=" + _dstPos +
                ", _transformTo=" + _transformTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveIntent that = (MoveIntent) o;
        return _role == that._role &&
                Objects.equals(_srcPos, that._srcPos) &&
                Objects.equals(_dstPos, that._dstPos) &&
                Objects.equals(_transformTo, that._transformTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_role, _srcPos, _dstPos, _transformTo);
    }

    private final Role _role;
    private final CellIndex _srcPos;
    private final CellIndex _dstPos;
    private final Piece _transformTo;
}
