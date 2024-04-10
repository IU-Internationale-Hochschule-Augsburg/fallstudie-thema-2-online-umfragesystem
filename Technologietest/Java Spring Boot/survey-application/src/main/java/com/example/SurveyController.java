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

	@GetMapping("/umfrage")
	public String getSurveyForm(Model model) {
		model.addAttribute("texteingabe", new TextInput());
		return "texteingabe";
	}

	@PostMapping("/umfrage")
	@Transactional
	public String postSurveyForm(@ModelAttribute TextInput te, Model model) {
		var umfrage = new Survey(te.getContent());
		surveyRepository.save(umfrage);
		model.addAttribute("texteingabe", te);
		return "result";
	}

}
