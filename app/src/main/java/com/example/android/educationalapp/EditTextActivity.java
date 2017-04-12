package com.example.android.educationalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EditTextActivity extends AppCompatActivity {
    final int NUMBER_OF_QUESTIONS = 14;
    int questionNumber;
    ArrayList<EditTextQuestion> editTextQuestions;
    EditTextQuestion editTextQuestion;
    String answer, minAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_edit_text);

        // Set title.
        questionNumber = getIntent().getExtras().getInt("question_number");
        TextView textViewQuestionNumber = (TextView) findViewById(R.id.edit_text_question_number);
        textViewQuestionNumber.setText(getResources().getString(R.string.question_title, questionNumber));

        // Set advice.
        TextView textViewAdvice = (TextView) findViewById(R.id.edit_text_advice);
        textViewAdvice.setText(getResources().getString(R.string.edit_text_advice, getIntent().getExtras().getString("player_name")));

        // Get random question.
        initEditTextQuestions();
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
        imageView.refreshDrawableState();

        // Show question and get answers.
        TextView textViewQuestion = (TextView) findViewById(R.id.edit_text_description);
        textViewQuestion.setText(editTextQuestion.getQuestion());
        answer = editTextQuestion.getAnswer();
        minAnswer = editTextQuestion.getminAnswer();
    }

    public void submitAnswer(View view) {

    }

    /**
     * Builds an ArrayList of EditTextQuestion with all the questions and answers for
     * EditTextActivity found at strings.xml.
     */
    void initEditTextQuestions() {
        EditTextQuestion editTextQuestion;
        int idQuestion, idAnswer, idMinAnswer;
        String question, answer, minAnswer;

        editTextQuestions = new ArrayList<EditTextQuestion>();

        for (int n = 0; n < NUMBER_OF_QUESTIONS; n++) {
            idQuestion = getResources().getIdentifier("edit_text_question_" + (n + 1), "string", getPackageName());
            question = getResources().getString(idQuestion).toUpperCase();
            Log.i("EditTextActivity", "Question " + (n + 1) + ": " + question);

            idAnswer = getResources().getIdentifier("edit_text_answer_" + (n + 1), "string", getPackageName());
            answer = getResources().getString(idAnswer).toUpperCase();
            Log.i("EditTextActivity", "Answer " + (n + 1) + ": " + answer);

            idMinAnswer = getResources().getIdentifier("edit_text_min_answer_" + (n + 1), "string", getPackageName());
            minAnswer = getResources().getString(idMinAnswer).toUpperCase();
            Log.i("EditTextActivity", "Min. answer " + (n + 1) + ": " + minAnswer);

            editTextQuestion = new EditTextQuestion(question, answer, minAnswer);
            editTextQuestions.add(n, editTextQuestion);
        }
        return;
    }
}
