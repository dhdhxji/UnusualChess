package com.example.unusualchess.board;

import com.example.unusualchess.board.pieces.Pawn;
import com.example.unusualchess.board.pieces.Queen;
import com.example.unusualchess.common.Role;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class BoardHolderTest {
    public static final int TEST_BOARD_WIDTH = 8;

    @Test
    public void testInit() {
        BoardHolder<Integer> testHolder = new BoardHolder<>(TEST_BOARD_WIDTH);

        //all elements should be null after init
        for(int rank = 0; rank < TEST_BOARD_WIDTH; ++rank) {
            for(int file = 0; file < TEST_BOARD_WIDTH; ++file) {
                CellIndex pos = new CellIndex(file, rank);

                assertNull("Cell " + pos + " has invalid initial value",
                         testHolder.get(pos));
            }
        }
    }

    @Test
    public void testSet() {
        BoardHolder<Integer> testHolder = new BoardHolder<>(TEST_BOARD_WIDTH);

        //setup values
        for(int rank = 0; rank < TEST_BOARD_WIDTH; ++rank) {
            for(int file = 0; file < TEST_BOARD_WIDTH; ++file) {
                CellIndex pos = new CellIndex(file, rank);
                int value = rank*TEST_BOARD_WIDTH + file;

                testHolder.set(pos, value);
            }
        }

        //check values
        for(int rank = 0; rank < TEST_BOARD_WIDTH; ++rank) {
            for(int file = 0; file < TEST_BOARD_WIDTH; ++file) {
                CellIndex pos = new CellIndex(file, rank);
                int value = rank*TEST_BOARD_WIDTH + file;

                assertEquals("Cell " + pos + " has invalid value after set",
                        new Integer(value), testHolder.get(pos));
            }
        }
    }

    @Test
    public void testClear() {
        BoardHolder<Integer> testHolder = new BoardHolder<>(TEST_BOARD_WIDTH);

        //setup values
        for(int rank = 0; rank < TEST_BOARD_WIDTH; ++rank) {
            for(int file = 0; file < TEST_BOARD_WIDTH; ++file) {
                CellIndex pos = new CellIndex(file, rank);
                int value = rank*TEST_BOARD_WIDTH + file;

                testHolder.set(pos, value);
            }
        }

        //clear values
        testHolder.clear();

        //all elements should be null after clear
        for(int rank = 0; rank < TEST_BOARD_WIDTH; ++rank) {
            for(int file = 0; file < TEST_BOARD_WIDTH; ++file) {
                CellIndex pos = new CellIndex(file, rank);

                assertNull("Cell " + pos + " has invalid value after clear",
                        testHolder.get(pos));
            }
        }
    }

    @Test
    public void testPolymorphicStoring() {
        final int baseVal = 0;
        final int child1Val = 1;
        final int child2Val = 2;

        class TestBase {
            public int get() {
                return baseVal;
            }
        }

        class TestChild1 extends TestBase {
            @Override
            public int get() {
                return child1Val;
            }
        }

        class TestChild2 extends TestBase {
            @Override
            public int get() {
                return child2Val;
            }
        }

        CellIndex pos1 = new CellIndex(5, 5);
        CellIndex pos2 = new CellIndex(6, 6);

        BoardHolder<TestBase> testHolder = new BoardHolder<>(TEST_BOARD_WIDTH);

        testHolder.set(pos1, new TestChild1());
        testHolder.set(pos2, new TestChild2());

        assertEquals(child1Val, testHolder.get(pos1).get());
        assertEquals(child2Val, testHolder.get(pos2).get());
    }

    private static <I extends Collection<T>,T> void assertCollectionsEquals(I expected,
                                                                            I actual) {
        //check is all expected values in actual set
        for(T item: expected) {
            assertTrue("Expected value " + item + " is not in actual set",
                    actual.contains(item));
        }

        //check is all actual values in expected set
        for(T item: actual) {
            assertTrue("Actual value " + item + " is not in expected set",
                    expected.contains(item));
        }
    }

    @Test
    public void testFindPiece() {
        BoardHolder<Piece> testHolder = new BoardHolder<>(TEST_BOARD_WIDTH);
        CellIndex[] positions = {
                new CellIndex('a', 1),
                new CellIndex('f', 5),
                new CellIndex('d', 7),
                new CellIndex('c', 8)
        };

        for(CellIndex pos: positions) {
            testHolder.set(pos, new Pawn(Role.WHITE));
        }

        assertCollectionsEquals(new ArrayList<>(Arrays.asList(positions)),
                testHolder.findPiece(new Pawn(Role.WHITE)));



        assertCollectionsEquals(new ArrayList<>(),
                testHolder.findPiece(new Pawn(Role.BLACK)));

        assertCollectionsEquals(new ArrayList<>(),
                testHolder.findPiece(new Queen(Role.WHITE)));
    }
}