package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;

import java.util.Set;

public class Queen extends Piece {
    public Queen(Role role) {
        super(Type.QUEEN, role);
    }

    @Override
    public Set<CellIndex> getAvailableMoves(CellIndex pos, Piece[][] pieces) {
        //TODO: Implement stub
        return null;
    }
}
