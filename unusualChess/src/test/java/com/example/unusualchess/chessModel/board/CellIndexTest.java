package com.example.unusualchess.chessModel.board;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CellIndexTest {
    public static final int BOARD_WIDTH = 8;

    @Test
    public void correctValues2d() {
        for(int file = 0; file < BOARD_WIDTH; ++file) {
            for(int rank = 0; rank < BOARD_WIDTH; ++rank) {
                CellIndex index = new CellIndex(file, rank);

                assertEquals("File value is incorrect", file, index.getFile());
                assertEquals("Rank value is incorrect", rank, index.getRank());
            }
        }
    }

    @Test
    public void testCorrectChessNotationLowerCase() {
        for(char file = 'a'; file < 'a' + BOARD_WIDTH; ++file) {
            for(int rank = 1; rank <= BOARD_WIDTH; ++rank) {
                CellIndex index = new CellIndex(file, rank);

                assertTrue("File value is not in correct range",
                        index.getFile() >= 0 && index.getFile() < BOARD_WIDTH);
                assertEquals("File value is incorrect", file - 'a', index.getFile());
                assertEquals("Rank value is incorrect", rank - 1, index.getRank());
            }
        }
    }

    @Test
    public void testCorrectChessNotationUpperCase() {
        for(char file = 'A'; file < 'A' + BOARD_WIDTH; ++file) {
            for(int rank = 1; rank <= BOARD_WIDTH; ++rank) {
                CellIndex index = new CellIndex(file, rank);

                assertTrue("File value is not in correct range",
                        index.getFile() >= 0 && index.getFile() < BOARD_WIDTH);
                assertEquals("File value is incorrect", file - 'A', index.getFile());
                assertEquals("Rank value is incorrect", rank - 1, index.getRank());
            }
        }
    }

    @Test
    public void testIncorrectChessNotation() {
        for(char file = 'J'; file < 'J' + BOARD_WIDTH; ++file) {
            for(int rank = 1; rank <= BOARD_WIDTH; ++rank) {
                CellIndex index = new CellIndex(file, rank);

                assertFalse("File value is not in correct range",
                        index.getFile() >= 0 && index.getFile() < BOARD_WIDTH);
                assertNotEquals("File value is incorrect",file - 'J',
                        index.getFile());
                assertEquals("Rank value is incorrect", rank - 1, index.getRank());
            }
        }
    }

    @Test
    public void testChessNotationNonLettersCharacter() {
        for(char file = '1'; file < '1' + BOARD_WIDTH; ++file) {
            for(int rank = 1; rank <= BOARD_WIDTH; ++rank) {
                CellIndex index = new CellIndex(file, rank);

                assertFalse("File value is not in correct range",
                        index.getFile() >= 0 && index.getFile() < BOARD_WIDTH);
                assertNotEquals("File value is incorrect",file - 'J',
                        index.getFile());
                assertEquals("Rank value is incorrect", rank - 1, index.getRank());
            }
        }
    }

    @Test
    public void testSum() {
        CellIndex a = new CellIndex(1, 2);
        CellIndex b = new CellIndex(3, 4);

        CellIndex res = a.add(b);

        assertEquals("Sum invalid", new CellIndex(4, 6), res);
    }

    @Test
    public void testSub() {
        CellIndex a = new CellIndex(1, 2);
        CellIndex b = new CellIndex(3, 4);

        CellIndex res = a.sub(b);

        assertEquals("Sub invalid", new CellIndex(-2, -2), res);
    }

    @Test
    public void testInverse() {
        CellIndex a = new CellIndex(1, 2);

        CellIndex res = a.inverse();

        assertEquals("Sum invalid", new CellIndex(-1, -2), res);
    }

    @Test
    public void testGenBasisCellVectors() {
        Set<CellIndex> expected = new HashSet<>();
        expected.add(new CellIndex(1, 0));
        expected.add(new CellIndex(0, 1));

        assertEquals("Generated Cell unit vectors is not valid",
                expected,
                CellIndex.genBasisCellVectors());
    }

    @Test
    public void testManhattanDistance() {
        CellIndex a = new CellIndex(5, 5);
        CellIndex b = new CellIndex(10, 10);

        assertEquals(10, b.manhattanDistance(a));
    }

    @Test
    public void testManhattanNormalize() {
        CellIndex a = new CellIndex(5, -5);
        CellIndex b = new CellIndex(0, 10);

        CellIndex aNormExp = new CellIndex(1, -1);
        CellIndex bNormExp = new CellIndex(0, 1);

        assertEquals(aNormExp, a.manhattanNormalize());
        assertEquals(bNormExp, b.manhattanNormalize());
    }
}
