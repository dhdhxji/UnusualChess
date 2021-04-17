package com.example.unusualchess.board.pieces;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.board.PieceTestCommonUtils;
import com.example.unusualchess.common.MoveIntent;
import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.ChessMoveEvent;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class PawnTest {
    /*
     *  TEST FIRST MOVE
     */

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *      -   -   -   -   -   -   -   -       * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *  4 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testWhiteStartMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('D', 2)
        );

        //Set up allowed moves
        util.allowedMoves.add(new CellIndex('D', 3));
        util.allowedMoves.add(new CellIndex('D', 4));

        util.test();
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   | P |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  5 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *      -   -   -   -   -   -   -   -
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testBlackStartMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.BLACK),
                new CellIndex('D', 7)
        );

        //Set up allowed moves
        util.allowedMoves.add(new CellIndex('D', 6));
        util.allowedMoves.add(new CellIndex('D', 5));

        util.test();
    }

    /*
     * TEST NOT FIRST MOVE
     */

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   | * |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *      -   -   -   -   -   -   -   -
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testWhiteNotFirstMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('D', 6)
        );

        util.movesHistory.add(new ChessMoveEvent<>(
                new CellIndex('D', 5),
                new CellIndex('D', 6),
                0,
                util.testPiece
        ));

        //Set up allowed moves
        util.allowedMoves.add(new CellIndex('D', 7));

        util.test();
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *      -   -   -   -   -   -   -   -       * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testBlackNotFirstMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.BLACK),
                new CellIndex('D', 3)
        );

        util.movesHistory.add(new ChessMoveEvent<>(
                new CellIndex('D', 4),
                new CellIndex('D', 3),
                0,
                util.testPiece
        ));

        //Set up allowed moves
        util.allowedMoves.add(new CellIndex('D', 2));

        util.test();
    }

    /*
     *  Move with environment
     */

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *      -   -   -   -   -   -   -   -       * - allowed moves
     *    +---+---+---+---+---+---+---+---+     B - barrier pieces
     *  4 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   | B | * | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | B | P | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   | B | B | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testWhiteEnvMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('D', 2)
        );

        //Set up environment
        util.board.set(new CellIndex('C', 1), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('C', 2), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('C', 3), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('D', 1), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('E', 1), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('E', 2), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('E', 3), new Pawn(Role.WHITE));


        //Set up allowed moves
        util.allowedMoves.add(new CellIndex('D', 3));
        util.allowedMoves.add(new CellIndex('D', 4));

        util.test();
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *      -   -   -   -   -   -   -   -       * - allowed moves
     *    +---+---+---+---+---+---+---+---+     B - barrier pieces
     *  4 |   |   |   | B |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   | B | * | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | B | P | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   | B | B | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testWhiteEnvRestrictedMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('D', 2)
        );

        //Set up environment
        util.board.set(new CellIndex('C', 1), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('C', 2), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('C', 3), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('D', 1), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('D', 4), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('E', 1), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('E', 2), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('E', 3), new Pawn(Role.WHITE));


        //Set up allowed moves
        util.allowedMoves.add(new CellIndex('D', 3));

        util.test();
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *      -   -   -   -   -   -   -   -       * - allowed moves
     *    +---+---+---+---+---+---+---+---+     B - barrier pieces
     *  4 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   | B | B | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | B | P | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   | B | B | B |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testWhiteEnvNoMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('D', 2)
        );

        //Set up environment
        util.board.set(new CellIndex('C', 1), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('C', 2), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('C', 3), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('D', 1), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('D', 3), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('E', 1), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('E', 2), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('E', 3), new Pawn(Role.WHITE));


        //There is no allowed moves

        util.test();
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *      -   -   -   -   -   -   -   -       * - allowed moves
     *    +---+---+---+---+---+---+---+---+     B - barrier pieces
     *  3 |   |   |   | B |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testWhiteNoEnvNoMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('D', 2)
        );

        //Set up environment
        util.board.set(new CellIndex('D', 3), new Pawn(Role.WHITE));

        //There is no allowed moves

        util.test();
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *      -   -   -   -   -   -   -   -       * - allowed moves
     *    +---+---+---+---+---+---+---+---+     B - barrier pieces
     *  4 |   |   |   | B |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testWhiteNoEnvRestrictedMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('D', 2)
        );

        //Set up environment
        util.board.set(new CellIndex('D', 4), new Pawn(Role.WHITE));


        //Set up allowed moves
        util.allowedMoves.add(new CellIndex('D', 3));

        util.test();
    }

    /*
     * Beat moves
     */

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *      -   -   -   -   -   -   -   -       * - allowed moves
     *    +---+---+---+---+---+---+---+---+     + - enemy pieces, that can be beaten
     *  4 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  3 |   |   | + | * | + |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testWhiteBeatMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('D', 2)
        );

        //Set up environment
        util.board.set(new CellIndex('C', 3), new Pawn(Role.BLACK));
        util.board.set(new CellIndex('E', 3), new Pawn(Role.BLACK));


        //Set up allowed moves
        util.allowedMoves.add(new CellIndex('D', 3));
        util.allowedMoves.add(new CellIndex('C', 3));
        util.allowedMoves.add(new CellIndex('E', 3));
        util.allowedMoves.add(new CellIndex('D', 4));

        util.test();
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *      -   -   -   -   -   -   -   -       * - allowed moves
     *    +---+---+---+---+---+---+---+---+     + - enemy pieces, that can be beaten
     *  3 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   | + | * | + |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testBlackBeatMove() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.BLACK),
                new CellIndex('D', 3)
        );

        //Set up environment
        util.board.set(new CellIndex('C', 2), new Pawn(Role.WHITE));
        util.board.set(new CellIndex('E', 2), new Pawn(Role.WHITE));


        //Set up allowed moves
        util.allowedMoves.add(new CellIndex('D', 2));
        util.allowedMoves.add(new CellIndex('C', 2));
        util.allowedMoves.add(new CellIndex('E', 2));
        util.allowedMoves.add(new CellIndex('D', 1));

        util.test();
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   | P |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *      -   -   -   -   -   -   -   -
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testWhiteTransform() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('d', 7)
        );

        Set<Piece> allowedTransforms = new HashSet<>();
        allowedTransforms.add(new Bishop(util.testPiece.getRole()));
        allowedTransforms.add(new Queen(util.testPiece.getRole()));
        allowedTransforms.add(new Knight(util.testPiece.getRole()));
        allowedTransforms.add(new Rook(util.testPiece.getRole()));


        Assert.assertEquals("Invalid transform",
                allowedTransforms,
                util.testPiece.getAvailableTransformations(
                        new MoveIntent(Role.WHITE, util.srcPos, new CellIndex('d', 8)),
                        util.board
                ));
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   |   |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *      -   -   -   -   -   -   -   -
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   | * |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testBlackTransform() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.BLACK),
                new CellIndex('d', 2)
        );

        Set<Piece> allowedTransforms = new HashSet<>();
        allowedTransforms.add(new Bishop(util.testPiece.getRole()));
        allowedTransforms.add(new Queen(util.testPiece.getRole()));
        allowedTransforms.add(new Knight(util.testPiece.getRole()));
        allowedTransforms.add(new Rook(util.testPiece.getRole()));


        Assert.assertEquals("Invalid transform",
                allowedTransforms,
                util.testPiece.getAvailableTransformations(
                        new MoveIntent(Role.WHITE, util.srcPos, new CellIndex('d', 1)),
                        util.board
                ));
    }

    /**
     *    +---+---+---+---+---+---+---+---+     <--- Black side
     *  8 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     P - test piece
     *  7 |   |   |   | * |   |   |   |   |     * - allowed moves
     *    +---+---+---+---+---+---+---+---+
     *  6 |   |   |   | P |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *      -   -   -   -   -   -   -   -
     *    +---+---+---+---+---+---+---+---+
     *  2 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+
     *  1 |   |   |   |   |   |   |   |   |
     *    +---+---+---+---+---+---+---+---+     <--- White side
     *      A   B   C   D   E   F   G   H
     */
    @Test
    public void testNoTransform() {
        PieceTestCommonUtils util = new PieceTestCommonUtils(
                new Pawn(Role.WHITE),
                new CellIndex('d', 6)
        );

        Set<Piece> allowedTransforms = new HashSet<>();

        Assert.assertEquals("Invalid transform",
                allowedTransforms,
                util.testPiece.getAvailableTransformations(
                        new MoveIntent(Role.WHITE, util.srcPos, new CellIndex('d', 74)),
                        util.board
                ));
    }
}