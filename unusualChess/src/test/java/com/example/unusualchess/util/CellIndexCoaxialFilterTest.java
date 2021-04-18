package com.example.unusualchess.util;

import com.example.unusualchess.board.CellIndex;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CellIndexCoaxialFilterTest {
    @Test
    public void testCoaxial() {
        CellIndex testPos = new CellIndex(5, 5);
        CellIndexFilter testFilter = new CellIndexCoaxialFilter(testPos);

        Set<CellIndex> testSet = new HashSet<>();
        testSet.add(new CellIndex(5, 1));
        testSet.add(new CellIndex(1, 5));
        testSet.add(new CellIndex(5, 5));

        assertEquals(testSet, testFilter.filter(testSet));
    }

    @Test
    public void testNotCoaxial() {
        CellIndex testPos = new CellIndex(5, 5);
        CellIndexFilter testFilter = new CellIndexCoaxialFilter(testPos);

        Set<CellIndex> testSet = new HashSet<>();
        testSet.add(new CellIndex(1, 1));
        testSet.add(new CellIndex(1, 2));
        testSet.add(new CellIndex(1, 0));

        assertEquals(new HashSet<CellIndex>(), testFilter.filter(testSet));
    }
}