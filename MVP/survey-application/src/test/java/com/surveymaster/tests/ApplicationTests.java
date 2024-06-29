package com.surveymaster.tests;

import com.surveymaster.controller.ParticipantSurveyController;
import com.surveymaster.controller.QuestionController;
import com.surveymaster.controller.SurveyController;
import com.surveymaster.controller.SurveyErrorController;
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

    // sanity check test
    @Test
    void contextLoads() {
        // assertions for convincing ourselves that the context creates controller
        assertThat(surveyController).isNotNull();
        assertThat(questionController).isNotNull();
        assertThat(surveyErrorController).isNotNull();
        assertThat(participantSurveyController).isNotNull();
    }
}
