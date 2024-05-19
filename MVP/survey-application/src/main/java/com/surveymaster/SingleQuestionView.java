package com.surveymaster;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class SingleQuestionView {

    Long questionId;

    Long surveyId;

    String questionText;

    String questionType;

    String description;

    List<String> answerOptions = new ArrayList<>();
}
