package com.example.android.educationalapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainMenuActivity extends AppCompatActivity {
    Bundle outState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main_menu);
/*
        TextView tx1 = (TextView) findViewById(R.id.main_menu_title);
        TextView tx2 = (TextView) findViewById(R.id.main_menu_description);
        TextView tx3 = (TextView) findViewById(R.id.main_menu_start_quiz);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/starwars.ttf");

        tx1.setTypeface(custom_font);
        tx2.setTypeface(custom_font);
        tx3.setTypeface(custom_font);
*/
    }

    void beginTest(View view) {
        MediaPlayer mediaPlayer = MediaPlayer.create(MainMenuActivity.this, R.raw.light_saber);
        mediaPlayer.start();

        //Intent intent = new Intent(this, EditTextActivity.class);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    void hallOfFame(View view) {
        MediaPlayer mediaPlayer = MediaPlayer.create(MainMenuActivity.this, R.raw.light_saber);
        mediaPlayer.start();

        Intent intent = new Intent(this, HallOfFameActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        /*
        super.onSaveInstanceState(outState);
        outState.putInt("goalsTeamA", goalsTeamA);
        outState.putInt("goalsTeamB", goalsTeamB);
        outState.putInt("yellowCardsTeamA", yellowCardsTeamA);
        outState.putInt("yellowCardsTeamB", yellowCardsTeamB);
        outState.putInt("redCardsTeamA", redCardsTeamA);
        outState.putInt("redCardsTeamB", redCardsTeamB);
        */
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        /*
        super.onRestoreInstanceState(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        goalsTeamA = savedInstanceState.getInt("goalsTeamA");
        goalsTeamB = savedInstanceState.getInt("goalsTeamB");
        yellowCardsTeamA = savedInstanceState.getInt("yellowCardsTeamA");
        yellowCardsTeamB = savedInstanceState.getInt("yellowCardsTeamB");
        redCardsTeamA = savedInstanceState.getInt("redCardsTeamA");
        redCardsTeamB = savedInstanceState.getInt("redCardsTeamB");

        displayAllScores();
        */
    }
}
