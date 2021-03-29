package com.example.unusualchess.board;

import com.example.unusualchess.board.pieces.Bishop;
import com.example.unusualchess.board.pieces.EmptyPiece;
import com.example.unusualchess.board.pieces.King;
import com.example.unusualchess.board.pieces.Knight;
import com.example.unusualchess.board.pieces.Pawn;
import com.example.unusualchess.board.pieces.Queen;
import com.example.unusualchess.board.pieces.Rook;
import com.example.unusualchess.common.MoveIntent;
import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.ChessInvalidMoveException;
import com.example.unusualchess.util.InvalidCellIndexException;
import com.example.unusualchess.util.InvalidPlayerException;

import java.util.Set;

public class ChessModel {
    /**
     * Perform move
     *
     * @param m move intent of player
     *
     * @throws ChessInvalidMoveException if src->dest is impossible move
     * @throws InvalidCellIndexException if src or dst is incorrect cell index
     */
    public void move(MoveIntent m)
            throws ChessInvalidMoveException, InvalidCellIndexException, InvalidPlayerException {
        //TODO: Implement stub
    }

    /**
     * Return all available move positions for a certain position
     *
     * @param pos position to get available moves for
     *
     * @return set of available moves
     */
    public Set<CellIndex> getAvailableMoves(CellIndex pos) {
        //TODO: Implement stub
        return null;
    }

    /**
     * Reset game, setup initial pieces configuration
     */
    public void reset() {
        //setup pawns
        for(int file = 0; file < BOARD_WIDTH; ++file) {
            _pieces[1][file] = new Pawn(Role.WHITE);
            _pieces[BOARD_WIDTH-2][file] = new Pawn(Role.BLACK);
        }

        //set rook`s
        _pieces[0][0] = new Rook(Role.WHITE);
        _pieces[0][BOARD_WIDTH-1] = new Rook(Role.WHITE);

        _pieces[BOARD_WIDTH-1][0] = new Rook(Role.WHITE);
        _pieces[BOARD_WIDTH-1][BOARD_WIDTH-1] = new Rook(Role.WHITE);

        //set knight`s
        _pieces[0][1] = new Knight(Role.WHITE);
        _pieces[0][BOARD_WIDTH-2] = new Knight(Role.WHITE);

        _pieces[BOARD_WIDTH-1][1] = new Bishop(Role.WHITE);
        _pieces[BOARD_WIDTH-1][BOARD_WIDTH-2] = new Bishop(Role.WHITE);

        //set bishop`s
        _pieces[0][2] = new Bishop(Role.WHITE);
        _pieces[0][BOARD_WIDTH-3] = new Bishop(Role.WHITE);

        _pieces[BOARD_WIDTH-1][2] = new Bishop(Role.WHITE);
        _pieces[BOARD_WIDTH-1][BOARD_WIDTH-3] = new Bishop(Role.WHITE);

        //set queen`s
        _pieces[0][3] = new Queen(Role.WHITE);
        _pieces[BOARD_WIDTH-1][3] = new Queen(Role.BLACK);

        //set kings
        _pieces[0][3] = new King(Role.WHITE);
        _pieces[BOARD_WIDTH-1][4] = new King(Role.BLACK);
    }





    public static final int BOARD_WIDTH = 8;

    private Piece[][] _pieces = new EmptyPiece[BOARD_WIDTH][BOARD_WIDTH];
}
