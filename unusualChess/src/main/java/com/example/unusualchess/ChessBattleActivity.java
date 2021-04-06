package com.example.unusualchess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.ChessModel;
import com.example.unusualchess.common.MoveIntent;
import com.example.unusualchess.common.Role;
import com.example.unusualchess.view.ChessBattleUI;

public class ChessBattleActivity extends AppCompatActivity implements View.OnClickListener {
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

        _ui = new ChessBattleUI(this, _model);
    }

    @Override
    public void onClick(View v) {
        try {
            _model.move(new MoveIntent(Role.WHITE, new CellIndex(0, 1), new CellIndex(0, 3)));
        } catch (Exception e) {
            Log.e(TAG, "onCreate: cannot move", e);
        }
    }





    private ChessModel _model;
    private ChessBattleUI _ui;
    private Button _switchBtn;
}