package com.surveymaster;

import lombok.Data;

@Data
public class AnswerView {

    private Long answerId;

    private String title;

    private Long questionId;

    // Saving the answer options
    private char answerOption1;
    private char answerOption2;
    private char answerOption3;
    private char answerOption4;
    private char answerOption5;
    private char answerOption6;
    private char answerOption7;
    private char answerOption8;
    private char answerOption9;
    private char answerOption10;

    private String textinput;
}
