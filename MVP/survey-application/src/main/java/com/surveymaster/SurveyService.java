package com.surveymaster;

import com.surveymaster.entity.Question;
import com.surveymaster.repository.QuestionRepository;
import com.surveymaster.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    public void deleteSurvey(String surveyId) {
        surveyRepository.deleteById(Long.parseLong(surveyId));

        // delete questions in loop
        // when a survey is deleted, its associated questions should also be deleted
        final var selectedQuestions = questionRepository.findBySurveyId(Long.parseLong(surveyId));
        for (final Question selectedQuestion : selectedQuestions) {
            questionRepository.deleteById(selectedQuestion.getQuestionId());
        }
    }
}
