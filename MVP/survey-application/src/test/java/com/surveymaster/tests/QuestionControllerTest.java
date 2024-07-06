// QUESTION TEST: Testing breadth is chosen over testing depth to increase coverage.
// Hence, we simply differentiate between "good" and "bad" cases.

// zu testen: LoginController, ParticipantSurveyController, QuestionController, QuestionService, SurveyController, SurveyService, UserService, WebSecurityConfig

package com.surveymaster.tests;

import com.surveymaster.QuestionController;
import com.surveymaster.QuestionService;
import com.surveymaster.QuestionsView;
import com.surveymaster.SingleQuestionView;
import com.surveymaster.entity.Question;
import com.surveymaster.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class QuestionControllerTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionService questionService;

    @Mock
    private Model model;

    @InjectMocks
    private QuestionController questionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadAddQuestion() {
        String surveyId = "1";
        String result = questionController.loadAddQuestion(surveyId, model);

        assertEquals("addQuestion", result);
        verify(model).addAttribute(eq("addQuestion"), any(SingleQuestionView.class));
    }

    @Test
    void testLoadQuestionsView() {
        String surveyId = "1";
        QuestionsView questionsView = new QuestionsView();
        when(questionService.getQuestionsView(surveyId)).thenReturn(questionsView);

        String result = questionController.loadQuestionsView(surveyId, model);

        assertEquals("questionsView", result);
        verify(model).addAttribute("questionsView", questionsView);
    }

    @Test
    void testButtonQuestionHandler_Delete() {
        String buttonQuestionHandler = ",delete_1";
        Question question = new Question();
        question.setSurveyId(1L);
        QuestionsView questionsView = new QuestionsView();

        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        when(questionService.getQuestionsView("1")).thenReturn(questionsView);

        String result = questionController.buttonQuestionHandler(buttonQuestionHandler, model);

        assertEquals("questionsView", result);
        verify(questionService).deleteQuestion("1");
        verify(model).addAttribute("questionsView", questionsView);
    }

    @Test
    void testButtonQuestionHandler_Edit() {
        String buttonQuestionHandler = ",edit_1";
        Question question = new Question();
        question.setSurveyId(1L);
        QuestionsView questionsView = new QuestionsView();

        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        when(questionService.getQuestionsView("1")).thenReturn(questionsView);

        String result = questionController.buttonQuestionHandler(buttonQuestionHandler, model);

        assertEquals("questionSettings", result);
        verify(model).addAttribute("questionsView", questionsView);
        verify(model).addAttribute("question", question);
    }

    @Test
    void testSaveQuestion_NewQuestion() {
        SingleQuestionView questionForm = new SingleQuestionView();
        questionForm.setSurveyId(1L);

        String result = questionController.saveQuestion(questionForm, model);

        assertEquals("redirect:/questions-view?surveyId=1", result);
        verify(questionRepository).save(any(Question.class));
    }

    @Test
    void testSaveQuestion_ExistingQuestion() {
        SingleQuestionView questionForm = new SingleQuestionView();
        questionForm.setQuestionId(1L);
        questionForm.setSurveyId(1L);

        Question existingQuestion = new Question();
        when(questionRepository.findById(1L)).thenReturn(Optional.of(existingQuestion));

        String result = questionController.saveQuestion(questionForm, model);

        assertEquals("redirect:/questions-view?surveyId=1", result);
        verify(questionRepository).save(existingQuestion);
    }

    @Test
    void testLoadAddSurveyViewAgain() {
        String result = questionController.loadAddSurveyViewAgain(model);

        assertEquals("redirect:/questions-view", result);
        verify(model).addAttribute(eq("questionsView"), any(QuestionsView.class));
    }
}