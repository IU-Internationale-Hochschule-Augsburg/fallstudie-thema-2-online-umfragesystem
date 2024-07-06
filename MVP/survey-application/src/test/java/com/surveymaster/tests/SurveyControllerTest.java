// SURVEY TEST: Testing breadth is chosen over testing depth to increase coverage.
// Hence, we simply differentiate between "good" and "bad" cases.

// zu testen: LoginController, ParticipantSurveyController, QuestionController, QuestionService, SurveyController, SurveyService, UserService

package com.surveymaster.tests;

import com.surveymaster.controller.SurveyController;
import com.surveymaster.entity.Survey;
import com.surveymaster.entity.User;
import com.surveymaster.mapper.QuestionsView;
import com.surveymaster.mapper.SurveyForm;
import com.surveymaster.mapper.SurveyView;
import com.surveymaster.repository.SurveyRepository;
import com.surveymaster.service.QuestionService;
import com.surveymaster.service.SurveyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SurveyControllerTest {

    @InjectMocks
    private SurveyController surveyController;

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private QuestionService questionService;

    @Mock
    private SurveyService surveyService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSurveyAdmin() {
        // Arrange
        User mockUser = new User();
        mockUser.setUserId(1L);
        when(surveyService.getCurrentUser()).thenReturn(mockUser);
        when(surveyRepository.findAllByUserId(1L)).thenReturn(Arrays.asList(new Survey(), new Survey()));

        // Act
        String viewName = surveyController.getSurveyAdmin(model);

        // Assert
        assertEquals("surveyView", viewName);
        verify(model).addAttribute(eq("surveyView"), any(SurveyView.class));
    }

    @Test
    void testLoadAddSurveyView() {
        String viewName = surveyController.loadAddSurveyView(model);

        assertEquals("addSurvey", viewName);
        verify(model).addAttribute(eq("survey"), any(SurveyForm.class));
    }

    @Test
    void testButtonHandlerDelete() {
        String buttonHandler = ",delete_123";

        String result = surveyController.buttonHandler(buttonHandler, model);

        assertEquals("redirect:/survey-admin", result);
        verify(surveyService).deleteSurvey("123");
    }

    @Test
    void testButtonHandlerEdit() {
        String buttonHandler = ",edit_456";
        when(questionService.getQuestionsView("456")).thenReturn(new QuestionsView());

        String result = surveyController.buttonHandler(buttonHandler, model);

        assertEquals("questionsView", result);
        verify(model).addAttribute(eq("questionsView"), any(QuestionsView.class));
    }

    @Test
    void testSaveSurveyNew() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setTitle("New Survey");
        User mockUser = new User();
        mockUser.setUserId(1L);
        when(surveyService.getCurrentUser()).thenReturn(mockUser);

        String result = surveyController.saveSurvey(surveyForm, model);

        assertEquals("redirect:/survey-admin", result);
        verify(surveyRepository).save(any(Survey.class));
    }

    @Test
    void testSaveSurveyExisting() {
        // Arrange
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setSurveyId(789L);
        surveyForm.setTitle("Updated Survey");
        Survey existingSurvey = new Survey();
        when(surveyRepository.findById(789L)).thenReturn(Optional.of(existingSurvey));
        User mockUser = new User();
        mockUser.setUserId(1L);
        when(surveyService.getCurrentUser()).thenReturn(mockUser);

        // Act
        String result = surveyController.saveSurvey(surveyForm, model);

        // Assert
        assertEquals("redirect:/survey-admin", result);
        verify(surveyRepository).save(existingSurvey);
    }

    @Test
    void testLoadAddSurveyViewAgain() {
        // Act
        String result = surveyController.loadAddSurveyViewAgain(model);

        // Assert
        assertEquals("redirect:/survey-admin", result);
        verify(model).addAttribute(eq("addSurvey"), any(SurveyView.class));
    }
}