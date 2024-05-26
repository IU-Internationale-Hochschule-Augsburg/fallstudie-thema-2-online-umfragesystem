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

    @GetMapping("/participant-view")
    public String loadParticipantView(@RequestParam("surveyId") String surveyId, Model model) {
        final var questions = questionRepository.findBySurveyId(Long.parseLong(surveyId));
        final var question = questions.get(0);

        final var survey = surveyRepository.findById(Long.parseLong(surveyId)).orElseThrow();

        final Answer newAnswer = new Answer(question.getQuestionId(), 'N', 'N', 'N',
                'N', 'N', 'N', 'N', 'N', 'N',
                'N', "");
        answerRepository.save(newAnswer);
        final var answer = answerRepository.findById(newAnswer.getAnswerId());

        model.addAttribute("survey", survey);
        model.addAttribute("question", question);
        model.addAttribute("answer", answer);
        return "respondentRadioButtonView";
    }

    @Transactional
    @PostMapping("/save-response")
    public String saveResponse(@RequestParam("surveyId") String surveyId, @ModelAttribute AnswerView answerView, Model model) {
        Answer answer;

        if (answerView.getAnswerId() == null) {
            answer = new Answer(answerView.getQuestionId(), answerView.getAnswerOption1(),
                    answerView.getAnswerOption2(), answerView.getAnswerOption3(),
                    answerView.getAnswerOption4(), answerView.getAnswerOption5(),
                    answerView.getAnswerOption6(), answerView.getAnswerOption7(),
                    answerView.getAnswerOption8(), answerView.getAnswerOption9(),
                    answerView.getAnswerOption10(), answerView.getTextinput());
        } else {
            answer = answerRepository.findById(answerView.getAnswerId()).orElseThrow();

            answer.setQuestionId(answerView.getQuestionId());
            answer.setAnswerOption1(answerView.getAnswerOption1());
            answer.setAnswerOption2(answerView.getAnswerOption2());
            answer.setAnswerOption3(answerView.getAnswerOption3());
            answer.setAnswerOption4(answerView.getAnswerOption4());
            answer.setAnswerOption5(answerView.getAnswerOption5());
            answer.setAnswerOption6(answerView.getAnswerOption6());
            answer.setAnswerOption7(answerView.getAnswerOption7());
            answer.setAnswerOption8(answerView.getAnswerOption8());
            answer.setAnswerOption9(answerView.getAnswerOption9());
            answer.setAnswerOption10(answerView.getAnswerOption10());
            answer.setTextinput((answerView.getTextinput()));
        }

        answerRepository.save(answer);
        model.addAttribute("answerView", answerView);
        return "redirect:/participant-view?surveyId=" + surveyId;
    }
}
