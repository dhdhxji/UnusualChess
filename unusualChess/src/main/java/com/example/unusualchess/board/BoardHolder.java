package com.example.unusualchess.board;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class designed for storing the game board state (piece by it`s positions)
 */
public class BoardHolder<T> {
    //TODO: Implement as dimension-independent collection (CellIndex-independent,
    // like big CellIndex-T HasMap or with other Collection)

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
     * @return value located at requested position, if it is exists. null in other cases
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

    /**
     * Get the board width
     * @return board width, positive number
     */
    public int getWidth() {
        return _board.size();
    }

    /**
     * Search positions of a specific pieces
     * @param p piece to search for
     * @return set of positions where pieces located are
     */
    public Set<CellIndex> findPiece(T p) {
        Set<CellIndex> positions = new HashSet<>();

        for(int rank = 0; rank < getWidth(); ++rank) {
            for(int file = 0; file < getWidth(); ++file) {
                CellIndex pos = new CellIndex(file, rank);

                if(get(pos) != null && get(pos).equals(p)) {
                    positions.add(pos);
                }
            }
        }

        return positions;
    }

    private List<List<T>> _board;
}
