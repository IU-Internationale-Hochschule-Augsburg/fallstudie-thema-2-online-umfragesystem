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

import java.util.Optional;
import java.util.stream.StreamSupport;

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

        // Check among all previous answers to see if user x has already submitted an answer to the question with questionId y
        // If this is the case, the old answer will be overwritten, and a new one will not be created
        var currentUser = ParticipantSurveyView.getUserId();
        var currentAnswers = answerRepository.findAll();

        Optional<Answer> optionalAnswer = StreamSupport.stream(currentAnswers.spliterator(), false)
                .filter(answer -> answer.getUserId().equals(currentUser)
                        && answer.getQuestionId().equals(lastQuestion.getQuestionId()))
                .findAny();

        if (optionalAnswer.isPresent()) {
            if (lastQuestion.getQuestionType().equals("checkbox")) {
                optionalAnswer.get().setAnswerOption1(participantSurveyView.getCheckBoxAnswerOption1());
                optionalAnswer.get().setAnswerOption2(participantSurveyView.getCheckBoxAnswerOption2());
                optionalAnswer.get().setAnswerOption3(participantSurveyView.getCheckBoxAnswerOption3());
                optionalAnswer.get().setAnswerOption4(participantSurveyView.getCheckBoxAnswerOption4());
                optionalAnswer.get().setAnswerOption5(participantSurveyView.getCheckBoxAnswerOption5());
                optionalAnswer.get().setAnswerOption6(participantSurveyView.getCheckBoxAnswerOption6());
                optionalAnswer.get().setAnswerOption7(participantSurveyView.getCheckBoxAnswerOption7());
                optionalAnswer.get().setAnswerOption8(participantSurveyView.getCheckBoxAnswerOption8());
                optionalAnswer.get().setAnswerOption9(participantSurveyView.getCheckBoxAnswerOption9());
                optionalAnswer.get().setAnswerOption10(participantSurveyView.getCheckBoxAnswerOption10());
            } else if (lastQuestion.getQuestionType().equals("radiobutton")) {
                optionalAnswer.get().setAnswerOption1(participantSurveyView.getRadioButtonAnswerOption1());
                optionalAnswer.get().setAnswerOption2(participantSurveyView.getRadioButtonAnswerOption2());
                optionalAnswer.get().setAnswerOption3(participantSurveyView.getRadioButtonAnswerOption3());
                optionalAnswer.get().setAnswerOption4(participantSurveyView.getRadioButtonAnswerOption4());
                optionalAnswer.get().setAnswerOption5(participantSurveyView.getRadioButtonAnswerOption5());
                optionalAnswer.get().setAnswerOption6(participantSurveyView.getRadioButtonAnswerOption6());
                optionalAnswer.get().setAnswerOption7(participantSurveyView.getRadioButtonAnswerOption7());
                optionalAnswer.get().setAnswerOption8(participantSurveyView.getRadioButtonAnswerOption8());
                optionalAnswer.get().setAnswerOption9(participantSurveyView.getRadioButtonAnswerOption9());
                optionalAnswer.get().setAnswerOption10(participantSurveyView.getRadioButtonAnswerOption10());
            } else {
                optionalAnswer.get().setTextinput(participantSurveyView.getTextinput());
            }

            answerRepository.save(optionalAnswer.get());
            model.addAttribute("optionalAnswer", optionalAnswer.get());
        } else {
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
            model.addAttribute("answer", answer);
        }

        model.addAttribute("participantSurveyView", participantSurveyView);
        return "redirect:/participant-view?surveyId=" + surveyId + "&currentQuestion=" + participantSurveyView.getCurrentQuestion();
    }
}
