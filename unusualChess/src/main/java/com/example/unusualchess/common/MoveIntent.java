package com.example.unusualchess.common;

import com.example.unusualchess.board.CellIndex;

public class MoveIntent {
    MoveIntent(Role role, CellIndex src, CellIndex dst) {
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

    public CellIndex gerDst() {
        return _dstPos;
    }



    private Role _role;

    private CellIndex _srcPos;
    private CellIndex _dstPos;

}
