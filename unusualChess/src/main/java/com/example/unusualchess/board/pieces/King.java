package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;

public class King extends Piece {
    public King(Role role) {
        super(Piece.Type.KING, role);
    }

    @Override
    public CellIndex[] getAvailableMoves(CellIndex pos, Piece[][] pieces) {
        //TODO: Implement stub
        return new CellIndex[0];
    }
}
