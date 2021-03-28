package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;

import java.util.Set;

public class Pawn extends Piece {
    public Pawn(Role role) {
        super(Type.PAWN, role);
    }

    @Override
    public Set<CellIndex> getAvailableMoves(CellIndex pos, Piece[][] pieces) {
        //TODO: Implement stub
        return null;
    }
}
