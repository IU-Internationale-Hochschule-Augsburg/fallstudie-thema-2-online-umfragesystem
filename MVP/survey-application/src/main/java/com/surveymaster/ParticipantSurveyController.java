package com.surveymaster;

import com.surveymaster.entity.Answer;
import com.surveymaster.repository.AnswerRepository;
import com.surveymaster.repository.QuestionRepository;
import com.surveymaster.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor

public class ParticipantSurveyController {
    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;
    private final AnswerRepository answerRepository;

    // Model attributes are pre-initialized with the request parameters
    @GetMapping("/participant-view")
    public String loadParticipantView(@RequestParam("surveyId") String surveyId, @RequestParam("currentQuestion") int currentQuestion, @ModelAttribute ParticipantSurveyView participantSurveyView, Model model) {
        final var questions = questionRepository.findBySurveyId(Long.parseLong(surveyId));

        if(currentQuestion == 0) {
            participantSurveyView.nextUserId();
        }

        if (participantSurveyView.getCurrentQuestion() < questions.size()) {
            final var question = questions.get(participantSurveyView.getCurrentQuestion());
            final var survey = surveyRepository.findById(Long.parseLong(surveyId)).orElseThrow();
            final Answer answer = new Answer(question.getQuestionId(), 'N', 'N', 'N',
                    'N', 'N', 'N', 'N', 'N', 'N',
                    'N', "");

            model.addAttribute("participantSurveyView", participantSurveyView);
            model.addAttribute("survey", survey);
            model.addAttribute("question", question);
            model.addAttribute("answer", answer);

            return "participantView";
        } else {
            return "surveyCompletionView";
        }
    }

    @Transactional
    @PostMapping("/save-response")
    public String saveResponse(@ModelAttribute("surveyId") String surveyId, @ModelAttribute ParticipantSurveyView participantSurveyView, @ModelAttribute Answer answer, Model model) {
        participantSurveyView.setCurrentQuestion(participantSurveyView.getCurrentQuestion() + 1);
        answer.setUserId(ParticipantSurveyView.getUserId());
        answerRepository.save(answer);
        model.addAttribute("answer", answer);
        model.addAttribute("participantSurveyView", participantSurveyView);
        return "redirect:/participant-view?surveyId=" + surveyId + "&currentQuestion=" + participantSurveyView.getCurrentQuestion();
    }
}
