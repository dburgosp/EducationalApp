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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditTextActivity extends AppCompatActivity {
    int questionNumber, rightAnswers;
    String playerName, rightAnswer, question, answer, minAnswer;
    EditTextQuestion editTextQuestion;
    ImageView imageView;
    ArrayList<Integer> editTextQuestionsOrder, checkBoxQuestionsOrder, radioButtonQuestionsOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_edit_text);

        // Set fonts.
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/starwars.ttf");
        TextView textView1 = (TextView) findViewById(R.id.activity_edit_text_title);
        Button button1 = (Button) findViewById(R.id.activity_edit_text_submit_button);
        TextView textView3 = (TextView) findViewById(R.id.activity_edit_text_question_number);
        textView1.setTypeface(custom_font);
        button1.setTypeface(custom_font);
        textView3.setTypeface(custom_font);
        button1.setAllCaps(true);

        // Get context for this activity.
        getContext();

        // Set title.
        textView3.setText(getResources().getString(R.string.question_title, questionNumber));

        // Set advice.
        TextView textViewAdvice = (TextView) findViewById(R.id.activity_edit_text_advice);
        textViewAdvice.setText(getResources().getString(R.string.edit_text_advice, playerName));

        // Get current question.
        int index = editTextQuestionsOrder.get(questionNumber - 1);
        readQuestion(index - 1);
        editTextQuestion = new EditTextQuestion(question, answer, minAnswer);

        // Set background image.
        imageView = (ImageView) findViewById(R.id.activity_edit_text_background);
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
        TextView textViewQuestion = (TextView) findViewById(R.id.activity_edit_text_description);
        textViewQuestion.setText(editTextQuestion.getQuestion());
        rightAnswer = editTextQuestion.getAnswer();
        minAnswer = editTextQuestion.getminAnswer();
    }

    /**
     * Get context variables for EditTextActivity.
     */
    void getContext() {
        editTextQuestionsOrder = new ArrayList<>();
        checkBoxQuestionsOrder = new ArrayList<>();
        radioButtonQuestionsOrder = new ArrayList<>();

        playerName = getIntent().getExtras().getString("player_name");
        questionNumber = getIntent().getExtras().getInt("question_number");
        rightAnswers = getIntent().getExtras().getInt("right_answers");
        editTextQuestionsOrder = getIntent().getExtras().getIntegerArrayList("edit_text_questions_order");
        checkBoxQuestionsOrder = getIntent().getExtras().getIntegerArrayList("check_box_questions_order");
        radioButtonQuestionsOrder = getIntent().getExtras().getIntegerArrayList("radio_button_questions_order");

        Log.i("EditTextActivity", "getContext - Player name: " + playerName);
        Log.i("EditTextActivity", "getContext - Current question: " + questionNumber + "/10");
        Log.i("EditTextActivity", "getContext - Right answers so far: " + rightAnswers);
    }

    /**
     * Reads question n from strings.xml.
     *
     * @param n number of the question.
     */
    void readQuestion(int n) {
        int idAnswer, idQuestion, idMinAnswer;

        idQuestion = getResources().getIdentifier("edit_text_question_" + (n + 1), "string", getPackageName());
        question = getResources().getString(idQuestion).toUpperCase();
        Log.i("EditTextActivity", "initQuestions - Question " + (n + 1) + ": " + question);

        idAnswer = getResources().getIdentifier("edit_text_answer_" + (n + 1), "string", getPackageName());
        answer = getResources().getString(idAnswer).toUpperCase();
        Log.i("EditTextActivity", "initQuestions - Answer " + (n + 1) + ": " + answer);

        idMinAnswer = getResources().getIdentifier("edit_text_min_answer_" + (n + 1), "string", getPackageName());
        minAnswer = getResources().getString(idMinAnswer).toUpperCase();
        Log.i("EditTextActivity", "initQuestions - Min. answer " + (n + 1) + ": " + minAnswer);
    }

    /**
     * Actions to be done when submit button is clicked on activity_edit_text.xml.
     *
     * @param view from which this method is called.
     */
    public void submitEditTextAnswer(View view) {
        EditText editText = (EditText) findViewById(R.id.activity_edit_text_answer);
        String answer = editText.getText().toString().toUpperCase();
        if (answer.isEmpty()) {
            // Answer cannot be empty.
            Toast toast = Toast.makeText(this, R.string.type_answer_error, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            // Play a sound.
            MediaPlayer mediaPlayer = MediaPlayer.create(EditTextActivity.this, R.raw._light_saber);
            mediaPlayer.start();

            if ((answer.equals(minAnswer)) || (answer.equals(rightAnswer))) {
                // Right answer.
                rightAnswers = rightAnswers + 1;
            }

            // Free memory.
            imageView.setBackground(null);

            // Start next activity.
            Intent intent;
            if (questionNumber == 10)
                intent = new Intent(this, FinalScoreActivity.class);
            else {
                questionNumber = questionNumber + 1;
                intent = new Intent(this, RadioButtonActivity.class);
                intent.putExtra("question_number", questionNumber);
                intent.putIntegerArrayListExtra("edit_text_questions_order", editTextQuestionsOrder);
                intent.putIntegerArrayListExtra("check_box_questions_order", checkBoxQuestionsOrder);
                intent.putIntegerArrayListExtra("radio_button_questions_order", radioButtonQuestionsOrder);
            }
            intent.putExtra("right_answers", rightAnswers);
            intent.putExtra("player_name", playerName);
            startActivity(intent);
            finish();
        }
    }
}
