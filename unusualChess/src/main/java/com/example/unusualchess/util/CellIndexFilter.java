package com.example.unusualchess.util;

import com.example.unusualchess.board.CellIndex;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Base class for CellIndex filters. Implement filtering by meetCriteria method
 */
public abstract class CellIndexFilter {

    /**
     * Checks is item meets filter criteria
     * @param i item to check
     * @return true, if item meets to criteria, else otherwise
     */
    protected abstract boolean isMeetCriteria(CellIndex i);

    /**
     * Perform filtering
     * @param inputData data to be filtered
     * @return filtered data
     */
    public Set<CellIndex> filter(Set<CellIndex> inputData) {
        Set<CellIndex> res = new HashSet<>(inputData);

        Iterator<CellIndex> i = res.iterator();
        while(i.hasNext()) {
            if( !isMeetCriteria(i.next()) ) {
                i.remove();
            }
        }

        return res;
    }
}
