package com.example.unusualchess.board;

import com.example.unusualchess.util.ChessInvalidMoveException;
import com.example.unusualchess.util.InvalidCellIndexException;

import java.util.Set;

public class ChessModel {
    /**
     * Perform move
     *
     * @param src source position of piece
     * @param dest destination position of piece
     *
     * @throws ChessInvalidMoveException if src->dest is impossible move
     * @throws InvalidCellIndexException if src or dst is incorrect cell index
     */
    public void move(CellIndex src, CellIndex dest)
            throws ChessInvalidMoveException, InvalidCellIndexException {
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
        //TODO: Implement stub
    }
}
