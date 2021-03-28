package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;

public class EmptyPiece extends Piece {
    public EmptyPiece() {
        super(Type.EMPTY, Role.NONE);
    }

    @Override
    public CellIndex[] getAvailableMoves(CellIndex pos, Piece[][] pieces) {
        return new CellIndex[0];
    }
}
