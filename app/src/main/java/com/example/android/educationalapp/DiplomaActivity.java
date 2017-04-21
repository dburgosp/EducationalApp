package com.example.android.educationalapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Calendar;

public class DiplomaActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_diploma);

        // Get context for this activity.
        getContext();

        // Play the music.
        mediaPlayer = MediaPlayer.create(DiplomaActivity.this, R.raw._the_throne_room);
        mediaPlayer.start();

        // Name and current date.
        TextView textViewName = (TextView) findViewById(R.id.diploma_name);
        TextView textViewDate = (TextView) findViewById(R.id.diploma_date);
        textViewName.setText(playerName);

        Calendar date = Calendar.getInstance();
        textViewDate.setText(getResources().getString(R.string.diploma_third_line, date.getTime().toString()));
    }

    /**
     * Get context variables for DiplomaActivity.
     */
    void getContext() {
        playerName = getIntent().getExtras().getString("player_name");
        Log.i("DiplomaActivity", "getContext - Player name: " + playerName);
    }

    @Override
    public void onBackPressed() {
        // Stops music and go back to the main menu.
        mediaPlayer.pause();
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }
}
