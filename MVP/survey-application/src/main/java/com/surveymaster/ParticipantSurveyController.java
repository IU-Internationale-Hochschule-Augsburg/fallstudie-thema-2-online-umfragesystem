package com.surveymaster;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor

public class ParticipantSurveyController {
    @GetMapping("/participant-view")
    public String loadParticipantView(Model model) {
        return "respondentCheckBoxView";
    }
}
