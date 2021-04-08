package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.BoardHolder;
import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.ChessMoveEvent;

import java.util.List;
import java.util.Set;

/**
 *     _O
 *    / //\
 *   {     }
 *    \___/
 *    (___)
 *     |_|
 *    /   \
 *   (_____)
 *  (_______)
 *  /_______\
 */
public class Bishop extends Piece {
    public Bishop(Role role) {
        super(Type.BISHOP, role);
    }

    @Override
    public Set<CellIndex> getAvailableMoves(CellIndex pos,
                                            BoardHolder<Piece> board,
                                            List<ChessMoveEvent<Piece>> moveHistory) {
        //TODO: Implement stub
        return null;
    }
}
