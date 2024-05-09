package com.example;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class SingleQuestionView {

    Long questionId;

    Long surveyId;

    String questionText;

    String questionType;

    List<String> answerOptions = new ArrayList<>();
}
