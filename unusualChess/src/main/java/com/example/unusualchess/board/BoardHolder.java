package com.example.unusualchess.board;

import com.example.unusualchess.board.CellIndex;

import java.util.ArrayList;
import java.util.List;

/**
 * Class designed for storing the game board state (piece by it`s positions)
 */
public class BoardHolder<T> {

    /**
     * Initialize the BoardHolder with given width
     * @param width is width of 2d board
     */
    public BoardHolder(int width) {
        _board = new ArrayList<>();
        for(int i = 0; i < width; ++i) {
            _board.add(new ArrayList<>(width));
            for(int j = 0; j < width; ++j) {
                _board.get(i).add(null);
            }
        }
    }

    /**
     * Set the piece value at specified position
     * @param pos position to set a piece
     * @param piece value to set in certain position
     */
    public void set(CellIndex pos, T piece) {
        _board.get(pos.getFile()).set(pos.getRank(), piece);
    }

    /**
     * Get value set in ceratin position
     * @param pos location of desired value
     * @return value located at requested position
     */
    public T get(CellIndex pos) {
        return _board.get(pos.getFile()).get(pos.getRank());
    }

    /**
     * Clear the board
     */
    public void clear() {
        for (List<T> row: _board) {
            for(int i = 0; i < row.size(); ++i) {
                row.set(i, null);
            }
        }
    }

    private List<List<T>> _board;
}
