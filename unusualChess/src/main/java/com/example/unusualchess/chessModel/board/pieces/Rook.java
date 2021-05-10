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
 *  _  _  _
 * | || || |
 * |_______|
 * \__ ___ /
 *  |___|_|
 *  |_|___|
 *  |___|_|
 * (_______)
 * /_______\
 */
public class Rook extends Piece {
    public Rook(Role role) {
        super(Type.ROOK, role);
    }

    @Override
    public Set<CellIndex> getAvailableMoves(CellIndex pos,
                                            BoardHolder<Piece> board,
                                            List<ChessMoveEvent<Piece>> moveHistory) {
        Set<CellIndex> directions = new HashSet<>();
        for(CellIndex i: CellIndex.genBasisCellVectors()) {
            directions.add(i);
            directions.add(i.inverse());
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
