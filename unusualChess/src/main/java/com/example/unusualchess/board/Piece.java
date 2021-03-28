package com.example.unusualchess.board;

import com.example.unusualchess.common.Role;

public abstract class Piece {
    public enum Type {
        EMPTY,
        PAWN,
        ROOK,
        BISHOP,
        KNIGHT,
        KING,
        QUEEN
    }

    public Piece(Type type, Role role) {
        _type = type;
        _role = role;
    }

    abstract public CellIndex[] getAvailableMoves(CellIndex pos, Piece[][] pieces);

    public Type getType() {
        return _type;
    }

    public Role getRole() {
        return _role;
    }

    private Type _type;
    private Role _role;
}
