package com.example.android.educationalapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
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
        videoView = (VideoView) findViewById(R.id.video_view);
        uri = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.intro);
        //videoView.setKeepScreenOn(true);
        videoView.setVideoURI(uri);
        videoView.start();

        // Define custom behaviour when intro video ends.
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                terminateIntro();
            }
        });

        Toast toast = Toast.makeText(this, R.string.intro_toast, Toast.LENGTH_SHORT);
        toast.show();
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
    void terminateIntro() {
        // Show activity_main_menu.
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
