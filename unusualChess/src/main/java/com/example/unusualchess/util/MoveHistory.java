package com.example.unusualchess.util;

import com.example.unusualchess.board.Piece;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class MoveHistory {

    /**
     * Get last action sequence number
     * @return last event sequence number, -1 if there no events
     */
    public int getLastMoveSeqNumber() {
        if(_moveHistory.size() == 0) {
            return -1;
        }

        return _moveHistory.get(_moveHistory.size()-1).getSeqNumber();
    }

    /**
     * Log into history that move has performed
     * @param rawEv raw move event without seqNumber set
     * @return MoveEvent which will be added to log
     */
    public ChessMoveEvent<Piece> addMove(ChessMoveEvent<Piece> rawEv) {
        ChessMoveEvent<Piece> target = new ChessMoveEvent<>(
                rawEv, getLastMoveSeqNumber() + 1);
        _moveHistory.add(target);

        return target;
    }

    /**
     * Log into history that last non-cancel move has cancelled
     * @return performed cancellation event
     */
    public List<ChessMoveEvent<Piece>> cancelLastMove() {
        List<ChessMoveEvent<Piece>> res = new LinkedList<>();

        List<ChessMoveEvent<Piece>> hist = getShortHistory(-1);
        ListIterator<ChessMoveEvent<Piece>> iterator = hist.listIterator(hist.size());
        while (iterator.hasPrevious()) {
            ChessMoveEvent<Piece> ev = iterator.previous();

            res.add(addMove(ev.getCancelMove()));
            if( !ev.isPreviousMoveBound() ) {
                break;
            }
        }

        return res;
    }

    /**
     * Determine is specific piece moved (based on reference)
     * @param p piece to check movement for (check by piece reference)
     * @return is piece moved
     */
    public boolean isPieceMoved(Piece p) {
        for(ChessMoveEvent<Piece> ev: getShortHistory(-1)) {
            if(ev.getPiece() == p || ev.getTransformFrom() == p) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get part of history after certain move sequence number.
     * Returns a copy of history list, but not history itself.
     * @param fromSeq start sequence number, -1 to fetch all history
     * @return part of history after certain move sequence number
     */
    public List<ChessMoveEvent<Piece>> getHistory(int fromSeq) {
        if(fromSeq == -1) {
            return new LinkedList<>(_moveHistory);
        }

        //Find start seq number
        int startIndex = 0;
        while(startIndex < _moveHistory.size() &&
                _moveHistory.get(startIndex).getSeqNumber() <= fromSeq) {
            startIndex++;
        }

        return new LinkedList<>(_moveHistory.subList(startIndex, _moveHistory.size()));
    }

    /**
     * Get part of history after certain move sequence number, without cancel moves.
     * Returns a copy of history list, but not history itself.
     * @param fromSeq start sequence number, -1 to fetch all history
     * @return part of history after certain move sequence number
     */
    public  List<ChessMoveEvent<Piece>> getShortHistory(int fromSeq) {
        List<ChessMoveEvent<Piece>> hist = getHistory(fromSeq);

        //Traverse by history and remove cancellation & cancelled moves
        ListIterator<ChessMoveEvent<Piece>> iterator = hist.listIterator();
        while(iterator.hasNext()) {
            ChessMoveEvent<Piece> ev = iterator.next();

            if(ev.isCancelMove()) {
                iterator.remove();
                iterator.previous();
                iterator.remove();
            }
        }

        return hist;
    }

    private final List<ChessMoveEvent<Piece>> _moveHistory = new LinkedList<>();
}
