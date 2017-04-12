package com.example.android.educationalapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckBoxActivity extends AppCompatActivity {
    final int NUMBER_OF_QUESTIONS = 9;
    int questionNumber, rightAnswers;
    ArrayList<CheckBoxQuestion> checkBoxQuestions;
    ArrayList<String> correctAnswers;
    CheckBoxQuestion checkBoxQuestion;
    String playerName;
    boolean checked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_check_box);

        // Get context for this activity.
        getContext();

        // Set title.
        TextView textViewQuestionNumber = (TextView) findViewById(R.id.check_box_question_number);
        textViewQuestionNumber.setText(getResources().getString(R.string.question_title, questionNumber));

        // Set advice.
        TextView textViewAdvice = (TextView) findViewById(R.id.check_box_advice);
        textViewAdvice.setText(getResources().getString(R.string.check_box_advice, playerName));

        // Get random question.
        initQuestions();
        int randomQuestion = (int) (Math.random() * NUMBER_OF_QUESTIONS) + 1;
        checkBoxQuestion = checkBoxQuestions.get(randomQuestion - 1);

        // Set background image.
        ImageView imageView = (ImageView) findViewById(R.id.check_box_background_image);
        switch (questionNumber) {
            case 4:
                imageView.setImageResource(R.drawable._04_r2_d2);
                break;

            case 8:
                imageView.setImageResource(R.drawable._08_luke_skywalker);
                break;
        }

        // Show question and answers.
        TextView textViewQuestion = (TextView) findViewById(R.id.check_box_description);
        textViewQuestion.setText(checkBoxQuestion.getQuestion());

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.check_box_1);
        checkBox1.setText(checkBoxQuestion.getAnswers().get(0));
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.check_box_2);
        checkBox2.setText(checkBoxQuestion.getAnswers().get(1));
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.check_box_3);
        checkBox3.setText(checkBoxQuestion.getAnswers().get(2));
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.check_box_4);
        checkBox4.setText(checkBoxQuestion.getAnswers().get(3));
    }

    /**
     * Get context variables for CheckBoxActivity.
     */
    void getContext() {
        correctAnswers = new ArrayList<String>();
        for (int i = 0; i < 4; i++) correctAnswers.add(i, "FALSE");

        playerName = getIntent().getExtras().getString("player_name");
        questionNumber = getIntent().getExtras().getInt("question_number");
        rightAnswers = getIntent().getExtras().getInt("right_answers");

        Log.i("CheckBoxActivity", "getContext - Player name: " + playerName);
        Log.i("CheckBoxActivity", "getContext - Current question: " + questionNumber + "/10");
        Log.i("CheckBoxActivity", "getContext - Right answers so far: " + rightAnswers);
    }

    /**
     * Builds an ArrayList of CheckBoxQuestion with all the questions and answers for
     * CheckBoxActivity found at strings.xml.
     */
    void initQuestions() {
        CheckBoxQuestion checkBoxQuestion;
        ArrayList<String> answers, rightAnswers;
        String question, answer, rightAnswer;
        int idAnswer, idQuestion, idRightAnswer;

        checkBoxQuestions = new ArrayList<CheckBoxQuestion>();

        for (int n = 0; n < NUMBER_OF_QUESTIONS; n++) {
            idQuestion = getResources().getIdentifier("check_box_question_" + (n + 1), "string", getPackageName());
            question = getResources().getString(idQuestion).toUpperCase();
            Log.i("CheckBoxActivity", "initQuestions - Question " + (n + 1) + ": " + question);

            answers = new ArrayList<String>();
            for (int i = 0; i < 4; i++) {
                idAnswer = getResources().getIdentifier("check_box_question_" + (n + 1) + "_answer_" + (i + 1), "string", getPackageName());
                answer = getResources().getString(idAnswer).toUpperCase();
                Log.i("CheckBoxActivity", "initQuestions - Answer " + (n + 1) + "." + (i + 1) + ": " + answer);
                answers.add(i, answer);
            }

            rightAnswers = new ArrayList<String>();
            for (int i = 0; i < 4; i++) {
                idRightAnswer = getResources().getIdentifier("check_box_question_" + (n + 1) + "_answer_" + (i + 1) + "_is_correct", "string", getPackageName());
                rightAnswer = getResources().getString(idRightAnswer).toUpperCase();
                Log.i("CheckBoxActivity", "initQuestions - Answer " + (n + 1) + "." + (i + 1) + " is correct: " + rightAnswer);
                rightAnswers.add(i, rightAnswer);
            }

            checkBoxQuestion = new CheckBoxQuestion(question, answers, rightAnswers);
            checkBoxQuestions.add(n, checkBoxQuestion);
        }
    }

    /**
     * Actions to be done when a CheckBox is checked on activity_check_box.xml.
     *
     * @param view
     */
    public void selectAnswer(View view) {
        // Play a sound.
        MediaPlayer mediaPlayer = MediaPlayer.create(CheckBoxActivity.this, R.raw.huge_door);
        mediaPlayer.start();

        int viewId = view.getId();
        CheckBox checkBox = (CheckBox) findViewById(viewId);
        switch (viewId) {
            case R.id.check_box_1:
                if (checkBox.isChecked()) {
                    checked = true;
                    correctAnswers.set(0, "TRUE");
                } else correctAnswers.set(0, "FALSE");
                break;

            case R.id.check_box_2:
                if (checkBox.isChecked()) {
                    checked = true;
                    correctAnswers.set(1, "TRUE");
                } else correctAnswers.set(1, "FALSE");
                break;

            case R.id.check_box_3:
                if (checkBox.isChecked()) {
                    checked = true;
                    correctAnswers.set(2, "TRUE");
                } else correctAnswers.set(2, "FALSE");
                break;

            case R.id.check_box_4:
                if (checkBox.isChecked()) {
                    checked = true;
                    correctAnswers.set(3, "TRUE");
                } else correctAnswers.set(3, "FALSE");
                break;
        }
    }

    /**
     * Actions to be done when submit button is clicked on activity_check_box.xml.
     *
     * @param view
     */
    void submitAnswer(View view) {
        if (!checked) {
            // No answer selected.
            Toast toast = Toast.makeText(this, R.string.radio_button_mandatory, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            // Play a sound.
            MediaPlayer mediaPlayer = MediaPlayer.create(CheckBoxActivity.this, R.raw.light_saber);
            mediaPlayer.start();

            if (correctAnswers.equals(checkBoxQuestion.getRightAnswers())) {
                // Right answer.
                rightAnswers = rightAnswers + 1;
            }

            // Start next activity.
            Intent intent = new Intent(this, RadioButtonActivity.class);
            intent.putExtra("right_answers", rightAnswers);
            intent.putExtra("player_name", playerName);
            intent.putExtra("question_number", questionNumber + 1);
            startActivity(intent);
        }
    }
}