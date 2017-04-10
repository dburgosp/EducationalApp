package com.example.android.educationalapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    int currentVideoPosition = -1;
    Uri uri;
    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        // Play intro video.
        if (savedInstanceState != null) {
            currentVideoPosition = savedInstanceState.getInt("currentVideoPosition");
        }
        videoView = (VideoView) findViewById(R.id.video_view);
        uri = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.intro);
        //videoView.setOnPreparedListener(this);
        videoView.setKeepScreenOn(true);
        videoView.setVideoURI(uri);
        videoView.start();

        // Define custom behaviour when intro video ends.
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                terminateIntro();
            }
        });
    }

    /**
     * Things to do when user taps on the screen.
     */
    void tapScreen(View view) {
        terminateIntro();
    }

    /**
     * When intro ends or is interrupted, the next active content view is activity_main_menu.
     */
    void terminateIntro()
    {
        // Show activity_main_menu.
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentVideoPosition", videoView.getCurrentPosition());
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
        currentVideoPosition = savedInstanceState.getInt("currentVideoPosition");
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
