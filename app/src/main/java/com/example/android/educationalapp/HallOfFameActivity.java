package com.example.android.educationalapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class HallOfFameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_hall_of_fame);
    }

    /**
     * Return to the previous activity.
     */
    void goBack(View view) {
        // Play a sound.
        MediaPlayer mediaPlayer = MediaPlayer.create(HallOfFameActivity.this, R.raw.light_saber);
        mediaPlayer.start();

        this.finish();
    }
}
