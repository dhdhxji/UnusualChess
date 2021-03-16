package com.example.unusualchess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewGameSettings extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game_settings_activity);

        Button startGameBtn = findViewById(R.id.activity_new_game_settings_start);
        startGameBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent startGameIntent  = new Intent(this, ChessBattleActivity.class);

        startActivity(startGameIntent);
    }
}