package com.example.android.educationalapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FinalScoreActivity extends AppCompatActivity {
    int rightAnswers;
    String playerName;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_final_score);

        // Set fonts.
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/starwars.ttf");
        TextView textView1 = (TextView) findViewById(R.id.activity_results_title);
        Button button2 = (Button) findViewById(R.id.activity_results_try_again);
        TextView textView3 = (TextView) findViewById(R.id.activity_results_score);
        textView1.setTypeface(custom_font);
        button2.setTypeface(custom_font);
        textView3.setTypeface(custom_font);
        button2.setAllCaps(true);

        // Get context for this activity.
        getContext();

        // Set text for first paragraph.
        TextView textViewResultsHeader = (TextView) findViewById(R.id.activity_results_header);
        textViewResultsHeader.setText(getResources().getString(R.string.test_result_header, playerName));

        // Show the results.
        String score = rightAnswers + "/10";
        textView3.setText(score);

        // Set background image and text for comments.
        imageView = (ImageView) findViewById(R.id.activity_results_background);
        TextView textViewComments = (TextView) findViewById(R.id.activity_results_comments);
        MediaPlayer mediaPlayer1, mediaPlayer2;
        switch (rightAnswers) {
            case 0:
                imageView.setImageResource(R.drawable._00_ewok);
                textViewComments.setText(R.string.test_result_comments_0);
                mediaPlayer1 = MediaPlayer.create(FinalScoreActivity.this, R.raw._darth_vader_nooo);
                mediaPlayer1.start();
                break;

            case 1:
                imageView.setImageResource(R.drawable._01_jar_jar_binks);
                textViewComments.setText(R.string.test_result_comments_1);
                mediaPlayer1 = MediaPlayer.create(FinalScoreActivity.this, R.raw._try_again);
                mediaPlayer1.start();
                break;

            case 2:
                imageView.setImageResource(R.drawable._02_boba_fett);
                textViewComments.setText(R.string.test_result_comments_2);
                mediaPlayer1 = MediaPlayer.create(FinalScoreActivity.this, R.raw._try_again);
                mediaPlayer1.start();
                break;

            case 3:
                imageView.setImageResource(R.drawable._03_chewbacca);
                textViewComments.setText(R.string.test_result_comments_3);
                mediaPlayer1 = MediaPlayer.create(FinalScoreActivity.this, R.raw._wookie);
                mediaPlayer1.start();
                mediaPlayer2 = MediaPlayer.create(FinalScoreActivity.this, R.raw._try_again);
                mediaPlayer2.start();
                break;

            case 4:
                imageView.setImageResource(R.drawable._04_r2_d2);
                textViewComments.setText(R.string.test_result_comments_4);
                mediaPlayer1 = MediaPlayer.create(FinalScoreActivity.this, R.raw._r2d2_processing);
                mediaPlayer1.start();
                mediaPlayer2 = MediaPlayer.create(FinalScoreActivity.this, R.raw._try_again);
                mediaPlayer2.start();
                break;

            case 5:
                imageView.setImageResource(R.drawable._05_padme_amidala);
                textViewComments.setText(R.string.test_result_comments_5);
                mediaPlayer1 = MediaPlayer.create(FinalScoreActivity.this, R.raw._try_again);
                mediaPlayer1.start();
                break;

            case 6:
                imageView.setImageResource(R.drawable._06_leia_organa);
                textViewComments.setText(R.string.test_result_comments_6);
                mediaPlayer1 = MediaPlayer.create(FinalScoreActivity.this, R.raw._try_again);
                mediaPlayer1.start();
                break;

            case 7:
                imageView.setImageResource(R.drawable._07_obi_wan_kenobi);
                textViewComments.setText(R.string.test_result_comments_7);
                mediaPlayer1 = MediaPlayer.create(FinalScoreActivity.this, R.raw._try_again);
                mediaPlayer1.start();
                break;

            case 8:
                imageView.setImageResource(R.drawable._08_luke_skywalker);
                textViewComments.setText(R.string.test_result_comments_8);
                mediaPlayer1 = MediaPlayer.create(FinalScoreActivity.this, R.raw._try_again);
                mediaPlayer1.start();
                break;

            case 9:
                imageView.setImageResource(R.drawable._09_darth_vader);
                textViewComments.setText(R.string.test_result_comments_9);
                mediaPlayer1 = MediaPlayer.create(FinalScoreActivity.this, R.raw._emperor_theme);
                mediaPlayer1.start();
                break;

            case 10:
                imageView.setImageResource(R.drawable._10_yoda);
                textViewComments.setText(R.string.test_result_comments_10);
                mediaPlayer1 = MediaPlayer.create(FinalScoreActivity.this, R.raw._you_win);
                mediaPlayer1.start();
                break;
        }

        // Button text.
        if (rightAnswers == 10) button2.setText(R.string.get_your_diploma);
        else button2.setText(R.string.try_again);
    }

    /**
     * Get context variables for EditTextActivity.
     */
    void getContext() {
        playerName = getIntent().getExtras().getString("player_name");
        rightAnswers = getIntent().getExtras().getInt("right_answers");
        Log.i("EditTextActivity", "getContext - Player name: " + playerName);
        Log.i("EditTextActivity", "getContext - Right answers so far: " + rightAnswers);
    }

    /**
     * Things to do when button is clicked.
     *
     * @param view from which this method is called.
     */
    public void finish(View view) {
        // Play a sound.
        MediaPlayer mediaPlayer = MediaPlayer.create(FinalScoreActivity.this, R.raw._light_saber);
        mediaPlayer.start();

        // Free memory.
        imageView.setBackground(null);

        // Behaviour of the button.
        Intent intent;
        if (rightAnswers == 10) intent = new Intent(this, DiplomaActivity.class);
        else intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra("player_name", playerName);
        startActivity(intent);
        finish();
    }
}
