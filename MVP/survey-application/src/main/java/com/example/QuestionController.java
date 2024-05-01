package com.example;

import com.example.entity.Question;
import com.example.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    // GetMapping method for the Add-Question screen, loading the Add-Question view
    @GetMapping("/add-question")
    public String loadAddQuestion(Model model) {
        model.addAttribute("addQuestion", new SurveyView());
        return "addQuestion";
    }

    @GetMapping("/button-question-handler")
    public String buttonQuestionHandler(@RequestParam("buttonQuestionHandler") String buttonQuestionHandler, Model model) {
        String questionId;
        Question selectedQuestion;
        QuestionsView questionsView;
        // if statement for button handling
        if (buttonQuestionHandler.startsWith(",delete_")) {
            questionId = buttonQuestionHandler.substring(8);
            selectedQuestion = questionRepository.findById(Long.parseLong(questionId)).orElseThrow();
            questionService.deleteQuestion(questionId);
            questionsView = questionService.getQuestionsView(String.valueOf(selectedQuestion.getSurveyId()));
            model.addAttribute("questionsView", questionsView);
        } else if (buttonQuestionHandler.startsWith(",edit_")) {
            questionId = buttonQuestionHandler.substring(6);
            addQuestionViewToModel(model, questionId);
            return "addQuestion";
        }
        return "questionsView";
    }

    private void addQuestionViewToModel(Model model, String questionId) {
        Question selectedQuestion;
        QuestionsView questionsView;
        selectedQuestion = questionRepository.findById(Long.parseLong(questionId)).orElseThrow();
        questionsView = questionService.getQuestionsView(String.valueOf(selectedQuestion.getSurveyId()));
        model.addAttribute("questionsView", questionsView);
    }
}
