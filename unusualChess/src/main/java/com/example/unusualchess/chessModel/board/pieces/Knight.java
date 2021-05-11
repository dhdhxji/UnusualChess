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
                                            List<ChessMoveEvent<Piece>> moveHistory) {
        Set<CellIndex> moveVectors = new HashSet<>();
        for(CellIndex base: CellIndex.genBasisCellVectors()) {
            Set<CellIndex> deviations = CellIndex.genBasisCellVectors();
            deviations.remove(base);

            for(CellIndex deviation: deviations) {
                moveVectors.add(base.add(base).add(deviation));
                moveVectors.add(base.add(base).add(deviation.inverse()));
            }
        }

        //complete available moves by inversion
        Set<CellIndex> complementMoves = new HashSet<>();
        for(CellIndex move: moveVectors) {
            complementMoves.add(move.inverse());
        }

        moveVectors.addAll(complementMoves);

        Set<CellIndex> moves = new HashSet<>();
        for(CellIndex vec: moveVectors) {
            CellIndex dst = pos.add(vec);
            if(isMovePossible(dst, board) || isBeatPossible(dst, board)) {
                moves.add(dst);
            }
        }

        return moves;
    }
}
