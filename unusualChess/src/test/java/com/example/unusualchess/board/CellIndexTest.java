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

    @Test
    public void testCorrectChessNotationLowerCase() {
        for(char file = 'a'; file < 'a' + BOARD_WIDTH; ++file) {
            for(int rank = 0; rank < BOARD_WIDTH; ++rank) {
                CellIndex index = new CellIndex(file, rank);

                assertTrue("File value is not in correct range",
                        index.getFile() >= 0 && index.getFile() < BOARD_WIDTH);
                assertEquals("File value is incorrect", index.getFile(), file - 'a');
                assertEquals("Rank value is incorrect", index.getRank(), rank);
            }
        }
    }

    @Test
    public void testCorrectChessNotationUpperCase() {
        for(char file = 'A'; file < 'A' + BOARD_WIDTH; ++file) {
            for(int rank = 0; rank < BOARD_WIDTH; ++rank) {
                CellIndex index = new CellIndex(file, rank);

                assertTrue("File value is not in correct range",
                        index.getFile() >= 0 && index.getFile() < BOARD_WIDTH);
                assertEquals("File value is incorrect", index.getFile(), file - 'A');
                assertEquals("Rank value is incorrect", index.getRank(), rank);
            }
        }
    }

    @Test
    public void testIncorrectChessNotation() {
        for(char file = 'J'; file < 'J' + BOARD_WIDTH; ++file) {
            for(int rank = 0; rank < BOARD_WIDTH; ++rank) {
                CellIndex index = new CellIndex(file, rank);

                assertFalse("File value is not in correct range",
                        index.getFile() >= 0 && index.getFile() < BOARD_WIDTH);
                assertNotEquals("File value is incorrect",file - 'J',
                        index.getFile());
                assertEquals("Rank value is incorrect", rank, index.getRank());
            }
        }
    }

    @Test
    public void testChessNotationNonLettersCharacter() {
        for(char file = '1'; file < '1' + BOARD_WIDTH; ++file) {
            for(int rank = 0; rank < BOARD_WIDTH; ++rank) {
                CellIndex index = new CellIndex(file, rank);

                assertFalse("File value is not in correct range",
                        index.getFile() >= 0 && index.getFile() < BOARD_WIDTH);
                assertNotEquals("File value is incorrect",file - 'J',
                        index.getFile());
                assertEquals("Rank value is incorrect", rank, index.getRank());
            }
        }
    }
}
