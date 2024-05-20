package com.surveymaster;

import com.surveymaster.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class ParticipantSurveyController {
    QuestionRepository questionRepository;

    @GetMapping("/participant-view")
    public String loadParticipantView(Model model) {
        return "respondentCheckBoxView";
    }

    @PostMapping("/save-response")
    public String saveResponse(Model model) {
        return "redirect:/participant-view";
    }
}
