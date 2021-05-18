package com.example.unusualchess.arbiter.ai;

import com.example.unusualchess.chessModel.common.MoveIntent;

public interface ChessAi {
    void movePerformed(MoveIntent m);
    MoveIntent getNextMove();
}
