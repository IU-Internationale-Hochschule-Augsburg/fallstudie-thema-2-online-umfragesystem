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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SurveyController {
	private final SurveyRepository surveyRepository;

	@GetMapping("/umfrage")
	public String getSurveyForm(Model model) {
		InputForm textInputs = new InputForm();
		textInputs.getTextInputs().add(new TextInput("Test1"));
		textInputs.getTextInputs().add(new TextInput("Test2"));
		model.addAttribute("inputForm", textInputs);
		return "textinputs";
	}

	@PostMapping("/umfrage")
	@Transactional
	public String SurveyForm(@ModelAttribute("textinputs") InputForm inputForm, Model model) {

		for(int i = 0; i < inputForm.getTextInputs().size(); i++) {
			var umfrage = new Survey(inputForm.getTextInputs().get(i).getContent());
			surveyRepository.save(umfrage);
		}
		model.addAttribute("inputForm", inputForm);
		return "result";
	}

}
