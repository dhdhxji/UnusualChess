package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;

public class Pawn extends Piece {
    public Pawn(Role role) {
        super(Type.PAWN, role);
    }

    @Override
    public CellIndex[] getAvailableMoves(CellIndex pos, Piece[][] pieces) {
        //TODO: Implement stub
        return new CellIndex[0];
    }
}
