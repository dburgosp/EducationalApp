package com.example.android.educationalapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {
    Bundle outState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main_menu);

        // Set fonts.
        TextView textView1 = (TextView) findViewById(R.id.activity_main_menu_title);
        Button button1 = (Button) findViewById(R.id.activity_main_menu_begin_button);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/starwars.ttf");
        textView1.setTypeface(custom_font);
        button1.setTypeface(custom_font);
        button1.setAllCaps(true);
    }

    public void letsGo(View view) {
        MediaPlayer mediaPlayer = MediaPlayer.create(MainMenuActivity.this, R.raw._light_saber);
        mediaPlayer.start();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
