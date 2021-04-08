package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.BoardHolder;
import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;

import java.util.Set;

/**
 *     _
 *    (_)
 *   (___)
 *   _|_|_
 *  (_____)
 *  /_____\
 */
public class Pawn extends Piece {
    public Pawn(Role role) {
        super(Type.PAWN, role);
    }

    @Override
    public Set<CellIndex> getAvailableMoves(CellIndex pos, BoardHolder<Piece> board) {
        //TODO: Implement stub
        return null;
    }
}
