package com.example.unusualchess.chessModel.board.pieces;

import com.example.unusualchess.chessModel.board.BoardHolder;
import com.example.unusualchess.chessModel.board.CellIndex;
import com.example.unusualchess.chessModel.board.Piece;
import com.example.unusualchess.chessModel.common.Role;
import com.example.unusualchess.chessModel.common.ChessMoveEvent;

import java.util.List;
import java.util.Set;

public class EmptyPiece extends Piece {
    public EmptyPiece() {
        super(Type.EMPTY, Role.NONE);
    }

    @Override
    public Set<CellIndex> getAvailableMoves(CellIndex pos,
                                            BoardHolder<Piece> board,
                                            List<ChessMoveEvent<Piece>> moveHistory) {
        return null;
    }
}
