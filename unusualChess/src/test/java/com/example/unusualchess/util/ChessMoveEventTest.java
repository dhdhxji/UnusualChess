package com.example.unusualchess.util;

import com.example.unusualchess.board.CellIndex;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChessMoveEventTest {
    @Test
    public void testCancelGeneration() {
        Object o = new Object();

        ChessMoveEvent<Object> ev = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                0,
                o,
                o,
                false,
                false
        );

        ChessMoveEvent<Object> expected = new ChessMoveEvent<>(
                new CellIndex('a', 2),
                new CellIndex('a', 1),
                -1,
                o,
                o,
                true,
                false
        );

        assertEquals(expected, ev.getCancelMove());
    }

    @Test
    public void testCancelGenerationWithTransform() {
        Object o = new Object();
        Object o2 = new Object();

        ChessMoveEvent<Object> ev = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                0,
                o,
                o2,
                false,
                false
        );

        ChessMoveEvent<Object> expected = new ChessMoveEvent<>(
                new CellIndex('a', 2),
                new CellIndex('a', 1),
                -1,
                o2,
                o,
                true,
                false
        );

        assertEquals(expected, ev.getCancelMove());
    }

    @Test
    public void testGetters() {
        Object o = new Object();

        ChessMoveEvent<Object> ev = new ChessMoveEvent<>(
                new CellIndex('a', 1),
                new CellIndex('a', 2),
                0,
                o,
                o,
                false,
                false
        );

        assertEquals(new CellIndex('a', 1), ev.getSrc());
        assertEquals(new CellIndex('a', 2), ev.getDst());
        assertEquals(0, ev.getSeqNumber());
        assertSame(o, ev.getPiece());
        assertSame(o, ev.getTransformTo());
        assertSame(o, ev.getTransformFrom());
        assertFalse(ev.isCancelMove());
        assertFalse(ev.isPreviousMoveBound());
    }

}