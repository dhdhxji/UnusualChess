package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.BoardHolder;
import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;

import java.util.Set;

/**
 *      .::.
 *      _::_
 *    _/____\_
 *    \      /
 *     \____/
 *     (____)
 *      |  |
 *      |__|
 *     /    \
 *    (______)
 *   (________)
 *   /________\
 */
public class King extends Piece {
    public King(Role role) {
        super(Piece.Type.KING, role);
    }

    @Override
    public Set<CellIndex> getAvailableMoves(CellIndex pos, BoardHolder<Piece> board) {
        //TODO: Implement stub
        return null;
    }
}
