package com.example.android.educationalapp;

import java.util.ArrayList;

/**
 * Created by David on 11/04/2017.
 */

public class RadioButtonQuestion {
    private String question;
    private ArrayList<String> answers;
    private int correctAnswer;

    public RadioButtonQuestion(String question, ArrayList<String> answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return this.question;
    }

    public ArrayList<String> getAnswers() {
        return this.answers;
    }

    public int getCorrectAnswer() {
        return this.correctAnswer;
    }
}
