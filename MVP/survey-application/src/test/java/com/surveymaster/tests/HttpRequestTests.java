package com.surveymaster.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpRequestTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testLoadingSurveyView() throws Exception {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/survey-admin", String.class)).contains("surveyView");
    }

    @Test
    void testLoadingAddSurvey() throws Exception {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/add-survey", String.class)).contains("addSurvey");
    }

    @Test
    void testLoadingAddQuestion() throws Exception {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/add-question", String.class)).contains("addQuestion");
    }

    @Test
    void testLoadingErrorPage() throws Exception {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/error", String.class)).contains("error");
    }
}
