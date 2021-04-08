package com.example.unusualchess.board;

import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.ChessMoveEvent;

import java.util.List;
import java.util.Set;

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

     abstract public Set<CellIndex> getAvailableMoves(CellIndex pos,
                                                      BoardHolder<Piece> board,
                                                      List<ChessMoveEvent<Piece>> moveHistory);

    public Type getType() {
        return _type;
    }

    public Role getRole() {
        return _role;
    }

    private final Type _type;
    private final Role _role;
}
