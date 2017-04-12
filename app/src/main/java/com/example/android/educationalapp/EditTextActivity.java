package com.example.android.educationalapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditTextActivity extends AppCompatActivity {
    final int NUMBER_OF_QUESTIONS = 14;
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

        setContentView(R.layout.activity_edit_text);

        // Get context for this activity.
        getContext();

        // Set title.
        TextView textViewQuestionNumber = (TextView) findViewById(R.id.edit_text_question_number);
        textViewQuestionNumber.setText(getResources().getString(R.string.question_title, questionNumber));

        // Set advice.
        TextView textViewAdvice = (TextView) findViewById(R.id.edit_text_advice);
        textViewAdvice.setText(getResources().getString(R.string.edit_text_advice, playerName));

        // Get random question.
        initQuestions();
        int randomQuestion = (int) (Math.random() * NUMBER_OF_QUESTIONS) + 1;
        editTextQuestion = editTextQuestions.get(randomQuestion - 1);

        // Set background image.
        ImageView imageView = (ImageView) findViewById(R.id.edit_text_background_image);
        switch (questionNumber) {
            case 2:
                imageView.setImageResource(R.drawable._02_boba_fett);
                break;
            case 6:
                imageView.setImageResource(R.drawable._06_leia_organa);
                break;
            case 10:
                imageView.setImageResource(R.drawable._10_yoda);
                break;
        }

        // Show question and get answers.
        TextView textViewQuestion = (TextView) findViewById(R.id.edit_text_description);
        textViewQuestion.setText(editTextQuestion.getQuestion());
        rightAnswer = editTextQuestion.getAnswer();
        minAnswer = editTextQuestion.getminAnswer();
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

    /**
     * Builds an ArrayList of EditTextQuestion with all the questions and answers for
     * EditTextActivity found at strings.xml.
     */
    void initQuestions() {
        EditTextQuestion editTextQuestion;
        int idQuestion, idAnswer, idMinAnswer;
        String question, answer, minAnswer;

        editTextQuestions = new ArrayList<EditTextQuestion>();

        for (int n = 0; n < NUMBER_OF_QUESTIONS; n++) {
            idQuestion = getResources().getIdentifier("edit_text_question_" + (n + 1), "string", getPackageName());
            question = getResources().getString(idQuestion).toUpperCase();
            Log.i("EditTextActivity", "initQuestions - Question " + (n + 1) + ": " + question);

            idAnswer = getResources().getIdentifier("edit_text_answer_" + (n + 1), "string", getPackageName());
            answer = getResources().getString(idAnswer).toUpperCase();
            Log.i("EditTextActivity", "initQuestions - Answer " + (n + 1) + ": " + answer);

            idMinAnswer = getResources().getIdentifier("edit_text_min_answer_" + (n + 1), "string", getPackageName());
            minAnswer = getResources().getString(idMinAnswer).toUpperCase();
            Log.i("EditTextActivity", "initQuestions - Min. answer " + (n + 1) + ": " + minAnswer);

            editTextQuestion = new EditTextQuestion(question, answer, minAnswer);
            editTextQuestions.add(n, editTextQuestion);
        }
    }

    /**
     * Actions to be done when submit button is clicked on activity_edit_text.xml.
     *
     * @param view
     */
    public void submitAnswer(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_text_answer);
        String answer = editText.getText().toString().toUpperCase();
        if (answer.isEmpty()) {
            // Answer cannot be empty.
            Toast toast = Toast.makeText(this, R.string.type_answer_error, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            // Play a sound.
            MediaPlayer mediaPlayer = MediaPlayer.create(EditTextActivity.this, R.raw.light_saber);
            mediaPlayer.start();

            if ((answer.equals(minAnswer)) || (answer.equals(rightAnswer))) {
                // Right answer.
                rightAnswers = rightAnswers + 1;
            }

            // Start next activity.
            Intent intent;
            if (questionNumber == 10)
                intent = new Intent(this, ResultsActivity.class);
            else {
                questionNumber = questionNumber + 1;
                intent = new Intent(this, RadioButtonActivity.class);
            }
            intent.putExtra("right_answers", rightAnswers);
            intent.putExtra("player_name", playerName);
            intent.putExtra("question_number", questionNumber);
            startActivity(intent);
        }
    }
}
