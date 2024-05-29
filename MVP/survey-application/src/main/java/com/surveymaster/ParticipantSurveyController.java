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
    public String loadParticipantView(@RequestParam("surveyId") String surveyId, @RequestParam("currentQuestion")
    int currentQuestion, @ModelAttribute ParticipantSurveyView participantSurveyView, Model model) {
        final var questions = questionRepository.findBySurveyId(Long.parseLong(surveyId));

        if (currentQuestion == 0) {
            participantSurveyView.nextUserId();
        }

        if (participantSurveyView.getCurrentQuestion() < questions.size()) {
            final var question = questions.get(participantSurveyView.getCurrentQuestion());
            final var survey = surveyRepository.findById(Long.parseLong(surveyId)).orElseThrow();

            model.addAttribute("participantSurveyView", participantSurveyView);
            model.addAttribute("survey", survey);
            model.addAttribute("question", question);

            return "participantView";
        } else {
            return "surveyCompletionView";
        }
    }

    @Transactional
    @PostMapping("/save-response")
    public String saveResponse(@ModelAttribute("surveyId") String surveyId, @ModelAttribute ParticipantSurveyView participantSurveyView, Model model) {
        // get last question
        final var lastQuestion = questionRepository.findBySurveyId(Long.parseLong(surveyId)).get(participantSurveyView.getCurrentQuestion());

        //increment counter for the next question
        participantSurveyView.setCurrentQuestion(participantSurveyView.getCurrentQuestion() + 1);

        // fill answer attributes
        Answer answer;
        if (lastQuestion.getQuestionType().equals("checkbox")) {
            answer = new Answer(lastQuestion.getQuestionId(), participantSurveyView.getCheckBoxAnswerOption1(),
                    participantSurveyView.getCheckBoxAnswerOption2(), participantSurveyView.getCheckBoxAnswerOption3(),
                    participantSurveyView.getCheckBoxAnswerOption4(), participantSurveyView.getCheckBoxAnswerOption5(),
                    participantSurveyView.getCheckBoxAnswerOption6(), participantSurveyView.getCheckBoxAnswerOption7(),
                    participantSurveyView.getCheckBoxAnswerOption8(), participantSurveyView.getCheckBoxAnswerOption9(),
                    participantSurveyView.getCheckBoxAnswerOption10());
        } else if (lastQuestion.getQuestionType().equals("radiobutton")) {
            answer = new Answer(lastQuestion.getQuestionId(), participantSurveyView.getRadioButtonAnswerOption1(),
                    participantSurveyView.getRadioButtonAnswerOption2(), participantSurveyView.getRadioButtonAnswerOption3(),
                    participantSurveyView.getRadioButtonAnswerOption4(), participantSurveyView.getRadioButtonAnswerOption5(),
                    participantSurveyView.getRadioButtonAnswerOption6(), participantSurveyView.getRadioButtonAnswerOption7(),
                    participantSurveyView.getRadioButtonAnswerOption8(), participantSurveyView.getRadioButtonAnswerOption9(),
                    participantSurveyView.getRadioButtonAnswerOption10());
        } else {
            //Type: open text response
            answer = new Answer(lastQuestion.getQuestionId(), participantSurveyView.getTextinput());
        }
        answer.setUserId(ParticipantSurveyView.getUserId());

        // save answer
        answerRepository.save(answer);

        // add to model
        model.addAttribute("answer", answer);
        model.addAttribute("participantSurveyView", participantSurveyView);
        return "redirect:/participant-view?surveyId=" + surveyId + "&currentQuestion=" + participantSurveyView.getCurrentQuestion();
    }
}
