package com.surveymaster;

import lombok.Data;

/* This is a wrapper to store the answer options for the radio button and checkbox question types separately
in variables. The reason for the wrapper is that otherwise, in the HTML script, the same answer option objects
are accessed twice (for radio button and checkbox), which leads to incorrect storage of options in the H2 database. */
@Data
public class ParticipantSurveyView {
    private static Long userId = 1000L;

    private Long surveyId;

    // counter
    private int currentQuestion;

    private char radioButtonAnswerOption1;
    private char radioButtonAnswerOption2;
    private char radioButtonAnswerOption3;
    private char radioButtonAnswerOption4;
    private char radioButtonAnswerOption5;
    private char radioButtonAnswerOption6;
    private char radioButtonAnswerOption7;
    private char radioButtonAnswerOption8;
    private char radioButtonAnswerOption9;
    private char radioButtonAnswerOption10;

    private char checkBoxAnswerOption1 = 'N';
    private char checkBoxAnswerOption2 = 'N';
    private char checkBoxAnswerOption3 = 'N';
    private char checkBoxAnswerOption4 = 'N';
    private char checkBoxAnswerOption5 = 'N';
    private char checkBoxAnswerOption6 = 'N';
    private char checkBoxAnswerOption7 = 'N';
    private char checkBoxAnswerOption8 = 'N';
    private char checkBoxAnswerOption9 = 'N';
    private char checkBoxAnswerOption10 = 'N';

    private String textinput;

    public void nextUserId() {
        userId++;
    }

    public static Long getUserId() {
        return userId;
    }
}
