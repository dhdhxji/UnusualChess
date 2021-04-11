package com.example.unusualchess.util;

import com.example.unusualchess.board.CellIndex;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CellIndexDirectionFilterTest {

    @Test
    public void correctValuesTest() {
        Set<CellIndex> testData = new HashSet<>();
        CellIndexFilter testFilter;
        Set<CellIndex> filteredData;

        //up direction test
        testFilter = new CellIndexDirectionFilter(CellIndexDirectionFilter.Direction.UP);
        testData.clear();
        testData.add(new CellIndex(0, 1));
        testData.add(new CellIndex(0, 2));

        filteredData = testFilter.filter(testData);
        assertEquals("Up direction filtering incorrect", testData, filteredData);

        //down direction test
        testFilter = new CellIndexDirectionFilter(CellIndexDirectionFilter.Direction.DOWN);
        testData.clear();
        testData.add(new CellIndex(0, -1));
        testData.add(new CellIndex(0, -2));

        filteredData = testFilter.filter(testData);
        assertEquals("Up direction filtering incorrect", testData, filteredData);

        //right direction test
        testFilter = new CellIndexDirectionFilter(CellIndexDirectionFilter.Direction.RIGHT);
        testData.clear();
        testData.add(new CellIndex(1, 0));
        testData.add(new CellIndex(2, 0));

        filteredData = testFilter.filter(testData);
        assertEquals("Up direction filtering incorrect", testData, filteredData);

        //left direction test
        testFilter = new CellIndexDirectionFilter(CellIndexDirectionFilter.Direction.LEFT);
        testData.clear();
        testData.add(new CellIndex(-1, 0));
        testData.add(new CellIndex(-2, 0));

        filteredData = testFilter.filter(testData);
        assertEquals("Up direction filtering incorrect", testData, filteredData);
    }

    @Test
    public void incorrectValues() {
        CellIndexFilter testFilter =
                new CellIndexDirectionFilter(CellIndexDirectionFilter.Direction.UP);
        Set<CellIndex> expectedData = new HashSet<>();

        Set<CellIndex> testData = new HashSet<>();
        testData.add(new CellIndex(0, 0));
        testData.add(new CellIndex(1, 1));
        testData.add(new CellIndex(0, -1));



        Set<CellIndex> filteredData = testFilter.filter(testData);
        assertEquals("Up direction filtering incorrect", expectedData, filteredData);
    }

    @Test
    public void testMultipleFilterArgs() {
        Set<CellIndex> testData = new HashSet<>();
        CellIndexFilter testFilter =
                new CellIndexDirectionFilter(CellIndexDirectionFilter.Direction.UP,
                                             CellIndexDirectionFilter.Direction.RIGHT);

        testData.add(new CellIndex(0, 1));
        testData.add(new CellIndex(0, 2));
        testData.add(new CellIndex(1, 0));

        Set<CellIndex> filtered = testFilter.filter(testData);

        assertEquals(testData, filtered);
    }

}