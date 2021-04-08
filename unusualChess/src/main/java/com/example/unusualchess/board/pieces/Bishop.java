package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.BoardHolder;
import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;

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
    public Set<CellIndex> getAvailableMoves(CellIndex pos, BoardHolder<Piece> board) {
        //TODO: Implement stub
        return null;
    }
}
