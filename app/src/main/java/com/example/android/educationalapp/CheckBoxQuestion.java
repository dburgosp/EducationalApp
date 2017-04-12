package com.example.android.educationalapp;

import java.util.ArrayList;

/**
 * Created by David on 12/04/2017.
 */

public class CheckBoxQuestion {
    private String question;
    private ArrayList<String> answers;
    private ArrayList<String> rightAnswers;

    public CheckBoxQuestion(String question, ArrayList<String> answers, ArrayList<String> rightAnswers) {
        this.question = question;
        this.answers = answers;
        this.rightAnswers = rightAnswers;
    }

    public String getQuestion() {
        return this.question;
    }

    public ArrayList<String> getAnswers() {
        return this.answers;
    }

    public ArrayList<String> getRightAnswers() {
        return this.rightAnswers;
    }
}
