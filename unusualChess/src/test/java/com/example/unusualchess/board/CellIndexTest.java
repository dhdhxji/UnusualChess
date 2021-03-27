package com.example.unusualchess.board;

import com.example.unusualchess.util.InvalidCellIndexException;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellIndexTest {
    @Test
    public void correctValues2d() throws InvalidCellIndexException {
        final int file = 5;
        final int rank = 5;

        CellIndex index = new CellIndex(file, rank);

        assertEquals("File value is incorrect", index.getFile(), file);
        assertEquals("Rank value is incorrect", index.getRank(), rank);
    }

    @Test
    public void incorrectValues2d() {
        assertThrows(InvalidCellIndexException.class,
                () -> {
                    CellIndex index = new CellIndex(-1, 5);
                });

        assertThrows(InvalidCellIndexException.class,
                () -> {
                    CellIndex index = new CellIndex(5, -1);
                });

        assertThrows(InvalidCellIndexException.class,
                () -> {
                    CellIndex index = new CellIndex(8, 5);
                });

        assertThrows(InvalidCellIndexException.class,
                () -> {
                    CellIndex index = new CellIndex(5, 8);
                });

        assertThrows(InvalidCellIndexException.class,
                () -> {
                    CellIndex index = new CellIndex(-1, 8);
                });
    }
}
