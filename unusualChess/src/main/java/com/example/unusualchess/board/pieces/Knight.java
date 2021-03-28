package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;

public class Knight extends Piece {
    public Knight(Role role) {
        super(Type.KNIGHT, role);
    }

    @Override
    public CellIndex[] getAvailableMoves(CellIndex pos, Piece[][] pieces) {
        //TODO: Implement stub
        return new CellIndex[0];
    }
}
