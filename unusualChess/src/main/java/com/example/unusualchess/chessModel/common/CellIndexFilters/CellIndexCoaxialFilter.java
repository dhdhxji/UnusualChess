package com.example.unusualchess.chessModel.common.CellIndexFilters;

import com.example.unusualchess.chessModel.board.CellIndex;
import com.example.unusualchess.chessModel.common.CellIndexFilter;

public class CellIndexCoaxialFilter extends CellIndexFilter {
    public CellIndexCoaxialFilter(CellIndex pos) {
        _pos = pos;
    }

    @Override
    protected boolean isMeetCriteria(CellIndex i) {
        return i.getRank() == _pos.getRank() ||
                i.getFile() == _pos.getFile();
    }

    protected CellIndex _pos;
}
