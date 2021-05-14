package com.example.unusualchess.arbiter.ai;

import com.example.unusualchess.chessModel.ChessModel;
import com.example.unusualchess.chessModel.board.CellIndex;
import com.example.unusualchess.chessModel.common.MoveIntent;
import com.example.unusualchess.chessModel.common.Role;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class RandomMoves implements ChessAi{
    public RandomMoves(ChessModel model, Role player) {
        _model = model;
        _player = player;
    }

    @Override
    public void movePerformed(MoveIntent m) {
        //Do nothing
    }

    @Override
    public MoveIntent getNextMove() {
        Set<CellIndex> piecePos = _model.getCurrentState().getPiecePositions();
        Iterator<CellIndex> it = piecePos.iterator();
        while (it.hasNext()) {
            CellIndex pos = it.next();
            if(_model.getCurrentState().get(pos).getRole() != _player ||
                    _model.getAvailableMoves(pos).size() == 0) {
                it.remove();
            }
        }

        CellIndex srcPos = getRandomSetElement(piecePos);
        CellIndex dstPos = getRandomSetElement(_model.getAvailableMoves(srcPos));

        MoveIntent m = new MoveIntent(_player, srcPos, dstPos);
        if(_model.transformAvailable(m).size() != 0) {
            m = new MoveIntent(_player, srcPos, dstPos,
                    getRandomSetElement(_model.transformAvailable(m)));
        }

        return m;
    }

    private static <T> T getRandomSetElement(Set<T> set) {
        int size = set.size();
        int item = new Random().nextInt(size);

        return (T)set.toArray()[item];
    }

    private final ChessModel _model;
    private final Role _player;
}
