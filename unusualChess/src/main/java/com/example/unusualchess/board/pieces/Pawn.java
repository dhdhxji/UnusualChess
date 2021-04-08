package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.BoardHolder;
import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.ChessMoveEvent;

import java.util.List;
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
    public Set<CellIndex> getAvailableMoves(CellIndex pos,
                                            BoardHolder<Piece> board,
                                            List<ChessMoveEvent<Piece>> moveHistory) {
        //TODO: Implement stub
        return null;
    }
}
