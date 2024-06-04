package com.surveymaster;

import lombok.Data;

/* This is a data transfer object to store the answer options for the radio button and checkbox question types separately
in variables. The reason for the dto is that otherwise, in the HTML script, the same answer option objects
are accessed twice (for radio button and checkbox), which leads to incorrect storage of options in the H2 database. */
@Data
public class ParticipantSurveyView {
    private static Long userId = 1000L;

    private Long surveyId;

    // counter
    private int currentQuestion;

    private char radioButtonAnswerOption1 = 'N';
    private char radioButtonAnswerOption2 = 'N';
    private char radioButtonAnswerOption3 = 'N';
    private char radioButtonAnswerOption4 = 'N';
    private char radioButtonAnswerOption5 = 'N';
    private char radioButtonAnswerOption6 = 'N';
    private char radioButtonAnswerOption7 = 'N';
    private char radioButtonAnswerOption8 = 'N';
    private char radioButtonAnswerOption9 = 'N';
    private char radioButtonAnswerOption10 = 'N';

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
