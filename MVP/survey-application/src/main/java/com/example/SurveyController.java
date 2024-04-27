package com.example;

import com.example.entity.Survey;
import com.example.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyRepository surveyRepository;

    //GetMapping Methode noch aus dem Technologietest
	/*@GetMapping("/survey")
	public String getSurveyForm(Model model) {
		InputForm textInputs = new InputForm();
		textInputs.getTextInputs().add(new TextInput("Test1"));
		textInputs.getTextInputs().add(new TextInput("Test2"));
		model.addAttribute("inputForm", textInputs);
		return "textinputs";
	}

	//PostMapping Methode noch aus dem Technologietest
	@PostMapping("/survey")
	@Transactional
	public String SurveyForm(@ModelAttribute("textinputs") InputForm inputForm, Model model) {

		for(int i = 0; i < inputForm.getTextInputs().size(); i++) {
			var umfrage = new Survey(inputForm.getTextInputs().get(i).getContent());
			surveyRepository.save(umfrage);
		}
		model.addAttribute("inputForm", inputForm);
		return "result";
	}*/


    // GetMapping method for the survey view, generating the screen on localhost:8080
    // Currently, test surveys are statically inserted
    @GetMapping("/survey-admin")
    public String getSurveyAdmin(Model model) {
        SurveyView surveys = new SurveyView();
        surveys.getSurveys().add(new Survey("Testumfrage 1"));
        surveys.getSurveys().add(new Survey("Testumfrage 2"));
        surveys.getSurveys().add(new Survey("Testumfrage 3"));
        surveys.getSurveys().add(new Survey("Testumfrage 4"));
        model.addAttribute("surveyView", surveys);
        return "surveyView";
    }

    // GetMapping method for the survey-add view, generating the screen on localhost:8080
    @GetMapping("/add-survey")
    public String loadAddSurveyView(Model model) {
        SurveyForm surveyForm = new SurveyForm();
        model.addAttribute("survey", surveyForm);
        return "addSurvey";
    }

    // GetMapping method for the Add-Question screen, loading the Add-Question view
    @GetMapping("/add-question")
    public String loadAddQuestion(Model model) {
        model.addAttribute("addQuestion", new SurveyView());
        return "addQuestion";
    }

    // GetMapping method for the Questions screen, loading the Questions view
    @GetMapping("/questions-view")
    public String loadQuestionsView(Model model) {
        model.addAttribute("questionsView", new SurveyView());
        return "questionsView";
    }

    // GetMapping method for the edit-Button, currently loading the add-survey screen
    // Later, it's a similar screen, but filled with data from the database
    @GetMapping("/survey-edit")
    public String loadEditView(Model model) {
        model.addAttribute("surveyEdit", new SurveyView());
        return  "redirect:/add-survey";
    }

    // Upon clicking the Save button in the survey-add view, we redirect to /survey-save,
    // where the data is saved accordingly in the database. Afterwards, we return to the surveys view.
    @PostMapping("/survey-save")
    public String saveSurvey(@ModelAttribute SurveyForm surveyForm, Model model) {
        var survey = new Survey(surveyForm.getTitle(), surveyForm.getStartdate(), surveyForm.getEnddate(), surveyForm.getDescription());
        surveyRepository.save(survey);
        model.addAttribute("survey", surveyForm);
        return "redirect:/survey-admin";
    }

    // Upon clicking the Cancel button, we redirect back to the survey view (without saving data)
    @PostMapping("/survey-admin")
    public String loadAddSurveyViewAgain(Model model) {
        model.addAttribute("addSurvey", new SurveyView());
        return "redirect:/survey-admin";
    }

    // Current functionality for the two buttons in the Add-Question view
    @PostMapping("/questions-view")
    public String loadQuestionViewAgain(Model model) {
        model.addAttribute("addQuestion", new SurveyView());
        return "redirect:/questions-view";
    }
}