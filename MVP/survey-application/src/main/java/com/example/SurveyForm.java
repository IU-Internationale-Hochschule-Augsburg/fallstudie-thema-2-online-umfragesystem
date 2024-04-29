package com.example;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class SurveyForm implements Serializable {
    private String title;

    private LocalDate startdate;

    private LocalDate enddate;

    private String description;

    //added
    private Long surveyId;

    public SurveyForm() {
    }
}
