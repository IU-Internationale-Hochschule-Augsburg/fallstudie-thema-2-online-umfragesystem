package com.surveymaster;

import com.surveymaster.entity.Answer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class AnswersView {
    String questionText;

    Long questionId;

    List<Answer> answers = new ArrayList<>();
}
