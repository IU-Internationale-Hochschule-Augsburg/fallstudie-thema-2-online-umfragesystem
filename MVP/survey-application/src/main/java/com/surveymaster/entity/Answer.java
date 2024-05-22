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
}
