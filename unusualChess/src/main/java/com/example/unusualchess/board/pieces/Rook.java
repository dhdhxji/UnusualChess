package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;

import java.util.Set;

public class Rook extends Piece {
    public Rook(Role role) {
        super(Type.ROOK, role);
    }

    @Override
    public Set<CellIndex> getAvailableMoves(CellIndex pos, Piece[][] pieces) {
        //TODO: Implement stub
        return null;
    }
}
