package com.surveymaster;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class SurveyForm implements Serializable {
    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private Long surveyId;

    public SurveyForm() {
    }
}
