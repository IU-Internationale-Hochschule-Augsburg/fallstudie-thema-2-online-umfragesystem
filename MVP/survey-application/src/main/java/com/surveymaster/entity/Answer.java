package com.surveymaster.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data

public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long answerId;

    private Long questionId;

    private Long userId;

    // We store the checked state of the answer options in the database using the characters 'Y' for yes and 'N' for no
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

    public Answer() {
    }

    public Answer(Long questionId, char answerOption1, char answerOption2, char answerOption3, char answerOption4,
                  char answerOption5, char answerOption6, char answerOption7, char answerOption8, char answerOption9, char answerOption10) {
        this.questionId = questionId;
        this.answerOption1 = answerOption1;
        this.answerOption2 = answerOption2;
        this.answerOption3 = answerOption3;
        this.answerOption4 = answerOption4;
        this.answerOption5 = answerOption5;
        this.answerOption6 = answerOption6;
        this.answerOption7 = answerOption7;
        this.answerOption8 = answerOption8;
        this.answerOption9 = answerOption9;
        this.answerOption10 = answerOption10;
    }

    public Answer(Long questionId, String textinput) {
        this.questionId = questionId;
        this.textinput = textinput;
    }
}
