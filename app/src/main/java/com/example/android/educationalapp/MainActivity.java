package com.example.android.educationalapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Play the intro video.
        videoView = (VideoView) findViewById(R.id.video_view);
        Uri uri = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.intro);
        videoView.setVideoURI(uri);
        videoView.start();
    }
}
