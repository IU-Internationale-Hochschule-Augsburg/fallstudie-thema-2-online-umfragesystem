package com.surveymaster.tests;

import com.surveymaster.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private SurveyController surveyController;
    @Autowired
    private QuestionController questionController;
    @Autowired
    private SurveyErrorController surveyErrorController;
    @Autowired
    private ParticipantSurveyController participantSurveyController;
    @Autowired
    private LoginController loginController;

    // sanity check test
    @Test
    void contextLoads() {
        assertThat(surveyController).isNotNull();
        assertThat(questionController).isNotNull();
        assertThat(surveyErrorController).isNotNull();
        assertThat(participantSurveyController).isNotNull();
        assertThat(loginController).isNotNull();
    }
}
