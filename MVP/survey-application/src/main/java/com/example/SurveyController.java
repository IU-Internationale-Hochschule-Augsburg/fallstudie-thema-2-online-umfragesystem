package com.example;

import com.example.entity.Survey;
import com.example.repository.SurveyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

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



	// GetMapping Methode für die Umfrageansicht, Erzeugen des Screens auf localhost:8080
	// Aktuell werden die Testumfragen statisch reinkopiert
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

	// GetMapping Methode für die Umfrage-hinzufügen-Ansicht, Erzeugen des Screens auf localhost:8080
	@GetMapping("/add-survey")
	public String loadAddSurveyView(Model model) {
		SurveyForm surveyForm = new SurveyForm();
		model.addAttribute("survey", surveyForm);
		return "addSurvey";
	}

	//GetMapping Methode für den Screen Frage-hinzufügen, Laden der Frage-hinzufügen-Ansicht
	@GetMapping("/add-question")
	public String loadAddQuestion(Model model) {
		model.addAttribute("addQuestion", new SurveyView());
		return "addQuestion";
	}

	//GetMapping Methode für den Screen Fragen-Ansicht, Laden der Fragen-Ansicht
	@GetMapping("/questions-view")
	public String loadQuestionsView(Model model) {
		model.addAttribute("questionsView", new SurveyView());
		return "questionsView";
	}



	// Mit einem Klick auf den Speichern-Button in der Umfrage-hinzufügen-Ansicht leiten wir um zu /survey-save,
	// wo die Daten entsprechend in der Datenbank gespeichert werden. Anschließend kehren wir zurück in die Umfragen-Ansicht.
	@PostMapping("/survey-save")
	public String saveSurvey(@ModelAttribute SurveyForm surveyForm, Model model) {
		var survey = new Survey(surveyForm.getTitle(), surveyForm.getStartdate(), surveyForm.getEnddate(), surveyForm.getDescription());
		surveyRepository.save(survey);
		model.addAttribute("survey", surveyForm);
		return "redirect:/survey-admin";
	}

	// Mit einem Klick auf den Abbrechen-Button leiten wir zurück zur Umfrageansicht (ohne Datensicherung)
	@PostMapping("/survey-admin")
	public String loadAddSurveyViewAgain(Model model) {
		model.addAttribute("addSurvey", new SurveyView());
		return "redirect:/survey-admin";
	}

	// Aktuelle Funktionalität für die beiden Button in der Frage-hinzufügen-Ansicht
	@PostMapping("/questions-view")
	public String loadQuestionViewAgain(Model model) {
		model.addAttribute("addQuestion", new SurveyView());
		return "redirect:/questions-view";
	}
}