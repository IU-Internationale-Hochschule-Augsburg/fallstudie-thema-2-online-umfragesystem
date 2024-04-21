package com.example.entity;
import jakarta.persistence.*;

@Entity
@Table
public class Survey {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long surveyId;
    private String textInput;

    protected Survey(){}

    public Survey(String textInput) {
        this.textInput = textInput;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public String getTextInput() {
        return textInput;
    }
}
