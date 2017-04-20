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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class LoginActivity extends AppCompatActivity {
    final int NUMBER_OF_EDIT_TEXT_QUESTIONS = 14;
    final int NUMBER_OF_CHECK_BOX_QUESTIONS = 10;
    final int NUMBER_OF_RADIO_BUTTON_QUESTIONS = 47;
    ArrayList<Integer> editTextQuestionsOrder, checkBoxQuestionsOrder, radioButtonQuestionsOrder;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        // Set fonts.
        TextView textView1 = (TextView) findViewById(R.id.activity_login_title);
        Button button1 = (Button) findViewById(R.id.activity_login_begin_button);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/starwars.ttf");
        textView1.setTypeface(custom_font);
        button1.setTypeface(custom_font);
        button1.setAllCaps(true);
    }

    /**
     * Actions to be done when 'begin text' button is clicked.
     *
     * @param view
     */
    public void beginTest(View view) {
        editText = (EditText) findViewById(R.id.activity_login_edit_text);
        String playerName = editText.getText().toString().toUpperCase();
        if (playerName.isEmpty()) {
            // Player's name cannot be empty.
            Toast toast = Toast.makeText(this, R.string.type_your_name_error, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            // Play a sound.
            MediaPlayer mediaPlayer = MediaPlayer.create(LoginActivity.this, R.raw._light_saber);
            mediaPlayer.start();

            // Generate random order of questions.
            editTextQuestionsOrder = generateRandomList(NUMBER_OF_EDIT_TEXT_QUESTIONS);
            checkBoxQuestionsOrder = generateRandomList(NUMBER_OF_CHECK_BOX_QUESTIONS);
            radioButtonQuestionsOrder = generateRandomList(NUMBER_OF_RADIO_BUTTON_QUESTIONS);

            // Save player's name and go to the first question.
            Intent intent = new Intent(this, RadioButtonActivity.class);
            intent.putExtra("player_name", playerName);
            intent.putIntegerArrayListExtra("edit_text_questions_order", editTextQuestionsOrder);
            intent.putIntegerArrayListExtra("check_box_questions_order", checkBoxQuestionsOrder);
            intent.putIntegerArrayListExtra("radio_button_questions_order", radioButtonQuestionsOrder);
            intent.putExtra("question_number", 1);
            intent.putExtra("right_answers", 0);
            startActivity(intent);
        }
    }

    /**
     * Shuffles an array list of maxValue integers.
     *
     * @param maxValue
     * @return shuffled list.
     */
    public ArrayList<Integer> generateRandomList(int maxValue) {
        ArrayList<Integer> questions = new ArrayList<Integer>();
        for (int i = 0; i < maxValue; questions.add(++i)) ;
        Collections.shuffle(questions);
        return questions;
    }
}
