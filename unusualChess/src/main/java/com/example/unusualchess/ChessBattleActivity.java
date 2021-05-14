package com.example.unusualchess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unusualchess.arbiter.ai.ChessAi;
import com.example.unusualchess.arbiter.ai.RandomMoves;
import com.example.unusualchess.chessModel.board.CellIndex;
import com.example.unusualchess.chessModel.ChessModel;
import com.example.unusualchess.chessModel.board.Piece;
import com.example.unusualchess.chessModel.common.MoveIntent;
import com.example.unusualchess.chessModel.common.Role;
import com.example.unusualchess.util.BoardListener;
import com.example.unusualchess.view.BoardViewAdapter;

import java.util.Set;

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

        _ai = new RandomMoves(_model, Role.BLACK);

        _switchBtn = findViewById(R.id.button3);
        _switchBtn.setOnClickListener(this);

        _ui = new BoardViewAdapter(this, _model, this);

        _currentMove = findViewById(R.id.current_player_move);
        updateCurrentPlayer();
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
            if(_model.getCurrentPlayer().equals(_model.getCurrentState().get(pos).getRole())) {
                _selectedPiece = pos;
                _ui.highlightTiles(_model.getAvailableMoves(pos));
            }
        } else if (_selectedPiece.equals(pos)) {
            _selectedPiece = null;
            _ui.unmarkTiles();
        } else {
            if(_model.getAvailableMoves(_selectedPiece).contains(pos)) {
                //perform a move
                move(_selectedPiece, pos);

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

    private void move(CellIndex src, CellIndex dst) {
        MoveIntent m = new MoveIntent(_model.getCurrentPlayer(), src, dst);
        Set<Piece> transforms = _model.transformAvailable(m);
        if(transforms.size() > 0) {
            m = new MoveIntent(_model.getCurrentPlayer(),
                    _selectedPiece, dst,
                    (Piece) transforms.toArray()[0]);
        }

        try {
            _model.move(m);
            updateCurrentPlayer();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        //Compute next move
        _ai.movePerformed(m);
        try {
            _model.move(_ai.getNextMove());
            updateCurrentPlayer();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void updateCurrentPlayer() {
        if(_model.getCurrentPlayer() == Role.WHITE) {
            _currentMove.setText(R.string.white_move_title);
        } else {
            _currentMove.setText(R.string.black_move_title);
        }
    }

    private CellIndex _selectedPiece = null;

    private ChessModel _model;
    private BoardViewAdapter _ui;
    private Button _switchBtn;
    private ChessAi _ai;

    private TextView _currentMove;
}