package com.example.unusualchess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.unusualchess.board.ChessModel;
import com.example.unusualchess.view.ChessBattleUI;

public class ChessBattleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chess_battle_activity);

        //get the model
        //TODO: receive from repository
        _model = new ChessModel();
        _model.reset();

        _ui = new ChessBattleUI(this, _model);
    }

    private ChessModel _model;
    private ChessBattleUI _ui;
}