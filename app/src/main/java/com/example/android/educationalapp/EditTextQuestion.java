package com.example.android.educationalapp;

/**
 * Created by David on 12/04/2017.
 */

public class EditTextQuestion {
    private String question;
    private String answer;
    private String minAnswer;

    public EditTextQuestion(String question, String answer, String minAnswer) {
        this.question = question;
        this.answer = answer;
        this.minAnswer = minAnswer;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getminAnswer() {
        return this.minAnswer;
    }
}
