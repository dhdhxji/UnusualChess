package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;

import java.util.Set;

public class Knight extends Piece {
    public Knight(Role role) {
        super(Type.KNIGHT, role);
    }

    @Override
    public Set<CellIndex> getAvailableMoves(CellIndex pos, Piece[][] pieces) {
        //TODO: Implement stub
        return null;
    }
}
