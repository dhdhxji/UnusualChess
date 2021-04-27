package com.example.unusualchess.board;

import com.example.unusualchess.board.pieces.Bishop;
import com.example.unusualchess.board.pieces.King;
import com.example.unusualchess.board.pieces.Knight;
import com.example.unusualchess.board.pieces.Pawn;
import com.example.unusualchess.board.pieces.Queen;
import com.example.unusualchess.board.pieces.Rook;
import com.example.unusualchess.common.MoveIntent;
import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.ChessInvalidMoveException;
import com.example.unusualchess.util.ChessModelListenerSupport;
import com.example.unusualchess.util.ChessMoveEvent;
import com.example.unusualchess.util.InvalidCellIndexException;
import com.example.unusualchess.util.InvalidPlayerException;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ChessModel extends ChessModelListenerSupport {
    /**
     * Perform move
     *
     * @param m move intent of player
     *
     * @throws ChessInvalidMoveException if src->dest is impossible move
     * @throws InvalidCellIndexException if src or dst is incorrect cell index
     * @throws InvalidPlayerException if it's not this player's turn
     */
    public void move(MoveIntent m)
            throws ChessInvalidMoveException, InvalidCellIndexException, InvalidPlayerException {
        //Check is move possible
        if(m.getRole() != _currentPlayer) {
            throw new InvalidPlayerException("Now is " + _currentPlayer + " players`s turn");
        }

        if( !isCellIndexValid(m.getSrc())) {
            throw new InvalidCellIndexException(m.getSrc());
        }

        if( !isCellIndexValid(m.getDst())) {
            throw new InvalidCellIndexException(m.getDst());
        }

        //TODO: uncomment check
        /*Set<CellIndex> availableMoves = getAvailableMoves(m.getSrc());
        if( !availableMoves.contains(m.getDst()) ) {
            throw new ChessInvalidMoveException(m.getSrc(), m.getDst());
        }*/

        //Perform move
        int moveSeqNumber = getLastMoveNumber();
        ChessMoveEvent<Piece> moveEvent = new ChessMoveEvent<>(m.getSrc(),
                m.getDst(),
                moveSeqNumber,
                _currentBoardState.get(m.getSrc()) );

        _moveHistory.add(moveEvent);
        _currentBoardState.set( m.getDst(), _currentBoardState.get(m.getSrc()) );
        _currentBoardState.set(m.getSrc(), null);

        movePerformed(moveEvent);

        //Change next move player
        if(_currentPlayer == Role.WHITE) {
            _currentPlayer = Role.BLACK;
        } else {
            _currentPlayer = Role.WHITE;
        }
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
        _currentBoardState = getInitialBoardSetup();
    }

    public static BoardHolder<Piece> getInitialBoardSetup() {
        BoardHolder<Piece> initial = new BoardHolder<>(BOARD_WIDTH);

        //setup pawns
        for(int file = 0; file < BOARD_WIDTH; ++file) {
            initial.set(new CellIndex(file, 1), new Pawn(Role.WHITE));
            initial.set(new CellIndex(file, BOARD_WIDTH-2), new Pawn(Role.BLACK));
        }

        //set rook`s
        initial.set(new CellIndex(0, 0), new Rook(Role.WHITE));
        initial.set(new CellIndex(BOARD_WIDTH-1, 0), new Rook(Role.WHITE));

        initial.set(new CellIndex(0, BOARD_WIDTH-1), new Rook(Role.BLACK));
        initial.set(new CellIndex(BOARD_WIDTH-1, BOARD_WIDTH-1), new Rook(Role.BLACK));

        //set knight`s
        initial.set(new CellIndex(1, 0), new Knight(Role.WHITE));
        initial.set(new CellIndex(BOARD_WIDTH-2 ,0), new Knight(Role.WHITE));

        initial.set(new CellIndex(1, BOARD_WIDTH-1), new Knight(Role.BLACK));
        initial.set(new CellIndex(BOARD_WIDTH-2, BOARD_WIDTH-1), new Knight(Role.BLACK));

        //set bishop`s
        initial.set(new CellIndex(2, 0), new Bishop(Role.WHITE));
        initial.set(new CellIndex(BOARD_WIDTH-3, 0), new Bishop(Role.WHITE));

        initial.set(new CellIndex(2, BOARD_WIDTH-1), new Bishop(Role.BLACK));
        initial.set(new CellIndex(BOARD_WIDTH-3, BOARD_WIDTH-1), new Bishop(Role.BLACK));

        //set queen`s
        initial.set(new CellIndex(3,0), new Queen(Role.WHITE));
        initial.set(new CellIndex(3, BOARD_WIDTH-1), new Queen(Role.BLACK));

        //set kings
        initial.set(new CellIndex(4, 0), new King(Role.WHITE));
        initial.set(new CellIndex(4, BOARD_WIDTH-1), new King(Role.BLACK));

        return initial;
    }

    /**
     * Get the current game state
     * @return current game state
     */
    public BoardHolder<Piece> getCurrentState() {
        return _currentBoardState;
    }

    /**
     * Get the last move sequence number
     * @return last move sequence number, -1 if there is no movements
     */
    public int getLastMoveNumber() {
        if(_moveHistory.size() == 0) {
            return -1;
        }

        return _moveHistory.get(_moveHistory.size()-1).getSeqNumber();
    }

    /**
     * Get part of history after certain move sequence number
     * @param startMoveSeqNumber start sequence number
     * @return part of history after certain move sequence number
     */
    public List<ChessMoveEvent<Piece>> getMoveHistoryFrom(int startMoveSeqNumber) {
        //Find start seq number
        int startIndex = 0;
        while(startIndex < _moveHistory.size() &&
                _moveHistory.get(startIndex).getSeqNumber() <= startMoveSeqNumber) {
            startIndex++;
        }

        return _moveHistory.subList(startIndex, _moveHistory.size()-1);
    }

    /**
     * Check is position valid
     * @param pos position to validate
     * @return is position valid
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isCellIndexValid(CellIndex pos) {
        return (pos.getFile() < BOARD_WIDTH ) && (pos.getRank() < BOARD_WIDTH) &&
                (pos.getRank() >= 0) && (pos.getFile() >= 0);
    }





    public static final int BOARD_WIDTH = 8;
    private BoardHolder<Piece> _currentBoardState = new BoardHolder<>(BOARD_WIDTH);
    private List<ChessMoveEvent<Piece>> _moveHistory = new LinkedList<>();
    private Role _currentPlayer = Role.WHITE;
}
