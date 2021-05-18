package com.example.unusualchess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.unusualchess.arbiter.ai.ChessAi;
import com.example.unusualchess.arbiter.ai.RandomMoves;
import com.example.unusualchess.chessModel.ChessModel;
import com.example.unusualchess.chessModel.board.CellIndex;
import com.example.unusualchess.chessModel.common.MoveIntent;
import com.example.unusualchess.chessModel.common.Role;
import com.example.unusualchess.util.BoardListener;
import com.example.unusualchess.view.BoardViewAdapter;

import java.util.Objects;

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
        Objects.requireNonNull(getSupportActionBar()).hide();
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
    public static final int MOVE_PERIOD_MS = 200;

    public PlayWorker(ChessModel m, ChessAi wh, ChessAi bl) {
        _model = m;
        _workerHandler = new Handler();

        _whiteAi = wh;
        _blackAi = bl;
        _work = () -> {
            makeMove();
            if(_started) {
                start();
            }
        };
    }

    public void makeMove() {
        ChessAi current = (_model.getCurrentPlayer() == Role.WHITE) ? _whiteAi : _blackAi;
        ChessAi enemy = (current == _whiteAi) ? _blackAi : _whiteAi;


        MoveIntent currentMove = current.getNextMove();
        try {
            ChessModel.Situation currentSit = _model.getCurrentSituation();
            if(currentSit == ChessModel.Situation.PAT ||
                    currentSit == ChessModel.Situation.CHECKMATE ||
                    _model.getMoveHistory().getHistory(-1).size() > 100) {
                //Game gone
                _model.reset();
                return;
            }

            _model.move(currentMove);
            enemy.movePerformed(currentMove);
        } catch (Exception e) {
            Log.e(TAG, "makeMove: " + e.getMessage());
        }
    }

    public void start() {
        _started = true;
        _workerHandler.postDelayed(_work, MOVE_PERIOD_MS);
    }

    public void stop() {
        _started = false;
    }

    private final ChessModel _model;
    private final ChessAi _whiteAi;
    private final ChessAi _blackAi;
    private final Handler _workerHandler;
    private final Runnable _work;
    private boolean _started = false;
}