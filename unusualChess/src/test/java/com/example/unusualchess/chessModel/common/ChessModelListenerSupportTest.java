package com.example.unusualchess.chessModel.common;

import com.example.unusualchess.chessModel.board.CellIndex;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChessModelListenerSupportTest {
    static class MockEventListener implements ChessModelListener<Integer> {
        @Override
        public void onMove(ChessMoveEvent<Integer> ev) {
            lastEvent = ev;
        }

        public ChessMoveEvent<Integer> lastEvent = null;
    }

    @Test
    public void testSendEvent() {
        ChessModelListenerSupport<Integer> t = new ChessModelListenerSupport<>();
        MockEventListener l = new MockEventListener();

        t.addListener(l);

        t.movePerformed(new ChessMoveEvent<>(null, null, 0, 0));

        assertNull(l.lastEvent.getSrc());
        assertNull(l.lastEvent.getDst());
        assertEquals(0, l.lastEvent.getSeqNumber());
        assertEquals(0, l.lastEvent.getPiece().intValue());

        CellIndex i = new CellIndex(5, 7);
        t.movePerformed(new ChessMoveEvent<>(i, i, 1, 0));

        assertEquals(i, l.lastEvent.getSrc());
        assertEquals(i, l.lastEvent.getDst());
        assertEquals(1, l.lastEvent.getSeqNumber());
    }

    @Test
    public void testAddTwoTimes() {
        ChessModelListenerSupport<Integer> t = new ChessModelListenerSupport<>();
        MockEventListener l = new MockEventListener();

        t.addListener(l);
        t.addListener(l);

        t.movePerformed(new ChessMoveEvent<>(null, null, 0, 0));

        assertNull(l.lastEvent.getSrc());
        assertNull(l.lastEvent.getDst());
        assertEquals(0, l.lastEvent.getSeqNumber());
    }

    @Test
    public void testRemove() {
        ChessModelListenerSupport<Integer> t = new ChessModelListenerSupport<>();
        MockEventListener l = new MockEventListener();

        t.addListener(l);
        t.removeListener(l);

        t.movePerformed(new ChessMoveEvent<>(null, null, 0, 0));

        assertNull(l.lastEvent);
    }
}