package com.example;

import com.example.entity.Question;
import com.example.repository.QuestionRepository;
import com.example.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    public void deleteSurvey(String id) {
        surveyRepository.deleteById(Long.parseLong(id));

        // delete questions in loop
        // when a survey is deleted, its associated questions should also be deleted
        var selectedQuestions = questionRepository.findBySurveyId(Long.parseLong(id));
        for (Question selectedQuestion : selectedQuestions) {
            questionRepository.deleteById(selectedQuestion.getQuestionId());
        }
    }
}
