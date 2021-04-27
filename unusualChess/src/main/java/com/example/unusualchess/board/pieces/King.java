package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.BoardHolder;
import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.MoveIntent;
import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.CellIndexCoaxialFilter;
import com.example.unusualchess.util.ChessMoveEvent;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *      .::.
 *      _::_
 *    _/____\_
 *    \      /
 *     \____/
 *     (____)
 *      |  |
 *      |__|
 *     /    \
 *    (______)
 *   (________)
 *   /________\
 */
public class King extends Piece {
    public King(Role role) {
        super(Piece.Type.KING, role);
    }

    @Override
    public Set<CellIndex> getAvailableMoves(CellIndex pos,
                                            BoardHolder<Piece> board,
                                            List<ChessMoveEvent<Piece>> moveHistory) {
        Set<CellIndex> moveVectors = new HashSet<>();

        for(CellIndex base: CellIndex.genBasisCellVectors()) {
            moveVectors.add(base);
            moveVectors.add(base.inverse());

            Set<CellIndex> derivatives = CellIndex.genBasisCellVectors();
            derivatives.remove(base);

            for(CellIndex derivative: derivatives) {
                moveVectors.add(base.add(derivative));
                moveVectors.add(base.inverse().add(derivative));
                moveVectors.add(base.add(derivative.inverse()));
                moveVectors.add(base.inverse().add(derivative.inverse()));
            }
        }

        Set<CellIndex> moves = new HashSet<>();
        for(CellIndex vec: moveVectors) {
            CellIndex dst = vec.add(pos);

            if(isMovePossible(dst, board) || isBeatPossible(dst, board)) {
                moves.add(dst);
            }
        }

        moves.addAll(getCastlingMoves(pos, board, moveHistory));

        return moves;
    }

    @Override
    public List<MoveIntent> getRequiredMoves(MoveIntent move,
                                             BoardHolder<Piece> board,
                                             List<ChessMoveEvent<Piece>> moveHistory) {
        List<MoveIntent> requiredMoves = super.getRequiredMoves(move, board, moveHistory);
        Set<CellIndex> castlingMoves = getCastlingMoves(move.getSrc(), board, moveHistory);

        if( !castlingMoves.contains(move.getDst()) ) {
            return requiredMoves;
        }

        //It is castling move, add rook should move
        CellIndex kingMoveDirection = move.getDst().sub(move.getSrc()).manhattanNormalize();

        //Moving in King direction before Rook located
        CellIndex rookSrc = move.getDst().add(kingMoveDirection);
        while(  board.get(rookSrc) == null ||
                !board.get(rookSrc).equals((new Rook(getRole())))) {
            rookSrc = rookSrc.add(kingMoveDirection);
        }

        CellIndex rookMoveDirection = kingMoveDirection.inverse();
        CellIndex rookDst = move.getDst().add(rookMoveDirection);

        requiredMoves.add(new MoveIntent( getRole(), rookSrc, rookDst ));

        return requiredMoves;
    }

    private Set<CellIndex> getCastlingMoves(CellIndex pos,
                                            BoardHolder<Piece> board,
                                            List<ChessMoveEvent<Piece>> moveHistory) {
        Set<CellIndex> castlingMoves = new HashSet<>();

        //check is king moved
        if(isPieceMoved(moveHistory)) {
            return castlingMoves;
        }

        //find unmoved rook`s
        Set<CellIndex> rookPos = board.findPiece(new Rook(getRole()));

        //check is rooks moved
        Iterator<CellIndex> i = rookPos.iterator();
        while(i.hasNext()) {
            Piece p = board.get(i.next());
            if(p.isPieceMoved(moveHistory)) {i.remove();}
        }


        rookPos = new CellIndexCoaxialFilter(pos).filter(rookPos);
        if(rookPos.isEmpty()) {return castlingMoves;}

        //check path from king to rook`s (is there is a barrier to these rooks)
        Iterator<CellIndex> iter = rookPos.iterator();
        while(iter.hasNext()) {
            CellIndex rook = iter.next();

            CellIndex d = rook.sub(pos).manhattanNormalize();
            CellIndex dst = pos.add(d);
            while(!dst.equals(rook)) {
                if( !isMovePossible(dst, board) ) {
                    iter.remove();
                    break;
                }

                dst = dst.add(d);
            }
        }

        //At this point leaves only these rooks, with which can be done castling move
        for(CellIndex r: rookPos) {
            CellIndex d = r.sub(pos).manhattanNormalize();
            castlingMoves.add(
                    pos.add(d).add(d)
            );
        }

        return castlingMoves;
    }
}
