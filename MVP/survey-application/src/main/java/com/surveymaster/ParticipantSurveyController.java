package com.surveymaster;

import com.surveymaster.repository.QuestionRepository;
import com.surveymaster.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class ParticipantSurveyController {
    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;

    @GetMapping("/participant-view")
    public String loadParticipantView(Model model) {
        var questions = questionRepository.findBySurveyId(10001L);
        var question1 = questions.get(2);

        var survey = surveyRepository.findById(10001L).orElseThrow();

        model.addAttribute("survey1", survey);
        model.addAttribute("question1", question1);
        return "respondentRadioButtonView";
    }

    @Transactional
    @PostMapping("/save-response")
    public String saveResponse(Model model) {
        return "redirect:/participant-view";
    }
}
