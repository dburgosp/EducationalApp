package com.example.android.educationalapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class DiplomaActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_diploma);

        // Play the music.
        mediaPlayer = MediaPlayer.create(DiplomaActivity.this, R.raw.the_throne_room);
        mediaPlayer.start();
        if (savedInstanceState != null) {
            super.onRestoreInstanceState(savedInstanceState);
            mediaPlayer.seekTo(savedInstanceState.getInt("currentAudioPosition"));
        }

        // Define custom behaviour when music ends.
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                endDiploma();
            }
        });
    }

    void endDiploma() {
        Intent intent = new Intent(this, HallOfFameActivity.class);
        startActivity(intent);
    }

    void hallOfFame(View view) {
        MediaPlayer mediaPlayer = MediaPlayer.create(DiplomaActivity.this, R.raw.light_saber);
        mediaPlayer.start();
        endDiploma();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("currentAudioPosition", mediaPlayer.getCurrentPosition());
        mediaPlayer.pause();
        super.onSaveInstanceState(outState);
    }
}
