package com.surveymaster.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;

    private Long surveyId;

    private String questionType;

    private String questionText;

    // Saving the answer options for the Radiobutton question type
    private String radiobutton1;
    private String radiobutton2;
    private String radiobutton3;
    private String radiobutton4;
    private String radiobutton5;
    private String radiobutton6;
    private String radiobutton7;
    private String radiobutton8;
    private String radiobutton9;
    private String radiobutton10;

    // Saving the answer options for the Checkbox question type
    private String checkbox1;
    private String checkbox2;
    private String checkbox3;
    private String checkbox4;
    private String checkbox5;
    private String checkbox6;
    private String checkbox7;
    private String checkbox8;
    private String checkbox9;
    private String checkbox10;

    public Question() {
    }

    public Question(String questionType, String questionText, String radiobutton1, String radiobutton2) {
        this.questionType = questionType;
        this.questionText = questionText;
        this.radiobutton1 = radiobutton1;
        this.radiobutton2 = radiobutton2;
    }

    public Question(Long surveyId, String questionType, String questionText) {
        this.surveyId = surveyId;
        this.questionType = questionType;
        this.questionText = questionText;
    }
}
