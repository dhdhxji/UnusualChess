package com.example.unusualchess;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Disable title bar
        getSupportActionBar().hide();
        setContentView(R.layout.main_menu_activity);

        Button play_button = findViewById(R.id.main_activity_startGame_btn);
        play_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent chActivity = new Intent(this, NewGameSettings.class);

        startActivity(chActivity);
    }
}