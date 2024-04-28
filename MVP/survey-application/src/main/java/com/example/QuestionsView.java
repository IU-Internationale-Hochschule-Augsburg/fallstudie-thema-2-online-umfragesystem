package com.example;

import com.example.entity.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionsView {
    String title;

    Long surveyId;

    List<Question> questions = new ArrayList<>();
}
