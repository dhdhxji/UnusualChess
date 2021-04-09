package com.example.unusualchess.board;

import androidx.annotation.NonNull;

import com.example.unusualchess.util.InvalidCellIndexException;

import java.util.Objects;

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
