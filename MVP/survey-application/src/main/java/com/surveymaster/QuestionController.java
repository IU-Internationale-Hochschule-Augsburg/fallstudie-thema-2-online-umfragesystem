package com.surveymaster;

import com.surveymaster.entity.Question;
import com.surveymaster.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    // GetMapping method for the Add-Question screen, loading the Add-Question view
    @PostMapping("/add-question")
    public String loadAddQuestion(@RequestParam("surveyId") String surveyId, Model model) {
        SingleQuestionView singleQuestion = new SingleQuestionView();
        singleQuestion.setSurveyId(Long.parseLong(surveyId));
        model.addAttribute("addQuestion", singleQuestion);
        return "addQuestion";
    }

    // the dynamic generation of the question views is still missing here!!
    @GetMapping("/questions-view")
    public String loadQuestionsView(@RequestParam("surveyId") String surveyId, Model model) {
        QuestionsView questionsView = questionService.getQuestionsView(surveyId);
        // the dynamic generation of the question views is still missing here
        model.addAttribute("questionsView", questionsView);
        return "questionsView";
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

    // Not completed POST mapping function
    @PostMapping("/question-save")
    public String saveQuestion(@ModelAttribute SingleQuestionView questionForm, Model model) {
        Question question;

        question = new Question(questionForm.getSurveyId(), questionForm.getQuestionType(), questionForm.getQuestionText());

        questionRepository.save(question);
        model.addAttribute("question", questionForm);
        return "redirect:/questions-view?surveyId=" + questionForm.surveyId;
    }

    // Upon clicking the Cancel button, we redirect back to the questions view (without saving data)
    @PostMapping("/questions-view")
    public String loadAddSurveyViewAgain(Model model) {
        model.addAttribute("questionsView", new QuestionsView());
        return "redirect:/questions-view";
    }

    private void addQuestionViewToModel(Model model, String questionId) {
        Question selectedQuestion;
        QuestionsView questionsView;
        selectedQuestion = questionRepository.findById(Long.parseLong(questionId)).orElseThrow();
        questionsView = questionService.getQuestionsView(String.valueOf(selectedQuestion.getSurveyId()));
        model.addAttribute("questionsView", questionsView);
    }
}
