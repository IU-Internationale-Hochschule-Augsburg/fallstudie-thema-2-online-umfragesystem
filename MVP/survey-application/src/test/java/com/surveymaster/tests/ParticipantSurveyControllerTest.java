// CONTROLLER TEST: Testing breadth is chosen over testing depth to increase coverage.
// Hence, we simply differentiate between "good" and "bad" cases.

// zu testen: LoginController, ParticipantSurveyController, QuestionController, QuestionService, SurveyController, SurveyService, UserService

package com.surveymaster.tests;

import com.surveymaster.mapper.AnswerMapper;
import com.surveymaster.controller.ParticipantSurveyController;
import com.surveymaster.mapper.ParticipantSurveyView;
import com.surveymaster.entity.Answer;
import com.surveymaster.entity.Question;
import com.surveymaster.entity.Survey;
import com.surveymaster.repository.AnswerRepository;
import com.surveymaster.repository.QuestionRepository;
import com.surveymaster.repository.SurveyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParticipantSurveyControllerTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private AnswerMapper answerMapper;

    @Mock
    private Model model;

    @InjectMocks
    private ParticipantSurveyController controller;

    private ParticipantSurveyView participantSurveyView;
    private Survey survey;
    private List<Question> questions;

    @BeforeEach
    void setUp() {
        participantSurveyView = new ParticipantSurveyView();
        survey = new Survey();
        survey.setEndDate(LocalDate.now().plusDays(1));
        questions = Arrays.asList(new Question(), new Question());
    }

    @Test
    void loadParticipantView_ActiveSurvey_ReturnsParticipantView() {
        when(questionRepository.findBySurveyId(anyLong())).thenReturn(questions);
        when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(survey));

        String result = controller.loadParticipantView("1", 0, participantSurveyView, model);

        assertEquals("participantView", result);
        verify(model, times(3)).addAttribute(anyString(), any());
    }

    @Test
    void loadParticipantView_ExpiredSurvey_ReturnsSurveyExpired() {
        survey.setEndDate(LocalDate.now().minusDays(1));
        when(questionRepository.findBySurveyId(anyLong())).thenReturn(questions);
        when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(survey));

        String result = controller.loadParticipantView("1", 0, participantSurveyView, model);

        assertEquals("surveyExpired", result);
        verify(model, times(1)).addAttribute(eq("participantSurveyView"), any());
    }

    @Test
    void loadParticipantView_AllQuestionsAnswered_ReturnsSurveyCompletionView() {
        when(questionRepository.findBySurveyId(anyLong())).thenReturn(questions);
        participantSurveyView.setCurrentQuestion(questions.size());

        String result = controller.loadParticipantView("1", questions.size(), participantSurveyView, model);

        assertEquals("surveyCompletionView", result);
    }

    @Test
    void saveResponse_NewAnswer_SavesNewAnswer() {
        Question lastQuestion = new Question();
        when(questionRepository.findBySurveyId(anyLong())).thenReturn(Arrays.asList(lastQuestion));
        when(answerRepository.findAll()).thenReturn(Arrays.asList());
        when(answerMapper.toAnswer(any(), any())).thenReturn(new Answer());

        String result = controller.saveResponse("1", participantSurveyView, model);

        verify(answerRepository).save(any(Answer.class));
        assertEquals("redirect:/participant-view?surveyId=1&currentQuestion=1", result);
    }

    @Test
    void saveResponse_ExistingAnswer_UpdatesExistingAnswer() {
        Question lastQuestion = new Question();
        lastQuestion.setQuestionId(1L);
        Answer existingAnswer = new Answer();
        existingAnswer.setUserId(ParticipantSurveyView.getUserId());
        existingAnswer.setQuestionId(1L);

        when(questionRepository.findBySurveyId(anyLong())).thenReturn(Arrays.asList(lastQuestion));
        when(answerRepository.findAll()).thenReturn(Arrays.asList(existingAnswer));
        when(answerMapper.updateAnswer(any(), any(), any())).thenReturn(existingAnswer);

        String result = controller.saveResponse("1", participantSurveyView, model);

        verify(answerRepository).save(existingAnswer);
        assertEquals("redirect:/participant-view?surveyId=1&currentQuestion=1", result);
    }
}