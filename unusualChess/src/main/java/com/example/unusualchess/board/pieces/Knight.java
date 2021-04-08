package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.BoardHolder;
import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;

import java.util.Set;

/**
 *    ^^__
 *   /  - \_
 * <|    __<
 * <|    \
 * <|     \
 * <|______\
 *  _|____|_
 * (________)
 * /________\
 */
public class Knight extends Piece {
    public Knight(Role role) {
        super(Type.KNIGHT, role);
    }

    @Override
    public Set<CellIndex> getAvailableMoves(CellIndex pos, BoardHolder<Piece> board) {
        //TODO: Implement stub
        return null;
    }
}
