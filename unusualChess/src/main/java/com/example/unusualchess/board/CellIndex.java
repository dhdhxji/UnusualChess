package com.example.unusualchess.board;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CellIndex {

    public CellIndex(int file, int rank){
        _file = file;
        _rank = rank;
    }

    public CellIndex(char file, int rank) {
        _file = Character.toLowerCase(file) - 'a';

        //Because in chess notation numeration starts from 1
        _rank = rank - 1;
    }

    /**
     * Get file value
     * @return file value (integer)
     */
    public int getFile() {
        return _file;
    }

    /**
     * Get rank value
     * @return rank value (integer)
     */
    public int getRank() {
        return _rank;
    }

    /**
     * Sum one CellIndex to another (like a vectors)
     * @param i another CellIndex
     * @return  sum of CellIndexes
     */
    public CellIndex add(CellIndex i) {
        return new CellIndex(
                getFile() + i.getFile(),
                getRank() + i.getRank()
        );
    }

    /**
     * Subtract one CellIndex to another (like a vectors)
     * @param i another CellIndex
     * @return  subtract of CellIndexes
     */
    public CellIndex sub(CellIndex i) {
        return new CellIndex(
                getFile() - i.getFile(),
                getRank() - i.getRank()
        );
    }

    /**
     * Calculate the inverse CellIndex
     * (source CellIndex + !source CellIndex = 0 CellIndex)
     * @return inverse cell index
     */
    public CellIndex inverse() {
        return new CellIndex(
                -getFile(),
                -getRank()
        );
    }

    /**
     * Generate all basis vectors for space, described by CellIndex
     * @return Set of basis vectors
     */
    public static Set<CellIndex> genBasisCellVectors() {
        Set<CellIndex> res = new HashSet<>();
        res.add(new CellIndex(1, 0));
        res.add(new CellIndex(0, 1));

        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellIndex cellIndex = (CellIndex) o;
        return  _file == cellIndex._file &&
                _rank == cellIndex._rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_file, _rank);
    }

    @NonNull
    @Override
    public String toString() {
        return "CellIndex{" +
                ", file=" + _file +
                ", rank=" + _rank +
                '}';
    }



    //Represent the column on the board
    private final int _file;
    //Represent the row on the board
    private final int _rank;
}
