package com.example.unusualchess.chessModel.board.pieces;

import com.example.unusualchess.chessModel.board.BoardHolder;
import com.example.unusualchess.chessModel.board.CellIndex;
import com.example.unusualchess.chessModel.board.Piece;
import com.example.unusualchess.chessModel.common.Role;
import com.example.unusualchess.chessModel.common.ChessMoveEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *    _()_
 *  _/____\_
 *  \      /
 *   \____/
 *   (____)
 *    |  |
 *    |__|
 *   /    \
 *  (______)
 * (________)
 * /________\
 */
public class Queen extends Piece {
    public Queen(Role role) {
        super(Type.QUEEN, role);
    }

    @Override
    public Set<CellIndex> getAvailableMoves(CellIndex pos,
                                            BoardHolder<Piece> board,
                                            List<ChessMoveEvent<Piece>> moveHistory) {
        Set<CellIndex> directions = new HashSet<>();
        for(CellIndex base: CellIndex.genBasisCellVectors()) {
            directions.add(base);
            directions.add(base.inverse());

            Set<CellIndex> derivatives = CellIndex.genBasisCellVectors();
            derivatives.remove(base);

            for(CellIndex derivative: derivatives) {
                directions.add(base.add(derivative));
                directions.add(base.add(derivative.inverse()));
                directions.add(base.inverse().add(derivative));
                directions.add(base.inverse().add(derivative.inverse()));
            }
        }

        Set<CellIndex> moves = new HashSet<>();
        for(CellIndex d: directions) {
            CellIndex dst = pos.add(d);

            while(isMovePossible(dst, board) || isBeatPossible(dst, board)) {
                moves.add(dst);

                if(isBeatPossible(dst, board)) {
                    break;
                }

                dst = dst.add(d);
            }
        }

        return moves;
    }
}
