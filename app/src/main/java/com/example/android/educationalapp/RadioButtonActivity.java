package com.example.android.educationalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RadioButtonActivity extends AppCompatActivity {
    final int NUMBER_OF_QUESTIONS = 47;
    ArrayList<RadioButtonQuestion> radioButtonQuestions;
    RadioButtonQuestion radioButtonQuestion;
    int checked = 0, questionNumber, rightAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_radio_button);

        // Set title.
        questionNumber = getIntent().getExtras().getInt("question_number");
        TextView textViewQuestionNumber = (TextView) findViewById(R.id.radio_button_question_number);
        textViewQuestionNumber.setText(getResources().getString(R.string.question_title, questionNumber));

        // Get random question.
        initRadioButtonQuestions();
        int randomQuestion = (int) (Math.random() * NUMBER_OF_QUESTIONS) + 1;
        radioButtonQuestion = radioButtonQuestions.get(randomQuestion - 1);

        // Set background image.
        ImageView imageView = (ImageView) findViewById(R.id.radio_button_background_image);
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
        imageView.refreshDrawableState();

        // Show question and answers.
        TextView textViewQuestion = (TextView) findViewById(R.id.check_box_description);
        textViewQuestion.setText(radioButtonQuestion.getQuestion());
        RadioButton radioButton1 = (RadioButton) findViewById(R.id.radio_button_1);
        radioButton1.setText(radioButtonQuestion.getAnswers().get(0));
        RadioButton radioButton2 = (RadioButton) findViewById(R.id.radio_button_2);
        radioButton2.setText(radioButtonQuestion.getAnswers().get(1));
        RadioButton radioButton3 = (RadioButton) findViewById(R.id.radio_button_3);
        radioButton3.setText(radioButtonQuestion.getAnswers().get(2));
        RadioButton radioButton4 = (RadioButton) findViewById(R.id.radio_button_4);
        radioButton4.setText(radioButtonQuestion.getAnswers().get(3));
    }

    public void checkRadioButton(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.radio_button_1:
                checked = 1;
                break;

            case R.id.radio_button_2:
                checked = 2;
                break;

            case R.id.radio_button_3:
                checked = 3;
                break;

            case R.id.radio_button_4:
                checked = 4;
                break;
        }
    }

    void submitAnswer(View view) {
        Intent intent;

        if (checked == 0) {
            // No answer selected.
            Toast toast = Toast.makeText(this, R.string.radio_button_mandatory, Toast.LENGTH_SHORT);
            toast.show();
        } else {
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

            rightAnswers = getIntent().getExtras().getInt("right_answers");
            if (checked == radioButtonQuestion.getCorrectAnswer()) {
                // Right answer.
                rightAnswers = rightAnswers + 1;
            }

            // Start next activity.
            intent.putExtra("right_answers", rightAnswers);
            intent.putExtra("player_name", getIntent().getExtras().getString("player_name"));
            intent.putExtra("question_number", questionNumber + 1);
            startActivity(intent);
        }
    }

    /**
     * Builds an ArrayList of RadioButtonQuestion with all the questions and answers for
     * RadioButtonActivity found at strings.xml.
     */
    void initRadioButtonQuestions() {
        RadioButtonQuestion radioButtonQuestion;
        ArrayList<String> answers;
        String question, answer;
        int idAnswer, idQuestion, idRightAnswer, rightAnswer;

        radioButtonQuestions = new ArrayList<RadioButtonQuestion>();

        for (int n = 0; n < NUMBER_OF_QUESTIONS; n++) {
            idQuestion = getResources().getIdentifier("radio_button_question_" + (n + 1), "string", getPackageName());
            question = getResources().getString(idQuestion).toUpperCase();
            Log.i("RadioButtonActivity", "Question " + (n + 1) + ": " + question);

            answers = new ArrayList<String>();
            for (int i = 0; i < 4; i++) {
                idAnswer = getResources().getIdentifier("radio_button_question_" + (n + 1) + "_answer_" + (i + 1), "string", getPackageName());
                answer = getResources().getString(idAnswer).toUpperCase();
                Log.i("RadioButtonActivity", "Answer " + (n + 1) + "." + (i + 1) + ": " + answer);
                answers.add(i, answer);
            }

            idRightAnswer = getResources().getIdentifier("radio_button_right_answer_" + (n + 1), "string", getPackageName());
            rightAnswer = Integer.parseInt(getResources().getString(idRightAnswer));
            Log.i("RadioButtonActivity", "Right answer " + (n + 1) + ": " + rightAnswer);

            radioButtonQuestion = new RadioButtonQuestion(question, answers, rightAnswer);
            radioButtonQuestions.add(n, radioButtonQuestion);
        }
    }
}
