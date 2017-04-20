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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckBoxActivity extends AppCompatActivity {
    int questionNumber, rightAnswers;
    String playerName, question;
    CheckBoxQuestion checkBoxQuestion;
    ArrayList<String> arrayOfCurrentAnswers, arrayOfAnswers, arrayOfRightAnswers;
    ArrayList<Integer> editTextQuestionsOrder, checkBoxQuestionsOrder, radioButtonQuestionsOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_check_box);

        // Set fonts.
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/starwars.ttf");
        TextView textView1 = (TextView) findViewById(R.id.activity_check_box_title);
        Button button1 = (Button) findViewById(R.id.activity_check_box_submit_button);
        TextView textView3 = (TextView) findViewById(R.id.activity_check_box_question_number);
        textView1.setTypeface(custom_font);
        button1.setTypeface(custom_font);
        textView3.setTypeface(custom_font);
        button1.setAllCaps(true);

        // Get context for this activity.
        getContext();

        // Set title.
        textView3.setText(getResources().getString(R.string.question_title, questionNumber));

        // Set advice.
        TextView textViewAdvice = (TextView) findViewById(R.id.activity_check_box_advice);
        textViewAdvice.setText(getResources().getString(R.string.check_box_advice, playerName));

        // Set background image.
        ImageView imageView = (ImageView) findViewById(R.id.activity_check_box_background);
        switch (questionNumber) {
            case 4:
                imageView.setImageResource(R.drawable._04_r2_d2);
                break;

            case 8:
                imageView.setImageResource(R.drawable._08_luke_skywalker);
                break;
        }

        // Get current question.
        int index = checkBoxQuestionsOrder.get(questionNumber - 1);
        readQuestion(index - 1);
        checkBoxQuestion = new CheckBoxQuestion(question, arrayOfAnswers, arrayOfRightAnswers);

        // Show question and answers.
        TextView textViewQuestion = (TextView) findViewById(R.id.activity_check_box_description);
        textViewQuestion.setText(checkBoxQuestion.getQuestion());

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.activity_check_box_answer_1);
        checkBox1.setText(checkBoxQuestion.getAnswers().get(0));
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.activity_check_box_answer_2);
        checkBox2.setText(checkBoxQuestion.getAnswers().get(1));
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.activity_check_box_answer_3);
        checkBox3.setText(checkBoxQuestion.getAnswers().get(2));
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.activity_check_box_answer_4);
        checkBox4.setText(checkBoxQuestion.getAnswers().get(3));
    }

    /**
     * Get context variables for CheckBoxActivity.
     */
    void getContext() {
        arrayOfCurrentAnswers = new ArrayList<String>();
        for (int i = 0; i < 4; i++) arrayOfCurrentAnswers.add(i, "FALSE");

        editTextQuestionsOrder = new ArrayList<Integer>();
        checkBoxQuestionsOrder = new ArrayList<Integer>();
        radioButtonQuestionsOrder = new ArrayList<Integer>();

        playerName = getIntent().getExtras().getString("player_name");
        questionNumber = getIntent().getExtras().getInt("question_number");
        rightAnswers = getIntent().getExtras().getInt("right_answers");
        editTextQuestionsOrder = getIntent().getExtras().getIntegerArrayList("edit_text_questions_order");
        checkBoxQuestionsOrder = getIntent().getExtras().getIntegerArrayList("check_box_questions_order");
        radioButtonQuestionsOrder = getIntent().getExtras().getIntegerArrayList("radio_button_questions_order");

        Log.i("CheckBoxActivity", "getContext - Player name: " + playerName);
        Log.i("CheckBoxActivity", "getContext - Current question: " + questionNumber + "/10");
        Log.i("CheckBoxActivity", "getContext - Right answers so far: " + rightAnswers);
    }

    /**
     * Reads question n from strings.xml.
     *
     * @param n number of the question.
     */
    void readQuestion(int n) {
        int idAnswer, idQuestion, idRightAnswer;
        String answer, rightAnswer;

        idQuestion = getResources().getIdentifier("check_box_question_" + (n + 1), "string", getPackageName());
        question = getResources().getString(idQuestion).toUpperCase();
        Log.i("CheckBoxActivity", "initQuestions - Question " + (n + 1) + ": " + question);

        arrayOfAnswers = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            idAnswer = getResources().getIdentifier("check_box_question_" + (n + 1) + "_answer_" + (i + 1), "string", getPackageName());
            answer = getResources().getString(idAnswer).toUpperCase();
            Log.i("CheckBoxActivity", "initQuestions - Answer " + (n + 1) + "." + (i + 1) + ": " + answer);
            arrayOfAnswers.add(i, answer);
        }

        arrayOfRightAnswers = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            idRightAnswer = getResources().getIdentifier("check_box_question_" + (n + 1) + "_answer_" + (i + 1) + "_is_correct", "string", getPackageName());
            rightAnswer = getResources().getString(idRightAnswer).toUpperCase();
            Log.i("CheckBoxActivity", "initQuestions - Answer " + (n + 1) + "." + (i + 1) + " is correct: " + rightAnswer);
            arrayOfRightAnswers.add(i, rightAnswer);
        }
    }

    /**
     * Actions to be done when a CheckBox is checked on activity_check_box.xml.
     *
     * @param view
     */
    public void selectAnswer(View view) {
        // Play a sound.
        MediaPlayer mediaPlayer = MediaPlayer.create(CheckBoxActivity.this, R.raw._huge_door);
        mediaPlayer.start();
        int viewId = view.getId();
        CheckBox checkBox = (CheckBox) findViewById(viewId);
        switch (viewId) {
            case R.id.activity_check_box_answer_1:
                if (checkBox.isChecked()) arrayOfCurrentAnswers.set(0, "TRUE");
                else arrayOfCurrentAnswers.set(0, "FALSE");
                break;

            case R.id.activity_check_box_answer_2:
                if (checkBox.isChecked()) arrayOfCurrentAnswers.set(1, "TRUE");
                else arrayOfCurrentAnswers.set(1, "FALSE");
                break;

            case R.id.activity_check_box_answer_3:
                if (checkBox.isChecked()) arrayOfCurrentAnswers.set(2, "TRUE");
                else arrayOfCurrentAnswers.set(2, "FALSE");
                break;

            case R.id.activity_check_box_answer_4:
                if (checkBox.isChecked()) arrayOfCurrentAnswers.set(3, "TRUE");
                else arrayOfCurrentAnswers.set(3, "FALSE");
                break;
        }
    }

    boolean isChecked() {
        boolean checked = false;
        int i = 1;
        while ((!checked) && (i <= 4)) {
            int idCheckBox = getResources().getIdentifier("activity_check_box_answer_" + i, "id", getPackageName());
            CheckBox checkBox = (CheckBox) findViewById(idCheckBox);
            checked = checkBox.isChecked();
            i++;
        }
        return checked;
    }

    /**
     * Actions to be done when submit button is clicked on activity_check_box.xml.
     *
     * @param view
     */
    public void submitCheckBoxAnswer(View view) {
        if (!isChecked()) {
            // No answer selected.
            Toast toast = Toast.makeText(this, R.string.radio_button_mandatory, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            // Play a sound.
            MediaPlayer mediaPlayer = MediaPlayer.create(CheckBoxActivity.this, R.raw._light_saber);
            mediaPlayer.start();

            if (arrayOfCurrentAnswers.equals(checkBoxQuestion.getRightAnswers())) {
                // Right answer.
                rightAnswers = rightAnswers + 1;
            }

            // Start next activity.
            Intent intent = new Intent(this, RadioButtonActivity.class);
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
        outState.putStringArrayList("arrayOfCurrentAnswers", arrayOfCurrentAnswers);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        arrayOfCurrentAnswers = savedInstanceState.getStringArrayList("arrayOfCurrentAnswers");
        for (int i = 1; i <= 4; i++) {
            int idCheckBox = getResources().getIdentifier("activity_check_box_answer_" + i, "id", getPackageName());
            CheckBox checkBox = (CheckBox) findViewById(idCheckBox);
            if (arrayOfCurrentAnswers.get(i - 1).equals("TRUE")) checkBox.setChecked(true);
            else checkBox.setChecked(false);
        }
    }
}