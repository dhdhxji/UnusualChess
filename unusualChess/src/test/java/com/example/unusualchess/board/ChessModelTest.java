package com.example.unusualchess.board;

import com.example.unusualchess.board.pieces.Pawn;
import com.example.unusualchess.common.MoveIntent;
import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.ChessMoveEvent;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChessModelTest {
    //General cases
    @Test
    public void testSimpleMove() throws Exception {
        ChessModel testModel = new ChessModel();
        MoveIntent move = new MoveIntent(
                Role.WHITE,
                new CellIndex('a', 2),
                new CellIndex('a', 4)
        );

        testModel.move(move);

        //Check moved piece
        assertEquals(new ChessMoveEvent<Piece>(
                new CellIndex('a', 2),
                new CellIndex('a', 4),
                0,
                new Pawn(Role.WHITE)
            ), testModel.getMoveHistoryFrom(-1).get(0));

        assertEquals(
                new Pawn(Role.WHITE),
                testModel.getCurrentState().get(new CellIndex('a', 4)));
    }

    //TODO: beat move
    //TODO: check ChessInvalidMoveException
    //TODO: check InvalidCellIndexException
    //TODO: check InvalidPlayerException
    //TODO: check beat move
    //TODO: check transform
    //TODO: check situation
    //TODO: check & mate situation
    //TODO: pawn transform
    //TODO: check board state updates
    //TODO: check last move history event seq number
    //TODO: cancel move



    //Move history check
    //TODO: Check is move added
    //TODO: Check is move revert interpreted normally
    //TODO: Check is piece moved after revert move
    //TODO: Getting history from certain position

}