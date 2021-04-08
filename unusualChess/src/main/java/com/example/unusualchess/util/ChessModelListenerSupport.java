package com.example.unusualchess.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Support class to manage listeners for ChessModel
 */
public class ChessModelListenerSupport {
    /**
     * Add new ChessModel events listener
     * @param l listener to add
     */
    public void addListener(ChessModelListener l) {
        _listeners.add(l);
    }

    /**
     * Remove previously added ChessModel events listener
     * @param l listener to remove
     */
    public void removeListener(ChessModelListener l) {
        _listeners.remove(l);
    }

    /**
     * Notify listeners about move event
     * @param ev chess movement event
     */
    public void movePerformed(ChessMoveEvent ev) {
        for (ChessModelListener l: _listeners) {
            l.onMove( ev );
        }
    }

    private final Set<ChessModelListener> _listeners = Collections.synchronizedSet(new HashSet<>());
}
