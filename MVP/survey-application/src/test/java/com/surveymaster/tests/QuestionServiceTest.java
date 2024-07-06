// QUESTION TEST: Testing breadth is chosen over testing depth to increase coverage.
// Hence, we simply differentiate between "good" and "bad" cases.

// zu testen: LoginController, ParticipantSurveyController, QuestionController, QuestionService, SurveyController, SurveyService, UserService

package com.surveymaster.tests;

import com.surveymaster.QuestionService;
import com.surveymaster.QuestionsView;
import com.surveymaster.entity.Question;
import com.surveymaster.entity.Survey;
import com.surveymaster.repository.AnswerRepository;
import com.surveymaster.repository.QuestionRepository;
import com.surveymaster.repository.SurveyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionServiceTest {

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteQuestion() {
        String questionId = "1";
        Question question = new Question();
        question.setQuestionId(1L);

        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        questionService.deleteQuestion(questionId);

        verify(answerRepository).deleteByQuestionId(1L);
        verify(questionRepository).deleteById(1L);
    }

    @Test
    void testGetQuestionsView() {
        String surveyId = "1";
        Survey survey = new Survey();
        survey.setSurveyId(1L);
        survey.setTitle("Test Survey");

        List<Question> questions = Arrays.asList(
                new Question(),
                new Question()
        );

        when(surveyRepository.findById(1L)).thenReturn(Optional.of(survey));
        when(questionRepository.findBySurveyId(1L)).thenReturn(questions);

        QuestionsView result = questionService.getQuestionsView(surveyId);

        assertNotNull(result);
        assertEquals(1L, result.getSurveyId());
        assertEquals("Test Survey", result.getTitle());
        assertEquals(questions, result.getQuestions());

        verify(surveyRepository).findById(1L);
        verify(questionRepository).findBySurveyId(1L);
    }

    @Test
    void testGetQuestionsView_SurveyNotFound() {
        String surveyId = "1";

        when(surveyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            questionService.getQuestionsView(surveyId);
        });

        verify(surveyRepository).findById(1L);
        verifyNoInteractions(questionRepository);
    }
}
