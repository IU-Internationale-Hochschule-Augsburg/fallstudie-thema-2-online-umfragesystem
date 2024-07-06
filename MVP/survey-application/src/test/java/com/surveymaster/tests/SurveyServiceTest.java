// SURVEY SERVICE TEST: Testing breadth is chosen over testing depth to increase coverage.
// Hence, we simply differentiate between "good" and "bad" cases.

// zu testen: LoginController, ParticipantSurveyController, QuestionController, QuestionService, SurveyController, SurveyService, UserService

package com.surveymaster.tests;

import com.surveymaster.entity.Question;
import com.surveymaster.SurveyService;
import com.surveymaster.entity.User;
import com.surveymaster.repository.AnswerRepository;
import com.surveymaster.repository.QuestionRepository;
import com.surveymaster.repository.SurveyRepository;
import com.surveymaster.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SurveyServiceTest {

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private SurveyService surveyService;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void deleteSurvey_ShouldDeleteSurveyQuestionsAndAnswers() {
        String surveyId = "1";
        Question question1 = new Question();
        question1.setQuestionId(1L);
        Question question2 = new Question();
        question2.setQuestionId(2L);
        List<Question> questions = Arrays.asList(question1, question2);

        when(questionRepository.findBySurveyId(1L)).thenReturn(questions);

        surveyService.deleteSurvey(surveyId);

        verify(answerRepository, times(1)).deleteByQuestionId(1L);
        verify(answerRepository, times(1)).deleteByQuestionId(2L);
        verify(questionRepository, times(1)).deleteById(1L);
        verify(questionRepository, times(1)).deleteById(2L);
        verify(surveyRepository, times(1)).deleteById(1L);
    }

    @Test
    void getCurrentUser_ShouldReturnCurrentUser() {
        String username = "testUser";
        User expectedUser = new User();
        expectedUser.setUsername(username);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(username);
        when(userRepository.getUserByUsername(username)).thenReturn(expectedUser);

        User result = surveyService.getCurrentUser();

        assertEquals(expectedUser, result);
        verify(userRepository, times(1)).getUserByUsername(username);
    }
}