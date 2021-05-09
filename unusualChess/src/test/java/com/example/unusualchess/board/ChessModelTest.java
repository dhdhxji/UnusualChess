package com.example.unusualchess.board;

import com.example.unusualchess.board.pieces.King;
import com.example.unusualchess.board.pieces.Pawn;
import com.example.unusualchess.board.pieces.Queen;
import com.example.unusualchess.common.MoveIntent;
import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.ChessInvalidMoveException;
import com.example.unusualchess.util.ChessMoveEvent;
import com.example.unusualchess.util.InvalidCellIndexException;
import com.example.unusualchess.util.InvalidPlayerException;

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

        //Check board state
        assertEquals(
                new Pawn(Role.WHITE),
                testModel.getCurrentState().get(new CellIndex('a', 4))
        );

        //Check moved piece in history
        assertEquals(new ChessMoveEvent<>(
                new CellIndex('a', 2),
                new CellIndex('a', 4),
                0,
                testModel.getCurrentState().get(new CellIndex('a', 4))
            ), testModel.getMoveHistory().getHistory(-1).get(0));
    }

    @Test
    public void testHistoryRefEdit() throws Exception{
        ChessModel testModel = new ChessModel();
        MoveIntent move = new MoveIntent(
                Role.WHITE,
                new CellIndex('a', 2),
                new CellIndex('a', 4)
        );

        testModel.move(move);

        //Edit history
        testModel.getMoveHistory().getHistory(-1).clear();

        //check is history edited
        assertFalse(testModel.getMoveHistory().getHistory(-1).isEmpty());
    }

    @Test
    public void testBeat() throws Exception {
        ChessModel testModel = new ChessModel();
        MoveIntent move = new MoveIntent(
                Role.WHITE,
                new CellIndex('a', 2),
                new CellIndex('a', 4)
        );
        testModel.move(move);

        move = new MoveIntent(
                Role.BLACK,
                new CellIndex('b', 7),
                new CellIndex('b', 5)
        );
        testModel.move(move);

        //beat move
        move = new MoveIntent(
                Role.WHITE,
                new CellIndex('a', 4),
                new CellIndex('b', 5)
        );
        testModel.move(move);

        assertEquals(new Pawn(Role.BLACK), testModel.getBeatenPieces().toArray()[0]);
        assertEquals(new Pawn(Role.WHITE),
                testModel.getCurrentState().get(new CellIndex('b', 5)));
        assertEquals(1, testModel.getBeatenPieces().size());
    }

    @Test
    public void testTransformMove() throws Exception{
        ChessModel testModel = new ChessModel() {
            @Override
            public BoardHolder<Piece> getInitialBoardSetup() {
                BoardHolder<Piece> initial = new BoardHolder<>(BOARD_WIDTH);
                initial.set(new CellIndex('a', 7),
                        new Pawn(Role.WHITE));

                return initial;
            }
        };

        MoveIntent move = new MoveIntent(
                Role.WHITE,
                new CellIndex('a', 7),
                new CellIndex('a', 8),
                new Queen(Role.WHITE)
        );
        testModel.move(move);

        assertEquals(
                new Queen(Role.WHITE),
                testModel.getCurrentState().get(new CellIndex('a', 8))
        );
    }

    @Test
    public void testCancelMove() throws Exception {
        ChessModel testModel = new ChessModel();
        MoveIntent move = new MoveIntent(
                Role.WHITE,
                new CellIndex('a', 2),
                new CellIndex('a', 4)
        );

        testModel.move(move);
        testModel.cancelLastMove();

        assertEquals(0, testModel.getMoveHistory().getShortHistory(-1).size());
        assertEquals(2, testModel.getMoveHistory().getHistory(-1).size());

        //Check is piece in old position
        assertEquals(new Pawn(Role.WHITE),
                testModel.getCurrentState().get(new CellIndex('a', 2)));
    }

    @Test
    public void testCancelMoveTransform() throws Exception {
        ChessModel testModel = new ChessModel() {
            @Override
            public BoardHolder<Piece> getInitialBoardSetup() {
                BoardHolder<Piece> initial = new BoardHolder<>(BOARD_WIDTH);
                initial.set(new CellIndex('a', 7),
                        new Pawn(Role.WHITE));

                return initial;
            }
        };

        MoveIntent move = new MoveIntent(
                Role.WHITE,
                new CellIndex('a', 7),
                new CellIndex('a', 8),
                new Queen(Role.WHITE)
        );

        testModel.move(move);
        testModel.cancelLastMove();

        assertEquals(0, testModel.getMoveHistory().getShortHistory(-1).size());

        //Check is piece in old position
        assertEquals(new Pawn(Role.WHITE),
                testModel.getCurrentState().get(new CellIndex('a', 7)));
    }

    @Test
    public void testCurrentStateMutable() {
        ChessModel testModel = new ChessModel();
        testModel.getCurrentState().set(new CellIndex('f', 4), new King(Role.WHITE));

        assertNull(testModel.getCurrentState().get(new CellIndex('f', 4)));
    }

    @Test(expected = ChessInvalidMoveException.class)
    public void testInvalidMove() throws Exception {
        ChessModel testModel = new ChessModel();
        MoveIntent move = new MoveIntent(
                Role.WHITE,
                new CellIndex('a', 2),
                new CellIndex('b', 3)
        );

        testModel.move(move);
    }

    @Test(expected = InvalidCellIndexException.class)
    public void testInvalidCellIndex() throws Exception {
        ChessModel testModel = new ChessModel();
        MoveIntent move = new MoveIntent(
                Role.WHITE,
                new CellIndex(8, 0),
                new CellIndex(0, 8)
        );

        testModel.move(move);
    }

    @Test(expected = InvalidPlayerException.class)
    public void testInvalidPlayer() throws Exception {
        ChessModel testModel = new ChessModel();
        MoveIntent move = new MoveIntent(
                Role.BLACK,
                new CellIndex('a', 2),
                new CellIndex('b', 3)
        );

        testModel.move(move);
    }

    @Test
    public void testCopyConstructor() throws Exception {
        ChessModel testModel = new ChessModel();
        MoveIntent move = new MoveIntent(
                Role.WHITE,
                new CellIndex('a', 2),
                new CellIndex('a', 4)
        );

        testModel.move(move);
        ChessModel testModelClone = new ChessModel(testModel);

        assertEquals(testModel, testModelClone);

        move = new MoveIntent(
                Role.BLACK,
                new CellIndex('b', 7),
                new CellIndex('b', 5)
        );
        testModelClone.move(move);

        assertNotEquals(testModel, testModelClone);
    }


    //TODO: check situation
    //TODO: check & mate situation
    //TODO: check board state updates

    //TODO: check & checkmate with required moves
    //TODO: try to move by enemy pieces
    //Move history check
    //TODO: Check is move added
    //TODO: Check is move revert interpreted normally
    //TODO: Check is piece moved after revert move
    //TODO: Getting history from certain position


}