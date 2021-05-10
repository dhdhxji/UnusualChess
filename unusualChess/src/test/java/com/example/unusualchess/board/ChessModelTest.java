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

import java.util.HashSet;
import java.util.Set;

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

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |BK |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  7 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   |   |WQ |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |WK-->  |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testCheckSituationDetermining() throws Exception{
        ChessModel testModel = new ChessModel() {
            @Override
            public BoardHolder<Piece> getInitialBoardSetup() {
                BoardHolder<Piece> initialBoard = new BoardHolder<>(BOARD_WIDTH);

                initialBoard.set(new CellIndex('d', 8), new King(Role.BLACK));
                initialBoard.set(new CellIndex('d', 4), new Queen(Role.WHITE));
                initialBoard.set(new CellIndex('f', 1), new King(Role.WHITE));

                return initialBoard;
            }
        };

        //is check for white (no)
        assertFalse(testModel.isCheckSituation());
        assertEquals(ChessModel.Situation.PROGRESS, testModel.getCurrentSituation());

        MoveIntent m = new MoveIntent(Role.WHITE,
                new CellIndex('f', 1),
                new CellIndex('g',1));
        testModel.move(m);

        //Is check for black (yes)
        assertTrue(testModel.isCheckSituation());
        assertEquals(ChessModel.Situation.CHECK, testModel.getCurrentSituation());

        assertFalse(testModel.isCheckSituation(Role.WHITE));
        assertTrue(testModel.isCheckSituation(Role.BLACK));
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   | * |BK | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  7 |   |   | * |   | * |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   |   |WQ |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testCheckSituationAvailableMoves() {
        ChessModel testModel = new ChessModel() {
            @Override
            public BoardHolder<Piece> getInitialBoardSetup() {
                BoardHolder<Piece> initialBoard = new BoardHolder<>(BOARD_WIDTH);

                initialBoard.set(new CellIndex('d', 8), new King(Role.BLACK));
                initialBoard.set(new CellIndex('d', 4), new Queen(Role.WHITE));

                return initialBoard;
            }
        };

        Set<CellIndex> expectedAvailableMoves = new HashSet<>();
        expectedAvailableMoves.add(new CellIndex('c', 8));
        expectedAvailableMoves.add(new CellIndex('c', 7));
        expectedAvailableMoves.add(new CellIndex('e', 8));
        expectedAvailableMoves.add(new CellIndex('e', 7));

        assertEquals(
                expectedAvailableMoves,
                testModel.getAvailableMoves(new CellIndex('d', 8))
        );
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  7 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |BW |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |BQ |   |   |WK |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testCheckMateSituationDetermining() {
        ChessModel testModel = new ChessModel() {
            @Override
            public BoardHolder<Piece> getInitialBoardSetup() {
                BoardHolder<Piece> initialBoard = new BoardHolder<>(BOARD_WIDTH);

                initialBoard.set(new CellIndex('d', 1), new King(Role.WHITE));
                initialBoard.set(new CellIndex('a', 1), new Queen(Role.BLACK));
                initialBoard.set(new CellIndex('a', 2), new Queen(Role.BLACK));

                return initialBoard;
            }
        };

        assertTrue(testModel.isCheckMateSituation());
        assertEquals(ChessModel.Situation.CHECKMATE, testModel.getCurrentSituation());
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |WQ |   |   |BK |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  7 |WQ |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testCheckMateSituationAvailableMoves() {
        ChessModel testModel = new ChessModel() {
            @Override
            public BoardHolder<Piece> getInitialBoardSetup() {
                BoardHolder<Piece> initialBoard = new BoardHolder<>(BOARD_WIDTH);

                initialBoard.set(new CellIndex('d', 8), new King(Role.BLACK));
                initialBoard.set(new CellIndex('a', 8), new Queen(Role.WHITE));
                initialBoard.set(new CellIndex('a', 7), new Queen(Role.WHITE));

                return initialBoard;
            }
        };

        Set<CellIndex> expectedAvailableMoves = new HashSet<>();

        assertEquals(
                expectedAvailableMoves,
                testModel.getAvailableMoves(new CellIndex('d', 8))
        );
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  7 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   |BQ |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |WK |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testPatSituation() {
        ChessModel testModel = new ChessModel() {
            @Override
            public BoardHolder<Piece> getInitialBoardSetup() {
                BoardHolder<Piece> initialBoard = new BoardHolder<>(BOARD_WIDTH);

                initialBoard.set(new CellIndex('a', 1), new King(Role.WHITE));
                initialBoard.set(new CellIndex('c', 2), new Queen(Role.BLACK));

                return initialBoard;
            }
        };

        assertTrue(testModel.isPatSituation());
        assertEquals(ChessModel.Situation.PAT, testModel.getCurrentSituation());
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  7 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   |BQ |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |WK |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testPatAvailableMoves() {
        ChessModel testModel = new ChessModel() {
            @Override
            public BoardHolder<Piece> getInitialBoardSetup() {
                BoardHolder<Piece> initialBoard = new BoardHolder<>(BOARD_WIDTH);

                initialBoard.set(new CellIndex('a', 1), new King(Role.WHITE));
                initialBoard.set(new CellIndex('c', 2), new Queen(Role.BLACK));

                return initialBoard;
            }
        };

        Set<CellIndex> expectedAvailableMoves = new HashSet<>();

        assertEquals(
                expectedAvailableMoves,
                testModel.getAvailableMoves(new CellIndex('a', 1))
        );
    }
}