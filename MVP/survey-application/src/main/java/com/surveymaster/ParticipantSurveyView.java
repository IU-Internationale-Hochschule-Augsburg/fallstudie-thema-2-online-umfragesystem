package com.surveymaster;

import com.surveymaster.entity.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParticipantSurveyView {
    private Long surveyId;

    private String title;

    // counter
    private int currentQuestion;

    private List<Question> questions = new ArrayList<>();

}
