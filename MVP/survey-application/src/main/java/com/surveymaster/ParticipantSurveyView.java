package com.surveymaster;

import com.surveymaster.entity.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParticipantSurveyView {
    private static Long userId = 1000L;

    private Long surveyId;

    // counter
    private int currentQuestion;

    private List<Question> questions = new ArrayList<>();

    public void nextUserId() {
        userId++;
    }

    public static Long getUserId() {
        return userId;
    }
}
