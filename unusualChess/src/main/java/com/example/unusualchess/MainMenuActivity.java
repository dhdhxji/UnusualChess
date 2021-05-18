package com.example.unusualchess;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.unusualchess.arbiter.ai.ChessAi;
import com.example.unusualchess.arbiter.ai.RandomMoves;
import com.example.unusualchess.chessModel.ChessModel;
import com.example.unusualchess.chessModel.board.CellIndex;
import com.example.unusualchess.chessModel.common.Role;
import com.example.unusualchess.util.BoardListener;
import com.example.unusualchess.view.BoardViewAdapter;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainMenuActivity extends AppCompatActivity
        implements View.OnClickListener, BoardListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Disable title bar
        getSupportActionBar().hide();
        setContentView(R.layout.main_menu_activity);

        Button play_button = findViewById(R.id.main_activity_startGame_btn);
        play_button.setOnClickListener(this);


        ChessModel model = new ChessModel();
        model.reset();

        ChessAi wai = new RandomMoves(model, Role.WHITE);
        ChessAi bai = new RandomMoves(model, Role.BLACK);
        new BoardViewAdapter(R.id.main_menu_board, this, model, this);

        _w = new PlayWorker(model, wai, bai);
    }

    @Override
    protected void onPause() {
        super.onPause();
        _w.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        _w.start();
    }

    @Override
    public void onClick(View v) {
        Intent chActivity = new Intent(this, NewGameSettings.class);

        startActivity(chActivity);
    }

    @Override
    public void onBoardClick(CellIndex pos) {
        //Do nothing
    }

    private PlayWorker _w;
}

class PlayWorker {
    public static String TAG = "PlayWorker";
    public static final int MOVE_PERIOD_MS = 1000;

    public PlayWorker(ChessModel m, ChessAi wh, ChessAi bl) {
        _model = m;
        _workerHandler = new Handler();

        _whiteAi = wh;
        _blackAi = bl;
        _work = new Runnable() {
            @Override
            public void run() {
                makeMove();
                if(_started) {
                    start();
                }
            }
        };
    }

    public void makeMove() {
        //TODO
        Log.d(TAG, "makeMove: " + "move");
    }

    public void start() {
        _started = true;
        _workerHandler.postDelayed(_work, MOVE_PERIOD_MS);
    }

    public void stop() {
        _started = false;
    }

    private ChessModel _model;
    private ChessAi _whiteAi;
    private ChessAi _blackAi;
    private Handler _workerHandler;
    private Runnable _work;
    private boolean _started = false;
}