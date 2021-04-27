package com.example.unusualchess.common;

import androidx.annotation.NonNull;

import com.example.unusualchess.board.CellIndex;

import java.util.Objects;

public class MoveIntent {
    public MoveIntent(Role role, CellIndex src, CellIndex dst) {
        _role = role;
        _srcPos = src;
        _dstPos = dst;
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

    @Override
    @NonNull
    public String toString() {
        return "MoveIntent{" +
                _role +
                ", src:" + _srcPos +
                ", dst:" + _dstPos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveIntent that = (MoveIntent) o;
        return _role == that._role &&
                _srcPos.equals(that._srcPos) &&
                _dstPos.equals(that._dstPos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_role, _srcPos, _dstPos);
    }

    private final Role _role;

    private final CellIndex _srcPos;
    private final CellIndex _dstPos;

}
