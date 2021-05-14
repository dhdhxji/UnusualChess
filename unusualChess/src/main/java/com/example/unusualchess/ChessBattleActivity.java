package com.example.unusualchess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.unusualchess.chessModel.board.CellIndex;
import com.example.unusualchess.chessModel.ChessModel;
import com.example.unusualchess.chessModel.board.Piece;
import com.example.unusualchess.chessModel.common.MoveIntent;
import com.example.unusualchess.chessModel.common.Role;
import com.example.unusualchess.chessModel.common.exception.ChessInvalidMoveException;
import com.example.unusualchess.chessModel.common.exception.InvalidCellIndexException;
import com.example.unusualchess.util.BoardListener;
import com.example.unusualchess.view.ChessBattleUI;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ChessBattleActivity extends AppCompatActivity
        implements View.OnClickListener, BoardListener {
    private static final String TAG =  "ChessBattleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chess_battle_activity);

        //get the model
        //TODO: receive from repository
        _model = new ChessModel();
        _model.reset();

        _switchBtn = findViewById(R.id.button3);
        _switchBtn.setOnClickListener(this);

        _ui = new ChessBattleUI(this, _model, this);
    }

    /**
     * For button processing
     * @param v
     */
    @Override
    public void onClick(View v) {
        try {
            _model.move(new MoveIntent(Role.WHITE, new CellIndex(0, 1), new CellIndex(0, 3)));
        } catch (Exception e) {
            Log.e(TAG, "onCreate: cannot move", e);
        }
    }

    @Override
    public void onBoardClick(CellIndex pos) {
        Log.d(TAG, "onBoardClick: " + pos + " prev pos " + _selectedPiece );

        if(_selectedPiece == null) {
            _selectedPiece = pos;
            _ui.highlightTiles(_model.getAvailableMoves(pos));
        } else if (_selectedPiece.equals(pos)) {
            _selectedPiece = null;
            _ui.unmarkTiles();
        } else {
            if(_model.getAvailableMoves(_selectedPiece).contains(pos)) {
                //perform a move
                MoveIntent m = new MoveIntent(_model.getCurrentPlayer(), _selectedPiece, pos);
                Set<Piece> transforms = _model.transformAvailable(m);
                if(transforms.size() > 0) {
                    m = new MoveIntent(_model.getCurrentPlayer(),
                            _selectedPiece, pos,
                            (Piece) transforms.toArray()[0]);
                }

                try {
                    _model.move(m);
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
                }

                _selectedPiece = null;
                _ui.unmarkTiles();
            } else {
                //chose another pos
                _selectedPiece = pos;
                _ui.unmarkTiles();
                _ui.highlightTiles(_model.getAvailableMoves(pos));
            }
        }
    }

    private CellIndex _selectedPiece = null;

    private ChessModel _model;
    private ChessBattleUI _ui;
    private Button _switchBtn;
}