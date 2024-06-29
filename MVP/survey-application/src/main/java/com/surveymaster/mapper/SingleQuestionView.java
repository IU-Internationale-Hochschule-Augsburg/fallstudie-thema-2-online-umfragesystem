package com.surveymaster.mapper;

import lombok.Data;

import java.io.Serializable;

@Data
public class SingleQuestionView implements Serializable {
    Long questionId;

    Long surveyId;

    String questionText = "";

    String questionType;

    String description = "";

    String answerOption1 = "";
    String answerOption2 = "";
    String answerOption3 = "";
    String answerOption4 = "";
    String answerOption5 = "";
    String answerOption6 = "";
    String answerOption7 = "";
    String answerOption8 = "";
    String answerOption9 = "";
    String answerOption10 = "";


    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswerOption1() {
        return answerOption1;
    }

    public void setAnswerOption1(String answerOption1) {
        this.answerOption1 = answerOption1;
    }

    public String getAnswerOption2() {
        return answerOption2;
    }

    public void setAnswerOption2(String answerOption2) {
        this.answerOption2 = answerOption2;
    }

    public String getAnswerOption3() {
        return answerOption3;
    }

    public void setAnswerOption3(String answerOption3) {
        this.answerOption3 = answerOption3;
    }

    public String getAnswerOption4() {
        return answerOption4;
    }

    public void setAnswerOption4(String answerOption4) {
        this.answerOption4 = answerOption4;
    }

    public String getAnswerOption5() {
        return answerOption5;
    }

    public void setAnswerOption5(String answerOption5) {
        this.answerOption5 = answerOption5;
    }

    public String getAnswerOption6() {
        return answerOption6;
    }

    public void setAnswerOption6(String answerOption6) {
        this.answerOption6 = answerOption6;
    }

    public String getAnswerOption7() {
        return answerOption7;
    }

    public void setAnswerOption7(String answerOption7) {
        this.answerOption7 = answerOption7;
    }

    public String getAnswerOption8() {
        return answerOption8;
    }

    public void setAnswerOption8(String answerOption8) {
        this.answerOption8 = answerOption8;
    }

    public String getAnswerOption9() {
        return answerOption9;
    }

    public void setAnswerOption9(String answerOption9) {
        this.answerOption9 = answerOption9;
    }

    public String getAnswerOption10() {
        return answerOption10;
    }

    public void setAnswerOption10(String answerOption10) {
        this.answerOption10 = answerOption10;
    }
}
