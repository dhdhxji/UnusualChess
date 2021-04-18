package com.example.unusualchess.util;

import com.example.unusualchess.board.CellIndex;

public class CellIndexCoaxialFilter extends CellIndexFilter{
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
