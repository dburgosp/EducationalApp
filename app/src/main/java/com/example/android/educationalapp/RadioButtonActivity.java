package com.example.android.educationalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.name;

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
        ImageView imageView = (ImageView) findViewById(R.id.background_image);
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

    void checkRadioButton1(View view) {
        checked = 1;
    }

    void checkRadioButton2(View view) {
        checked = 2;
    }

    void checkRadioButton3(View view) {
        checked = 3;
    }

    void checkRadioButton4(View view) {
        checked = 4;
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

            if (checked == radioButtonQuestion.getCorrectAnswer()) {
                // Right answer.
                rightAnswers = getIntent().getExtras().getInt("right_answers");
                intent.putExtra("right_answers", rightAnswers + 1);
            }

            // Start next activity.
            intent.putExtra("player_name", name);
            intent.putExtra("question_number", questionNumber + 1);
            startActivity(intent);
        }
    }

    void initRadioButtonQuestions() {
        RadioButtonQuestion radioButtonQuestion;
        ArrayList<String> answers;
        int idAnswer, idQuestion, idRightAnswer;

        radioButtonQuestions = new ArrayList<RadioButtonQuestion>();

        for (int n = 0; n < NUMBER_OF_QUESTIONS; n++) {
            answers = new ArrayList<String>();
            for (int i = 0; i < 4; i++) {
                idAnswer = getResources().getIdentifier("radio_button_question_" + (n + 1) + "_answer_" + (i + 1), "string", getPackageName());
                answers.add(i, getResources().getString(idAnswer));
            }
            idQuestion = getResources().getIdentifier("radio_button_question_" + (n + 1), "string", getPackageName());
            idRightAnswer = getResources().getIdentifier("radio_button_right_answer_" + (n + 1), "string", getPackageName());
            radioButtonQuestion = new RadioButtonQuestion(getResources().getString(idQuestion), answers, Integer.parseInt(getResources().getString(idRightAnswer)));
            radioButtonQuestions.add(n, radioButtonQuestion);
        }
    }
}
