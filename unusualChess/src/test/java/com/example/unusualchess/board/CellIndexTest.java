package com.example.unusualchess.board;

import com.example.unusualchess.util.InvalidCellIndexException;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellIndexTest {
    public static final int BOARD_WIDTH = 8;

    @Test
    public void correctValues2d() {
        for(int file = 0; file < BOARD_WIDTH; ++file) {
            for(int rank = 0; rank < BOARD_WIDTH; ++rank) {
                CellIndex index = new CellIndex(file, rank);

                assertEquals("File value is incorrect", index.getFile(), file);
                assertEquals("Rank value is incorrect", index.getRank(), rank);
            }
        }
    }
}
