package com.example.android.educationalapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
    }

    /**
     * Actions to be done when 'begin text' button is clicked.
     *
     * @param view
     */
    void beginTest(View view) {
        editText = (EditText) findViewById(R.id.login_edit_text);
        String playerName = editText.getText().toString();
        if (playerName.isEmpty()) {
            // Player's name cannot be empty.
            Toast toast = Toast.makeText(this, R.string.type_your_name_error, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            // Play a sound.
            MediaPlayer mediaPlayer = MediaPlayer.create(LoginActivity.this, R.raw.light_saber);
            mediaPlayer.start();

            // Save player's name and go to the first question.
            Intent intent = new Intent(this, RadioButtonActivity.class);
            intent.putExtra("player_name", playerName);
            intent.putExtra("question_number", 1);
            intent.putExtra("right_answers", 0);
            startActivity(intent);
        }
    }
}
