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
        Set<CellIndex> directions = new HashSet<>();
        for(CellIndex base: CellIndex.genBasisCellVectors()) {
            Set<CellIndex> derivatives = CellIndex.genBasisCellVectors();
            derivatives.remove(base);
            for(CellIndex derivative: derivatives) {
                directions.add(base.add(derivative));
                directions.add(base.add(derivative.inverse()));
                directions.add(base.inverse().add(derivative));
                directions.add(base.inverse().add(derivative.inverse()));
            }
        }

        //For every direction generate all possible moves
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
