package com.example.unusualchess.board;

import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.ChessMoveEvent;

import java.util.List;
import java.util.Set;

public abstract class Piece {
    public enum Type {
        EMPTY,
        PAWN,
        ROOK,
        BISHOP,
        KNIGHT,
        KING,
        QUEEN
    }

    public Piece(Type type, Role role) {
        _type = type;
        _role = role;
    }

    /**
     * Get all available moves/beats for certain position and board situation
     * @param pos current piece location
     * @param board current board setup
     * @param moveHistory history of current game
     * @return all available moves for this piece
     */
    abstract public Set<CellIndex> getAvailableMoves(CellIndex pos,
                                                     BoardHolder<Piece> board,
                                                     List<ChessMoveEvent<Piece>> moveHistory);

    /**
     * Check is current piece was moved
     * @param moveHistory history of current game
     * @return true is piece was moved, false otherwise
     */
    public final boolean isPieceMoved(List<ChessMoveEvent<Piece>> moveHistory) {
        for(ChessMoveEvent<Piece> m: moveHistory) {
            if(m.getPiece() == this) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get type of current piece
     * @return type of current piece
     */
    public final Type getType() {
        return _type;
    }

    /**
     * Get role of current piece
     * @return role of current piece
     */
    public final Role getRole() {
        return _role;
    }

    private final Type _type;
    private final Role _role;
}
