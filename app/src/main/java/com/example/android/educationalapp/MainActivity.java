package com.example.android.educationalapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        // Play intro video.
        videoView = (VideoView) findViewById(R.id.video_view);
        Uri uri = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.intro_es);
        videoView.setVideoURI(uri);
        videoView.start();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /*
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
        super.onRestoreInstanceState(savedInstanceState);
        /*
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
