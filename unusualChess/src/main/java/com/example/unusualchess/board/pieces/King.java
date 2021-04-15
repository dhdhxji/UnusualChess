package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.BoardHolder;
import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.ChessMoveEvent;

import java.util.HashSet;
import java.util.List;
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
    public Set<CellIndex> getAvailableMoves(CellIndex pos,
                                            BoardHolder<Piece> board,
                                            List<ChessMoveEvent<Piece>> moveHistory) {
        Set<CellIndex> moveVectors = new HashSet<>();

        for(CellIndex base: CellIndex.genBasisCellVectors()) {
            moveVectors.add(base);
            moveVectors.add(base.inverse());

            Set<CellIndex> derivatives = CellIndex.genBasisCellVectors();
            derivatives.remove(base);

            for(CellIndex derivative: derivatives) {
                moveVectors.add(base.add(derivative));
                moveVectors.add(base.inverse().add(derivative));
                moveVectors.add(base.add(derivative.inverse()));
                moveVectors.add(base.inverse().add(derivative.inverse()));
            }
        }

        Set<CellIndex> moves = new HashSet<>();
        for(CellIndex vec: moveVectors) {
            CellIndex dst = vec.add(pos);

            if(isMovePossible(dst, board) || isBeatPossible(dst, board)) {
                moves.add(dst);
            }
        }

        return moves;
    }
}
