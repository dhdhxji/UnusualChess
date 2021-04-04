package com.example.unusualchess.util;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.ChessModel;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ChessModelListenerSupportTest {
    class MockEventListener implements ChessModelListener {
        @Override
        public void onMove(ChessMoveEvent ev) {
            lastEvent = ev;
        }

        public ChessMoveEvent lastEvent = null;
    }

    @Test
    public void testSendEvent() {
        ChessModelListenerSupport t = new ChessModelListenerSupport();
        MockEventListener l = new MockEventListener();

        t.addListener(l);

        t.movePerformed(null, null, 0);

        assertNull(l.lastEvent.getSrc());
        assertNull(l.lastEvent.getDst());
        assertEquals(0, l.lastEvent.getSeqNumber());

        CellIndex i = new CellIndex(5, 7);
        t.movePerformed(i, i, 1);

        assertEquals(i, l.lastEvent.getSrc());
        assertEquals(i, l.lastEvent.getDst());
        assertEquals(1, l.lastEvent.getSeqNumber());
    }

    @Test
    public void testAddTwoTimes() {
        ChessModelListenerSupport t = new ChessModelListenerSupport();
        MockEventListener l = new MockEventListener();

        t.addListener(l);
        t.addListener(l);

        t.movePerformed(null, null, 0);

        assertNull(l.lastEvent.getSrc());
        assertNull(l.lastEvent.getDst());
        assertEquals(0, l.lastEvent.getSeqNumber());
    }

    @Test
    public void testRemove() {
        ChessModelListenerSupport t = new ChessModelListenerSupport();
        MockEventListener l = new MockEventListener();

        t.addListener(l);
        t.removeListener(l);

        t.movePerformed(null, null, 0);

        assertNull(l.lastEvent);
    }
}