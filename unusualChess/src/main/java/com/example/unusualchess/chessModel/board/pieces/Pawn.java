package com.example.unusualchess.chessModel.board.pieces;

import com.example.unusualchess.chessModel.board.BoardHolder;
import com.example.unusualchess.chessModel.board.CellIndex;
import com.example.unusualchess.chessModel.board.Piece;
import com.example.unusualchess.chessModel.common.MoveIntent;
import com.example.unusualchess.chessModel.common.Role;
import com.example.unusualchess.chessModel.common.CellIndexFilters.CellIndexDirectionFilter;
import com.example.unusualchess.chessModel.common.CellIndexFilter;
import com.example.unusualchess.chessModel.common.ChessMoveEvent;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *     _
 *    (_)
 *   (___)
 *   _|_|_
 *  (_____)
 *  /_____\
 */
public class Pawn extends Piece {
    public Pawn(Role role) {
        super(Type.PAWN, role);
    }

    @Override
    public Set<CellIndex> getAvailableMoves(CellIndex pos,
                                            BoardHolder<Piece> board,
                                            List<ChessMoveEvent<Piece>> moveHistory) {
        boolean moved = isPieceMoved(moveHistory);

        CellIndexDirectionFilter.Direction moveDirection =
                getRole() == Role.WHITE ?
                        CellIndexDirectionFilter.Direction.UP:
                        CellIndexDirectionFilter.Direction.DOWN;

        CellIndexFilter upFilter = new CellIndexDirectionFilter(moveDirection);
        CellIndexFilter sidesFilter = new CellIndexDirectionFilter(
                CellIndexDirectionFilter.Direction.LEFT,
                CellIndexDirectionFilter.Direction.RIGHT
        );

        Set<CellIndex> axisVectors = new HashSet<>();
        for(CellIndex i: CellIndex.genBasisCellVectors()) {
            axisVectors.add(i);
            axisVectors.add(i.inverse());
        }

        Set<CellIndex> moveDirVectors = upFilter.filter(axisVectors);
        Set<CellIndex> sideDirVectors = sidesFilter.filter(axisVectors);

        //Calc available moves
        Set<CellIndex> mMoves = getAvailableMMoves(pos, board, moveDirVectors, moved);

        //Check beat vectors for certain Role
        Set<CellIndex> bMoves =
                getAvailableBMoves(pos, board, moveDirVectors, sideDirVectors);

        Set<CellIndex> moves = new HashSet<>(mMoves);
        moves.addAll(bMoves);

        return moves;
    }

    @Override
    public Set<Piece> getAvailableTransformations(MoveIntent move, BoardHolder<Piece> board) {
        Set<Piece> transforms = super.getAvailableTransformations(move, board);

        if(getRole() == Role.WHITE && move.getDst().getRank() == board.getWidth()-1 ||
           getRole() == Role.BLACK && move.getDst().getRank() == 0) {
            transforms.addAll(genTransformations(getRole()));
        }

        return transforms;
    }

    private Set<CellIndex> getAvailableMMoves(CellIndex pos,
                                              BoardHolder<Piece> board,
                                              Set<CellIndex> moveDirVectors,
                                              boolean moved) {
        Set<CellIndex> moves = new HashSet<>();

        //Check move vectors
        //remove 1st step invalid moves
        Set<CellIndex> moveDirections = new HashSet<>(moveDirVectors);

        Iterator<CellIndex> dirIterator = moveDirections.iterator();
        while(dirIterator.hasNext()) {
            CellIndex dst = pos.add(dirIterator.next());
            if( !isMovePossible(dst, board) ) {
                dirIterator.remove();
            } else {
                moves.add(dst);
            }
        }

        //Calculate all possible moves by 1st step result
        if( !moved) {
            for (CellIndex moveDirection1 : moveDirections) {
                for(CellIndex moveDirection2: moveDirections) {
                    CellIndex dst = moveDirection1.add(moveDirection2).add(pos);

                    if (isMovePossible(dst, board)) {
                        moves.add(dst);
                    }
                }
            }
        }

        return moves;
    }

    private Set<CellIndex> getAvailableBMoves(CellIndex pos,
                                              BoardHolder<Piece> board,
                                              Set<CellIndex> moveDirections,
                                              Set<CellIndex> sideDirections) {
        Set<CellIndex> moves = new HashSet<>();

        for(CellIndex m: moveDirections) {
            for(CellIndex s: sideDirections) {
                CellIndex dst = pos.add(m).add(s);

                if(isBeatPossible( dst, board) ) {
                    moves.add(dst);
                }
            }
        }

        return moves;
    }

    /**
     * Generate transformation pieces for Pawn
     * @param r role to generate transformation pieces for
     * @return generated transformation pieces
     */
    private Set<Piece> genTransformations(Role r) {
        Set<Piece> p = new HashSet<>();

        p.add(new Queen(r));
        p.add(new Knight(r));
        p.add(new Bishop(r));
        p.add(new Rook(r));

        return p;
    }
}
