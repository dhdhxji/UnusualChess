package com.example.unusualchess.util;

import com.example.unusualchess.board.CellIndex;

/**
 * Filter CellIndex vector by direction.
 */
public class CellIndexDirectionFilter extends CellIndexFilter{
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public CellIndexDirectionFilter(Direction... d) {
        _d = d;
    }

    @Override
    protected boolean isMeetCriteria(CellIndex i) {
        for (Direction d: _d) {
            boolean isValid = true;

            switch (d) {
                case UP:
                    isValid = (i.getFile() == 0) && i.getRank() > 0;
                    break;
                case DOWN:
                    isValid = (i.getFile() == 0) && i.getRank() < 0;
                    break;
                case LEFT:
                    isValid = (i.getFile() < 0) && i.getRank() == 0;
                    break;
                case RIGHT:
                    isValid = (i.getFile() > 0) && i.getRank() == 0;
                    break;
            }

            if(isValid)
                return true;
        }

        return false;
    }

    private final Direction[] _d;
}
