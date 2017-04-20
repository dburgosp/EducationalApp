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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RadioButtonActivity extends AppCompatActivity {
    ArrayList<String> answers;
    RadioButtonQuestion radioButtonQuestion;
    int checked = 0, questionNumber, rightAnswers, rightAnswer;
    String playerName, question;
    ArrayList<Integer> editTextQuestionsOrder, checkBoxQuestionsOrder, radioButtonQuestionsOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_radio_button);

        // Set fonts.
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/starwars.ttf");
        TextView textView1 = (TextView) findViewById(R.id.activity_radio_button_title);
        Button button1 = (Button) findViewById(R.id.activity_radio_button_submit_button);
        TextView textView3 = (TextView) findViewById(R.id.activity_radio_button_question_number);
        textView1.setTypeface(custom_font);
        button1.setTypeface(custom_font);
        textView3.setTypeface(custom_font);
        button1.setAllCaps(true);

        // Get context for this activity.
        getContext();

        // Set title.
        textView3.setText(getResources().getString(R.string.question_title, questionNumber));

        // Get current question.
        int index = radioButtonQuestionsOrder.get(questionNumber - 1);
        readQuestion(index - 1);
        radioButtonQuestion = new RadioButtonQuestion(question, answers, rightAnswer);

        // Set background image.
        ImageView imageView = (ImageView) findViewById(R.id.activity_radio_button_background);
        switch (questionNumber) {
            case 1:
                imageView.setImageResource(R.drawable._01_jar_jar_binks);
                break;
            case 3:
                imageView.setImageResource(R.drawable._03_chewbacca);
                break;
            case 5:
                imageView.setImageResource(R.drawable._05_padme_amidala);
                break;
            case 7:
                imageView.setImageResource(R.drawable._07_obi_wan_kenobi);
                break;
            case 9:
                imageView.setImageResource(R.drawable._09_darth_vader);
                break;
        }

        // Show question and answers.
        TextView textViewQuestion = (TextView) findViewById(R.id.activity_radio_button_description);
        textViewQuestion.setText(radioButtonQuestion.getQuestion());
        RadioButton radioButton1 = (RadioButton) findViewById(R.id.activity_radio_button_answer_1);
        radioButton1.setText(radioButtonQuestion.getAnswers().get(0));
        RadioButton radioButton2 = (RadioButton) findViewById(R.id.activity_radio_button_answer_2);
        radioButton2.setText(radioButtonQuestion.getAnswers().get(1));
        RadioButton radioButton3 = (RadioButton) findViewById(R.id.activity_radio_button_answer_3);
        radioButton3.setText(radioButtonQuestion.getAnswers().get(2));
        RadioButton radioButton4 = (RadioButton) findViewById(R.id.activity_radio_button_answer_4);
        radioButton4.setText(radioButtonQuestion.getAnswers().get(3));
    }

    /**
     * Get context variables for RadioButtonActivity.
     */
    void getContext() {
        editTextQuestionsOrder = new ArrayList<Integer>();
        checkBoxQuestionsOrder = new ArrayList<Integer>();
        radioButtonQuestionsOrder = new ArrayList<Integer>();

        playerName = getIntent().getExtras().getString("player_name");
        questionNumber = getIntent().getExtras().getInt("question_number");
        rightAnswers = getIntent().getExtras().getInt("right_answers");
        editTextQuestionsOrder = getIntent().getExtras().getIntegerArrayList("edit_text_questions_order");
        checkBoxQuestionsOrder = getIntent().getExtras().getIntegerArrayList("check_box_questions_order");
        radioButtonQuestionsOrder = getIntent().getExtras().getIntegerArrayList("radio_button_questions_order");

        Log.i("RadioButtonActivity", "getContext - Player name: " + playerName);
        Log.i("RadioButtonActivity", "getContext - Current question: " + questionNumber + "/10");
        Log.i("RadioButtonActivity", "getContext - Right answers so far: " + rightAnswers);
    }

    /**
     * Reads question n from strings.xml.
     *
     * @param n number of the question.
     */
    void readQuestion(int n) {
        String answer;
        int idAnswer, idQuestion, idRightAnswer;

        idQuestion = getResources().getIdentifier("radio_button_question_" + (n + 1), "string", getPackageName());
        question = getResources().getString(idQuestion).toUpperCase();
        Log.i("RadioButtonActivity", "initQuestions - Question " + (n + 1) + ": " + question);

        answers = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            idAnswer = getResources().getIdentifier("radio_button_question_" + (n + 1) + "_answer_" + (i + 1), "string", getPackageName());
            answer = getResources().getString(idAnswer).toUpperCase();
            Log.i("RadioButtonActivity", "initQuestions - Answer " + (n + 1) + "." + (i + 1) + ": " + answer);
            answers.add(i, answer);
        }

        idRightAnswer = getResources().getIdentifier("radio_button_right_answer_" + (n + 1), "string", getPackageName());
        rightAnswer = Integer.parseInt(getResources().getString(idRightAnswer));
        Log.i("RadioButtonActivity", "initQuestions - Right answer " + (n + 1) + ": " + rightAnswer);
    }

    /**
     * Actions to be done when a RadioButton is checked on activity_radio_button.xml.
     *
     * @param view
     */
    public void checkRadioButton(View view) {
        // Play a sound.
        MediaPlayer mediaPlayer = MediaPlayer.create(RadioButtonActivity.this, R.raw._impblst);
        mediaPlayer.start();

        int viewId = view.getId();
        switch (viewId) {
            case R.id.activity_radio_button_answer_1:
                checked = 1;
                break;

            case R.id.activity_radio_button_answer_2:
                checked = 2;
                break;

            case R.id.activity_radio_button_answer_3:
                checked = 3;
                break;

            case R.id.activity_radio_button_answer_4:
                checked = 4;
                break;
        }
    }

    /**
     * Actions to be done when submit button is clicked on activity_radio_button.xml.
     *
     * @param view
     */
    public void submitRadioButtonAnswer(View view) {
        Intent intent;

        if (checked == 0) {
            // No answer selected.
            Toast toast = Toast.makeText(this, R.string.radio_button_mandatory, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            // Play a sound.
            MediaPlayer mediaPlayer = MediaPlayer.create(RadioButtonActivity.this, R.raw._light_saber);
            mediaPlayer.start();

            // Select next activity.
            switch (questionNumber) {
                case 1:
                case 5:
                case 9:
                    intent = new Intent(this, EditTextActivity.class);
                    break;

                default:
                    intent = new Intent(this, CheckBoxActivity.class);
                    break;
            }

            if (checked == radioButtonQuestion.getCorrectAnswer()) {
                // Right answer.
                rightAnswers = rightAnswers + 1;
            }

            // Start next activity.
            intent.putExtra("right_answers", rightAnswers);
            intent.putExtra("player_name", playerName);
            intent.putExtra("question_number", questionNumber + 1);
            intent.putIntegerArrayListExtra("edit_text_questions_order", editTextQuestionsOrder);
            intent.putIntegerArrayListExtra("check_box_questions_order", checkBoxQuestionsOrder);
            intent.putIntegerArrayListExtra("radio_button_questions_order", radioButtonQuestionsOrder);
            startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("checked", checked);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        checked = savedInstanceState.getInt("checked");
        if (checked != 0) {
            int idQuestion = getResources().getIdentifier("activity_radio_button_answer_" + checked, "id", getPackageName());
            RadioButton radioButton = (RadioButton) findViewById(idQuestion);
            radioButton.setChecked(true);
        }
    }
}
