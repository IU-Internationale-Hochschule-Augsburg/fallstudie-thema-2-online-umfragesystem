package com.surveymaster.dataAnalysis;


import com.surveymaster.QuestionService;
import com.surveymaster.QuestionsView;
import com.surveymaster.entity.Answer;
import com.surveymaster.entity.Question;
import com.surveymaster.entity.Survey;
import com.surveymaster.repository.AnswerRepository;
import com.surveymaster.repository.QuestionRepository;
import com.surveymaster.repository.SurveyRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Data
public class AnalysisController {
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final AnswerService answerService;

    private List<Survey> surveys = new ArrayList<>();

    @GetMapping("/analysis")
    public String loadAnalysis(@RequestParam("surveyId") String surveyId, Model model) {
        final QuestionsView questionsView = questionService.getQuestionsView(surveyId);
        model.addAttribute("questionsView", questionsView);
        final List<Question> questions = questionsView.getQuestions();
        for (Question question : questions) {
            final AnswersView answersView = answerService.getAnswersView(Long.toString(question.getQuestionId()));
            model.addAttribute("answersView" + question, answersView);
        }
        return "analysisView";
    }

    @GetMapping("/compare-questions")
    public String loadCompareQuestions(@RequestParam("surveyId") String surveyId, Model model) {
        final var questions = questionRepository.findBySurveyId(Long.parseLong(surveyId));
        final var survey = surveyRepository.findById(Long.parseLong(surveyId)).orElseThrow();
        model.addAttribute("survey", survey);

        for (int i = 0; i < questions.size(); i++) {
            final var answers = answerRepository.findByQuestionId(questions.get(i).getQuestionId());
            final var question = questions.get(i);
            model.addAttribute("answers" + i, answers);
            model.addAttribute("question" + i, question);
        }
        return "compareView";
    }

    @GetMapping("button-analysis-handler")
    public String buttonHandler(@RequestParam("buttonHandler") String buttonHandler, Model model) {
        String surveyId;
        if (buttonHandler.startsWith(",compare_")) {
            // the ID in HTML is appended with an underscore to the button name. Using substring, the ID is split from the label
            surveyId = buttonHandler.substring(9);
            final QuestionsView questionsView = questionService.getQuestionsView(surveyId);
            model.addAttribute("questionsView", questionsView);
            final var survey = surveyRepository.findById(Long.parseLong(surveyId)).orElseThrow();
            model.addAttribute("survey", survey);
            return "compareView";
        } else if(buttonHandler.startsWith(",diagram_")) {
            var questionId = buttonHandler.substring(9);
        } else if(buttonHandler.startsWith(",table_")) {
            var questionId = buttonHandler.substring(9);
        } else if (buttonHandler.startsWith(",back_")) {
            // the ID in HTML is appended with an underscore to the button name. Using substring, the ID is split from the label
            surveyId = buttonHandler.substring(6);
            final QuestionsView questionsView = questionService.getQuestionsView(surveyId);
            model.addAttribute("questionsView", questionsView);
            final var survey = surveyRepository.findById(Long.parseLong(surveyId)).orElseThrow();
            model.addAttribute("survey", survey);
            return "analysisView";}
        return "redirect:/analysisView";
    }
}
