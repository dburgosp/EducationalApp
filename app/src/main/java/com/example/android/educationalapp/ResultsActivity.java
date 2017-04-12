package com.example.android.educationalapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {
    int questionNumber, rightAnswers;
    ArrayList<EditTextQuestion> editTextQuestions;
    EditTextQuestion editTextQuestion;
    String playerName, rightAnswer, minAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_results);

        // Get context for this activity.
        getContext();

        // Set text for first paragraph.
        TextView textViewResultsHeader = (TextView) findViewById(R.id.test_result_header);
        textViewResultsHeader.setText(getResources().getString(R.string.test_result_header, playerName));

        // Show the results.
        TextView textViewResults = (TextView) findViewById(R.id.test_result);
        textViewResults.setText(rightAnswers + "/10");

        // Set background image and text for comments.
        ImageView imageView = (ImageView) findViewById(R.id.test_result_bg);
        TextView textViewComments = (TextView) findViewById(R.id.test_result_comments);
        MediaPlayer mediaPlayer;
        switch (rightAnswers) {
            case 0:
                imageView.setImageResource(R.drawable._00_ewok);
                textViewComments.setText(R.string.test_result_comments_0);
                mediaPlayer = MediaPlayer.create(ResultsActivity.this, R.raw.darth_vader_nooo);
                mediaPlayer.start();
                break;

            case 1:
                imageView.setImageResource(R.drawable._01_jar_jar_binks);
                textViewComments.setText(R.string.test_result_comments_1);
                mediaPlayer = MediaPlayer.create(ResultsActivity.this, R.raw.try_again);
                mediaPlayer.start();
                break;

            case 2:
                imageView.setImageResource(R.drawable._02_boba_fett);
                textViewComments.setText(R.string.test_result_comments_2);
                mediaPlayer = MediaPlayer.create(ResultsActivity.this, R.raw.try_again);
                mediaPlayer.start();
                break;

            case 3:
                imageView.setImageResource(R.drawable._03_chewbacca);
                textViewComments.setText(R.string.test_result_comments_3);
                mediaPlayer = MediaPlayer.create(ResultsActivity.this, R.raw.wookie);
                mediaPlayer.start();
                break;

            case 4:
                imageView.setImageResource(R.drawable._04_r2_d2);
                textViewComments.setText(R.string.test_result_comments_4);
                mediaPlayer = MediaPlayer.create(ResultsActivity.this, R.raw.r2d2_processing);
                mediaPlayer.start();
                break;

            case 5:
                imageView.setImageResource(R.drawable._05_padme_amidala);
                textViewComments.setText(R.string.test_result_comments_5);
                mediaPlayer = MediaPlayer.create(ResultsActivity.this, R.raw.try_again);
                mediaPlayer.start();
                break;

            case 6:
                imageView.setImageResource(R.drawable._06_leia_organa);
                textViewComments.setText(R.string.test_result_comments_6);
                mediaPlayer = MediaPlayer.create(ResultsActivity.this, R.raw.try_again);
                mediaPlayer.start();
                break;

            case 7:
                imageView.setImageResource(R.drawable._07_obi_wan_kenobi);
                textViewComments.setText(R.string.test_result_comments_7);
                mediaPlayer = MediaPlayer.create(ResultsActivity.this, R.raw.try_again);
                mediaPlayer.start();
                break;

            case 8:
                imageView.setImageResource(R.drawable._08_luke_skywalker);
                textViewComments.setText(R.string.test_result_comments_8);
                mediaPlayer = MediaPlayer.create(ResultsActivity.this, R.raw.try_again);
                mediaPlayer.start();
                break;

            case 9:
                imageView.setImageResource(R.drawable._09_darth_vader);
                textViewComments.setText(R.string.test_result_comments_9);
                mediaPlayer = MediaPlayer.create(ResultsActivity.this, R.raw.emperor_theme);
                mediaPlayer.start();
                break;

            case 10:
                imageView.setImageResource(R.drawable._10_yoda);
                textViewComments.setText(R.string.test_result_comments_10);
                mediaPlayer = MediaPlayer.create(ResultsActivity.this, R.raw.you_win);
                mediaPlayer.start();
                break;
        }

        // Button text.
        TextView textView = (TextView) findViewById(R.id.try_again);
        if (rightAnswers == 10) textView.setText(R.string.get_your_diploma);
        else textView.setText(R.string.try_again);
    }

    /**
     * Get context variables for EditTextActivity.
     */
    void getContext() {
        playerName = getIntent().getExtras().getString("player_name");
        questionNumber = getIntent().getExtras().getInt("question_number");
        rightAnswers = getIntent().getExtras().getInt("right_answers");

        Log.i("EditTextActivity", "getContext - Player name: " + playerName);
        Log.i("EditTextActivity", "getContext - Current question: " + questionNumber + "/10");
        Log.i("EditTextActivity", "getContext - Right answers so far: " + rightAnswers);
    }

    public void letsGo(View view) {
        // Play a sound.
        MediaPlayer mediaPlayer = MediaPlayer.create(ResultsActivity.this, R.raw.light_saber);
        mediaPlayer.start();

        // Behaviour of the button.
        Intent intent;
        if (rightAnswers == 10) intent = new Intent(this, DiplomaActivity.class);
        else intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra("player_name", playerName);
        startActivity(intent);
    }
}
