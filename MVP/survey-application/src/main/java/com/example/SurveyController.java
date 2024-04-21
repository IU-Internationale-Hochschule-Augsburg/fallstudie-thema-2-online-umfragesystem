package com.example;

import com.example.entity.Survey;
import com.example.repository.SurveyRepository;
import jakarta.transaction.Transactional;
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

	@GetMapping("/survey")
	public String getSurveyForm(Model model) {
		InputForm textInputs = new InputForm();
		textInputs.getTextInputs().add(new TextInput("Test1"));
		textInputs.getTextInputs().add(new TextInput("Test2"));
		model.addAttribute("inputForm", textInputs);
		return "textinputs";
	}

	@PostMapping("/survey")
	@Transactional
	public String SurveyForm(@ModelAttribute("textinputs") InputForm inputForm, Model model) {

		for(int i = 0; i < inputForm.getTextInputs().size(); i++) {
			var umfrage = new Survey(inputForm.getTextInputs().get(i).getContent());
			surveyRepository.save(umfrage);
		}
		model.addAttribute("inputForm", inputForm);
		return "result";
	}

	@GetMapping("/survey-admin")
	public String getSurveyAdmin(Model model) {
		SurveyView surveys = new SurveyView();
		surveys.getSurveys().add(new com.example.Survey("Testumfrage 1"));
		surveys.getSurveys().add(new com.example.Survey("Testumfrage 2"));
		surveys.getSurveys().add(new com.example.Survey("Testumfrage 3"));
		surveys.getSurveys().add(new com.example.Survey("Testumfrage 4"));
		model.addAttribute("surveyView", surveys);
		return "surveyView";
	}

	@GetMapping("/add-survey")
	public String loadAddSurveyView(Model model) {
		model.addAttribute("surveyView", new SurveyView());
		return "addSurvey";
	}

	@PostMapping("/survey-save")
	public String saveSurvey(Model model) {
		return "redirect:/survey-admin";
	}
}