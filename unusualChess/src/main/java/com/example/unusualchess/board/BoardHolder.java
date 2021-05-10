package com.example.unusualchess.board;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Class designed for storing the game board state (piece by it`s positions)
 */
public class BoardHolder<T> {
    /**
     * Initialize the BoardHolder with given width
     * @param width is width of 2d board
     */
    public BoardHolder(int width) {
        _width = width;
        _board = new HashMap<>();
    }

    /**
     * Initialize BoardHolder from another BoardHolder (make a copy)
     * @param ref BoardHolder initializer
     */
    public BoardHolder(BoardHolder<T> ref) {
        _board = new HashMap<>(ref._board);
        _width = ref._width;
    }

    /**
     * Set the piece value at specified position
     * @param pos position to set a piece
     * @param piece value to set in certain position
     */
    public void set(CellIndex pos, T piece) {
        if(piece == null) {
            _board.remove(pos);
        } else {
            _board.put(pos, piece);
        }
    }

    /**
     * Get value set in ceratin position
     * @param pos location of desired value
     * @return value located at requested position, if it is exists. null in other cases
     */
    public T get(CellIndex pos) {
        return _board.get(pos);
    }

    /**
     * Clear the board
     */
    public void clear() {
        _board.clear();
    }

    /**
     * Get the board width
     * @return board width, positive number
     */
    public int getWidth() {
        return _width;
    }

    /**
     * Search positions of a specific pieces
     * @param p piece to search for
     * @return set of positions where pieces located are
     */
    public Set<CellIndex> findPiece(T p) {
        Set<CellIndex> positions = new HashSet<>();

        for (Map.Entry<CellIndex, T> entry : _board.entrySet()) {
            if (entry.getValue().equals(p)) {
                positions.add(entry.getKey());
            }
        }

        return positions;
    }

    /**
     * Get all available pieces positions
     * @return set of all available pieces positions
     */
    public Set<CellIndex> getPiecePositions() {
        return _board.keySet();
    }

    @NonNull
    @Override
    public String toString() {
        return "BoardHolder{" +
                "_board=" + _board +
                ", _width=" + _width +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardHolder<?> that = (BoardHolder<?>) o;
        return _width == that._width &&
                Objects.equals(_board, that._board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_board, _width);
    }

    private final HashMap<CellIndex, T> _board;
    private final int _width;
}
