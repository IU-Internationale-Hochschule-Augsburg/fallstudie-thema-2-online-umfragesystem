package com.example;

import com.example.repository.QuestionRepository;
import com.example.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class QuestionService {
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;

    public void deleteQuestion(String id) {
        var selectedQuestion = questionRepository.findById(Long.parseLong(id)).orElseThrow();
        questionRepository.deleteById(selectedQuestion.getQuestionId());
    }

    public QuestionsView getQuestionsView(String surveyId) {
        // With the surveyID, the corresponding survey is searched for.
        // If it exists, it is saved in the 'survey' variable; otherwise, a NoSuchElementException is thrown
        var survey = surveyRepository.findById(Long.parseLong(surveyId)).orElseThrow();

        // Setting the ID and title of the survey in questionView
        QuestionsView questionsView = new QuestionsView();
        questionsView.setSurveyId(survey.getSurveyId());
        questionsView.setTitle(survey.getTitle());

        // The surveyID is also used to search in the questionRepository, as the surveyID is also present in the
        // Question table. Then, the Question is set accordingly
        var questions = questionRepository.findBySurveyId(Long.parseLong(surveyId));
        questionsView.setQuestions(questions);
        return questionsView;
    }
}
