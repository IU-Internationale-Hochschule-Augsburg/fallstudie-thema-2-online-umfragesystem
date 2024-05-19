package com.surveymaster;

import com.surveymaster.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerRepository answerRepository;
    @GetMapping("/answers-view")
    public String loadAnswersView(Model model) {
        model.addAttribute("answersView", new AnswersView());
        return "answersView";
    }

    @GetMapping("/add-answer")
    public String loadAddAnswer(Model model) {
        model.addAttribute("addAnswer", new AnswersView());
        return "addAnswer";
    }
}
