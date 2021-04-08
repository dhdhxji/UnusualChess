package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.BoardHolder;
import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.ChessMoveEvent;

import java.util.List;
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
    public Set<CellIndex> getAvailableMoves(CellIndex pos,
                                            BoardHolder<Piece> board,
                                            List<ChessMoveEvent> moveHistory) {
        //TODO: Implement stub
        return null;
    }
}
