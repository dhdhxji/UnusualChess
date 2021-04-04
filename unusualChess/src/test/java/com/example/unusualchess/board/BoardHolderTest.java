package com.example.unusualchess.board;

import org.junit.Test;

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
}